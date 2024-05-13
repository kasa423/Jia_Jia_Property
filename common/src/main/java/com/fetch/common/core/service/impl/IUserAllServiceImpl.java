package com.fetch.common.core.service.impl;

import com.fetch.common.core.domain.SysUser;
import com.fetch.common.core.mapper.UserMapper;
import com.fetch.common.core.service.IUserAllService;
import com.fetch.common.exception.IllegalParameterException;
import com.fetch.system.mapper.SysMenuMapper;
import com.fetch.system.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/12 15:26
 */
@Service
public class IUserAllServiceImpl implements IUserAllService {

    private final UserMapper userMapper;

    private final SysRoleMapper roleMapper;

    private final SysMenuMapper menuMapper;

    public IUserAllServiceImpl(UserMapper userMapper, SysRoleMapper roleMapper, SysMenuMapper menuMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public SysUser getUserInfo(Integer userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public void register(SysUser user) {
        SysUser sysUser = userMapper.selectUserByUsername(user.getUsername());
        if (Objects.nonNull(sysUser)){
            throw new IllegalParameterException("用户名已存在");
        }
        userMapper.register(user);
        Integer userId = user.getId();
        // 注册成功后，分配默认角色
        roleMapper.associateUserWithRoles(userId, new Integer[]{1});
    }
}
