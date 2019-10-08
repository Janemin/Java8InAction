package com.helium.forkjoin;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorInAction {

    public static final String text = " * An object for traversing and partitioning elements of a source.  The source\n" +
            " * of elements covered by a Spliterator could be, for example, an array, a\n" +
            " * {@link Collection}, an IO channel, or a generator function.\n" +
            " *\n" +
            " * <p>A Spliterator may traverse elements individually ({@link\n" +
            " * #tryAdvance tryAdvance()}) or sequentially in bulk\n" +
            " * ({@link #forEachRemaining forEachRemaining()}).\n" +
            " *\n" +
            " * <p>A Spliterator may also partition off some of its elements (using\n" +
            " * {@link #trySplit}) as another Spliterator, to be used in\n" +
            " * possibly-parallel operations.  Operations using a Spliterator that\n" +
            " * cannot split, or does so in a highly imbalanced or inefficient\n" +
            " * manner, are unlikely to benefit from parallelism.  Traversal\n" +
            " * and splitting exhaust elements; each Spliterator is useful for only a single\n" +
            " * bulk computation.";

    public static void main(String[] args) {
//        IntStream intStream = IntStream.rangeClosed(0, 10);
//        Spliterator.OfInt spliterator = intStream.spliterator();
//        Consumer<Integer> consumer = System.out::println;
//        spliterator.forEachRemaining(consumer);

        MySpliteratorText spliteratorText = new MySpliteratorText(text);
        System.out.println(spliteratorText.stream().count());

        spliteratorText.stream().forEach(System.out::println);
    }

    static class MySpliteratorText {
        private final String[] data;

        public MySpliteratorText(String text) {
            Objects.requireNonNull(text, "The parameter can not be null");
            this.data = text.split("\n");
        }

        public Stream<String> stream() {
            return StreamSupport.stream(new MySpliterator(), false);
        }

        public Stream<String> parallelStream() {
            return StreamSupport.stream(new MySpliterator(), true);
        }

        private class MySpliterator implements Spliterator<String> {

            private int start, end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if(start <= end) {
                    action.accept(MySpliteratorText.this.data[start++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if(mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;

                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE | SIZED | SUBSIZED;
            }
        }
    }
}
