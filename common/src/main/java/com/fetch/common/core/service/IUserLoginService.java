package com.fetch.common.core.service;

import com.fetch.common.core.domain.Result;
import com.fetch.common.core.domain.SysUser;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:28
 */
public interface IUserLoginService {

    /**
     * 用户登录
     *
     * @return
     */
    Result<Object> login(SysUser user);

    Result<Object> logout();
}
