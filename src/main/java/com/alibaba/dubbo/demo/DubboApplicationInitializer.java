package com.alibaba.dubbo.demo;

import org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.apache.dubbo.config.spring.util.BeanRegistrar;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huangrongchao on 2018/7/9.
 * @version 1.0
 */
public class DubboApplicationInitializer implements ApplicationContextInitializer{

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        BeanRegistrar.registerInfrastructureBean((BeanDefinitionRegistry) context,
                ReferenceAnnotationBeanPostProcessor.BEAN_NAME, ReferenceAnnotationBeanPostProcessor.class);
    }
}
