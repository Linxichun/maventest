package chun.xi.lin.intfandabs.intf;

/**
 * Created by Lin.XiChun on 2018/5/15.
 */
public class ChineseMan implements HumanIntf {

    @Override
    public String sayWord() {
        String wordStr = "你好";   // 中文问好
        System.out.println(wordStr);    // 打印问好语句
        return wordStr;
    }
}
