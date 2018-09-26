package chun.xi.lin.load_balance;
import java.util.*;

/**
 * 加权轮询
 * Created by Lin.XiChun on 2018/7/30.
 */
public class WeightRoundRobin {

    private static Integer pos = 0;

    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++){   // 这里权重越多，相当于排到服务器列表的选择更多
                serverList.add(server);
            }
        }

        String server = null;
        synchronized (pos)
        {
            if (pos >= serverList.size())
                pos = 0;
            server = serverList.get(pos);
            pos ++;
        }

        return server;
    }
}
