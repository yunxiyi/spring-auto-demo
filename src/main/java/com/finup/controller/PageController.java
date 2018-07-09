package com.finup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangrongchao on 2018/4/24.
 * @version 1.0
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
