package com.lublog.gateway.realm;

import com.lublog.constant.SysConstant;
import com.lublog.po.Role;
import com.lublog.po.User;
import com.lublog.service.RoleService;
import com.lublog.service.UserService;
import com.lublog.utils.RegexpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PasswordRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("开始授权");
        User user= (User) principals.getPrimaryPrincipal();
        Set<String> roles=new HashSet<>();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        if (user == null){
            return info;
        }
        Role role=roleService.queryByRoleId(user.getRoleId());
        roles.add(role.getRoleName());
        return info;
    }

    /**
     *
     * @param token 手机号或者邮箱
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("密码方式开始认证");
        String userContact=(String)token.getPrincipal();
        User user = new User();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        if (userContact.matches(RegexpUtil.RegExp_PHONE)){
            log.info("手机");
            user = userService.queryUserByUserPhone(userContact);
            info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());

        }
        if (userContact.matches(RegexpUtil.RegExp_Mail)){
            log.info("邮箱");
            user=userService.queryUserByUserEmail(userContact);
            info=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }
        if (userContact == null || userContact.equals("")){
            throw new UnknownAccountException("账户错误");
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(SysConstant.CURRENT_USER,user);
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        log.info("info is {}", info);
        return info;
    }

    public static void main(String[] args) {
        ByteSource source = ByteSource.Util.bytes("hello".getBytes());
        System.out.println(new SimpleHash("MD5", "1234", source, 1));
    }
}
