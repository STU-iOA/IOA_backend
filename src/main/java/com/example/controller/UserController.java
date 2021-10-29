package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.generator.entity.User;
import com.example.generator.services.UserService;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-28
 */
//@Controller
@RestController
@RequestMapping("/generator/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result getVerificationCode(@RequestBody User user) {
        String account= user.getAccount();
        String password= user.getPassword();
        //这里填入判断账号密码对错的API
        boolean if_allow=true;
        //
        if (!if_allow)
            return ResultFactory.buildFailResult("错误");
        Long userId=userService.if_allow(account,password);
        //返回token
        StpUtil.setLoginId(userId);
        return ResultFactory.buildSuccessResult(StpUtil.getTokenValue());
    }
}

