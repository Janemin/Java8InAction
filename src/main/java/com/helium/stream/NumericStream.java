package com.helium.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStream {

    public static void main(String[] args) {
        // Integer stream
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer total = list.stream().reduce(0, (i, j) -> i + j);

        list.stream().reduce(0, Integer::sum);

        // int stream
        list.stream().mapToInt(Integer::intValue).sum();

        // boxed -> int stream transfer to Integer stream
        Stream<Integer> stream = IntStream.range(0, 10).boxed();
    }
}
