package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.generator.services.UserCollectServer;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/generator/userCollect")
public class UserCollectController {
    @Autowired(required = false)
    UserCollectServer userCollectServer;
    //收藏oa
    @RequestMapping(value = "/collectOA", method = RequestMethod.GET)
    public Result collectOA(@RequestParam String token, @RequestParam Long oaId){
        userCollectServer.insertUserCollect(oaId,Long.valueOf(StpUtil.getLoginIdByToken(token).toString()));
        return ResultFactory.buildSuccessResult(null);
    }
    //取消收藏
    @RequestMapping(value = "/delectCollectOA", method = RequestMethod.GET)
    public Result delectCollectOA(@RequestParam String token, @RequestParam Long oaId){
        Long userId=Long.valueOf(StpUtil.getLoginIdByToken(token).toString());
        userCollectServer.delectUserCollect(oaId,userId);
        return ResultFactory.buildSuccessResult(null);
    }
}

