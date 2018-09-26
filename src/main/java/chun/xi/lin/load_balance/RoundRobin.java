package chun.xi.lin.load_balance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 轮询法
 * Created by Lin.XiChun on 2018/7/30.
 */
public class RoundRobin {

    private static Integer pos = 0;


    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos) {
            if (pos >= keySet.size())
                pos = 0;
            server = keyList.get(pos);
            pos ++;
        }

        return server;
    }
}
