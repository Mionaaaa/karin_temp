package com.karin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int stamp = atomicInteger.getStamp();  // 获取版本号
            System.out.println("a1 -> " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(1, 2,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1);
            System.out.println("a2 -> " + atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(2, 1,
                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("a3 -> " + atomicInteger.getStamp());
        }, "a").start();

        new Thread(()->{
            int stamp = atomicInteger.getStamp();
            System.out.println("b1 -> " + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(1, 6,
                    stamp, stamp + 1));
//            System.out.println(atomicInteger.compareAndSet(1, 6,
//                    atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("b2 -> " + atomicInteger.getStamp());
        }, "b").start();
    }
}
