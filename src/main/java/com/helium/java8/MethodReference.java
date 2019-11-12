package com.helium.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.helium.java8.FruitsColor.*;

/**
 * Created by JaneMin on 2019-09-26.
 */
public class MethodReference {

    private static <T> void userConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        userConsumer(consumer, "TEST");

        userConsumer(System.out::println, "TEST");

        List<Apple> apples = Arrays.asList(new Apple(GREEN, 140),
                new Apple(RED, 120),
                new Apple(YELLOW, 135),
                new Apple(GREEN, 170));

        apples.sort((a1, a2) -> - Long.compare(a1.getWeight(), a2.getWeight()));
        System.out.println(apples);

        apples.sort(Comparator.comparingLong(Apple::getWeight));
        System.out.println(apples);

        Function<String, Integer> parseInt = Integer::parseInt;
        parseInt.apply("1");

        BiFunction<String, Integer, Character> charAt = String::charAt;
        charAt.apply("Lambda", 1);

        String str = new String("Lambda");

        Function<Integer, Character> strCharAt = str::charAt;
        strCharAt.apply(1);


        Supplier<String> strMaker = String::new;
        strMaker.get();

        BiFunction<FruitsColor, Long, Apple> applePicker = Apple::new;
        applePicker.apply(FruitsColor.RED, 100L);
    }
}
