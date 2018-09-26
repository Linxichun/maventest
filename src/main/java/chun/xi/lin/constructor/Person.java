package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/5/2.
 */
public class Person {

//    public Person() {
//        System.out.println("执行构造函数");
//    }
//
//    public Person(String name, int age) {
//        System.out.println("执行构造函数,参数name："+name+"；参数age："+age);
//    }

    public Person() {
        System.out.println("执行Person构造函数");
    }

    /* 名字 */
    public String name;
    /* 年龄 */
    private int age;

    /* 测试变量 */
    public String b1 = "普通变量";

    /* 测试变量 */
    public final String f_b1 = null;
    /* 测试变量 */
    private final String f_b2=null;

    /* 测试变量 */
    public static String s_b1;
    /* 测试变量 */
    private static String s_b2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String sayWord(){
        return "shixiandaima";
    };
}
