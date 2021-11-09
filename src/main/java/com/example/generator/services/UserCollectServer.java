package com.example.generator.services;

import com.example.generator.entity.UserCollect;
import com.example.generator.mapper.UserCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
