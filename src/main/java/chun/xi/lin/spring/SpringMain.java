package chun.xi.lin.spring;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.Writer;

/**
 * Created by Lin.XiChun on 2018/6/18.
 */
public class SpringMain {
    public static void main(String[] args) {
        String str = "1.0.0";
        System.out.println(str.endsWith(".0"));
        System.out.println(str.replaceAll("(.0)$", ""));
    }
}
