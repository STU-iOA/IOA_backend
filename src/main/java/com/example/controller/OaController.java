package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.vo.OaListDto;
import com.example.entity.TbOa;
import com.example.entity.TbUserFavorites;
import com.example.result.Result;
import com.example.result.ResultFactory;
import com.example.service.ITbOaService;
import com.example.service.ITbUserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-29
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/oa")
public class OaController {
    @Autowired
    private ITbOaService oaService;
    @Autowired(required = false)
    private ITbUserFavoritesService userCollectService;

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result geDetails(@RequestParam Long OAId){
        TbOa oa = oaService.getOa(OAId);
        if (oa == null) return ResultFactory.buildFailResult("OA 不存在。");
        return ResultFactory.buildSuccessResult(oa);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result getList(@RequestParam Long page, @RequestParam Long size, @RequestParam(defaultValue="",required = false) String str,
                         @RequestParam(defaultValue = "1") boolean order){
        OaListDto oaListDto=oaService.oaList2Dto(oaService.getOaList(page, size, str, order));
        return ResultFactory.buildSuccessResult(oaListDto);
    }

    //查看收藏oa信息
    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public Result getFavorites(@RequestParam String token,@RequestParam Long page,@RequestParam Long size){
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        Long userId = Long.valueOf(loginId.toString());
        List<Long> oaIdList = userCollectService.getByUserId(page,size,userId).getRecords().stream().map(TbUserFavorites::getOaId).collect(Collectors.toList());
        if (oaIdList.size() == 0) return ResultFactory.buildFailResult("收藏夹为空。");
        return ResultFactory.buildSuccessResult(oaService.oaList2Dto(oaService.getOaListByList(1L, size, oaIdList)));
    }

    @RequestMapping(value = "/auto-update-oa", method = RequestMethod.GET)
    public Result autoUpdateOa(){
        new Thread(()->oaService.autoUpdateOa()).start();
        return ResultFactory.buildSuccessResult("Auto-update started.");
    }

    @GetMapping(value = "/list-by-department")
    public Result getListByDepartment(@RequestParam Long page,@RequestParam Long size, @RequestParam String department){
        return ResultFactory.buildSuccessResult(oaService.oaList2Dto(oaService.getOaListByDepartment(page, size, department)));
    }
}

