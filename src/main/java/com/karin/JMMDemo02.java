package com.karin;

import java.util.concurrent.atomic.AtomicInteger;

public class JMMDemo02 {
    // volatile 不保证原子性;
    // AtomicInteger 原子类的Integer;
    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add(){
        //num++;
        num.getAndIncrement(); // AtomicInteger + 1 方法, CAS;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
