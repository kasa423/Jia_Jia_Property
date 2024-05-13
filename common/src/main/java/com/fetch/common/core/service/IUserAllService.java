package com.fetch.common.core.service;

import com.fetch.common.core.domain.SysUser;

public interface IUserAllService {


    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    SysUser getUserInfo(Integer userId);

    /**
     * 用户注册
     * @param user
     */
    void register(SysUser user);
}
