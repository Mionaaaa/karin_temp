package com.karin;

import java.util.concurrent.*;

// Executors 工具类、3大方法；
// 使用了线程池之后，使用线程池来创建线程；
public class Demo01 {
    public static void main(String[] args) {
//        // 获取CPU核数
//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程；
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池；
            threadpool.shutdown();
        }
    }
}