package com.alibaba.dubbo.demo;

import javax.annotation.PostConstruct;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ModuleConfig;
import org.apache.dubbo.config.MonitorConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfigBinding;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangrongchao on 2018/7/9.
 * @version 1.0
 */
//@EnableDubboConfig
@Configuration
public class DubboAutoConfiguration {

    @ConfigurationProperties(prefix = "dubbo.application")
    @Bean
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    ModuleConfig moduleConfig;

    @Bean
    @ConfigurationProperties("dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }

    @PostConstruct
    public void init() {
        System.out.println(registryConfig());
    }

    ProtocolConfig protocolConfig;

    MonitorConfig monitorConfig;

    ProviderConfig providerConfig;

    ConsumerConfig consumerConfig;
}
