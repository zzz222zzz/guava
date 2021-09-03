package com.zm.guava.day001;

import com.zm.guava.entity.Apple;

import java.util.function.Predicate;

public class predicate {

    public static void main(String[] args) {

        Predicate<Apple> redApple = (apple)->apple.getColor().equals("red");

    }
}
