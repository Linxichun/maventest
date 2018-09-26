package chun.xi.lin.proxy.cglib;

/**
 * Created by Lin.XiChun on 2018/7/27.
 */
public class BookFacadeCglibTest {
    public static void main(String[] args) {
        BookFacadeImpl1 bookFacade=new BookFacadeImpl1();
        BookFacadeCglib  cglib=new BookFacadeCglib();
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(bookFacade);
        bookCglib.addBook();
    }
}
