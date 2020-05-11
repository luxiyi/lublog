package com.lublog.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource({"classpath:lublog-provider.xml"})
@MapperScan("com.lublog.provider.dao")
public class ProviderApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ProviderApplication.class);
        SpringApplication.run(ProviderApplication.class, args);
        logger.info("服务端启动成功！！！");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
