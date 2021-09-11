package com.zm.guava.day003.Reduce;


import com.zm.guava.day003.Student;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.zm.guava.util.StudentUtil.student;
import static java.util.stream.Collectors.*;


public class GroupingByTest {

    public static void main(String[] args) {

        /**
         * groupingBy
         */
        // 统计各个年级的学生信息。
        Optional.ofNullable(student().collect(groupingBy(Student::getGradeType))).ifPresent(m->{ m.keySet().forEach(System.out::println); });
        // 统计各个年级的学生人数。
        Optional.ofNullable(student().collect(
                groupingBy(Student::getGradeType,
                        counting()))).ifPresent(System.out::println);
        // 二级分组
        Optional.ofNullable(student().collect(
                groupingBy(Student::getGradeType, groupingBy(s->{
            if(s.getTotalScore()<400) {return "LOW";}
            else if(s.getTotalScore()>=400 && s.getTotalScore()<=500) {return "MID";}
            else {return "HIGH";}
        })))).ifPresent(System.out::println);
        // 统计各个年级的人数，并有序输出。
        Optional.ofNullable(student().collect(
                groupingBy(Student::getGradeType, TreeMap::new, counting()))).ifPresent(System.out::println);
        // 自定义排序的TreeMap
        Optional.ofNullable(student().collect(
                    groupingBy(Student::getGradeType,
                               () -> new TreeMap<>((o1, o2) -> -o1.compareTo(o2)),
                               counting()))
        ).ifPresent(System.out::println);

        /**
         *         collectingAndThen 每个年级的最高分
          */
        Optional.ofNullable(student().collect(
                groupingBy(Student::getGradeType, maxBy(Comparator.comparing(Student::getTotalScore))))
        ).ifPresent(System.out::println);
        Optional.ofNullable(student().collect(
                groupingBy(Student::getGradeType,
                collectingAndThen(maxBy(Comparator.comparing(Student::getTotalScore)),
                        o->o.get().getName())))).ifPresent(System.out::print);


        /**
         * groupingByConcurrent  带并发的收集器
         */
        groupingByConcurrent();


        /**
         *  partitioningBy
         */

//        Optional.ofNullable(student().collect(partitioningBy(Student::isLocal))).ifPresent(System.out::println);
//        Optional.ofNullable(student().collect(partitioningBy(Student::isLocal,averagingInt(Student::getTotalScore)))).ifPresent(System.out::println);

    }

    private static void groupingByConcurrent() {
        System.out.println("-------------------------");
        //groupingByConcurrent
        ArrayList<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 2000000; i++) {
            Student tmp = new Student();
            tmp.setTotalScore(random.nextInt(20) + 10);
            students.add(tmp);
        }

        long l = System.currentTimeMillis();
        System.out.println(students.stream().count());
        Map<Integer, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getTotalScore));
        System.out.println(map.keySet().size());
        System.out.println(System.currentTimeMillis() - l);

        l = System.currentTimeMillis();
        ConcurrentMap<Integer, List<Student>> map2 = students.parallelStream().collect(Collectors.groupingByConcurrent(Student::getTotalScore));
        System.out.println(System.currentTimeMillis() - l);

        boolean result = mapCompar(students, map, map2);
        System.out.println(result);
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
