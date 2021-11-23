package com.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.result.Result;
import com.example.result.ResultFactory;
import com.example.service.ITbUserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("user-favorites")
public class UserFavoritesController {
    @Autowired(required = false)
    ITbUserFavoritesService userCollectService;
    //收藏oa
    @RequestMapping(value = "/add-to-favorites", method = RequestMethod.GET)
    public Result addToFavorites(@RequestParam String token, @RequestParam Long oaId) {
        Object loginId = StpUtil.getLoginIdByToken(token).toString();
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        int resCode = userCollectService.save(oaId, Long.valueOf(loginId.toString()));
        if (resCode == 0) return ResultFactory.buildFailResult("该 OA 已收藏。");
        if (resCode == -1) return ResultFactory.buildFailResult("OA 不存在。");
        if (resCode == -2) return ResultFactory.buildFailResult("用户不存在。");
        return ResultFactory.buildSuccessResult(null);
    }
    //取消收藏
    @RequestMapping(value = "/remove-from-favorites", method = RequestMethod.GET)
    public Result removeFromFavorites(@RequestParam String token, @RequestParam Long oaId) {
        Object loginId = StpUtil.getLoginIdByToken(token).toString();
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        int resCode = userCollectService.remove(oaId, Long.valueOf(loginId.toString()));
        if (resCode == 0) return ResultFactory.buildFailResult("该 OA 未收藏。");
        if (resCode == -1) return ResultFactory.buildFailResult("OA 不存在。");
        if (resCode == -2) return ResultFactory.buildFailResult("用户不存在。");
        return ResultFactory.buildSuccessResult(null);
    }
}

