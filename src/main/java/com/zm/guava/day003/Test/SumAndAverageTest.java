package com.zm.guava.day003.Test;

import com.zm.guava.day003.Student;

import java.util.Comparator;
import java.util.Optional;

import static com.zm.guava.util.StudentUtil.student;
import static java.util.stream.Collectors.*;

public class SumAndAverageTest {
    public static void main(String[] args) {
        // 数值操作
        Optional.ofNullable(student().collect(counting())).ifPresent(System.out::println);
        Optional.ofNullable(student().collect(maxBy(Comparator.comparing(Student::getTotalScore)))).ifPresent(System.out::println);
        Optional.ofNullable(student().collect(minBy(Comparator.comparing(Student::getTotalScore)))).ifPresent(System.out::println);
        Optional.ofNullable(student().collect(summingDouble(Student::getTotalScore))).ifPresent(System.out::println);
        Optional.ofNullable(student().collect(averagingDouble(Student::getTotalScore))).ifPresent(System.out::println);
        // 综述
        Optional.ofNullable(student().collect(summarizingDouble(Student::getTotalScore))).ifPresent(System.out::println);
    }
}
