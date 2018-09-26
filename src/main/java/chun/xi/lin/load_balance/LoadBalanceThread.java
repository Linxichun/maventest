package chun.xi.lin.load_balance;

/**
 * Created by Lin.XiChun on 2018/7/30.
 */
public class LoadBalanceThread implements Runnable{
    @Override
    public void run() {
        // 轮询
        System.out.println(RoundRobin.getServer());
    }
}
