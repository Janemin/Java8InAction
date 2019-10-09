package com.helium.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction1 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            completableFuture.complete(get());
        }).start();
        System.out.println("do something");

//        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
        });
    }

    private static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }

}
