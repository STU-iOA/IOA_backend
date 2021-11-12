package com.example.generator.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.OADto;
import com.example.demo.OAListDto;
import com.example.generator.entity.Department;
import com.example.generator.entity.Oa;
import com.example.generator.mapper.DepartmentMapper;
import com.example.generator.mapper.OaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OAService {
    @Autowired(required = false)
    public OaMapper oaMapper;
    @Autowired(required = false)
    DepartmentMapper departmentMapper;
    public IPage<Oa> getOAList(Long page,Long size,String searchStr){
        QueryWrapper<Oa> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("title",searchStr).or().like("context",searchStr);
        queryWrapper.orderByDesc("timestamp");
        return oaMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
    public IPage<Oa> getOAListByList(Long page,Long size,List<Long> longList){
        QueryWrapper<Oa> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("OAId",longList);
        queryWrapper.orderByDesc("timestamp");
        return oaMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
    public Oa getOA(Long OAId){
        return oaMapper.selectById(OAId);
    }

    public OAListDto oaList2Dto(IPage<Oa> oaIPage){
        OAListDto oaListDto=new OAListDto();
        oaListDto.setIfNext(oaIPage.isSearchCount());
        List<OADto> oaDtoList=new ArrayList<>();
        OADto ot;
        for(Oa item:oaIPage.getRecords()){
            ot=new OADto();
            BeanUtils.copyProperties(item,ot);
            ot.setDepartment(departmentMapper.selectById(item.getDepartmentId()).getName());
            oaDtoList.add(ot);
        }
        oaListDto.setOaDtoList(oaDtoList);
        return oaListDto;
    }
}
