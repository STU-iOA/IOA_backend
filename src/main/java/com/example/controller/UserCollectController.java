package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.generator.entity.UserCollect;
import com.example.generator.services.UserCollectServer;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-29
 */
@Controller
@RequestMapping("/generator/userCollect")
public class UserCollectController {
    @Autowired(required = false)
    UserCollectServer userCollectServer;
    @RequestMapping(value = "/collectOA", method = RequestMethod.GET)
    public Result collectOA(@RequestParam String token, @RequestParam Long oaId){
        UserCollect userCollect=userCollectServer.insertUserCollect(oaId,Long.valueOf(StpUtil.getLoginIdByToken(token).toString()));
        return ResultFactory.buildSuccessResult(userCollect);
    }

}

