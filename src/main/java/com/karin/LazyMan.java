package com.karin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 懒汉式单例模式
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName() + " ok");
    }

    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws Exception {
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }

    /**
     * 1、分配内存空间;
     * 2、执行构造方法，初始化对象;
     * 3、把这个对象指向这个空间;
     * 123
     * 132 A
     *     B  // 此时lazyMan还没有完成构造;
     */
}
