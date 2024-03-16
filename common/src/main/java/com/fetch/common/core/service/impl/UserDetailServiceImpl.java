package com.fetch.common.core.service.impl;

import com.fetch.common.core.domain.LoginUser;
import com.fetch.common.core.domain.SysUser;
import com.fetch.common.core.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:28
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUser = userMapper.selectUserByUsername(username);
        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> roles = userMapper.selectRolePermissionByUserId(sysUser.getId());
        return new LoginUser(sysUser, null);
    }
}
