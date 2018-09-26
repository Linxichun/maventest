package chun.xi.lin.proxy;

/**
 * Created by Lin.XiChun on 2018/7/24.
 */
public class RealSubject implements Subject{

    @Override
    public void rent() {
        System.out.println("RealSubject的rent方法！");
    }

    @Override
    public void hello(String str) {
        System.out.println("RealSubject的hello方法！参数：" + str);
    }
}
