package chun.xi.lin.webmvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Lin.XiChun on 2018/7/22.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
