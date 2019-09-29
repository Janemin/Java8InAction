package com.helium.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6);

        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());

        System.out.println(result);


        listDish().stream().map(Dish::getName).forEach(System.out::println);

        // flatMap
        String[] words = {"Java 8", "Lambda", "stream"};
        Arrays.stream(words).map(s -> s.split("")) // Stream<String[]>
                .flatMap(Arrays::stream) // Stream<String>
                .distinct()
                .collect(toList());
    }

    private static List<Dish> listDish() {
        return Arrays.asList(
                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 400, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rich", true, 360, DishType.OTHER),
                new Dish("season fiuit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 300, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH)
        );
    }
}
