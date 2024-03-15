package com.fetch.common.handler;

import com.alibaba.fastjson.JSON;
import com.fetch.common.core.domain.Result;
import com.fetch.common.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/10 21:12
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 认证失败
        Result<Object> unauthorized = new Result<>(HttpStatus.UNAUTHORIZED.value(), "用户未认证");
        String json = JSON.toJSONString(unauthorized);
        WebUtils.renderString(response, json);
    }
}
