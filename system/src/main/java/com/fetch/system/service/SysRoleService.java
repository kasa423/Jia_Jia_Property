package com.fetch.system.service;

import com.fetch.system.domain.SysRole;

import java.util.List;

public interface SysRoleService {


    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Integer> queryRoleIdList(Integer userId);


    void assignRoles(Integer userId, Integer roleId);

    List<SysRole> queryRoleList(Integer userId);
}
