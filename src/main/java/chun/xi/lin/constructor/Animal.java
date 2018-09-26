package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/5/2.
 */
public class Animal {

    /* 毛色 */
    private String color;
    /* 腿条数 */
    private int legNum;

    public Animal(String color) {
        this(color,4);
    }

    public Animal(String color, int legNum) {
        this.color = color;
        this.legNum = legNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLegNum() {
        return legNum;
    }

    public void setLegNum(int legNum) {
        this.legNum = legNum;
    }
}
