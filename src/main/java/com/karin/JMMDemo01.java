package com.karin;

import java.util.concurrent.TimeUnit;

public class JMMDemo01 {
    // 不加volatile 程序就会死循环；
    // 加volatile 可以保证可见性；
    private volatile static int num = 0;
    public static void main(String[] args) {
        // 线程1对主内存的变化不知道；
        new Thread(()->{ while (num == 0){} }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
