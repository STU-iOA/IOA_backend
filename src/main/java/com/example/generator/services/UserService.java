package com.example.generator.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.generator.entity.User;
import com.example.generator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required=false)
    UserMapper userMapper;
    public Long if_allow(String account,String password){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.eq("password",password);
        User user=userMapper.selectOne(queryWrapper);
        if (user!=null){
            return user.getUserId();
        }
        //添加用户
        User user1=new User();
        user1.setAccount(account);
        user1.setPassword(password);
        userMapper.insert(user1);
        return user1.getUserId();
    }
    public User getUser(Long userId){
        return userMapper.selectById(userId);
    }

}
