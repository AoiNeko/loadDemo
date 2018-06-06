package com.aoineko;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class DemoClassLoader extends ClassLoader {

    public DemoClassLoader(ClassLoader prt) {
        super(prt);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        ClassLoader parent = this.getParent();
        System.out.println("开始加载"+ name);
        Class obj = null;

        if (obj == null) {
            System.out.println("查找加载过的" + name);
            /* Returns the class with the given <a href="#name">binary name</a> if this
             * loader has been recorded by the Java virtual machine as an initiating
             * loader of a class with that <a href="#name">binary name</a>.  Otherwise
             * <tt>null</tt> is returned.*/
            obj = findLoadedClass(name);
        }
        if(obj != null) {
            System.out.println("查到自己加载过的" + name);
        }

        if (parent != null && obj == null) {
            System.out.println("当前loader加载过,委托双亲加载" + name);
            try {
                obj = parent.loadClass(name);
            }
            catch (Exception e) {}
        }


        if (obj == null) {
            try {
                System.out.println("不属于双亲加载的类，自行加载" + name);
                obj = loadDemoClass(name);
                return obj;
            } catch (EOFException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("双亲从jvm取出加载过的Class:" + obj.getName());
            return obj;
        }

        throw new ClassNotFoundException();
    }


    private Class<?> loadDemoClass(String name) throws EOFException {
        Class clz = null;

        try {
            String path = "/home/aoineko/windisk/github/loaderdemo/outsrc/" + name.replace(".", "/") + ".class";
            System.out.println("正在加载path为" + path + "的类");
            byte[] bytes = getClassFileBytes(path);
            System.out.println("正在调用ClasssLoader的defineClass方法将字节码解析为Class对象。defineClass是final修饰的无法重写(override)");
//            System.out.println("实例化class对象,引起父类的加载");
            clz = defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (clz == null) {
            throw new EOFException();
        }
        System.out.println("resolve 类" + clz.getName());
        resolveClass(clz);

        System.out.println("DemoClassLoader返回加载好的" + clz.getName());
        return clz;
    }


    private byte[] getClassFileBytes(String classFile) throws Exception {
        FileInputStream fis = new FileInputStream(classFile );
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

}
