package com.fetch.system.service.impl;

import com.fetch.system.domain.SysRole;
import com.fetch.system.mapper.SysRoleMapper;
import com.fetch.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/11 20:00
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper roleMapper;

    public SysRoleServiceImpl(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        return roleMapper.queryRoleIdList(userId);
    }

    @Override
    public void assignRoles(Integer userId, Integer roleId) {
        roleMapper.associateUserWithRoles(userId, new Integer[]{roleId});
    }

    @Override
    public List<SysRole> queryRoleList(Integer userId) {
        return roleMapper.queryRoleList(userId);
    }
}
