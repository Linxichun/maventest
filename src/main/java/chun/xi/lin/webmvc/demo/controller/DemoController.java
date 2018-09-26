package chun.xi.lin.webmvc.demo.controller;

import chun.xi.lin.webmvc.annotation.MyAutowired;
import chun.xi.lin.webmvc.annotation.MyController;
import chun.xi.lin.webmvc.annotation.MyRequestMapping;
import chun.xi.lin.webmvc.annotation.MyRequestParam;
import chun.xi.lin.webmvc.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lin.XiChun on 2018/7/22.
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired
    private DemoService demoService;

    @MyRequestMapping("/getId")
    public int getId(
            HttpServletRequest req,
            HttpServletResponse res,
            @MyRequestParam("parmId")int parmId){
        return demoService.getId(parmId);
    }

    @MyRequestMapping("/addId")
    public void addId(
            HttpServletRequest req,
            HttpServletResponse res,
            @MyRequestParam("parmId")int parmId){

    }
}
