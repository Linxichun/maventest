package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/5/2.
 */
public class InstanceVariableInitializer {

    private int i = 1;
    private int j = i + 1;

    public InstanceVariableInitializer(int var){
        System.out.println(i);
        System.out.println(j);
        this.i = var;
        System.out.println(i);
        System.out.println(j);
    }

    {   // 实例代码块
        System.out.println("test!");
        System.out.println("1_j:"+j);
        j += 3;
        System.out.println("2_j:"+j);
    }

    public static void main(String[] args) {
        System.out.println("0");
        new InstanceVariableInitializer(8);
    }
}
