package com.helium.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(final String log) {
        System.out.println(Thread.currentThread() + " - " + System.currentTimeMillis() + " - " + log);
    }



    @Override
    public Supplier<List<T>> supplier() {
        log("supplier new ArrayList");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator add item");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        log("combiner merge result");
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        return t -> t;
//        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(
                EnumSet.of(Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }
}
