package com.lublog.gateway.interceptor;


import com.lublog.po.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
public class AuthInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("拦截器");
//        String uri= request.getRequestURI();
//        System.out.println(uri);
//        if (uri.endsWith("login.html") || uri.endsWith("login") ||
//        uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") ||
//        uri.endsWith(".png") || uri.endsWith(".gif")|| uri.endsWith("/druid/*")){
//            return true;
//        }
//        HttpSession session=request.getSession();
//        User user= (User) session.getAttribute("currentUser");
//        if (user == null){
//            response.sendRedirect("/login.html");
//            return false;
//        }
//        return true;
//    }

}
