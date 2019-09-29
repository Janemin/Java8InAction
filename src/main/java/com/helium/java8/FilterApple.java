package com.helium.java8;

import java.util.*;

import static com.helium.java8.FruitsColor.*;

/**
 * Created by JaneMin on 2019-09-23.
 */
public class FilterApple {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(GREEN, 140),
                new Apple(RED, 120),
                new Apple(YELLOW, 135),
                new Apple(GREEN, 170));

        findApples(apples, GREEN);

        findAppleByFilter(apples, new GreenAndWeight150Filter());

        findAppleByFilter(apples, apple -> Objects.nonNull(apple) && YELLOW.equals(apple.getColor()) && 150 > apple.getWeight());
    }


    /**
     * demo1
     * @param apples
     * @return
     */
    public static List<Apple> findGreenApple(List<Apple> apples) {
        if (apples == null || apples.isEmpty()) {
            return Collections.emptyList();
        }

        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * demo2
     * @param apples
     * @param color
     * @return
     */
    public static List<Apple> findApples(List<Apple> apples, FruitsColor color) {
        if (apples == null || apples.isEmpty()) {
            return Collections.emptyList();
        }

        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * demo3
     */
    @FunctionalInterface
    public interface AppleFilter{

        boolean filter(Apple apple);

    }

    public static List<Apple> findAppleByFilter(List<Apple> apples, AppleFilter appleFilter) {
        if (apples == null || apples.isEmpty()) {
            return Collections.emptyList();
        }

        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class GreenAndWeight150Filter implements AppleFilter{

        @Override
        public boolean filter(Apple apple) {
            return Objects.nonNull(apple)
                    && GREEN.equals(apple.getColor())
                    && 150 <= apple.getWeight();
        }
    }
}
