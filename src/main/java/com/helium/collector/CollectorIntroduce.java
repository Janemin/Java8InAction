package com.helium.collector;

import com.helium.stream.Dish;
import com.helium.stream.DishType;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 410, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rich", true, 350, DishType.OTHER),
                new Dish("season fiuit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 300, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH)
        );

        System.out.println(groupByDishType(menu));
    }

    private static Map<DishType, List<Dish>> groupByDishType(List<Dish> menu) {
        return Optional.ofNullable(menu).orElseGet(ArrayList::new)
                .stream()
                .collect(Collectors.groupingBy(Dish::getType));
    }
}
