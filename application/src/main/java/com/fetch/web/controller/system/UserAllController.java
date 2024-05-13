package com.fetch.web.controller.system;

import com.fetch.common.core.domain.Result;
import com.fetch.common.core.domain.SysUser;
import com.fetch.common.core.service.IUserAllService;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/12 15:23
 */
@RestController
@RequestMapping("/user")
public class UserAllController {
    private final IUserAllService userService;

    public UserAllController(IUserAllService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<String> register(SysUser user) {
        userService.register(user);
        return new Result<String>().success("注册成功");
    }

    @GetMapping("/info/{userId}")
    public Result<SysUser> getUserInfo(@PathVariable Integer userId) {
        SysUser userInfo = userService.getUserInfo(userId);
        return new Result<SysUser>().success(userInfo);
    }
}
