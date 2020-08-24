package com.Thread02;

public class Demo03 {
    ThreadLocal<String> t1 = new ThreadLocal<>();
    private String content;
    public String getContent() { return t1.get(); }
    public void setContent(String content) { t1.set(content); }

    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                demo03.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("---------------");
                System.out.println(Thread.currentThread().getName() + " --> " + demo03.getContent());
            }, String.valueOf(i)).start();
        }
    }
}
