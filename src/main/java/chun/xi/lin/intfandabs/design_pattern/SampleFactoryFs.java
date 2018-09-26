package chun.xi.lin.intfandabs.design_pattern;

import chun.xi.lin.intfandabs.intf.HumanIntf;

/**
 * 简单工厂反射实现
 * Created by Lin.XiChun on 2018/5/17.
 */
public class SampleFactoryFs {
    public static HumanIntf makeHuman(Class c){
        HumanIntf human = null;
        try {
            human = (HumanIntf) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            System.out.println("不支持抽象类或接口");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("没有足够权限，即不能访问私有对象");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("类不存在");
            e.printStackTrace();
        }
        return human;
    }
}
