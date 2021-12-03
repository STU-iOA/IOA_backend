package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.TbDaily;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author www
 * @since 2021-11-27
 */
public interface ITbDailyService extends IService<TbDaily> {
    TbDaily insert(TbDaily daily);
    int delete(Long dailyId);
    List<TbDaily> id2List(Long userId);
    List<TbDaily> id2ListByDate(Long userId, LocalDate date);
    TbDaily cheak(Long dailyId);
}
