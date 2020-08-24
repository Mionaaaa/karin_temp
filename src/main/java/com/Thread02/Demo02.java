package com.Thread02;

public class Demo02 {
    private String content;
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized (Demo02.class) {
                    demo02.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("---------------");
                    System.out.println(Thread.currentThread().getName() + " --> " + demo02.getContent());
                }
            }, String.valueOf(i)).start();
        }
    }
}
