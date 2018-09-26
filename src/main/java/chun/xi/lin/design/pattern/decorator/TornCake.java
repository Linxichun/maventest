package chun.xi.lin.design.pattern.decorator;

/**
 * Created by Lin.XiChun on 2018/5/31.
 */
public class TornCake extends Pancake {

    public TornCake() {
        desc = "手抓饼";
    }

    @Override
    public double price() {
        return 4;
    }

}
