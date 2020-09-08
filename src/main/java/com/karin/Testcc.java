package com.karin;

import java.lang.ref.SoftReference;

public class Testcc {
    private static class Bigger{
        public int[] values;
        public String name;
        public Bigger(String name){
            this.name = name;
            values = new int[1024];
        }
    }

    public static void main(String[] args) {
        int count = 100000;
        SoftReference[] values = new SoftReference[count];
        for (int i = 0; i < count; i++) {
            values[i] = new SoftReference<Bigger>(new Bigger("Bigger-" + i));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(((Bigger)(values[i].get())).name);
        }
    }
}
