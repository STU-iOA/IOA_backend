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
    boolean ifAllowed(TbUser user);
    TbUser getUser(Long userId);
    Long insertUser(String account);
    boolean login(String account, String password);
}
