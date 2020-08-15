package com.lublog.provider.dao;

import com.lublog.po.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
    @Select("select * from t_role where role_id = #{param1} and role_flag = 0")
    Role queryByRoleId(Integer roleId);

    Role queryByRoleName(String roleName);
}