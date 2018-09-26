package chun.xi.lin.bottom.classloader.demo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class DeClassLoaderTest {
    public static void main(String[] args) {
//        FileUtils.test("D:\\Lin.xichun\\Self-group training\\JAVA_01\\demo\\Test.class");
        DeClassLoader diskLoader = new DeClassLoader("D:\\Lin.xichun\\Self-group training\\JAVA_01\\demo");
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
