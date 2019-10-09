package com.helium.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CustomFutureInAction {

    public static void main(String[] args) {
        Future<Long> future = invoke(() -> {
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Long.MIN_VALUE;
        });
        System.out.println("do something");

        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<T>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        thread.start();
        Future<T> future = new Future<T>(){

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}
