package com.finup.config;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;

/**
 * @author huangrongchao on 2018/4/24.
 * @version 1.0
 */
@Configuration
public class BeanConfiguration {

    @Autowired
    ErrorPageRegistry tomcatReactiveWebServerFactory;

    @PostConstruct
    public void init() {
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errors/401.html");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errors/404.html");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/500.html");
        tomcatReactiveWebServerFactory.addErrorPages(error401Page, error404Page, error500Page);
    }

//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new RewriteFilter());//注册rewrite过滤器
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter(RewriteFilter.REWRITE_TO, "/index.html");
//        registration.addInitParameter(RewriteFilter.REWRITE_PATTERNS, "/ui/*");
//        registration.setName("rewriteFilter");
//        registration.setOrder(1);
//        return registration;
//    }

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @PostConstruct
    public void dispatcherServlet() {
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
//        handlerMappings.size();
    }

}
