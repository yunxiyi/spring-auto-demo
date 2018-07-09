package com.finup.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author huangrongchao on 2018/4/24.
 * @version 1.0
 */
public class DispatcherServletBean extends DispatcherServlet {

    @Override
    protected void onRefresh(ApplicationContext context) {
        initStrategies(context);
    }

}
