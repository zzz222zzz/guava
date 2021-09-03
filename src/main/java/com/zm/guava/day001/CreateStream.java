package com.zm.guava.day001;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) {
        //1  CreateStreamFromCollection
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
        //2  CreateStreamFromValues
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        //3  CreateStreamFromArrays
        Integer[] i = new Integer[]{1,2,3};
        Stream<Integer> stream1 = Arrays.stream(new Integer[]{1,2,3});
        //4 CreateStreamFromFiles
        Path path = Paths.get("E:\\project\\guava\\src\\main\\resources\\streamfile");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        }catch (IOException e){
            throw new RuntimeException();
        }
        //5 CreateStreamFromIterator
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 1).limit(10);
        iterate.forEach(System.out::println);
        //6 CreateStreamFromGenerate
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        //7 CreateStreamFrom
        SplittableRandom splittableRandom = new SplittableRandom();
        IntStream intStream  = splittableRandom.ints(10);
        System.out.println("intstream");
        intStream.forEach(System.out::println);
        char a = 'A';
        System.out.println(a+1);
    }
}
