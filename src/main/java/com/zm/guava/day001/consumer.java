package com.zm.guava.day001;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class consumer {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(20);
        Runnable c = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sout(Thread.currentThread().getName());
            sout(list.size());
        };
        sout(list.size());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(c);
        list.add(30);
        sout(Thread.currentThread().getName());
        sout(list.size());
    }


    static void sout(Object o){
        System.out.println(o);
    }
}
