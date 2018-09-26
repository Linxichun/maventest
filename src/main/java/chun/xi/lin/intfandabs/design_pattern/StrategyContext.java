package chun.xi.lin.intfandabs.design_pattern;

/**
 * 环境角色：持有一个策略类的引用，最终给客户端调用。
 * Created by Lin.XiChun on 2018/5/17.
 * 这里有别于简单工厂模式，这里不负责管你到底是使用什么哪个算法，
 * 只要你都按我的StrategyCalc形式定好实现类，使用的时候，指定其对象类型，你就可以按你的实现类算法执行
 * 也就是说，无论怎么添加算法，我这个类不用再对新的算法实现类进行处理
 */
public class StrategyContext {

    // 计算类作为成员变量
    StrategyCalc strategyCalc;

    // 构造函数
    public StrategyContext(StrategyCalc strategyCalc){
        this.strategyCalc = strategyCalc;
    }

    // 引用方法
    public void contextInterface(){
        strategyCalc.algorithmInterface();
    }
}
