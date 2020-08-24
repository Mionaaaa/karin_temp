package com.karin;

import java.util.concurrent.atomic.AtomicReference;

// 自旋锁
public class SpinlockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " --> mylock");

        while (!atomicReference.compareAndSet(null, thread)){

        }
    }
    // 解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " --> myUnlock");
        atomicReference.compareAndSet(thread, null);
    }
}
