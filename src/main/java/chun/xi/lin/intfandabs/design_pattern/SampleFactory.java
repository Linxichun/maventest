package chun.xi.lin.intfandabs.design_pattern;

import chun.xi.lin.intfandabs.intf.ChineseMan;
import chun.xi.lin.intfandabs.intf.HumanIntf;
import chun.xi.lin.intfandabs.intf.JapaneseMan;

/**
 * Created by Lin.XiChun on 2018/5/17.
 */
public class SampleFactory {
    public static HumanIntf makeHuman(String type){
        if(type.equals("c")){
            HumanIntf chinese = new ChineseMan();
            return chinese;
        }else if(type.equals("j")){
            HumanIntf japanese = new JapaneseMan();
            return japanese;
        }else{
            System.out.println("生产不出来");
            return null;
        }
    }
}
