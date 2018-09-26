package chun.xi.lin.constructor;

/**
 * Created by Lin.XiChun on 2018/5/2.
 */
public class Student extends Person{

    /* 学号 */
    private String sid;
    /* 班级名 */
    private String className;

    public Student() {
        System.out.println("执行Student的构造函数");
//        Student(1);    // = Student(1);
    }

    public Student(int parm){
        System.out.println("执行Student的构造函数1:"+parm);
    }

//    public Student(String parm){
//        System.out.println("linxc test2:"+parm);
//    }

//    public Student(String name, int age) {
////        super(name, age); // 1-也就是说只能子类最后一层构造函数去执行父类的构造方法
////        this(age);
//    }
//
//    public Student(String sid) {
////        super(sid,27);    // 2-也就是说只能子类最后一层构造函数去执行父类的构造方法
//        this.sid = sid;
//    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void test(){
        System.out.println("1");
    }
}
