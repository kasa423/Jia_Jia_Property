package com.fetch.web.controller;

import com.fetch.common.core.domain.Result;
import com.fetch.common.core.domain.SysUser;
import com.fetch.common.core.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:23
 */
@RestController
@RequestMapping("/verify")
public class LoginController {

    @Autowired
    private UserLoginServiceImpl loginService;

    @ResponseBody
    @PostMapping("/login")
    public Result<Object> login(@RequestBody SysUser user) {
        return loginService.login(user);
    }

    @ResponseBody
    @GetMapping("logout")
    public Result<Object> logout() {
        return loginService.logout();
    }

}
