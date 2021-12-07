package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.TbOa;
import com.example.entity.TbUserFavorites;
import com.example.mapper.TbOaMapper;
import com.example.mapper.TbUserFavoritesMapper;
import com.example.mapper.TbUserMapper;
import com.example.service.ITbUserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
@Service
public class TbUserFavoritesServiceImpl implements ITbUserFavoritesService {
    @Autowired(required = false)
    TbUserFavoritesMapper userFavoritesMapper;
    @Autowired
    TbOaMapper oaMapper;
    @Autowired
    TbUserMapper userMapper;

    public int save(Long oaId, Long userId) {
        TbUserFavorites newRecord = new TbUserFavorites();
        newRecord.setOaId(oaId);
        newRecord.setUserId(userId);
        TbUserFavorites record = userFavoritesMapper.selectOne(
                new QueryWrapper<TbUserFavorites>().eq("oa_id", oaId).eq("user_id", userId));
        if (record != null) return 0; // 记录存在于数据库中
        TbOa tbOa = oaMapper.selectById(oaId);
        if (tbOa == null) return -1; // OA 不存在
        if (userMapper.selectById(userId) == null) return -2; // 用户不存在
        tbOa.setFavoredCount(tbOa.getFavoredCount() + 1);
        oaMapper.updateById(tbOa);
        return userFavoritesMapper.insert(newRecord);
    }
    public int remove(Long oaId, Long userId) {
        QueryWrapper<TbUserFavorites> queryWrapper =
                new QueryWrapper<TbUserFavorites>().eq("oa_id", oaId).eq("user_id", userId);
        TbUserFavorites record = userFavoritesMapper.selectOne(queryWrapper);
        if (record == null) return 0; // 记录不存在于数据库中
        TbOa tbOa = oaMapper.selectById(oaId);
        if (tbOa == null) return -1; // OA 不存在
        tbOa.setFavoredCount(tbOa.getFavoredCount() - 1);
        oaMapper.updateById(tbOa);
        return userFavoritesMapper.delete(queryWrapper);
    }
    public IPage<TbUserFavorites> getByUserId(Long page, Long size, Long userId) {
        return userFavoritesMapper.selectPage(new Page<>(page,size),
                new QueryWrapper<TbUserFavorites>().eq("user_id",userId).orderByDesc("create_time"));
    }
}
