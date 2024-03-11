package com.fetch.web.controller;

import com.fetch.common.core.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:23
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("")
    public Result<Object> login(String username, String password){
        retrun user
    }
}
