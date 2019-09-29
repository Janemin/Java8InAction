package com.helium.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // init value
        Integer total = list.stream().reduce(0, (i, j) -> i + j);

        Optional<Integer> totalOpt = list.stream().reduce((i, j) -> i + j);

        Optional<Integer> max = list.stream().reduce(Integer::max);

    }

}
