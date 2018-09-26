package chun.xi.lin.intfandabs.design_pattern.obj;

import chun.xi.lin.intfandabs.design_pattern.StrategyCalc;

/**
 * 具体策略角色：包装了相关的算法和行为
 * Created by Lin.XiChun on 2018/5/17.
 */
public class ConcreteStrategyB extends StrategyCalc{

    @Override
    public void algorithmInterface() {
        System.out.println("算法B");
    }
}
