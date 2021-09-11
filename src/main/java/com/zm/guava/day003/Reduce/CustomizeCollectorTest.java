package com.zm.guava.day003.Reduce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomizeCollectorTest {

    public static void main(String[] args) {
        /**
         *
         * public interface Collector<T, A, R> {
         *  Supplier<A> supplier();
         *  BiConsumer<A, T> accumulator();
         *  Function<A, R> finisher();
         *  BinaryOperator<A> combiner();
         *  Set<Characteristics> characteristics();
         * }r
         *
         */




        List<Integer> dishes = Stream.of(1,2).collect(
                ArrayList::new,
                ArrayList::add,
                List::addAll);



    }

}
