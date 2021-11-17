package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.TbUser;
import com.example.mapper.TbUserMapper;
import com.example.service.ITbUserService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
@Service
public class TbUserServiceImpl implements ITbUserService {
    @Autowired
    TbUserMapper userMapper;
    public Long if_allow(String account) {
        TbUser user=userMapper.selectOne(new QueryWrapper<TbUser>().eq("account", account));
        if (user!=null){
            return user.getId();
        }
        //添加用户
        TbUser newUser = new TbUser();
        newUser.setAccount(account);
        userMapper.insert(newUser);
        return newUser.getId();
    }
    public TbUser getUser(Long userId){
        return userMapper.selectById(userId);
    }

    @Override
    public boolean login(String username, String password) {
        String apiUrl = "http://wechat.stu.edu.cn//webservice_oa/OA/Login" +
                "?username=" + username + "&password=" + password;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder(apiUrl).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        int res = 0;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                res = Integer.parseInt(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res == 1;
    }
}
