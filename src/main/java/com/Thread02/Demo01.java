package com.Thread02;

public class Demo01 {
    private String content;
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                demo01.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("---------------");
                System.out.println(Thread.currentThread().getName() + " --> " + demo01.getContent());
            }, String.valueOf(i)).start();
        }
    }
}
