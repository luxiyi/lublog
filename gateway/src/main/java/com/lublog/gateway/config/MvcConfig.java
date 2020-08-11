package com.lublog.gateway.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MvcConfig {
    //注册一个视图控制器
//    @Autowired
//    private AuthInterceptor authInterceptor;

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login.html");
//        registry.addViewController("/main").setViewName("main");
//        registry.addViewController("/role/manager").setViewName("role_manager");
//        registry.addViewController("/auth/manager").setViewName("auth_manager");
//        registry.addViewController("/user/manager").setViewName("user_manager");
//        registry.addViewController("/403.html").setViewName("403");
//    }
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        InterceptorRegistration registration = registry.addInterceptor(authInterceptor);
////        registration.addPathPatterns("/**");
////        registration.order(1);
////    }
//
//    @Bean
//    public DataSourceTransactionManager manager(DataSource dataSource){
//        DataSourceTransactionManager manager= new DataSourceTransactionManager();
//        manager.setDataSource(dataSource);
//        return manager;
//    }
//
//    //负责展示视图
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(){
//        StatViewServlet servlet = new StatViewServlet();
//        ServletRegistrationBean bean = new ServletRegistrationBean();
//        bean.addUrlMappings("/druid/*");
//        Map init = new HashMap();
////        init.put("loginUsername","hello");
////        init.put("loginPassword","123");
//        init.put("allow","127.0.0.1");
//        bean.setInitParameters(init);
//        bean.setServlet(servlet);
//        return bean;
//    }
//    //WebStatFilter负责收集数据
//    @Bean
//    public FilterRegistrationBean registrationBean(){
//        WebStatFilter webStatFilter = new WebStatFilter();
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        Map init = new HashMap();
//        init.put("exclusions ","*.js,*.png,*.css,*.jpg,*.gif");
//        registrationBean.setFilter(webStatFilter);
//        registrationBean.setInitParameters(init);//设置初始化参数
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
}
