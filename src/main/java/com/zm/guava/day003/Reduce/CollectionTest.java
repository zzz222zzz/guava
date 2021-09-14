package com.zm.guava.day003.Reduce;

import com.zm.guava.day003.Student;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

import static com.zm.guava.util.StudentUtil.*;
import static java.util.stream.Collectors.*;

public class CollectionTest {

    public static void main(String[] args) {
        /**
         *     toSet toList toCollection
         */
        Optional.of(student().collect(toList())).ifPresent(System.out::println);
        Optional.of(student().collect(toSet())).ifPresent(System.out::println);
        Optional.of(student().collect(Collectors.toCollection(ArrayList::new))).ifPresent(System.out::println);

        /**
         * toMap
         */
//        Optional.of(
//                student().collect(toMap(Student::getName,Student::getTotalScore))
//        ).ifPresent(System.out::println);
        Optional.of(studentWithRepeat().collect(toMap(Student::getName,Student::getTotalScore,(k1,k2)->k1))).ifPresent(System.out::println);
        System.out.println("//以年级为键学生人数为值统计信息");

        Optional.of(student().collect(toMap(student -> student.getGradeType(), v->1L, Long::sum))).ifPresent(System.out::println);
        System.out.println("// treeMap 默认排L");
        Optional.of(student().collect(
                toMap(Student::getName,
                        Student::getTotalScore,
                        (k1,k2)->k1,
                        TreeMap::new))
        ).ifPresent(System.out::println);
        System.out.println("// 按照拼音排序");
        Optional.of(student().collect(
                toMap(Student::getName,
                        Student::getTotalScore,
                        (k1,k2)->k1
                , ()->new TreeMap<>(Collator.getInstance(Locale.CHINESE)::compare)))
        ).ifPresent(System.out::println);
        System.out.println("// 插入顺序排序");
        Optional.of(student().collect(
                toMap(Student::getName,
                        Student::getTotalScore,
                        (k1, k2)->k1,LinkedHashMap::new))
        ).ifPresent(System.out::println);


    }

}
