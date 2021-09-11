package com.zm.guava.day003.Resolve;

import com.zm.guava.day003.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zm.guava.util.StudentUtil.student;
import static com.zm.guava.util.StudentUtil.studentLastMonth;
import static java.util.stream.Collectors.*;

public class MapTest {

    public static void main(String[] args) {
        //输出学生名单
        Optional.of(student().map(Student::getName).collect(toList())).ifPresent(System.out::println);

        // flatMap
        List<String> words = Arrays.asList("hello", "world");
        List<String> collect = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(Arrays.toString(collect.toArray()));

    }
}
