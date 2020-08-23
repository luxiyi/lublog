package com.lublog.gateway.interceptor;


import com.lublog.constant.SysConstant;
import com.lublog.po.User;
import com.lublog.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private static final String USER_AGENT = "user-agent";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器");
        // 请求uri
        String uri= request.getRequestURI();
        // 获取访问用户的信息
        log.debug("UserAgent: {}", request.getHeader(USER_AGENT));
        // 获取来访用户的IP地址
        log.debug("用户访问地址: {}, 来路地址: {}", uri, IPUtils.getIpAddrByRequest(request));
        if (uri.endsWith("index.html") || uri.endsWith("index") || !(uri.startsWith("/admin")) ||
                uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") ||
                uri.endsWith(".png") || uri.endsWith(".gif")|| uri.endsWith("/druid/*") ){
            return true;
        }
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute(SysConstant.CURRENT_USER);
        if (user == null){
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }

}
