package com.zm.guava.day003.Reduce;

import com.zm.guava.day003.Student;

import java.util.Optional;


import static com.zm.guava.util.StudentUtil.student;
import static java.util.stream.Collectors.*;

public class JoinTest {

    public static void main(String[] args) {
        //
        Optional.of(student().map(Student::getName).collect(joining())).ifPresent(System.out::println);
        Optional.of(student().map(Student::getName).collect(joining(","))).ifPresent(System.out::println);
        Optional.of(student().map(Student::getName).collect(joining(",","Names[","]"))).ifPresent(System.out::println);

    }

}
