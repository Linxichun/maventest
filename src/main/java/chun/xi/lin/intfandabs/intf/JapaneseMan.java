package chun.xi.lin.intfandabs.intf;

/**
 * Created by Lin.XiChun on 2018/5/15.
 */
public class JapaneseMan implements HumanIntf{

    @Override
    public String sayWord() {
        String wordStr = "こんにちは";   // 日语问好
        System.out.println(wordStr);    // 打印问好语句
        return wordStr;
    }
}
