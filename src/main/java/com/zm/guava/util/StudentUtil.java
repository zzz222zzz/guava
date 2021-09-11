package com.zm.guava.util;

import com.zm.guava.day003.Student;

import java.util.Map;
import java.util.stream.Stream;

public class StudentUtil {

    public static Stream<Student> student() {
        return Stream.of(
                new Student("刘一", 721, true, Student.GradeType.THREE),
                new Student("陈二", 637, true, Student.GradeType.THREE),
                new Student("张三", 666, true, Student.GradeType.THREE),
                new Student("李四", 531, true, Student.GradeType.ONE),
                new Student("王五", 483, false, Student.GradeType.THREE),
                new Student("赵六", 367, true, Student.GradeType.THREE),
                new Student("孙七", 499, false, Student.GradeType.TWO));
    }

    public static Stream<Student> studentLastMonth() {
        return Stream.of(
                new Student("刘一", 821, true, Student.GradeType.THREE),
                new Student("陈二", 537, true, Student.GradeType.THREE),
                new Student("张三", 567, true, Student.GradeType.THREE),
                new Student("李四", 743, true, Student.GradeType.ONE),
                new Student("王五", 323, false, Student.GradeType.THREE),
                new Student("赵六", 542, true, Student.GradeType.THREE),
                new Student("孙七", 633, false, Student.GradeType.TWO));
    }

    public static Stream<Student> studentWithRepeat() {
        return Stream.of(
                new Student("刘一", 721, true, Student.GradeType.THREE),
                new Student("陈二", 637, true, Student.GradeType.THREE),
                new Student("张三", 666, true, Student.GradeType.THREE),
                new Student("李四", 531, true, Student.GradeType.ONE),
                new Student("王五", 483, false, Student.GradeType.THREE),
                new Student("赵六", 367, true, Student.GradeType.THREE),
                new Student("赵六", 366, true, Student.GradeType.THREE),
                new Student("孙七", 499, false, Student.GradeType.TWO));
    }

    public static Stream<Student> studentWithNull() {
        return Stream.of();
    }

    public void sout(Student student) {
        String a = "hello";
    }

    public static void printMap(Map m){
        System.out.println(m.keySet().stream().map(k->k+"="+m.get(k)));
    }
}
