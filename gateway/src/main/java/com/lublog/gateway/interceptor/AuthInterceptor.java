package com.lublog.gateway.interceptor;


import com.lublog.constant.SysConstant;
import com.lublog.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器");
        String uri= request.getRequestURI();
        log.info(uri);
        if (uri.endsWith("index.html") || uri.endsWith("index") ||
                uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") ||
                uri.endsWith(".png") || uri.endsWith(".gif")|| uri.endsWith("/druid/*")){
            return true;
        }
//        HttpSession session=request.getSession();
//        User user= (User) session.getAttribute(SysConstant.CURRENT_USER);
//        if (user == null){
//            response.sendRedirect("front/index.html");
//            return false;
//        }
        return true;
    }

}
