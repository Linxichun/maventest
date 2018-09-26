package chun.xi.lin.load_balance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin.XiChun on 2018/8/2.
 */
public class WeightRoundRobin2 {

    /**上次选择的服务器*/
    private int currentIndex = -1;
    /**当前调度的权值*/
    private int currentWeight = 0;
    /**最大权重*/
    private int maxWeight;
    /**权重的最大公约数*/
    private int gcdWeight;
    /**服务器数*/
    private int serverCount;

    private List<Server> servers = new ArrayList<Server>();

    /*
     * 得到两值的最大公约数
     */
    public int greaterCommonDivisor(int a, int b){
        if(a % b == 0){
            return b;
        }else{
            return greaterCommonDivisor(b,a % b);
        }
    }
    /*
     * 得到list中所有权重的最大公约数，实际上是两两取最大公约数d，然后得到的d
     * 与下一个权重取最大公约数，直至遍历完
     */
    public int greatestCommonDivisor(List<Server> servers){
        int divisor = 0;
        for(int index = 0, len = servers.size(); index < len - 1; index++){
            if(index ==0){
                divisor = greaterCommonDivisor(
                        servers.get(index).getWeight(), servers.get(index + 1).getWeight());
            }else{
                divisor = greaterCommonDivisor(divisor, servers.get(index).getWeight());
            }
        }
        return divisor;
    }

    /*
     * 得到list中的最大的权重
     */
    public int greatestWeight(List<Server> servers){
        int weight = 0;
        for(Server server : servers){
            if(weight < server.getWeight()){
                weight = server.getWeight();
            }
        }
        return weight;
    }

    /**
     *  算法流程：
     *  假设有一组服务器 S = {S0, S1, …, Sn-1}
     *  有相应的权重，变量currentIndex表示上次选择的服务器
     *  权值currentWeight初始化为0，currentIndex初始化为-1 ，当第一次的时候返回 权值取最大的那个服务器，
     *  通过权重的不断递减 寻找 适合的服务器返回；
     *  自解：
     *  currentWeight为当前调度的权值，一开始他会等于最大权重值，【决定哪台服务器的】一轮下来后，会以公约数的单位递减，
     *  调度值currentWeight比公约数小的话说明，要设置调度值为最大权重值，进行【所有服务器执行一遍】的一轮
     *  模拟下面首次的流程，这里首次会比较头疼：
     */
    public Server getServer(){
        while(true){
            currentIndex = (currentIndex + 1) % serverCount;    // 其实就是服务器的位移，取余其实就是为了轮询
            if(currentIndex == 0){ // 说明下一轮刚准备开始（在第一次或一轮结束后）
                currentWeight = currentWeight - gcdWeight;  // 权重调度值=当前权重调度值-权重最大公约数
                if(currentWeight <= 0){ // 若权重调度值<=0,则需重新开始赋值（赋给最大权重值）
                    currentWeight = maxWeight;
                    if(currentWeight == 0){
                        return null;
                    }
                }
            }
            // 筛选出当前权重值大于本次权重调度值的服务器
            if(servers.get(currentIndex).getWeight() >= currentWeight){ // 0 1 2 3 0 1 2 3
                return servers.get(currentIndex);
            }
        }
    }

    public void init(){
        servers.add(new Server("192.168.191.1", 1));
        servers.add(new Server("192.168.191.2", 2));
        servers.add(new Server("192.168.191.4", 4));
        servers.add(new Server("192.168.191.8", 8));


        maxWeight = greatestWeight(servers);    // 获取最大的权重
        gcdWeight = greatestCommonDivisor(servers); // 获取最大公约数
        serverCount = servers.size(); // 获取服务器的台数
    }

    public static void main(String args[]){
        WeightRoundRobin2 weightRoundRobin = new WeightRoundRobin2();
        weightRoundRobin.init();

        for(int i = 0; i < 15; i++){    // 循环执行15次
            Server server = weightRoundRobin.getServer();   //
            System.out.println("第"+(i+1)+"次结果：server " + server.getIp() + " weight=" + server.getWeight());
        }
    }

}

class Server{
    private String ip;
    private int weight;

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
