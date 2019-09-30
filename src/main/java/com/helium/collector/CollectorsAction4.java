package com.helium.collector;

import com.helium.stream.Dish;
import com.helium.stream.DishType;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.helium.collector.CollectorsAction.menu;

public class CollectorsAction4 {

    public static void main(String[] args) {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();

    }

    private static void testSummingDouble() {
        Double total = menu.stream()
                .collect(Collectors.summingDouble(Dish::getCalories));

        menu.stream().mapToInt(Dish::getCalories).sum();
    }

    private static void testSummingLong() {
        Long total = menu.stream()
                .collect(Collectors.summingLong(Dish::getCalories));
    }


    private static void testSummingInt() {
        Integer total = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
    }

    private static void testToCollection() {
        LinkedList<Dish> collect = menu.stream()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static void testToConcurrentMap() {
        ConcurrentMap<String, Integer> map = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
    }

    private static void testToConcurrentMapWithBinaryOperator() {
        ConcurrentMap<DishType, Long> map = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, a -> 1L, (a, b) -> a + b));
    }

    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        ConcurrentSkipListMap<DishType, Long> map = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, a -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new));
    }


    private static void testToList() {
        List<Dish> collect = menu.stream().collect(Collectors.toList());
    }

    private static void testToSet() {
        Set<Dish> collect = menu.stream().collect(Collectors.toSet());
    }

    private static void testToMap() {
        Map<String, Integer> map = menu.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getCalories));

        Map<String, Integer> syncMap = menu.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Dish::getName, Dish::getCalories),
                        Collections::synchronizedMap));
    }

    private static void testToMapWithBinaryOperator() {
        Map<DishType, Long> map = menu.stream()
                .collect(Collectors.toMap(Dish::getType, a -> 1L, (a, b) -> a + b));
    }

    private static void testToMapWithBinaryOperatorAndSupplier() {
        Hashtable<DishType, Long> map = menu.stream()
                .collect(Collectors.toMap(Dish::getType, a -> 1L, (a, b) -> a + b, Hashtable::new));
    }


}
