package chun.xi.lin.proxy.dynamic_type;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Lin.XiChun on 2018/7/25.
 */
public class DynamicProxyMain {

    static void customer(ProxyInterface pi){
        pi.say();
    }

    public static void main(String[] args){
        RealObject real = new RealObject();
        // 产生一个代理对象
        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(
                ProxyInterface.class.getClassLoader(),new Class[]{ProxyInterface.class}, new ProxyObject(real));
        // 去执行相应的方法
        customer(proxy);
    }
}


interface ProxyInterface{
    void say();
}

// 被代理类
class RealObject implements ProxyInterface{
    public void say(){
        System.out.println("i'm talking");
    }
}

// 代理类，实现InvocationHandler 接口
class ProxyObject implements InvocationHandler {
    private Object proxied = null;
    public ProxyObject(){ // 无参构造
    }
    public ProxyObject(Object proxied){ // 有参构造
        this.proxied  = proxied;
    }
    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        System.out.println("hello");
        return arg1.invoke(proxied, arg2);
    };
}

