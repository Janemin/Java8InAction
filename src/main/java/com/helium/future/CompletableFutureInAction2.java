package com.helium.future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JaneMin on 2019-10-16.
 */
public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

//        AtomicBoolean finished = new AtomicBoolean(false);
//
//        CompletableFuture.supplyAsync(CompletableFutureInAction1::get)
//                .whenComplete((v, t) -> {
//                    Optional.ofNullable(v).ifPresent(System.out::println);
//                    Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
//                    finished.set(true);
//                });                     // 守护线程
//
////        Thread.currentThread().join();
//
//        while (!finished.get()) {
//            Thread.sleep(1);
//        }

//        ExecutorService executor = Executors.newFixedThreadPool(2); // 非守护线程

        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });        // 通过线程工厂生成daemon线程
//        executor.execute(() -> System.out.println("test ..."));
//        executor.shutdown();

        // 使用线程池中的线程
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                });

    }
}
