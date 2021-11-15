package com.example.generator.services;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.OADto;
import com.example.demo.OAListDto;
import com.example.generator.entity.Oa;
import com.example.generator.mapper.DepartmentMapper;
import com.example.generator.mapper.OaMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class OAService {
    @Autowired(required = false)
    OaMapper oaMapper;
    @Autowired(required = false)
    DepartmentMapper departmentMapper;

    public static List<String> StringToList(String str){

        List<String> list = new ArrayList<>();

        String[] strArr = str.split(",");

        Collections.addAll(list, strArr);

        return list;
    }

    //OA自动获取
    public void insertOA() {
        while (true) {
            System.out.println("测试循环");
            System.out.println(LocalDateTime.now());
            int star=1;
            int end=1;
            QueryWrapper<Oa> queryWrapper=new QueryWrapper<>();
            queryWrapper.ne("OAId",0);
            List<Oa> oaList=oaMapper.selectList(queryWrapper);
            Oa oa=oaList.get(1);
            LocalDateTime time=oa.getTimestamp();
            boolean ifgoone=true;
            while (ifgoone) {
                String apiUrl = "http://wechat.stu.edu.cn//webservice_oa/OA/GetDOCdetail?row_start=" + star + "&row_end=" + end;
                URI uri = null;
                CloseableHttpClient httpClient = HttpClients.createDefault();
                try {
                    uri = new URIBuilder(apiUrl).build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                HttpGet httpGet = new HttpGet(uri);
                CloseableHttpResponse response = null;
                try {
                    response = httpClient.execute(httpGet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String ref = "";
                try {
//                System.out.println(EntityUtils.toString(response.getEntity()));
                    assert response != null;
                    ref = EntityUtils.toString(response.getEntity(), "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ref = ref.substring(1, ref.length() - 1);
                HashMap hashMap = JSON.parseObject(ref, HashMap.class);
                //传入OA
                Oa oatem = new Oa();
                String timestr=hashMap.get("DOCVALIDDATE")+" "+hashMap.get("DOCVALIDTIME");
                DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                oatem.setTimestamp(LocalDateTime.parse(timestr,df));
                if (time.isBefore(oatem.getTimestamp())){
                    //oa初始化
                    oatem.setCollectNumber(0);
                    oatem.setContent((String) hashMap.get("DOCCONTENT"));
                    oatem.setDepartmentName((String) hashMap.get("DEPARTMENTNAME"));
                    oatem.setLoginId(0);
                    oatem.setTitle((String) hashMap.get("DOCSUBJECT"));
                    oaMapper.insert(oatem);
                }else {
                    ifgoone=false;
                }
                star=star+1;
                end=end+1;
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public IPage<Oa> getOAList(Long page,Long size,String searchStr,Boolean order){
        QueryWrapper<Oa> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("title",searchStr).or().like("content",searchStr);
        if (order){
            queryWrapper.orderByDesc("timestamp");
        }else{
            queryWrapper.orderByDesc("collectNumber");
        }
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

    public Oa oaJoinNumber(Long OaId){
        Oa oa=getOA(OaId);
        oa.setCollectNumber(oa.getCollectNumber()+1);
        oaMapper.updateById(oa);
        return oa;
    }
}
