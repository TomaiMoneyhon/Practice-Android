package com.example.ins.customerdrill;

import java.io.Serializable;

/**
 * Created by ins on 20/07/2015.
 */
public class Customer implements Serializable{

    private String name;
    private int age;
    private boolean isMale;

    public Customer(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
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

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}
