package com.demo.test;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class TestB {

    public TestB(){
        System.out.println("Test构造函数执行");
    }
    static{
        System.out.println("static语句块执行");
    }
    public static void display(){
        System.out.println("静态方法被执行");
    }


    public static void main(String[] args) {
       TestB b = new TestB();
       b.display();
    }

}
