package chun.xi.lin.intfandabs.abs;

/**
 * Created by Lin.XiChun on 2018/5/15.
 */
public class ChineseMan extends HumanAbs{

    @Override
    String sayWord() {
        String wordStr = "你好";   // 中文问好
        System.out.println(wordStr);    // 打印问好语句
        return wordStr;
    }

}
