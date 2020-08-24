package com.karin;

import java.util.function.Consumer;
// Consumer 消费型接口，只有输入，没有返回值；
public class Demo004 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>(){
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };

        Consumer<String> consumer1 = (str) -> { System.out.println(str); };
        consumer.accept("asdfg11");
        consumer1.accept("asdfg");
    }
}
