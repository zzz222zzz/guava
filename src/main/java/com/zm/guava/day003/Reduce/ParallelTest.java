package com.zm.guava.day003.Reduce;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

    public static void main(String[] args) {
        // 并行流线程数
//        poolNum();
        long a = 1000_0000L;
        // parallel test
        System.out.println("sumByFor="+measureSumPerf(ParallelTest::sumByFor, 1000_0000));
        System.out.println("sumByStreamIterate="+measureSumPerf(
                s->ParallelTest.sumByStreamIterate(Stream.iterate(0L, i -> i + 1L).limit(a)), 1000_0000));
        System.out.println("sumByParallelStreamIterate="+measureSumPerf(
                s->ParallelTest.sumByParallelStreamIterate(Stream.iterate(0L, i -> i + 1L).limit(a)), 1000_0000));
        System.out.println("sumByStreamRange="+measureSumPerf(s->ParallelTest.sumByStreamRange(LongStream.rangeClosed(0, a)), 1000_0000));

        System.out.println("sumByParallelStreamRange="+measureSumPerf(s->ParallelTest.sumByParallelStreamRange(LongStream.rangeClosed(0, a)), 1000_0000));
//        System.out.println("sumBySideEffectParallel="+measureSumPerf(ParallelTest::sumBySideEffectParallel, 1000_0000));


    }

    private static void poolNum() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    // 测试方法  执行函数10次，选最小的时间返回
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
//        adder.getClass().getDeclaredMethod()
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Long apply = adder.apply(n);
//            System.out.println("sum = "+apply);
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

    public static long sumByStreamIterate(Stream<Long> a) {
        return a.reduce(Long::sum).orElse(0L);
    }

    public static long sumByStreamRange(LongStream a) {
        return a.sum();
    }

    public static long sumByParallelStreamIterate(Stream<Long> a) {
        return a.parallel().reduce(Long::sum).orElse(0L);
    }

    public static long sumByParallelStreamRange(LongStream a) {
        return a.parallel().sum();
    }

    public static long sumBySideEffectParallel(long a){
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
