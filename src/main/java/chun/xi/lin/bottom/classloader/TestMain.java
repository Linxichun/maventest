package chun.xi.lin.bottom.classloader;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("\nBootstrapClassLoader加载的目录:");
        System.out.println(System.getProperty("sun.boot.class.path").replaceAll(";",";\n"));
        System.out.println("\nExtClassLoader加载的目录:");
        System.out.println(System.getProperty("java.ext.dirs").replaceAll(";",";\n"));
        System.out.println("\nAppClassLoader加载的目录:");
        System.out.println(System.getProperty("java.class.path").replaceAll(";",";\n"));
    }
}
