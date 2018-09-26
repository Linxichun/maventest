package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/5/2.
 */
public class ConstructorTest {
    public static void main(String[] args) {
        // 类的实例化
//        Person p = new Person("lxc",27);

        // 父类和子类的调用
//        Student s = new Student();

        // 构造函数自己的调用
        Student s = new Student(1);
        System.out.println(s.name);

        Person ps = new Student();
        /**
         * 子类调用父类的public变量
         * */
        System.out.println(ps.b1);
        ps.b1 = "子变量值";
        System.out.println(ps.b1);

        /**
         * 测试静态变量
         * final和static配private都不可以被子类继承
         * 1、不能直接调用类名加静态变量名调用private的静态变量
         * 2、final修饰的f_b1不能被修改
         * */
        Person.s_b1 = "p_sb1";
        Student.s_b1 = "p_sb2";
//        ps.f_b1 = "ps_fb1";   // 报错，编译不让通过

        Person person = new Person();
        person.setName("ceshizhe");
    }

//    private static P parseP(Person ps){
//        P p = new P();
//        p = parseS(ps);
//        return  p;
//    }
//
//    private static S parseS(Person ps){
//        S s = new S();
//        s.pid = ps.getName();
//        s.setSid("11");
//        return  s;
//    }
}
