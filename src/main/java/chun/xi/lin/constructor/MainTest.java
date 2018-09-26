package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/7/27.
 */
public class MainTest {
    public static void main(String[] args) {
        Person p = new Person();
        if(p instanceof Person) System.out.println("yes");
    }
}
