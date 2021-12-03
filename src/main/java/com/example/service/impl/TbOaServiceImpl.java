package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vo.OaListDto;
import com.example.vo.DocDetail;
import com.example.entity.TbOa;
import com.example.mapper.TbOaMapper;
import com.example.service.ITbOaService;
import com.example.vo.OaListItem;
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
import java.util.List;
import java.util.Locale;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author www
 * @since 2021-11-17
 */
@Service
public class TbOaServiceImpl implements ITbOaService {
    @Autowired(required = false)
    TbOaMapper oaMapper;

    //OA自动获取
    public void autoUpdateOa() {
        while (true) {
            /*// 从数据库 select 一条出来
            TbOa oa = oaMapper.selectOne(new QueryWrapper<TbOa>().orderByDesc("timestamp").last("limit 1"));
            boolean flag = true;
            for (int start = 1, end = 1; flag; start++, end++) {
                //从接口获取最新的一条
                DocDetail doc = getNewOa(start, end);
                if (doc == null) break;
                // 如果 title 不一样就存进数据库
                if (!oa.getTitle().equals(doc.getDOCSUBJECT())) save(doc);
                else flag = false;
            }*/
            for (int start = 1, end = 1;; start++, end++) {
                DocDetail doc = getNewOa(start, end);
                if (doc == null || oaMapper.selectOne(new QueryWrapper<TbOa>()
                        .eq("title", doc.getDOCSUBJECT()).orderByDesc("create_time").last("limit 1")) == null)
                    break;
                save(doc);
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void save(DocDetail doc) {
        TbOa newOa = new TbOa();
        newOa.setTitle(doc.getDOCSUBJECT());
        newOa.setContent(doc.getDOCCONTENT());
        newOa.setTimestamp(doc.getDOCVALIDDATE() == null || doc.getDOCVALIDTIME() == null ? null
                : LocalDateTime.parse(doc.getDOCVALIDDATE()+" "+doc.getDOCVALIDTIME(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA)));
        newOa.setLoginId(doc.getLOGINID());
        newOa.setLastName(doc.getLASTNAME());
        newOa.setSubcompanyName(doc.getSUBCOMPANYNAME());
        newOa.setDepartmentName(doc.getDEPARTMENTNAME());
        newOa.setFavoredCount(0);
        newOa.setReadCount(0);
        oaMapper.insert(newOa);
    }

    private DocDetail getNewOa(int start, int end) {
        String apiUrl = "http://wechat.stu.edu.cn//webservice_oa/OA/GetDOCdetail?row_start=" + start + "&row_end=" + end;
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
        if (response == null || response.getStatusLine().getStatusCode() != 200) return null;
        try {
            ref = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(ref.substring(1, ref.length() - 1), DocDetail.class);
    }

    public IPage<TbOa> getOaListByDepartment(Long page, Long size, String departmentName) {
        return oaMapper.selectPage(new Page<>(page,size),
                new QueryWrapper<TbOa>().eq("subcompany_name", departmentName)
                        .orderByDesc("timestamp"));
    }

    public IPage<TbOa> getOaList(Long page, Long size, String searchStr, Boolean order) {
        QueryWrapper<TbOa> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",searchStr).or().like("content",searchStr);
        if (order){
            queryWrapper.orderByDesc("timestamp");
        }else{
            queryWrapper.orderByDesc("read_count");
        }
        return oaMapper.selectPage(new Page<>(page,size), queryWrapper);
    }

    public IPage<TbOa> getOaListByList(Long page, Long size, List<Long> longList) {
        QueryWrapper<TbOa> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",longList);
        return oaMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
    public TbOa getOa(Long OAId){
        TbOa tbOa = oaMapper.selectById(OAId);
        if (tbOa == null) return null;
        tbOa.setReadCount(tbOa.getReadCount() + 1);
        oaMapper.updateById(tbOa);
        return tbOa;
    }

    public OaListDto oaList2Dto(IPage<TbOa> oaIPage) {
        OaListDto oaListDto = new OaListDto();
        oaListDto.setIfNext(oaIPage.isSearchCount());
        List<OaListItem> list = new ArrayList<>();
        OaListItem ot;
        for (TbOa item: oaIPage.getRecords()) {
            ot = new OaListItem();
            BeanUtils.copyProperties(item, ot);
            list.add(ot);
        }
        oaListDto.setOaDtoList(list);
        return oaListDto;
    }
}
