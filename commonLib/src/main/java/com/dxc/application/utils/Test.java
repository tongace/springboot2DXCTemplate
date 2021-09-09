package com.dxc.application.utils;

import com.dxc.application.model.Person;

public class Test {
    public static void main(String[] args){
        Person p = new Person();
        p.setFirstName("cjajaj"+((char)34)+"cccc");
        System.out.println(p.getFirstName());
        System.out.println(JsonUtil.toJsonString(p));
    }
}
