package com.helium.stream;

import java.util.Arrays;
import java.util.List;

public class StreamMatch {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6);

        // allMatch
        boolean matched = list.stream().allMatch(i -> i > 3);

        // anyMatch
        boolean matched2 = list.stream().anyMatch(i -> i > 3);

        // noneMatch
        boolean matched3 = list.stream().noneMatch(i -> i > 3);
    }
}
