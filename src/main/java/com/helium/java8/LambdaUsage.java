package com.helium.java8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

import static com.helium.java8.FruitsColor.*;

/**
 * Created by JaneMin on 2019-09-26.
 */
public class LambdaUsage {

    /**
     * Predicate demo
     * @param sourceList
     * @param predicate
     * @return
     */
    private static List<Apple> filter(List<Apple> sourceList, Predicate<Apple> predicate) {
        if(sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream().filter(predicate).collect(Collectors.toList());
    }


    /**
     * BiPredicate demo
     * @param sourceList
     * @param predicate
     * @return
     */
    private static List<Apple> filterByBiCondition(List<Apple> sourceList, BiPredicate<Long, FruitsColor> predicate) {
        if(sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream().filter(apple -> predicate.test(apple.getWeight(), apple.getColor())).collect(Collectors.toList());
    }

    /**
     * Consumer demo
     * @param sourceList
     * @param consumer
     */
    private static void eatApple(List<Apple> sourceList, Consumer<Apple> consumer) {
        if(sourceList == null || sourceList.isEmpty()) {
            return;
        }
        sourceList.forEach(consumer);
    }

    /**
     * BiConsumer demo
     * @param sourceList
     * @param consumer
     */
    private static void eatApple(List<Apple> sourceList, BiConsumer<Long, FruitsColor> consumer) {
        if(sourceList == null || sourceList.isEmpty()) {
            return;
        }
        sourceList.forEach(apple -> consumer.accept(apple.getWeight(), apple.getColor()));
    }

    /**
     * Function demo
     * @param apple
     * @param saleStrategy
     * @return
     */
    private static BigDecimal saleApple(Apple apple, Function<Apple, BigDecimal> saleStrategy) {
        if (apple == null) {
            return BigDecimal.ZERO;
        }
        return saleStrategy.apply(apple);
    }

    /**
     * BiFunction
     * @param apple
     * @param unitPrice
     * @param saleStrategy
     * @return
     */
    private static BigDecimal saleApple(Apple apple, BigDecimal unitPrice, BiFunction<Apple, BigDecimal, BigDecimal> saleStrategy) {
        if (apple == null) {
            return BigDecimal.ZERO;
        }
        return saleStrategy.apply(apple, unitPrice);
    }

    /**
     * Supplier
     * @param supplier
     * @return
     */
    private static Apple pickApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(GREEN, 140),
                new Apple(RED, 120),
                new Apple(YELLOW, 135),
                new Apple(GREEN, 170));

        filter(apples, apple -> RED.equals(apple.getColor()));

        filterByBiCondition(apples, (weight, color) -> RED.equals(color) && weight > 100);

        eatApple(apples, System.out::println);

        eatApple(apples, (weight, color) -> System.out.println("Eating the apple which the color is " + color
                + " and weight is " + weight));

        Optional<BigDecimal> money = apples.stream().map(apple -> saleApple(apple, (appleParam) ->
                new BigDecimal(appleParam.getWeight()).multiply(new BigDecimal("0.3")))).reduce(BigDecimal::add);
        System.out.println(money.orElse(BigDecimal.ZERO));


        final BigDecimal unitPrice = new BigDecimal("0.3");
        apples.stream().map(apple -> saleApple(apple, unitPrice, (appleParam, unitPriceParam) ->
                new BigDecimal(appleParam.getWeight()).multiply(unitPriceParam))).reduce(BigDecimal::add);

        apples.add(pickApple(() -> new Apple(RED, 1000)));


    }
}
