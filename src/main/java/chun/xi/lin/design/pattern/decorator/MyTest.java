package chun.xi.lin.design.pattern.decorator;

import org.junit.Test;

/**
 * Created by Lin.XiChun on 2018/5/31.
 */
public class MyTest {

    @Test
    public void test() {
        Pancake tornCake = new TornCake();
        //手抓饼基础价
        System.out.println(String.format("%s ￥%s", tornCake.getDesc(), tornCake.price()));

        Pancake roujiamo = new Roujiamo();
        roujiamo = new FiredEgg(roujiamo);
        roujiamo = new FiredEgg(roujiamo);
        roujiamo = new Ham(roujiamo);
//        roujiamo = new MeatFloss(roujiamo);
//        roujiamo = new Cucumber(roujiamo);
        //我好饿
        System.out.println(String.format("%s ￥%s", roujiamo.getDesc(), roujiamo.price()));
    }
}
