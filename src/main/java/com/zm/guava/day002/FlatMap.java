package com.zm.guava.day002;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FlatMap {


    public static void main(String[] args) {
        //Java给定两个数字列表，返回所有的数对  [1, 3][1, 4][2, 3][2, 4][3, 3][3, 4]
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4);
        Stream.of(1,2,3);
        Stream<Integer> stream = StreamSupport.stream(Spliterators.spliterator(Arrays.asList(1, 2, 3), 3), false);
        stream.forEach(System.out::println);

        List<Stream<int[]>> collect = integers1.stream().map(i -> integers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());

//        collect.forEach(i->i.forEach(j-> System.out.print(Arrays.toString(j))));

        //列出各不相同的字母
        List<String> google = Arrays.asList("hello","word");
        //[h, e, l, l, o][w, o, r, d]
        List<String[]> collect1 = google.stream().map(s -> s.split("")).distinct().collect(Collectors.toList());
//        collect1.forEach(i-> System.out.print(Arrays.toString(i)));
        //helloword  Arrays::stream  把数组变成一个单独的流
        List<Stream<String>> collect2 = google.stream().map(s -> s.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());
//        collect2.forEach(i-> i.forEach(System.out::print));

        google.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::print);
    }
}
