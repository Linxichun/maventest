package chun.xi.lin.bottom.classloader;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        ClassLoader cl = Test.class.getClassLoader();
        System.out.println("ClassLoader is:"+cl.toString());
        System.out.println("ClassLoader\'s parent is:"+cl.getParent().toString());
        System.out.println("ClassLoader\'s parent is:"+cl.getParent().getParent().toString());
//        cl = int.class.getClassLoader();
//        System.out.println("ClassLoader is:"+cl.toString());

    }
}
