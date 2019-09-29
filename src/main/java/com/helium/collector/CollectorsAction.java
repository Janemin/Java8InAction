package com.helium.collector;

import com.helium.stream.Dish;
import com.helium.stream.DishType;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsAction {

    public final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, DishType.MEAT),
            new Dish("beef", false, 700, DishType.MEAT),
            new Dish("chicken", false, 420, DishType.MEAT),
            new Dish("french fries", true, 530, DishType.OTHER),
            new Dish("rich", true, 350, DishType.OTHER),
            new Dish("season fiuit", true, 120, DishType.OTHER),
            new Dish("pizza", true, 550, DishType.OTHER),
            new Dish("prawns", false, 300, DishType.FISH),
            new Dish("salmon", false, 450, DishType.FISH)
    );

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    private static void testAveragingDouble() {
        System.out.println("CollectorsAction.testAveragingDouble");
        Double average = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(average);
    }

    private static void testAveragingInt() {
        System.out.println("CollectorsAction.testAveragingInt");
        Double average = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(average);
    }

    private static void testAveragingLong() {
        System.out.println("CollectorsAction.testAveragingLong");
        Double average = menu.stream().collect(Collectors.averagingLong(Dish::getCalories));
        System.out.println(average);
    }

    private static void testCollectingAndThen() {
        System.out.println("CollectorsAction.testCollectingAndThen");
        String msg = menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories),
                        average -> "The average calories is " + average));
        System.out.println(msg);

        // to unmodifiable list
        List<Dish> unmodifiableMenu = menu.stream().filter(dish -> DishType.MEAT.equals(dish.getType()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private static void testCounting() {
        System.out.println("CollectorsAction.testCounting");
//        long streamCount = menu.stream().count();
        Long count = menu.stream().collect(Collectors.counting());
        System.out.println(count);
    }

    private static void testGroupingByFunction() {
        System.out.println("CollectorsAction.testGroupingByFunction");
        Map<DishType, List<Dish>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(map);

    }

    private static void testGroupingByFunctionAndCollector() {
        System.out.println("CollectorsAction.testGroupingByFunctionAndCollector");
        Map<DishType, Long> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<DishType, Double> collect1 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
    }

    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.println("CollectorsAction.testGroupingByFunctionAndSupplierAndCollector");
        TreeMap<DishType, Double> map = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
    }
    
    private static void testSummarizingInt() {
        System.out.println("CollectorsAction.testSummarizingInt");
        IntSummaryStatistics statistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));

        statistics.getAverage();
        statistics.getCount();
        statistics.getMax();
        statistics.getMin();
        statistics.getSum();
    }
}
