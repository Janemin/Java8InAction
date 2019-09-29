package com.helium.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6);

        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());

        System.out.println(result);

        result = list.stream().distinct().collect(Collectors.toList());

        System.out.println(result);

        result = list.stream().skip(5).collect(Collectors.toList());

        System.out.println(result);

        result = list.stream().limit(5).collect(Collectors.toList());

        System.out.println(result);
    }
}
