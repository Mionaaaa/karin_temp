package com.karin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
// 异步调用 completableFuture   异步执行、成功回调、失败回调
public class FutureDemo001 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " runAsync -> void");
        });
        System.out.println("1111");
        completableFuture.get();

        // 有返回值的supplyAsync 异步回调
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()-> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync -> Integer");
            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture1.whenComplete((t, u)->{
            System.out.println("t -> " + t); // 正常的返回结果
            System.out.println("u -> " + u); // 错误信息：java.util.concurrent.CompletionException
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 233; // 可以获取到错误的返回结果
        }).get());
    }
}
