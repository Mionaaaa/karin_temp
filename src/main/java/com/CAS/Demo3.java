package com.CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo3 {
    // 总访问量;
    volatile static int count = 0;

    // 模拟访问的方法;

    /**
     * 耗时太长的原因是什么？
     *      程序中的request方法使用了synchronized关键字修饰, 保证了并发情况下, request方法同一时刻下
     *      只允许一个线程进入, request加锁相当于串行执行了, count的结果和我们预期的一直, 只是时间消耗太长了;
     *
     * count++ 操作实际上是由3步来完成的（jvm执行引擎）
     *         1、获取count的值, 记作A: A=count;
     *         2、将A值+1, 得到B: B = A + 1;
     *         3、将B值赋值给count;
     * 如何解决耗时长的问题？
     *      升级第三步
     *      1、获取锁;
     *      2、获取以下count最新的值, 记作LV;
     *      3、判断LV是否等于A, 如果相等, 则将B的值赋值给count, 并返回true, 否则返回false;
     *      4、释放锁;
     */
    public static void request() throws InterruptedException {
        // 模拟耗时5毫秒;
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         * 分析一下问题:
         *      count++ 操作实际上是由3步来完成的（jvm执行引擎）
         *          1、获取count的值, 记作A: A=count;
         *          2、将A值+1, 得到B: B = A + 1;
         *          3、将B值赋值给count;
         *      如果有A、B两个线程同时执行count++ 他们同时执行到上面步骤的第一步, 得到的count是一样的
         *      2步操作结束后, count只加1, 导致count结果不正确;
         *
         * 怎么解决结果不正确 ?
         *      对count++操作的时候, 我们让多个线程排队处理, 多个线程同时到达request()方法的时候,
         *      只能允许一个线程可以进去操作, 其他的线程在外面等待, 等里面的处理完毕以后, 外面等着的再进去
         *      这样操作的count++就是排队进行的, 结果一定是正确的;
         *
         * 怎么实现排队效果
         *      java中的synchronized关键字和ReentrantLock都可以实现对资源加锁, 保证并发正确性,
         *      多线程的情况下可以保证被锁住的资源被 "串行" 访问;
         */
        // count ++;
        int expectCount;
        while (!compareAndSwap((expectCount = getCount()), expectCount + 1)) {

        }
    }

    public static synchronized boolean compareAndSwap(int expectCount, int newCount){
        // 判断count当前值是否和期望值expectCount一致，如果一致，将newCount赋值给count
        if (getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }

    public static int getCount(){
        return count;
    }
    public static void main(String[] args) throws InterruptedException {
        // 开始时间
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            new Thread(()->{
                // 模拟用户行为，每个用户访问10次网站
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();


        }
        // 怎么保证100个线程结束之后 在执行下面的代码
        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + ",耗时： " + (endTime - startTime) + ", count = " + count);
    }
}
