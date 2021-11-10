package com.example.generator.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.generator.entity.Oa;
import com.example.generator.entity.UserCollect;
import com.example.generator.mapper.UserCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

@Service
public class UserCollectServer {
    @Autowired(required = false)
    UserCollectMapper userCollectMapper;
    public UserCollect insertUserCollect(Long oaId, Long userId){
        UserCollect userCollect=new UserCollect();
        userCollect.setOAId(oaId);
        userCollect.setUserId(userId);
        userCollect.setTime(LocalDateTime.now());
        userCollectMapper.insert(userCollect);
        return userCollect;
    }
    public int delectUserCollect(Long oaId, Long userId){
        QueryWrapper<UserCollect> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        queryWrapper.eq("OAId",oaId);
        int t=userCollectMapper.delete(queryWrapper);
        return t;
    }
    public IPage<UserCollect> getUserCollect(Long page,Long size,Long userId){
        QueryWrapper<UserCollect> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        return userCollectMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
}
