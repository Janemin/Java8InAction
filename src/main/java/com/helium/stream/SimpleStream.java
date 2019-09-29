package com.helium.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.spi.LocaleServiceProvider;
import java.util.stream.Collectors;

public class SimpleStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 400, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rich", true, 350, DishType.OTHER),
                new Dish("season fiuit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 300, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH)
        );


    }

    private static List<String> getDishNameWithLowCalories(List<Dish> menu) {
        if (menu == null || menu.isEmpty()) {
            return Collections.emptyList();
        }

        return menu.stream()                                        // to stream
                .filter(dish -> dish.getCalories() < 400)           // filter
                .sorted(Comparator.comparing(Dish::getCalories))    // sort
                .map(Dish::getName)                                 // map to unit-result
                .collect(Collectors.toList());                      // collect to result collection
    }
}
