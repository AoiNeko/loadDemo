import com.aoineko.DemoClassLoader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppMain {

    public static void main(String[] args) {
        DemoClassLoader demoClassLoader = new DemoClassLoader(DemoClassLoader.class.getClassLoader());
        try {
            //加载DemoPrinter
//            Class demoClz = demoClassLoader.loadClass("com.aoineko.demo.DemoPrinter");
            Class demoClz = demoClassLoader.loadClass("com.aoineko.demo.DemoSubPrinter");
//            System.out.println("获取静态引用" +demoClz.getField("num").get(null));

            waitASec();
            //获取say方法
            System.out.println("获取方法引用");
            Method method = demoClz.getMethod("say", String.class);
            System.out.println("实例化加载类");
            waitASec();
            Object ins = demoClz.newInstance();
            method.invoke(ins, "instance say hello 1");
//            method.invoke(ins, "instance say hello 2");

//            method.invoke(demoClz.newInstance(), "instance say hello 3");
            method.invoke(demoClz.newInstance(), "instance say hello 4");
//            DemoPrinter demoPrinter = new DemoPrinter();
//            demoPrinter.say("Instance say hello ");
//            String s =  new String("asds");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void waitASec() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }
}
