package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer uid;

    private String luser;

    private String uname;

    private String phone;

    private String age;

    private String sex;

    private String addr;

    private String usericon;

    private Integer flag;

    public User() {
    }

    public User(Integer uid, String luser, String uname, String phone, String age, String sex, String addr, String usericon, Integer flag) {
        this.uid = uid;
        this.luser = luser;
        this.uname = uname;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.addr = addr;
        this.usericon = usericon;
        this.flag = flag;
    }

}