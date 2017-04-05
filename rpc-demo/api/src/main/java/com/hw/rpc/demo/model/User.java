package com.hw.rpc.demo.model;

import java.io.Serializable;

/**
 * Created by huwei on 2017/4/5.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2041286658932031763L;
    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
