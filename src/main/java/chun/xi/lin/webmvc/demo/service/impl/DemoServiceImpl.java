package chun.xi.lin.webmvc.demo.service.impl;

import chun.xi.lin.webmvc.annotation.MyService;
import chun.xi.lin.webmvc.demo.service.DemoService;

/**
 * Created by Lin.XiChun on 2018/7/22.
 */
@MyService
public class DemoServiceImpl implements DemoService{
    @Override
    public int getId(int id) {
        return id;
    }
}
