package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="t_student")
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String password;

    private String nickName;

    private String userPhone;

    private String age;

    private String sex;

    private String userAddr;

    private String userIcon;

    private Integer flag;

    public User() {
    }

//    public User(Integer uid, String luser, String uname, String phone, String age, String sex, String addr, String usericon, Integer flag) {
//        this.uid = uid;
//        this.luser = luser;
//        this.uname = uname;
//        this.phone = phone;
//        this.age = age;
//        this.sex = sex;
//        this.addr = addr;
//        this.usericon = usericon;
//        this.flag = flag;
//    }


    public User(Integer userId, String userName, String password, String nickName, String userPhone, String age, String sex, String userAddr, String userIcon, Integer flag) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.userPhone = userPhone;
        this.age = age;
        this.sex = sex;
        this.userAddr = userAddr;
        this.userIcon = userIcon;
        this.flag = flag;
    }
}