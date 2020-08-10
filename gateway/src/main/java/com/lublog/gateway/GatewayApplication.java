package com.lublog.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ImportResource("classpath:lublog-consumer.xml")
public class GatewayApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
        SpringApplication.run(GatewayApplication.class, args);
        logger.info("消费端启动成功！！！");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/File/**")
                .addResourceLocations("file:File/");
    }
}
