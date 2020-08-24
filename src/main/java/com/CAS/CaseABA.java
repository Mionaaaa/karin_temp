package com.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CaseABA {
    public static AtomicInteger a = new AtomicInteger(1);

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值：" + a.get());
            try {
                int expectNum = a.get();
                int newNum = expectNum + 1;
                Thread.sleep(1000);

                boolean isCASSuccess = a.compareAndSet(expectNum, newNum);
                System.out.println("操作线程" + Thread.currentThread().getName() + ", CAS操作: " + isCASSuccess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "主线程").start();

        new Thread(()->{
            try {
                Thread.sleep(20);
                a.incrementAndGet();
                System.out.println("操作线程" + Thread.currentThread().getName() + ", [increment]: " + a.get());
                a.decrementAndGet();
                System.out.println("操作线程" + Thread.currentThread().getName() + ", [decrement]: " + a.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "干扰线程").start();
    }
}
