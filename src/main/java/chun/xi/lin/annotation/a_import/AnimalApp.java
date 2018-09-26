package chun.xi.lin.annotation.a_import;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by Lin.XiChun on 2018/7/24.
 */
@SpringBootApplication
@ComponentScan
@Import({Dog.class,Cat.class})
// 还可以引入一个配置类@Import({MyAnimalConfig.class})
public class AnimalApp {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(AnimalApp.class, args);
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Cat.class));
        context.close();
    }
}
