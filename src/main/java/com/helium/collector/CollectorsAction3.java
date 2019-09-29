package com.helium.collector;

import com.helium.stream.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.helium.collector.CollectorsAction.menu;

public class CollectorsAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();

        testPartitioningByWithPredicateAndCollector();
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


}
