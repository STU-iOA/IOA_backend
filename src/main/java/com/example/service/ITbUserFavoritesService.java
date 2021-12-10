package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.entity.TbUserFavorites;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
public interface ITbUserFavoritesService {
    int save(Long oaId, Long userId);
    int remove(Long oaId, Long userId);
    IPage<TbUserFavorites> getByUserId(Long page,Long size,Long userId);
}