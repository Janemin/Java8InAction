package com.helium.collector;

import com.helium.stream.Dish;
import com.helium.stream.DishType;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.helium.collector.CollectorsAction.menu;

public class CollectorsAction2 {

    public static void main(String[] args) {

        testGroupByConcurrentWithFunction();
        testGroupByConcurrentWithFunctionAndCollector();
        testGroupByConcurrentWithFunctionAndSupplierAndCollector();

        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();

        testMapping();

        testMaxBy();
        testMinBy();

    }

    private static void testGroupByConcurrentWithFunction() {
        System.out.println("CollectorsAction2.testGroupByConcurrentWithFunction");
        ConcurrentMap<DishType, List<Dish>> map = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType));
        System.out.println(map);
    }

    private static void testGroupByConcurrentWithFunctionAndCollector() {
        System.out.println("CollectorsAction2.testGroupByConcurrentWithFunctionAndCollector");
        ConcurrentMap<DishType, Double> map = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        System.out.println(map);
    }

    private static void testGroupByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.println("CollectorsAction2.testGroupByConcurrentWithFunctionAndCollector");
        ConcurrentSkipListMap<DishType, Double> map = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        System.out.println(map);
    }

    private static void testJoining() {
        System.out.println("CollectorsAction2.testJoining");
        String names = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(names);
    }

    private static void testJoiningWithDelimiter() {
        System.out.println("CollectorsAction2.testJoiningWithDelimiter");
        String names = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(names);
    }

    private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.println("CollectorsAction2.testJoiningWithDelimiterAndPrefixAndSuffix");
        String names = menu.stream().map(Dish::getName).collect(Collectors.joining(", ", "Menu names: ", "."));
        System.out.println(names);
    }

    private static void testMapping() {
        System.out.println("CollectorsAction2.testMapping");
        String names = menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(", ")));
        System.out.println(names);
    }

    private static void testMaxBy() {
        System.out.println("CollectorsAction2.testMaxBy");
        menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testMinBy() {
        System.out.println("CollectorsAction2.testMaxBy");
        menu.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

}
