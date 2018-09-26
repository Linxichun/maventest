package chun.xi.lin.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin.XiChun on 2018/7/27.
 */
public class GenericityTest {
    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        stringArrayList.add("111");
        integerArrayList.add(1);

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试-类型相同");
        }else{
            System.out.println("泛型测试-类型不同");
        }
    }
}
