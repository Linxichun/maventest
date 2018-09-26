package chun.xi.lin.intfandabs.design_pattern;

import chun.xi.lin.intfandabs.intf.ChineseMan;
import chun.xi.lin.intfandabs.intf.HumanIntf;
import chun.xi.lin.intfandabs.intf.JapaneseMan;

/**
 * Created by Lin.XiChun on 2018/5/17.
 */
public class SampleFactoryTest {
    public static void main(String[] args) {
//        Object c = new ChineseMan();
        HumanIntf man = SampleFactory.makeHuman("c");
        man.sayWord();
        HumanIntf womman = SampleFactory.makeHuman("j");
        womman.sayWord();
//        HumanIntf test = SampleFactory.makeHuman("tttt");
        //
//        HumanIntf human = null;
//        human = SampleFactoryFs.makeHuman(ChineseMan.class);
//        human = SampleFactoryFs.makeHuman(JapaneseMan.class);
//        HumanIntf man = SampleFactoryFs.makeHuman(ChineseMan.class);
//        man.sayWord();
//        HumanIntf woman = SampleFactoryFs.makeHuman(JapaneseMan.class);
//        woman.sayWord();
    }
}
