package com.zm.guava.day003.Test;

import com.zm.guava.day003.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

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
         * }
         *
         */




        List<Integer> dishes = Stream.of(1,2).collect(
                ArrayList::new,
                ArrayList::add,
                List::addAll);



    }

}
