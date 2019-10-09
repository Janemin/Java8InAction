package com.helium.future;

import java.util.concurrent.*;

public class FutureInAction {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> task = executorService.submit(() -> {
            try {
                Thread.sleep(10_000L);
                return "Success";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Failed";
        });

//        task.get();
        task.get(10, TimeUnit.MICROSECONDS);
        executorService.shutdown();
    }


}
