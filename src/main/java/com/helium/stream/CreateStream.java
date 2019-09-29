package com.helium.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {


    /**
     * stream from collection
     * @return
     */
    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("Java 8", "Lambda", "stream");
        return list.stream();
    }

    /**
     * Stream of
     * @return
     */
    private static Stream<String> createStreamFromValues() {
        return Stream.of("Java 8", "Lambda", "stream");
    }

    /**
     * Arrays stream
     * @return
     */
    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"Java 8", "Lambda", "stream"});
    }

    /**
     * Files lines (nio)
     */
    private static void createStreamFromFile() {
        Path path = Paths.get("D:\\IdeaProjects\\test\\src\\stream\\CreateStream.java");
        try(Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Stream iterate  (infinite stream without limit)
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, n ->  n + 2).limit(10);
    }


    /**
     * Stream generate (infinite stream without limit)
     * @return
     */
    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }



    static class ObjSupplier implements Supplier<Obj>{

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index++;
            return new Obj(index, "Name->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    public static void main(String[] args) {
//        createStreamFromCollection().forEach(System.out::println);
        createObjStreamFromGenerate().forEach(System.out::println);
    }
}
