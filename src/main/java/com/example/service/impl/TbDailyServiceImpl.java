package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.TbDaily;
import com.example.mapper.TbDailyMapper;
import com.example.service.ITbDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www
 * @since 2021-11-27
 */
@Service
public class TbDailyServiceImpl extends ServiceImpl<TbDailyMapper, TbDaily> implements ITbDailyService {
    @Autowired
    TbDailyMapper tbDailyMapper;
    @Override
    public TbDaily insert(TbDaily daily) {
        tbDailyMapper.insert(daily);
        return daily;
    }

    @Override
    public int delete(Long dailyId) {
        return tbDailyMapper.deleteById(dailyId);
    }

    @Override
    public List<TbDaily> id2List(Long userId ) {
        return tbDailyMapper.selectList(new QueryWrapper<TbDaily>().eq("userId",userId));
    }

    @Override
    public List<TbDaily> id2ListByDate(Long userId, LocalDate date) {
        QueryWrapper<TbDaily> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        queryWrapper.ge("date",date.atTime(0,0,0));
        queryWrapper.le("date",date.atTime(23,59,59));
        return tbDailyMapper.selectList(queryWrapper);
    }

    @Override
    public TbDaily cheak(Long dailyId) {
        TbDaily tbDaily=tbDailyMapper.selectById(dailyId);
        if (tbDaily.getIfComplete()==1)
            tbDaily.setIfComplete(0);
        else
            tbDaily.setIfComplete(1);
        tbDailyMapper.updateById(tbDaily);
        return tbDaily;
    }
}
