package com.lublog.gateway.config;


import com.lublog.gateway.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig {
    //配置过滤器
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
//        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
//        filterFactoryBean.setSecurityManager(securityManager);
//        Map map=new LinkedHashMap();
//        map.put("/login.html","anon");
//        map.put("login","anon");
//        map.put("*.css","anon");
//        map.put("*.js","anon");
//        map.put("/*","authc");
//        map.put("/logout","logout");
//        filterFactoryBean.setFilterChainDefinitionMap(map);
//        filterFactoryBean.setLoginUrl("login.html");
//        filterFactoryBean.setUnauthorizedUrl("403.html");
//        return filterFactoryBean;
//    }
//    //配置安全管理器
//    @Bean
//    public SecurityManager securityManager(CustomRealm realm){
//        DefaultWebSecurityManager webSecurityManager=new DefaultWebSecurityManager();
//        webSecurityManager.setRealm(realm);
//        return webSecurityManager;
//    }
//
//    //配置自定义域
//    @Bean
//    public CustomRealm customRealm(HashedCredentialsMatcher matcher, MemoryConstrainedCacheManager memoryConstrainedCacheManager){
//        CustomRealm realm=new CustomRealm();
//        realm.setCredentialsMatcher(matcher);
//        realm.setCacheManager(memoryConstrainedCacheManager);
//        return realm;
//    }
//    //密码匹配器
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(2);
//        return matcher;
//    }
//
//    //整合thymeleaf
//    @Bean
//    public ShiroDialect shiroDialect(){
//        ShiroDialect dialect=new ShiroDialect();
//        return dialect;
//    }
//
//    //针对类使用aop代理，因为shiro使用注解方式实现对请求进行验证
//    @Bean
//    public DefaultAdvisorAutoProxyCreator proxyCreator(){
//        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//    //注解生效通知
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
//        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(manager);
//        return advisor;
//    }
//    //缓存管理
//    @Bean
//    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
//        MemoryConstrainedCacheManager memoryConstrainedCacheManager=new MemoryConstrainedCacheManager();
//        return memoryConstrainedCacheManager;
//    }

}
