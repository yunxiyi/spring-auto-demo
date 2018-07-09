package com.finup.controller;

import org.apache.dubbo.demo.DemoService;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangrongchao on 2018/4/24.
 * @version 1.0
 */
@Controller("/data")
public class DataController {

    @Reference(check = false)
    DemoService demoService;

    @PostConstruct
    public void init() {
        demoService.sayHello("hello world");
    }

    @RequestMapping("/info")
    @ResponseBody
    public Object info() {
        Map<String, Object> map = new HashMap<>();

        map.put("message", "134");
        return map;
    }

}
