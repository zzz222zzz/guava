package com.zm.guava.util;

import com.zm.guava.day003.Student;

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
}
