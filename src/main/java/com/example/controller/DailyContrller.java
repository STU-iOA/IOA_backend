package com.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.entity.TbDaily;
import com.example.result.Result;
import com.example.result.ResultFactory;
import com.example.service.ITbDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily")
public class DailyContrller {
    @Autowired
    ITbDailyService dailyService;
    @PostMapping(value = "/add-to-daily")
    public Result addtodaily(@RequestBody TbDaily tbDaily){
        return ResultFactory.buildSuccessResult(dailyService.insert(tbDaily));
    }
    @GetMapping(value = "/remove-from-daily")
    public Result removefromdaily(@RequestParam Long dailyId){
        return ResultFactory.buildSuccessResult(dailyService.delete(dailyId));
    }
    @GetMapping(value = "/list")
    public Result list(@RequestParam String token){
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        return ResultFactory.buildSuccessResult(Long.valueOf(loginId.toString()));
    }
//    @GetMapping(value = "/check")
//    public Result check(@RequestParam Long daily)
}
