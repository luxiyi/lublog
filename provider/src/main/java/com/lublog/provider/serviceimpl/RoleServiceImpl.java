package com.lublog.provider.serviceimpl;

import com.lublog.po.Role;
import com.lublog.provider.dao.RoleMapper;
import com.lublog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述RoleServiceImpl
 * @Author: lxy
 * @time: 2020/8/13 0:02
 */
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role queryByRoleId(Integer roleId) {
        return roleMapper.queryByRoleId(roleId);
    }
}
