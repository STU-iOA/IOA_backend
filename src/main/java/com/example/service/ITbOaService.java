package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.vo.OaListDto;
import com.example.entity.TbOa;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
public interface ITbOaService {
    void autoUpdateOa();
    void stopUpdating();
    IPage<TbOa> getOaListByDepartment(Long page, Long size, String departmentName);
    IPage<TbOa> getOaList(Long page, Long size, String searchStr, Boolean order);
    IPage<TbOa> getOaListByList(Long page, Long size, List<Long> longList);
    TbOa getOa(Long OAId);
    OaListDto oaList2Dto(IPage<TbOa> oaIPage);
}
