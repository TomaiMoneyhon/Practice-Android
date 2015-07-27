package com.example.hackeru.listactivityexample;

import java.lang.Override;import java.lang.String; /**
 * Created by hackeru on 27/07/2015.
 */
public class Dog {

    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
