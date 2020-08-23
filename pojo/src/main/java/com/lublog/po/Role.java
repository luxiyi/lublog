package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "t_role")
public class Role {
    private Integer roleId;

    private String roleName;

    private String authIds;

    private String roleDescription;

    private Integer roleFlag;

    public Role() {
    }
}