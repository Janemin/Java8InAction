package com.helium.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by JaneMin on 2019-11-12.
 */
public class CompletableFutureInAction5 {

    public static void main(String[] args) throws InterruptedException {
/*        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 11111");
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 22222");
            return 2;
        }), () -> System.out.println("Done")).join();*/

/*        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 11111");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 22222");
            return CompletableFutureInAction1.get();
        }), v -> 10 * v)
                .thenAccept(System.out::println);*/

/*        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 11111");
            return CompletableFutureInAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 22222");
            return CompletableFutureInAction1.get();
        }), (v) -> System.out.println(10 * v));*/

/*        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 11111");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 22222");
            return CompletableFutureInAction1.get();
        }), () -> System.out.println("any task finished")).join();*/

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4).stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(Collectors.toList());

/*        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done")).join();*/

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done")).join();


    }
}
