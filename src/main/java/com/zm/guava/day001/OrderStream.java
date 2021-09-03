package com.zm.guava.day001;

import java.util.stream.Stream;

public class OrderStream {

    public static void main(String[] args) {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        /**
         * 事实上，输出的结果却是随着链条垂直移动的。比如说，当 Stream 开始处理 d2 元素时，它实际上会在执行完 filter 操作后，再执行 forEach 操作，接着才会处理第二个元素。
         * 是不是很神奇？为什么要设计成这样呢？
         * 原因是出于性能的考虑。这样设计可以减少对每个元素的实际操作数，看完下面代码你就明白了：
         */
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A"); // 过滤出以 A 为前缀的元素
                });
        /**
         * // map:      d2
         * // anyMatch: D2
         * // map:      a2
         * // anyMatch: A2
         */
        /**
         * 终端操作 anyMatch()表示任何一个元素以 A 为前缀，返回为 true，就停止循环。所以它会从 d2 开始匹配，接着循环到 a2 的时候，返回为 true ，于是停止循环。
         * 由于数据流的链式调用是垂直执行的，map这里只需要执行两次。相对于水平执行来说，map会执行尽可能少的次数，而不是把所有元素都 map 转换一遍。
         */

        //  利用这种垂直执行的特性，我们可以干什么？  下面这两端代码就有了区别,减少了代码执行的次数
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A"); // 过滤出以 A 为前缀的元素
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出
        // map:     d2
        // filter:  D2
        // map:     a2
        // filter:  A2
        // forEach: A2
        // map:     b1
        // filter:  B1
        // map:     b3
        // filter:  B3
        // map:     c
        // filter:  C
            // 调换了map和filter的顺序 map操作只执行了一次
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出
        // filter:  d2
        // filter:  a2
        // map:     a2
        // forEach: A2
        // filter:  b1
        // filter:  b3
        // filter:  c

    }
}
