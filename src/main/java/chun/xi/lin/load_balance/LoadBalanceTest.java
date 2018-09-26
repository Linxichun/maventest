package chun.xi.lin.load_balance;

/**
 * Created by Lin.XiChun on 2018/7/30.
 */
public class LoadBalanceTest {
    public static void main(String[] args) {
//        for (int i = 0; i <20 ; i++) {
//            LoadBalanceThread lt = new LoadBalanceThread();
//            lt.run();
//        }

        for (int i = 0; i <50 ; i++) {

            // 轮询
//            System.out.println("第"+(i+1)+"次打印："+RoundRobin.getServer());

            // 随机
//            System.out.println("第"+(i+1)+"次打印："+Random.getServer());

            // Hash
//            System.out.println("第"+(i+1)+"次打印："+Hash.getServer());

            // 加权轮询
//            System.out.println("第"+(i+1)+"次打印："+WeightRoundRobin.getServer());

            // 加权随机
            System.out.println("第"+(i+1)+"次打印："+WeightRandom.getServer());
        }
    }
}
