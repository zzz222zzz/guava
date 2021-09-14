package com.zm.guava.day003.Reduce;

import com.zm.guava.day003.Student;

import java.util.Comparator;
import java.util.Optional;

import static com.zm.guava.util.StudentUtil.student;
import static java.util.stream.Collectors.*;

public class SumAndAverageTest {
    public static void main(String[] args) {
        // 数值操作
        Optional.ofNullable(student().collect(counting())).ifPresent(System.out::println);
        Optional.of(student().collect(maxBy(Comparator.comparing(Student::getTotalScore)))).ifPresent(System.out::println);
        Optional.of(student().collect(minBy(Comparator.comparing(Student::getTotalScore)))).ifPresent(System.out::println);
        Optional.of(student().collect(summingDouble(Student::getTotalScore))).ifPresent(System.out::println);
        Optional.of(student().collect(averagingDouble(Student::getTotalScore))).ifPresent(System.out::println);

        System.out.println("summarizingDouble");
        Optional.of(student().collect(summarizingDouble(Student::getTotalScore))).ifPresent(System.out::println);

        // findFirst  返回Optional类型


    }
}
