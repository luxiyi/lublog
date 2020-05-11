package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {

  private static final long serialVersionUID = -4747060271344691281L;

  private String luser;

  private String pass;

  private String confirm;

  private Integer flag;

  public LoginUser() {
  }

  public LoginUser(String luser, String pass, String confirm, Integer flag) {
      this.luser = luser;
      this.pass = pass;
      this.confirm = confirm;
      this.flag = flag;
  }

}