package chun.xi.lin.design.pattern.decorator;

/**
 * Created by Lin.XiChun on 2018/5/31.
 */
public class Roujiamo extends Pancake {

    public Roujiamo() {
        desc = "肉夹馍";
    }

    @Override
    public double price() {
        return 6;
    }

}