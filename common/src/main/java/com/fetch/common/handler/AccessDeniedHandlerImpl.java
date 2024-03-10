package com.fetch.common.handler;

import com.alibaba.fastjson.JSON;
import com.fetch.common.core.domain.Result;
import com.fetch.common.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/10 20:49
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 处理权限不足
        Result<Object> permissionDenied = new Result<>(HttpStatus.FORBIDDEN.value(), "权限不足，请联系管理员");
        String jsonString = JSON.toJSONString(permissionDenied);
        WebUtils.renderString(response, jsonString);
    }
}
