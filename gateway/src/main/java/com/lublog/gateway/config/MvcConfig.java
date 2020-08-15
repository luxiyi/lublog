package com.lublog.gateway.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.lublog.gateway.interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer{
    //注册一个视图控制器



    //负责展示视图
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.addUrlMappings("/druid/*");
        Map init = new HashMap();
        init.put("allow","127.0.0.1");
        init.put("allow","116.62.180.224");
        bean.setInitParameters(init);
        bean.setServlet(servlet);
        return bean;
    }
    //WebStatFilter负责收集数据
    @Bean
    public FilterRegistrationBean registrationBean(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        Map init = new HashMap();
        init.put("exclusions ","*.js,*.png,*.css,*.jpg,*.gif");
        registrationBean.setFilter(webStatFilter);
        registrationBean.setInitParameters(init);//设置初始化参数
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
