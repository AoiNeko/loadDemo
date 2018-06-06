package com.aoineko.demo;

import java.util.Date;

public class DemoPrinter {

    public  static  final String hello= "Hello world";
    public static final int num = 5;
    public static Date createDate = null;
    static {
        System.out.println("执行DemoPrinter的<clinit>");
        createDate = new Date();
    }

    public DemoPrinter() {
        System.out.println("父类DemoPrinter初始化");
    }

    public  static void sayHello () {
        System.out.println(hello);
    }

    public void sayH(String string) {
        System.out.println(this.toString() + string);
    }

    public void say(String string) {
        System.out.println(this.toString() + string);
    }
}