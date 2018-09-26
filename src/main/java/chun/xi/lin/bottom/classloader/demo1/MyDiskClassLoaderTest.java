package chun.xi.lin.bottom.classloader.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class MyDiskClassLoaderTest {
    public static void main(String[] args) {

        //创建自定义classloader对象。
        MyDiskClassLoader diskLoader = new MyDiskClassLoader(
                "D:\\Lin.xichun\\Self-group training\\JAVA_01\\demo");
        System.out.println("自定义类加载器它默认的父类加载器是："+diskLoader.getParent().toString());
        try {
            //加载class文件
            Class c = diskLoader.loadClass("xun.xi.lin.bottom.classloader.Test");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
