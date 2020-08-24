package com.karin;
import java.util.concurrent.TimeUnit;

public class Lock8 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        }, "B").start();
    }
}


class Phone{
    public synchronized void sendSms(){
        System.out.println("sendSms");
    }

    public synchronized void call(){
        System.out.println("call");
    }
}