package com.zm.guava.day003.Resolve;


import com.zm.guava.day003.Student;
import com.zm.guava.util.StudentUtil;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

import static com.zm.guava.util.StudentUtil.student;

public class MiddleTest {
    public static void main(String[] args) {

        // sorted排序
        Optional.of(student()
                .sorted(Comparator
                        .comparing(Student::getTotalScore)
                        .thenComparing(Student::getName))
                .collect(Collectors.toList())).ifPresent(System.out::println);
        Optional.of(student()
                .sorted(Comparator
                        .comparing(Student::getTotalScore)
                        .thenComparing(Collator.getInstance(Locale.CHINESE)::compare))
                .collect(Collectors.toList())).ifPresent(System.out::println);


        //  reduce
        student().map(s->1L).reduce((a,b)->a+b);
        student().count();






        // 7 8 方法引用
        Optional.of(student().map(o->null)).ifPresent(System.out::println);
        processStringList(Collections.emptyList());

    }

    static void processStringList(List<String> stringList) {
        // process stringList
    }



}
