package com.helium.collector;

import com.helium.stream.Dish;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.helium.collector.CollectorsAction.menu;

public class CollectorsAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();

        testPartitioningByWithPredicateAndCollector();

        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
    }

    private static void testPartitioningByWithPredicate() {
        System.out.println("CollectorsAction3.testPartitioningByWithPredicate");
        Map<Boolean, List<Dish>> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(map);
    }

    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.println("CollectorsAction3.testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.averagingInt(Dish::getCalories)));
        System.out.println(map);
    }

    private static void testReducingBinaryOperator() {
        System.out.println("CollectorsAction3.testReducingBinaryOperator");
        Optional<Dish> maxCalories = menu.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    private static void testReducingBinaryOperatorAndIdentity() {
        System.out.println("CollectorsAction3.testReducingBinaryOperatorAndIdentity");
        Integer total = menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, Integer::sum));
    }

    private static void testReducingBinaryOperatorAndIdentityAndFunction() {
        System.out.println("CollectorsAction3.testReducingBinaryOperatorAndIdentityAndFunction");
        Integer total = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }

    private static void testSummarizingDouble() {
        DoubleSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
        statistics.getAverage();
    }

    private static void testSummarizingInt() {
        IntSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        statistics.getAverage();
    }

    private static void testSummarizingLong() {
        LongSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingLong(Dish::getCalories));
        statistics.getAverage();
    }


}
