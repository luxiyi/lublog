package com.lublog.gateway.realm;

import com.lublog.constant.SysConstant;
import com.lublog.po.User;
import com.lublog.service.AuthService;
import com.lublog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
public class CustomRealm {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AuthService authService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        User user= (User) principals.getPrimaryPrincipal();
//        List<String> perms=authService.findPermsByUserName(user.getUserName());
//        Set set=new HashSet(perms);
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.setStringPermissions(set);
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String userName=(String)token.getPrincipal();
//        log.info(userName);
//        User user=userService.(userName);
//        if (user == null){
//            throw new UnknownAccountException("账户不存在");
//        }
//        Session session = SecurityUtils.getSubject().getSession();
//        session.setAttribute(SysConstant.CURRENT_USER,user);
//        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
//        System.out.println(info+"___________________________________");
//        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
//
//        return info;
//    }
}
