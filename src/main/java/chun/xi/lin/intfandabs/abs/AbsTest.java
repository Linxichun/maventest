package chun.xi.lin.intfandabs.abs;

/**
 * Created by Lin.XiChun on 2018/5/15.
 */
public class AbsTest {
    public static void main(String[] args) {
        /**
         * 下面这样会报错，抽象类不能直接被实例化
         * 为了安全考虑，抽象类不能直接被实例化，因为抽象类中存在的抽象方法无法被正常执行
         * 之所以抽象类也可以拥有构造函数，就是为了辅助子类可以在实例化时共同执行代码
         * */
//        HumanAbs h = new HumanAbs();

    }
}
