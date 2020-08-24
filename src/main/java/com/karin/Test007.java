package com.karin;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求，用一行代码实现：
 * 五个用户筛选：ID为偶数、年龄大于23、用户名转换为大写、用户名倒着排序、只使出一个用户
 */
public class Test007 {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter(u->{return u.getId() % 2 == 0;})
                .filter(u->{return u.getAge() > 23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((uu1, uu2)->{return uu2.compareTo(uu1);})
                .forEach(System.out::println);
    }
}

class User{
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}