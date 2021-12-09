package com.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.vo.UserDailyDto;
import com.example.result.Result;
import com.example.result.ResultFactory;
import com.example.service.ITbDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/daily")
public class DailyController {
    @Autowired
    ITbDailyService dailyService;
    @PostMapping(value = "/add-to-daily")
    public Result addtodaily(@RequestBody UserDailyDto tbDaily){
        Object loginId = StpUtil.getLoginIdByToken(tbDaily.getToken());
        if (loginId == null) return ResultFactory.buildResultTokenError("token有误");
        tbDaily.setUserId(Long.valueOf(loginId.toString()));
        tbDaily.setIfComplete(0);
        tbDaily.setDate(LocalDateTime.now());
        tbDaily.setTimesTamp(new Date().getTime());
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
        return ResultFactory.buildSuccessResult(dailyService.id2List(Long.valueOf(loginId.toString())));
    }
    @GetMapping(value = "/listByDate")
    public Result listByDate(@RequestParam String token,@RequestParam LocalDate date){
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId == null) return ResultFactory.buildResultTokenError(null);
        return ResultFactory.buildSuccessResult(dailyService.id2ListByDate(Long.valueOf(loginId.toString()),date));
    }
    @GetMapping(value = "/check")
    public Result check(@RequestParam Long dailyId){
        return ResultFactory.buildSuccessResult(dailyService.cheak(dailyId));
    }
}
