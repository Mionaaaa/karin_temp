package com.Thread02;

public class SingletonDemo {
    private static SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "我是构造方法SingletonDemo()");
    }

    public static SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }
    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i <= 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
