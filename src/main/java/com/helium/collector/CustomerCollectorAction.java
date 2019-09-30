package com.helium.collector;


import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CustomerCollectorAction {

    public static void main(String[] args) {

        List<Double> result = Stream.generate(Random::new)
                .skip(3)
                .limit(1000)
                .map(Random::nextDouble)
                .collect(new ToListCollector<>());
    }
}
