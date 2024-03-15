package com.fetch.common.core.service.impl;

import com.fetch.common.constants.RedisKeyConstants;
import com.fetch.common.core.domain.LoginUser;
import com.fetch.common.core.domain.Result;
import com.fetch.common.core.domain.SysUser;
import com.fetch.common.core.mapper.UserMapper;
import com.fetch.common.core.redis.RedisCache;
import com.fetch.common.core.service.IUserLoginService;
import com.fetch.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:54
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {

    @Resource
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Result<Object> login(SysUser user) {
        Result<Object> result = new Result<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        }catch(Exception e){
            // 认证失败
            result.failed(HttpStatus.UNAUTHORIZED.value(),"账户或密码有误");
            return result;
        }
        if (!Objects.isNull(authentication)){
            // 认证成功, 需要返回一个 token
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String username = loginUser.getUsername();
            String token = JwtUtils.createJWT(userId, username,null);
            Map<String, String> map = new HashMap<>(1);
            map.put(RedisKeyConstants.USER_LOGIN_KEY, token);
            redisCache.setCacheObject(RedisKeyConstants.USER_LOGIN_KEY+userId, loginUser);
            return result.success(map);
        }
        // 失败，未知异常
        return result.failed("登录失败，未知异常");
    }

    @Override
    public Result<Object> logout() {
        Result<Object> result = new Result<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser= (LoginUser) authentication.getPrincipal();
            String id = loginUser.getUser().getId().toString();
            redisCache.deleteObject(RedisKeyConstants.USER_LOGIN_KEY+id);
            return result.success();
        }catch (Exception e){
            return result.failed("退出登录失败");
        }
    }
}
