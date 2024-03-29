package com.helium.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CustomCompletableFutureInAction {

    public static void main(String[] args) {

        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10_000L);
                return "Success";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Failed";
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println(cause.toString());
            }
        });

    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<T>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<T>(){

            private Completable<T> completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };
        Thread thread = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);

                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Throwable cause) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(cause);
                }
            }
        });
        thread.start();


        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable cause);
    }

    private interface Callable<T> {
        T action();
    }
}
