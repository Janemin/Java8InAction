package com.helium.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {

        // System.out.println(calc());
        // RecursiveTask
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println(result);

        // RecursiveAction
        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        int actionResult = AccumulatorRecursiveAction.AccumulatorHelper.getResult();
        System.out.println(actionResult);

    }

    private static int calc() {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }
}
