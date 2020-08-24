package com.karin;
import java.util.function.Function;

public class Demo002 {
    public static void main(String[] args) {

        Function<String, String> function = new Function<String, String>(){
            @Override
            public String apply(String str) {
                return str;
            }
        };

        Function function1 = (str) -> {return str;};
        System.out.println(function1.apply("asd"));
    }
}
