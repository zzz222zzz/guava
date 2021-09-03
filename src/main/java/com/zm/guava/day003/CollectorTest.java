package com.zm.guava.day003;


import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class CollectorTest {

    public static void main(String[] args) {

        //groupingBy
//        Optional.ofNullable(student().collect(groupingBy(Student::getGradeType))).ifPresent(System.out::println);
//        Optional.ofNullable(student().collect(groupingBy(Student::getGradeType, counting()))).ifPresent(System.out::println);
//        Optional.ofNullable(student().collect(groupingBy(Student::getGradeType, TreeMap::new, counting()))).ifPresent(System.out::println);
//        Optional.ofNullable(student().collect(groupingBy(Student::getGradeType, () -> new TreeMap<>((o1, o2) -> -o1.compareTo(o2)), counting()))).ifPresent(System.out::println);


        System.out.println("-------------------------");
        //groupingByConcurrent
        ArrayList<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 200000000; i++) {
            Student tmp = new Student();
            tmp.setTotalScore(random.nextInt(20) + 10);
            students.add(tmp);
        }

        long l = System.currentTimeMillis();
        System.out.println(students.stream().count());
        Map<Integer, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getTotalScore));
        System.out.println(System.currentTimeMillis() - l);

        l = System.currentTimeMillis();
        ConcurrentMap<Integer, List<Student>> map2 = students.parallelStream().collect(Collectors.groupingByConcurrent(Student::getTotalScore));
        System.out.println(System.currentTimeMillis() - l);

        boolean result = mapCompar(students, map, map2);
        System.out.println(result);

    }

    private static Stream<Student> student() {
        return Stream.of(
                new Student("刘一", 721, true, Student.GradeType.THREE),
                new Student("陈二", 637, true, Student.GradeType.THREE),
                new Student("张三", 666, true, Student.GradeType.THREE),
                new Student("李四", 531, true, Student.GradeType.ONE),
                new Student("王五", 483, false, Student.GradeType.THREE),
                new Student("赵六", 367, true, Student.GradeType.THREE),
                new Student("孙七", 499, false, Student.GradeType.TWO));
    }

    public static class TreeMapWithComparator implements Supplier<TreeMap> {

        @Override
        public TreeMap get() {
            return new TreeMap<>((o1, o2) -> {
                Student.GradeType i1 = (Student.GradeType) o1;
                Student.GradeType i2 = (Student.GradeType) o2;
                return -i1.compareTo(i2);
            });
        }
    }


    private static boolean mapCompar(List<Student> studentDTOS, Map<Integer, List<Student>> hashMap, Map<Integer, List<Student>> hashMap2) {

        boolean isChange = false;
        for (Map.Entry<Integer, List<Student>> entry1 : hashMap.entrySet()) {
            List<Student> m1value = entry1.getValue();
            if (hashMap2.get(entry1.getKey()) == null) {
                break;
            }
            List<Student> m2value = hashMap2.get(entry1.getKey());

            if (!CollectionUtils.isEqualCollection(m1value, m2value)) {
                List<Student> stus = studentDTOS.stream().filter(stu -> Objects.equals(entry1.getKey(), stu.getTotalScore())).collect(toList());

                System.out.println("key:" + entry1.getKey());
                System.out.println("1:" + CollectionUtils.isEqualCollection(stus, m1value));
                System.out.println("2:" + CollectionUtils.isEqualCollection(stus, m2value));
                System.out.println();
                System.out.println("样板数据：");
                stus.forEach(s -> System.out.print(s.getName() + ","));
                System.out.println();
                System.out.println("m1数据：");
                m1value.forEach(m1 -> System.out.print(m1.getName() + ","));
                System.out.println();
                System.out.println("m2数据：");
                m2value.forEach(m2 -> System.out.print(m2.getName() + ","));
                break;
            }
            isChange = true;
        }
        return isChange;
    }
}
