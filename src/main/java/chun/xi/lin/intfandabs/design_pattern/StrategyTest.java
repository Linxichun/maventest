package chun.xi.lin.intfandabs.design_pattern;

import chun.xi.lin.intfandabs.design_pattern.obj.ConcreteStrategyA;
import chun.xi.lin.intfandabs.design_pattern.obj.ConcreteStrategyB;

/**
 * 策略模式测试类
 * Created by Lin.XiChun on 2018/5/17.
 */
public class StrategyTest {
    public static void main(String[] args) {

        StrategyContext context = null; //

        context = new StrategyContext(new ConcreteStrategyA());
        context.contextInterface();

        context = new StrategyContext(new ConcreteStrategyB());
        context.contextInterface();
    }
}
