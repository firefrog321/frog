package com.demo.test;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestB {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        String key="ha";
        int h;
        int i= (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(i);
/*创建node，然后last引用新创建的node，达到添加到尾部的目的
原来的尾节点中的next指向newNode，newNode的上个节点prev指向oldNode*/
    }

}
