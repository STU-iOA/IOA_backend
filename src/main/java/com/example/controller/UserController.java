package com.example.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.generator.entity.User;
import com.example.generator.services.UserService;
import com.example.result.Result;
import com.example.result.ResultFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author www
 * @since 2021-10-28
 */
//@Controller
@RestController
@RequestMapping("/generator/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result getVerificationCode(@RequestBody User user) {
        String account= user.getAccount();
        String password= user.getPassword();
        //这里填入判断账号密码对错的API
        boolean if_allow=login(account, password);
        //
        if (!if_allow)
            return ResultFactory.buildFailResult("错误");
        Long userId=userService.if_allow(account,password);
        //返回token
        StpUtil.setLoginId(userId);
        return ResultFactory.buildSuccessResult(StpUtil.getTokenValue());
    }

    private boolean login(String username, String password) {
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

