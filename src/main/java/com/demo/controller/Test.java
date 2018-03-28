package com.demo.controller;

import com.demo.utils.LoggerUtils;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String args[]){
       /* String[] s={"11","22"};
        List list= Arrays.asList(s);
        System.out.print(list);*/
        String s= LoggerUtils.getAddressByIP("0");
        System.out.print(s);
    }
}
