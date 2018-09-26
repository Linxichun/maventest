package chun.xi.lin.webmvc.servlet;

import chun.xi.lin.webmvc.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 优化版DispatcherServlet
 * Created by Lin.XiChun on 2018/7/22.
 */
public class MyOpDispatcherServlet extends HttpServlet{

   /* private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<String>();

    // 定义IOC容器
    private Map<String,Object> ioc = new HashMap<String,Object>();

    // 定义处理器映射器
//    private Map<String,Method> handlerMapping = new HashMap<String,Method>();

    // 定义处理器映射器--【优化版】
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 6、调用自己写的doDispatcher
        try {
            doDispatcher(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 堆栈异常也写到响应对象里面
            resp.getWriter().write(e.getStackTrace().toString());
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 执行反射
        // 得到相对路径
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        //
        if(!handlerMapping.containsKey(url)){   // 不存在url的话就404错误
            resp.getWriter().write("404 Not Found！！");
            return;
        }
        // 若存在，则获取handMapping中的对应方法
        Method method = this.handlerMapping.get(url);
//        // 从url中获取参数
//        Map<String, String[]> params = req.getParameterMap();
//        // 获取beanName
//        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
//        method.invoke(ioc.get(beanName), );
        System.out.println(method);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        // 2、扫描到所有相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        // 3、初始化刚刚扫描到的类，并且将其存入到IOC容器中
        doInstance();
        // 4、自动注入
        doAutowired();
        // 5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("My Spring MVC is init ");
    }

    private void initHandlerMapping() {
        // url和method进行关联
        if(ioc.isEmpty()){return;}  // 如果为空，直接返回
        for (Map.Entry<String,Object> entry: ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            // 由于我的url是Controller里面取的
            if(!clazz.isAnnotationPresent(MyController.class)){return;} // 不是MyController就返回

            String baseUrl = "";
            if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();   // 获取Controller类上的RequestMapping值
            }

            // 由于Spring只认public的方法，所以采用getMethods
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(MyRequestMapping.class)){continue;}  // 没用MyRequestMapping注解的话直接过

                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                // 拼上MyRequestMapping注解方法上的value（url）,处理多斜杠转成单斜杠
                String url = (baseUrl + "/" +requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("Mapped："+url+","+method);
            }
            
        }
    }

    // 注入
    private void doAutowired() {
        // 如果ioc容器为空，直接返回
        if(ioc.isEmpty()){ return;}

        for (Map.Entry<String, Object> entry: ioc.entrySet()) { // 遍历IOC容器里面每个对象
            // 注入，无非就是自动给属性赋值
            Field[] fields = entry.getValue().getClass().getDeclaredFields();   // 获取这些对象的每个字段
            for (Field field : fields) {
                if(!field.isAnnotationPresent(MyAutowired.class)) {  // 没加MyAutowired注解，直接返回
                    continue;
                }
                // 如果你的字段加了这个注解
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                String beanName = autowired.value();    // 自定义的注解value
                if("".equals(beanName.trim())){
                    beanName = field.getType().getName();
                }
                // 设置字段可访问
                field.setAccessible(true);
                try {
                    // 向对象（参数1）的这个字段（filed）设置新值（参数2）
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }

            }
        }
    }

    // ioc容器初始化
    private void doInstance() {
        if(classNames.isEmpty()){ return; }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                // 利用反射机制把这个对象加载出来
                if(clazz.isAnnotationPresent(MyController.class)){ // MyController注解的
                    Object instance = clazz.newInstance();
                    // IOC容器key为类名，类名的首字母小写
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(MyService.class)){ // MyService注解的
                    // 1、默认的类名首字母小写
                    // 2、如果是自定义的beanName，我们会优先使用自定义的
                    MyService service = clazz.getAnnotation(MyService.class);
                    // 获取自定义的注解值value
                    String beanName = service.value();
                    if("".equals(beanName)){
                        // 如果没设置，则使用自己的类名（首字母小写）作为IOC容器的key
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);

                    // 3、如果写的是接口，自动注入它的实现类
                    //  返回直接实现的接口
                    Class<?>[] interfaces=clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(i.getName(),instance);
                    }
                }else{
                    // 直接忽略
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String lowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().
                getResource("/" + scanPackage.replaceAll("\\.", "/"));

//        File classPathDir = new File(url.toString().replaceAll("%20"," "));
        File classPathDir = new File("D:/Program Files/Apache/apache-tomcat-8.0.51/webapps/ROOT/WEB-INF/classes/chun/xi/lin/webmvc/demo/");
        for (File file: classPathDir.listFiles()){
            if(file.isDirectory()){ // 如果是目录，则递归扫描
                doScanner(scanPackage+"."+file.getName());
            }else{
                String className = scanPackage + "" + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=contextConfig){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*//******************************************** 方法改版 ***********************************************

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try{
            Handler handler = getHandler(req);
            if(null==handler){
                // 没有匹配上，返回404错误
                resp.getWriter().write("404 Not Found！！");
                return;
            }

            // 获取方法的参数列表
            Class<?>[] parameterTypes = handler.method.getParameterTypes();
            // 定义数组保存所有需要自动赋值的参数值
            Object[] paramValues = new Object[parameterTypes.length];

            Map<String, String[]> params = req.getParameterMap();
            for (Map.Entry<String,String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "")
                        .replaceAll(",\\s", ",");
                // 如果找到匹配的对象，则开始填充参数
                if(!handler.parmIndexMapping.containsKey(param.getKey())){continue;}
                int index = handler.parmIndexMapping.get(param.getKey());
                paramValues[index]=convert(parameterTypes[index],value);
            }

            // 设置方法的request和response对象
            Integer reqIndex = handler.parmIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
            Integer repsIndex = handler.parmIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[repsIndex] = resp;

            // 反射执行方法
            handler.method.invoke(handler.controller,paramValues);
        }catch (Exception e){
            throw e;
        }


    }

    private Handler getHandler(HttpServletRequest req){
        if(handlerMapping.isEmpty()){ return null; }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (Handler handler : handlerMapping) {
            try {
                Matcher matcher = handler.pattern.matcher(url);
                if(!matcher.matches()){ continue; }
                return handler;
            }catch (Exception e){
                throw e;
            }
        }
        return null;
    }

    private Object convert(Class<?> type, String value){
        if(Integer.class == type){
            return Integer.valueOf(value);
        }
        return value;
    }

    private class Handler{

        protected Object controller; // 保存方法对应的实例
        protected Method method;    // 保存映射的方法
        protected Pattern pattern;
        protected Map<String,Integer> parmIndexMapping; // 参数顺序

        *//**
         * 构造一个Handler的基本参数
         * *//*
        protected Handler(Pattern pattern, Object controller, Method method){
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;

            parmIndexMapping = new HashMap<String, Integer>();
            putParmIndexMapping(method);
        }

        private void putParmIndexMapping(Method method) {
            // 提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if(a instanceof MyRequestParam){
                        String paramName = ((MyRequestParam)a).value();
                        if(!"".equals(paramName.trim())){
                            parmIndexMapping.put(paramName,i);
                        }
                    }
                }
            }

            // 提取方法中的request和response参数
            Class<?>[] paramsTypes = method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length; i++) {
                Class<?> type = paramsTypes[i];
                if(type==HttpServletRequest.class || type==HttpServletResponse.class){
                    parmIndexMapping.put(type.getName(), i);
                }
            }
        }
    }*/
}
