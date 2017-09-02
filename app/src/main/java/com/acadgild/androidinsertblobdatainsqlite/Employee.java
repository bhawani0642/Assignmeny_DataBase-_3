package com.acadgild.androidinsertblobdatainsqlite;

import android.graphics.Bitmap;

/**
 * Created by Pri on 9/2/2017.
 */
//creating employee class
public class Employee {
    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }



    private Bitmap bmp;
    private String name;
    private int age;
    //constructor
    public Employee(Bitmap b, String n, int k) {
        bmp = b;
        name = n;
        age = k;
    }
    //generating getters and setters
    public Bitmap getBitmap() {
        return bmp;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setBitmap(){
        this.bmp=bmp;
    }

}


