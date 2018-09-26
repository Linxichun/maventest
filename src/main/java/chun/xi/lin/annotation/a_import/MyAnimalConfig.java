package chun.xi.lin.annotation.a_import;

import org.springframework.context.annotation.Bean;

/**
 * Created by Lin.XiChun on 2018/7/24.
 */
public class MyAnimalConfig {

    @Bean
    public Dog getDog(){
        return new Dog();
    }

    @Bean
    public Cat getCat(){
        return new Cat();
    }
}
