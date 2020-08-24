package com.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CaseABA02 {
    public static AtomicStampedReference<Integer> a = new AtomicStampedReference(new Integer(1), 1);

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("操作线程" + Thread.currentThread().getName() + ", 初始值：" + a.getReference());
            try {
                Integer expectReference = a.getReference();  // 1
                Integer newReference = expectReference + 1;  // 2
                Integer expectStamp = a.getStamp();  // 1
                Integer newStamp = expectStamp + 1;  // 2
                Thread.sleep(1000);

                boolean isCASSuccess = a.compareAndSet(expectReference, newReference, expectStamp, newStamp);
                System.out.println("操作线程" + Thread.currentThread().getName() + ", CAS操作: " + isCASSuccess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "主线程").start();

        new Thread(()->{
            try {
                Thread.sleep(20);
                // stamp 1 -> 2;
                a.compareAndSet(a.getReference(), (a.getReference() + 1), a.getStamp(), (a.getStamp() + 1));
                System.out.println("操作线程" + Thread.currentThread().getName() + ", [increment]: " + a.getReference());
                // stamp 2 -> 3;
                a.compareAndSet(a.getReference(), (a.getReference() - 1), a.getStamp(), (a.getStamp() + 1));
                System.out.println("操作线程" + Thread.currentThread().getName() + ", [decrement]: " + a.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "干扰线程").start();
    }
}
