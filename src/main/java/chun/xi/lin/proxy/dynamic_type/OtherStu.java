package chun.xi.lin.proxy.dynamic_type;

/**
 * Created by Lin.XiChun on 2018/7/26.
 */
public class OtherStu {
    private String name;
    public OtherStu(String name) {
        this.name = name;
    }

    public void giveMoney(int money) {
        try {
            //假设数钱花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "外班学生上交班费"+money+"元");
    }
}
