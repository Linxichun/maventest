package chun.xi.lin.proxy.dynamic_type;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class ProxyTest1 {
    public static void main(String[] args) {

        //创建一个实例对象，这个对象是被代理的对象
        OtherStu lisi = new OtherStu("李四");

        //创建一个与代理对象相关联的InvocationHandle
        InvocationHandler stuHandler = new StuInvocationHandler<OtherStu>(lisi);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        OtherStu stuProxy = (OtherStu) Proxy.newProxyInstance(
                OtherStu.class.getClassLoader(), new Class<?>[]{OtherStu.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney(50);
    }
}
