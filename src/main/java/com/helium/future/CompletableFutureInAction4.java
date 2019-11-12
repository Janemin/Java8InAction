package com.helium.future;

import java.util.concurrent.CompletableFuture;

/**
 * Created by JaneMin on 2019-11-12.
 */
public class CompletableFutureInAction4 {
    public static void main(String[] args) throws InterruptedException {
/*        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));*/

        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(10, v))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(System.out::println);   // 结果不会传递给 thenRun()
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 10.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                });

        Thread.sleep(100L);
    }
}
