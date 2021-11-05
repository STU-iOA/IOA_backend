package com.example.generator.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.generator.entity.Oa;
import com.example.generator.mapper.OaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OAService {
    @Autowired(required = false)
    public OaMapper oaMapper;
    public IPage<Oa> getOAList(Long page,Long size,String searchStr){
        QueryWrapper<Oa> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("title",searchStr).or().like("context",searchStr);
        queryWrapper.orderByDesc("timestamp");
        return oaMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
    public Oa getOA(Long OAId){
        return oaMapper.selectById(OAId);
    }
}
