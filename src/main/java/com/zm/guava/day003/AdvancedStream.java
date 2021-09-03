package com.zm.guava.day003;

import java.util.*;
import java.util.function.Supplier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AdvancedStream {

    public static void main(String[] args) {
        Data data = new Data();

        // 1. collect 收集器

//        ArrayList<Integer> collect = data.get().limit(10).collect(Collectors.toCollection(ArrayList::new));
//        System.out.println(data.get().limit(10).collect(Collectors.averagingInt(n -> n + 2)));


        List<Dish> dishes = Arrays.asList(new Dish("FISH", 300, "salmon"),
                new Dish("MEAT", 800, "pork"),
                new Dish("MEAT", 500, "beef"),
                new Dish("MEAT", 300, "chicken"),
                new Dish("OTHERS", 200, "rice"),
                new Dish("OTHERS", 400, "pizza"));

        // 以类型分组  Collectors.groupingBY(Function)
        Map<String, List<Dish>> listMap = dishes.stream().collect(groupingBy(Dish::getType));

        dishes.parallelStream().collect(groupingBy(Dish::getType));

        // 多级分组
        Map<String, Map<String, List<Dish>>> map = dishes.stream()
                .collect(groupingBy(Dish::getType,groupingBy(AdvancedStream::getCaloriesLevel)));
        map.keySet().forEach(k->System.out.println(k+"="+map.get(k)));

        Optional.ofNullable(dishes.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.averagingInt(Dish::getCalories), a -> "The Average totalScore is->" + a)
        )).ifPresent(System.out::println);

    }

    static class Data implements Supplier<Stream<Integer>>{

        @Override
        public Stream<Integer> get() {
            return Stream.iterate(0, n -> n + 2);
        }
    }


    private static Stream<Integer> createIntStream(){
        return Stream.iterate(0,n->n+2);
    }

    private static String getCaloriesLevel(Dish d){
        if (d.getCalories() <= 300) {
            return "LOW";
        } else if (d.getCalories() < 500 && d.getCalories() > 300) {
            return "MIDDLE";
        } else {
            return "HIGH";
        }
    }

}
