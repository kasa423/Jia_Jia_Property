package com.fetch.common.filter;

import com.alibaba.fastjson.parser.ParserConfig;
import com.fetch.common.constants.RedisKeyConstants;
import com.fetch.common.core.domain.LoginUser;
import com.fetch.common.core.redis.RedisCache;
import com.fetch.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;

/**
 * @author jiang chen
 * @ClassName JwtAuthenticationTokenFilter
 * @date 2024/02/29 15:15
 * @description: TODO
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token 获取其中的userId
        String userId;
        try {
            Claims claims = JwtUtils.parseJWT(token);
            userId = claims.getId();
        } catch (Exception e) {
            throw new RuntimeException("token 过期");
        }

        String redisKey = RedisKeyConstants.USER_LOGIN_KEY + userId;
        // 从redis 中获取用户信息
        LoginUser loginUser = (LoginUser) redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        // 存入SecurityContextHolder
        // todo 权限信息

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
