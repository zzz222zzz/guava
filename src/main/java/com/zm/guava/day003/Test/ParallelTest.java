package com.zm.guava.day003.Test;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

    public static void main(String[] args) {
        // 并行流线程数
//        poolNum();

        // parallel test
        System.out.println(measureSumPerf(ParallelTest::sumByFor, 1000_0000));
        System.out.println(measureSumPerf(ParallelTest::sumByStreamIterate, 1000_0000));
        System.out.println(measureSumPerf(ParallelTest::sumByParallelStreamIterate, 1000_0000));
        System.out.println(measureSumPerf(ParallelTest::sumByStreamRange, 1000_0000));
        System.out.println(measureSumPerf(ParallelTest::sumByParallelStreamRange, 1000_0000));
        System.out.println(measureSumPerf(ParallelTest::sumBySideEffectParallel, 1000_0000));


    }

    private static void poolNum() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    // 测试方法  执行函数10次，选最小的时间返回
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            System.out.println("sum = "+adder.apply(n));
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static long sumByFor(long a) {
        long sum = 0;
        for (long i = 0; i <= a; i++) {
            sum += i;
        }
        return sum;
    }

    public static long sumByStreamIterate(long a) {
        return Stream.iterate(0L, i -> i + 1L).limit(a+1).reduce(Long::sum).orElse(0L);
    }

    public static long sumByStreamRange(long a) {
        return LongStream.rangeClosed(0, a).sum();
    }

    public static long sumByParallelStreamIterate(long a) {
        return Stream.iterate(0L, i -> i + 1L).limit(a+1).parallel().reduce(Long::sum).orElse(0L);
    }

    public static long sumByParallelStreamRange(long a) {
        return LongStream.rangeClosed(0, a).parallel().sum();
    }

    public static long sumBySideEffectParallel(long a){
        //错误思维  ： 初始化一个累加器，一个个遍历列表中的元素，把它们和累加器相加
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(0,a).parallel().forEach(accumulator::add);
        return accumulator.getSum();
    }

    static class Accumulator {
        private long sum = 0L;
        public void add(long a){
            sum += a;
        }
        public long getSum(){
            return this.sum;
        }
    }
}
