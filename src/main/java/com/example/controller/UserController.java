package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.vo.UserDto;
import com.example.vo.TokenDto;
import com.example.result.Result;
import com.example.result.ResultFactory;
import com.example.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-28
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ITbUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result getVerificationCode(@RequestBody UserDto user) {
        if (!userService.login(user.getAccount(), user.getPassword())) return ResultFactory.buildFailResult("账号或密码错误。");
        boolean exists = userService.ifAllowed(user);
        StpUtil.setLoginId(user.getId());
        //返回token
        String token = StpUtil.getTokenValue();
        if (exists) return ResultFactory.buildSuccessResult(token);
        return ResultFactory.buildResult(202, "用户第一次登录成功。", token);
    }

    @PostMapping(value = "/account")
    public Result getAccount(@RequestBody TokenDto tokenDto) {
        Object loginId = StpUtil.getLoginIdByToken(tokenDto.getToken());
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        return ResultFactory.buildSuccessResult(userService.getUser(Long.valueOf(loginId.toString())).getAccount());
    }

/*    @RequestMapping(value = "/get-user", method = RequestMethod.POST)
    public Result getVerificationCode(@RequestBody TokenDto tokenDto){
        Object loginId = StpUtil.getLoginIdByToken(tokenDto.getToken());
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        return ResultFactory.buildSuccessResult(userService.getUser(Long.valueOf(loginId.toString())));
    }*/

}

