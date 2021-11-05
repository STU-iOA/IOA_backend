package com.example.controller;


import com.example.demo.OADto;
import com.example.generator.entity.Oa;
import com.example.generator.services.DepartmentService;
import com.example.generator.services.OAService;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/generator/oa")
public class OaController {
    @Autowired
    public OAService oaService;
    @Autowired
    public DepartmentService departmentService;
    @RequestMapping(value = "/getOA", method = RequestMethod.GET)
    public Result getOA(@RequestParam Long OAId){
        Oa oa=oaService.getOA(OAId);
        OADto oaDto=new OADto();
        BeanUtils.copyProperties(oa,oaDto);
        oaDto.setDepartment(departmentService.getDepartment(oa.getDepartmentId()).getName());
        return ResultFactory.buildSuccessResult(oaDto);
    }
    
}

