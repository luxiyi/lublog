package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lublog.constant.SysConstant;
import com.lublog.po.User;
import com.lublog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: java类作用描述UserController
 * @Author: lxy
 * @time: 2020/4/7 23:55
 */
@SuppressWarnings("all")
@Controller
public class UserController {
    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public String login(String userName, String password,HttpServletRequest request){
        LOG.info("userName = {}, password = {}", userName ,password);
        String info;
        User user = userService.queryUserByUserEmail(userName);
        LOG.info("user is {}", JSON.toJSONString(user));
        if (user == null) {
            info = "提示：用户不存在，请重新输入";
            LOG.info("登录失败");
            return info;
        }
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            info = "登录成功";
            LOG.info("登录成功");
            return info;
        }catch (AuthenticationException e){
            e.printStackTrace();
            info = "提示：密码错误，请重新输入";
            LOG.info("登录失败");
            return info;
        }
    }



    // 注册功能
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(HttpSession session, String info, String userName, String password, String confirm, String userPhone) {
        LOG.info("userName = {}, password = {}, confirm = {}, userPhone = {}", userName ,password, confirm, userPhone);
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(confirm)
                || StringUtils.isBlank(userPhone)) {
            LOG.info("不能为空");
            info="注册失败,请重新输入";
            return info;
        }

        // 确认密码
        if (!password.equals(confirm) ) {
            LOG.info("注册失败，密码不一样");
            info="注册失败,请重新输入";
            return info;
        }
        // 如果已经存在
        User exitUser = userService.queryUserByUserName(userName);
        LOG.info("exitUser = {}", exitUser);
        if (exitUser == null) {
            LOG.info("exitUser = {}", JSON.toJSONString(exitUser));
            userService.insertUser(userName, password, userPhone);
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            user.setUserPhone(userPhone);
            session.setAttribute("user", user);
            LOG.info("注册成功");
            info="注册成功";
            return info;
        } else {
            LOG.info("注册失败");
            info="注册失败,请重新输入";
            return info;
        }

    }

    // 展现个人信息
//    @RequestMapping(value = "queryUser", method = RequestMethod.POST)
//    @ResponseBody
//    public User queryUser(HttpSession session, LoginUser user, User userinfo) {
//        user = (User) session.getAttribute("user");
//        LOG.info("admin = {}", user);
//        userinfo = userService.findUserByluser(user.getLuser());
//        session.setAttribute("userinfo", userinfo);
//        LOG.info("userinfo= {}", JSON.toJSONString(userinfo));
//        return userinfo;
//    }


    // 修改个人信息
//    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
//    @ResponseBody
//    public String updateUser(String info, LoginUser user, User userinfo, MultipartFile userhead, HttpServletRequest request, HttpSession session) throws IllegalStateException, IOException {
//        // 得到已经修改的信息
//        // 判断非空
//        if (userinfo == null) {
//            LOG.info("不能为空");
//            info="修改失败,请重新输入";
//            return info;
//        }
//        //获取文件的名字
//        String fileName = userhead.getOriginalFilename();
//        //获取文件类型
//        String filetype=userhead.getContentType();
//        LOG.info("fileName endsWith jpg is {}", fileName.endsWith(".jpg"));
//        LOG.info("fileName = {}, filetype = {}", fileName, filetype);
//        //获取本地保存文件的路径,保存到eclipse的项目中图片文件夹里
//        String path = request.getServletContext().getRealPath("/img");
//        //保存在D:\ruanjiananzhuang\eclipse\workspace\.metadata\.plugins
//        //  \org.eclipse.wst.server.core\tmp0\wtpwebapps\books\
//        LOG.info("path = {}", path);
//        File file = new File(path);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        //将文件放到文件夹
//        fileName = UUID.randomUUID().toString()+fileName;
//        //文件新路径
//        path = path+File.separator+fileName;
//        file = new File(path);
//        try {
//            userhead.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //自动创建文件img、写入数据
//        //将用户头像的名字更新成此次创建的文件名字
//        //todo 将路径加上 filename改为path，就不在前端显示路径
//        user=(LoginUser)session.getAttribute("admin");
//        userService.updataUsericon(path, user.getLuser());
//        // 通过传过来的bid判断需要更新的信息是否存在
//        User isexit = userService.findUserByluser(user.getLuser());
//        // 不存在，就更新
//        if (isexit != null) {
//            userService.updateUser(userinfo);
//            info="修改成功";
//            //重点==============手动更新，将原来的修改为现在的名字
//            userinfo.setUserIcon(fileName);
//            //再设置到属性中，手动更新
//            session.setAttribute("userinfo", userinfo);
//            return info;
//        } else {
//            info="修改失败,请重新输入";
//            LOG.info("失败");
//            return info;
//        }
//    }

//    @RequestMapping(value = "/find", method = RequestMethod.GET)
//    @ResponseBody
//    public LoginUser testUser (String luser){
//        LoginUser user = userService.queryUserByUserName(luser);
//        LOG.info("admin = {}, luser = {}", JSON.toJSON(user), luser);
//        if(user == null){
//            user = new LoginUser();
//        }
//        return user;
//    }

    // 注销功能
    @RequestMapping(value ="admin/logout", method = RequestMethod.GET)
    public String loginout(HttpSession session) throws Exception {
        LOG.info("logout");
        Object obj=session.getAttribute(SysConstant.CURRENT_USER);
        if (obj == null) {
            return "admin/login";
        }else {
            session.invalidate();
            LOG.info("注销成功");
            return  "admin/login";
        }
    }


}
