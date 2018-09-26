package chun.xi.lin.intfandabs.intf;

/**
 * Created by Lin.XiChun on 2018/5/15.
 */
public class IntfTest {
    /**
     * 定义地球人说话方法
     * 用接口作为传参类型，实现多态
     * */
    private String earthHumanSay(HumanIntf human){
        // 这里直接传基类的说话方法
        return human.sayWord();
    }

    /**
     * main方法来测试
     * */
    public static void main(String[] args) {
        /**
         * 和抽象类一样，接口里全是抽象方法，所以不能直接被实例化
         * */
//        HumanIntf humanIntf = new HumanIntf();
        // 实例化对象
        IntfTest intfTest = new IntfTest();
        ChineseMan chineseMan = new ChineseMan();
//        System.out.println(chineseMan);
        chineseMan.sayWord();
        // 调用方法
        intfTest.earthHumanSay(chineseMan);
//        HumanIntf h = new ChineseMan();
//        ChineseMan c = new ChineseMan();
//        ChineseMan c = new ChineseMan();

    }
}
