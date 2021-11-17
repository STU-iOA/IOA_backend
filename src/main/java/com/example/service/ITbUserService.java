package com.example.service;

import com.example.entity.TbUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
public interface ITbUserService {
    Long if_allow(String account);
    TbUser getUser(Long userId);
    boolean login(String account, String password);
}
