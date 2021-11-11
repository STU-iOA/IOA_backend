package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.OADto;
import com.example.demo.OAListDto;
import com.example.generator.entity.Oa;
import com.example.generator.entity.UserCollect;
import com.example.generator.services.DepartmentService;
import com.example.generator.services.OAService;
import com.example.generator.services.UserCollectServer;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
@RequestMapping("/generator/oa")
public class OaController {
    @Autowired
    public OAService oaService;
    @Autowired
    public DepartmentService departmentService;
    @Autowired(required = false)
    UserCollectServer userCollectServer;
    @RequestMapping(value = "/getOA", method = RequestMethod.GET)
    public Result getOA(@RequestParam Long OAId){
        Oa oa=oaService.getOA(OAId);
        OADto oaDto=new OADto();
        BeanUtils.copyProperties(oa,oaDto);
        oaDto.setDepartment(departmentService.getDepartment(oa.getDepartmentId()).getName());
        return ResultFactory.buildSuccessResult(oaDto);
    }

    @RequestMapping(value = "/OAList", method = RequestMethod.GET)
    public Result OAList(@RequestParam Long page,@RequestParam Long size,@RequestParam(defaultValue="",required = false) String str){
        return ResultFactory.buildSuccessResult(oaService.oaList2Dto(oaService.getOAList(page, size, str)));
    }

    //查看收藏oa信息
    @RequestMapping(value = "/getCollectOA", method = RequestMethod.GET)
    public Result getCollectOA(@RequestParam String token,@RequestParam Long page,@RequestParam Long size){
        Long userId=Long.valueOf(StpUtil.getLoginIdByToken(token).toString());
        List<Long> oaIdList=userCollectServer.getUserCollect(page,size,userId).getRecords().stream().map(UserCollect::getOAId).collect(Collectors.toList());
        return ResultFactory.buildSuccessResult(oaService.oaList2Dto(oaService.getOAListByList(page, size, oaIdList)));
    }
    @RequestMapping(value = "/insertOA", method = RequestMethod.GET)
    public Result insertOA(){
        oaService.insertOA();
        return ResultFactory.buildSuccessResult("ok");
    }
}

