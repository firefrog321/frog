package com.demo.test;

import java.util.Collections;

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
        System.out.println(new TestB().fly());
    }

}
