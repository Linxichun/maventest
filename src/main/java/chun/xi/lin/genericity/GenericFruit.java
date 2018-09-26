package chun.xi.lin.genericity;

/**
 * Created by Lin.XiChun on 2018/7/27.
 */
public class GenericFruit {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        // apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        // 编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(person);

        // 使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);
        System.out.println("到show_2没有异常");

        // 使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);
        System.out.println("到show_3没有异常");

        // 这样执行也是可以的
        generateTest.show_4(apple, person);
        System.out.println("到show_4没有异常");
    }
}

class Fruit{
    @Override
    public String toString() {
        return "fruit";
    }
}

class Apple extends Fruit{
    @Override
    public String toString() {
        return "apple";
    }
}

class Person{
    @Override
    public String toString() {
        return "Person";
    }
}

class GenerateTest<T>{

    public void show_1(T t){
        System.out.println(t.toString());
    }

    // 在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
    // 基本和show_3方法是一个概念
    public <T> void show_2(T t){
        System.out.println(t.toString());
    }

    // 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    // 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
    public <E> void show_3(E t){
        System.out.println(t.toString());
    }

    // 对于泛型而言，要求我传入的参数类型和使用的参数类型是一致的，但是作为参数的类型它们彼此之间是可以多样的，即使都用T表示
    public <T> void show_4(T t1, T t2){
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }
}
