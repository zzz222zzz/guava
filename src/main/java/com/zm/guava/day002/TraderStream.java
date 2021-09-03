package com.zm.guava.day002;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TraderStream {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader alan = new Trader("Alan","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader brain = new Trader("Brain","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(raoul, 2011, 310),
                new Transaction(raoul, 2012, 1000),
                new Transaction(mario, 2011, 320),
                new Transaction(mario, 2012, 710),
                new Transaction(brain, 2011, 330),
                new Transaction(alan, 2012, 950)
        );

        //1.找出2011年发生的所有交易，并按交易额非序（从低到高）。
        sorted(transactions);
        //2.交易员都在哪些不同的城市工作过？
        distincted(transactions);
        //3.查找所有来自于剑桥的交易员，并按姓名排序。
        cambridge(transactions);
        //4.返回所有交易员的姓名字符串，按字母顺序排序。
        sortedByName(transactions);
        //5.有没有交易员是在米兰工作的?
        isMilan(transactions);
        //6.打印生活在剑桥的交易员的所有交易额。
        getMilan(transactions);
        //7.所有交易中，最高的交易额是多少？
        getMax(transactions);
        //8.找到交易额最小的交易。
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).limit(1);

        //对象流  与  原始数据流的转换 mapToInt <->  boxed
        transactions.stream().mapToInt(Transaction::getValue).boxed();

        // 挑战  创建一个勾股数流  例如 {3,4,5}
        System.out.println("challenge 1");
        IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream.rangeClosed(a, 100).filter((b) -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj((b) -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
        ).forEach(arr-> System.out.println(Arrays.stream(arr).boxed().map(String::valueOf).collect(joining(",","[","]"))));

        // 斐波那契数列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20).forEach(t->System.out.println("["+t[0]+","+t[1]+"]"));

        // collect  求一个流中最大值和最小值
        List<Integer> list = Arrays.asList(1, 4, 9);
        System.out.println("min : "+ list.stream().min(Integer::compareTo).orElse(0));
        System.out.println("sum"+list.stream().collect(reducing(0, (a, b) -> a + b)));
        Integer integer = list.stream().reduce(Integer::sum).get();
        //reduce 和 collect
        List<Integer> collect = list.stream().map(a -> a + 1).collect(Collectors.toList());
       list.stream().map(a -> a + 1).reduce(new ArrayList<>(), (List<Integer> l, Integer i) -> {
            l.add(i);
            return l;
        }, (List<Integer> l1, List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;
        });

        ArrayList<Integer> objects = new ArrayList<>();
            System.out.println(objects.stream().max(Integer::compareTo));
//            list.stream().collect(groupingBy())
    }

    private static void getMax(List<Transaction> transactions) {
        System.out.println("QUESTION 7");
        Integer integer = transactions.stream().map(Transaction::getValue).reduce(Integer::max).orElse(0);
        System.out.println("max transaction value"+integer);
    }

    private static void getMilan(List<Transaction> transactions) {
        System.out.println("QUESTION 6");
        int milan = transactions.stream().filter(t -> t.getTrader().getCity().toLowerCase(Locale.ROOT).equals("milan"))
                .mapToInt(Transaction::getValue).reduce(0, Integer::sum);
        System.out.println("sum in milan"+ milan);

        // 会涉及到拆箱
    }

    private static void isMilan(List<Transaction> transactions) {
        System.out.println("QUESTION 5");
        boolean milan = transactions.stream().anyMatch(t -> t.getTrader().getCity().toLowerCase(Locale.ROOT).equals("milan"));
        System.out.println("isMilan" + milan);
    }

    private static void sortedByName(List<Transaction> transactions) {
        System.out.println("QUESTION 4");
        List<String> collect = transactions.stream().map(t -> t.getTrader().getName()).sorted(Comparator.comparing(n -> n.charAt(0))).collect(Collectors.toList());
        System.out.println(collect);
        // 拼接字符串效率不高，建议使用 collect(joining()) 内部使用StringBuilder
        transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(joining());
    }

    private static void sorted(List<Transaction> transactions) {
        System.out.println("QUESTION 1");
        List<Transaction> collect = transactions.stream().filter(transaction -> 2011==(transaction.getYear()))
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void distincted(List<Transaction> transactions) {
        System.out.println("QUESTION 2");
        List<String> collect = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void cambridge(List<Transaction> transactions) {
        System.out.println("QUESTION 3");
        List<Trader> cambridge = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(cambridge);
        // 没有去重，需要distinct
    }

    class inner {
        private int[] ints = new int[3];

        public int[] getInts() {
            return ints;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }
    }

}
