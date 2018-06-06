package com.aoineko.demo;

import com.aoineko.demo.DemoPrinter;

import java.util.Date;

public class DemoSubPrinter extends DemoPrinter{
    public static final int fInt = 5;
    public static int sInt = 6;
    private int pInt = 0;
    static {
        System.out.println("执行子类DemoSubPrinter的<clinit>");
    }

    public DemoSubPrinter () {
        System.out.println("子类DemoSubPrinter初始化");
    }

    @Override
    public void say(String string) {
        Date date = new Date();
        System.out.println(date.toString() + " " + this.toString() + string);
    }

    @Override
    public void sayH(String string) {
        System.out.println(this.toString() + string);
    }
}
