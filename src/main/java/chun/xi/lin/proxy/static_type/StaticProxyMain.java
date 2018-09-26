package chun.xi.lin.proxy.static_type;

/**
 * Created by Lin.XiChun on 2018/7/25.
 */
public class StaticProxyMain {

    // 这里传入的是接口类型的对象，方便向上转型，实现多态
    public static void consumer(ProxyInterface pi){
        pi.say();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        consumer(new ProxyObject());
    }
}

// 代理接口
interface ProxyInterface{
    void say();
}

// 被代理者
class RealObject implements ProxyInterface{
    //实现接口方法
    @Override
    public void say() {
        // TODO Auto-generated method stub
        System.out.println("say");
    }

}


// 代理者
class ProxyObject implements ProxyInterface{

    @Override
    public void say() {
        // TODO Auto-generated method stub
        //dosomething for example
        System.out.println("hello proxy");
        new RealObject().say();
        System.out.println("this is method end");
    }

}
