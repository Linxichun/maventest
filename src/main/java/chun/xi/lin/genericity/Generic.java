package chun.xi.lin.genericity;

/**
 * Created by Lin.XiChun on 2018/7/27.
 */
// 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
// 在实例化泛型类时，必须指定T的具体类型
public class Generic<H> {
    // key这个成员变量的类型为T,T的类型由外部指定
    private H key;

    public Generic(H key) { // 泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public H getKey(){ // 泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    public static void main(String[] args) {
   /*     // 泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        // 传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");

        System.out.println("泛型测试—key is " + genericInteger.getKey());
        System.out.println("泛型测试—key is " + genericString.getKey());*/

/*        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);

        System.out.println("泛型测试-key is " + generic.getKey());
        System.out.println("泛型测试-key is " + generic1.getKey());
        System.out.println("泛型测试-key is " + generic2.getKey());
        System.out.println("泛型测试-key is " + generic3.getKey());*/

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue1(gInteger); // 这种会报错，即使Integer是Number的子类


    }

    public static void showKeyValue(Generic<Number> obj){
        System.out.println("泛型测试-key value is " + obj.getKey());
    }

    /**
     * 参数中采用类型通配符
     * */
    public static void showKeyValue1(Generic<?> obj){
        System.out.println("泛型测试-key value is " + obj.getKey());
    }

    /**
     * 添加上下边界
     * */
    public void showKeyValue2(Generic<? extends Number> obj){
        System.out.println("泛型测试-key value is " + obj.getKey());
    }

    /**
     * 泛型方法的基本介绍
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass)throws InstantiationException , IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }
}
