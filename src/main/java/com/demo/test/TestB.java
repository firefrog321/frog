package com.demo.test;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class TestB {


    public void eat() {
        System.out.print("B类的eat方法");
    }

    public int fly() {
        int x = 1;
        try {
            return x;
        } finally {
            return 2;
        }

    }



    public static void main(String[] args) {
        String s=
        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println(new Random().nextLong());
    }

}
