package com.example.controller;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.baidu.aip.nlp.AipNlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Sample {
    public static final String APP_ID = "22102435";
    public static final String API_KEY = "TOi1b51QOUGtQIEvPZie8Z3a";
    public static final String SECRET_KEY = "8FrbFLBfLeMS6gTMcNmvhbiuEggw3Ymo";

    public HashMap<String,String> Sample(String content,String title2) {
        AipNlp client = new AipNlp("22102435", "TOi1b51QOUGtQIEvPZie8Z3a", "8FrbFLBfLeMS6gTMcNmvhbiuEggw3Ymo");
//        String content = "为深入学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，总结交流和宣传过渡校区学风建设的创新方法和成功经验，推进学风建设创新发展、凝练特色和提高成效，激励同学们奋发努力，健康成长。根据过渡校区实际，决定开展学风建设创新案例征集评选活动。现就有关事项通知如下:一、指导思想以习近平新时代中国特色社会主义思想为指导，认真学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，坚持党对教育工作的全面领导，以学风建设为重点，不断改进、探索更有效的学风建设内容与形式，积极调动大学生学习的积极性、主动性，在学生中形成勤奋学习、刻苦钻研、探索进取、开拓创新的良好学习风气二、征集范围过渡校区各班级组织的特色鲜明、符合实际、成效明显的学风建设创新案例以及组织实施情况，可以是已经开展或准备开展的项目，包括但不限于以下形式.促进学科专业学习的有效方法和组织实施情况,提高同学们学习热情和学习效率的创新做法和组织实施情况,加强学风管理的有效做法和经验,明确专业方向，树立专业信心的有效做法和经验.请各班级于 2021 年 12 月3日前，将《过渡校区学风建设创新案例申报表》（附件1），发送到邮箱：zzyao@stu.edu.cn，文档名称修改为2021+班级名称+学风建设创新案例申报表，本次申报只需要提交附件1申报表";
        int maxSummaryLen = 100;
        HashMap<String, Object> options = new HashMap();
        options.put("title", "标题");
        JSONObject res = client.newsSummary(content, maxSummaryLen, options);
        System.out.println(res.toString());
//        String title2 = "关于开展过渡校区学风建设创新案例征集评选活动的通知";
//        String content2 = "为深入学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，总结交流和宣传过渡校区学风建设的创新方法和成功经验，推进学风建设创新发展、凝练特色和提高成效，激励同学们奋发努力，健康成长。根据过渡校区实际，决定开展学风建设创新案例征集评选活动。现就有关事项通知如下:一、指导思想以习近平新时代中国特色社会主义思想为指导，认真学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，坚持党对教育工作的全面领导，以学风建设为重点，不断改进、探索更有效的学风建设内容与形式，积极调动大学生学习的积极性、主动性，在学生中形成勤奋学习、刻苦钻研、探索进取、开拓创新的良好学习风气二、征集范围过渡校区各班级组织的特色鲜明、符合实际、成效明显的学风建设创新案例以及组织实施情况，可以是已经开展或准备开展的项目，包括但不限于以下形式.促进学科专业学习的有效方法和组织实施情况,提高同学们学习热情和学习效率的创新做法和组织实施情况,加强学风管理的有效做法和经验,明确专业方向，树立专业信心的有效做法和经验.请各班级于 2021 年 12 月3日前，将《过渡校区学风建设创新案例申报表》（附件1），发送到邮箱：zzyao@stu.edu.cn，文档名称修改为2021+班级名称+学风建设创新案例申报表，本次申报只需要提交附件1申报表";
        HashMap<String, Object> options2 = new HashMap();
        JSONObject res2 = client.keyword(title2, content, options2);
        List<String> keywordref=new ArrayList<>();
        HashMap<String, String> options3 = new HashMap();
        if(res2.has("items")){
            JSONArray keywordArr = res2.getJSONArray("items");

            for(int i = 0; i < keywordArr.length(); ++i) {
                JSONObject keyword = keywordArr.getJSONObject(i);
                System.out.print("keyword：");
                System.out.println(keyword.getString("tag"));
                keywordref.add(keyword.getString("tag"));
            }
            options3.put("keyword",StringUtils.join(keywordref,';'));
        }else{
            options3.put("keyword","");
        }

        options3.put("Summary",res.get("summary").toString());
        return options3;
    }

    public static void main(String[] args) {
        AipNlp client = new AipNlp("22102435", "TOi1b51QOUGtQIEvPZie8Z3a", "8FrbFLBfLeMS6gTMcNmvhbiuEggw3Ymo");
        String content = "为深入学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，总结交流和宣传过渡校区学风建设的创新方法和成功经验，推进学风建设创新发展、凝练特色和提高成效，激励同学们奋发努力，健康成长。根据过渡校区实际，决定开展学风建设创新案例征集评选活动。现就有关事项通知如下:一、指导思想以习近平新时代中国特色社会主义思想为指导，认真学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，坚持党对教育工作的全面领导，以学风建设为重点，不断改进、探索更有效的学风建设内容与形式，积极调动大学生学习的积极性、主动性，在学生中形成勤奋学习、刻苦钻研、探索进取、开拓创新的良好学习风气二、征集范围过渡校区各班级组织的特色鲜明、符合实际、成效明显的学风建设创新案例以及组织实施情况，可以是已经开展或准备开展的项目，包括但不限于以下形式.促进学科专业学习的有效方法和组织实施情况,提高同学们学习热情和学习效率的创新做法和组织实施情况,加强学风管理的有效做法和经验,明确专业方向，树立专业信心的有效做法和经验.请各班级于 2021 年 12 月3日前，将《过渡校区学风建设创新案例申报表》（附件1），发送到邮箱：zzyao@stu.edu.cn，文档名称修改为2021+班级名称+学风建设创新案例申报表，本次申报只需要提交附件1申报表";

        int maxSummaryLen = 100;
        HashMap<String, Object> options = new HashMap();
        options.put("title", "标题");
        JSONObject res = client.newsSummary(content, maxSummaryLen, options);
        System.out.println(res.toString());
        String title2 = "关于开展过渡校区学风建设创新案例征集评选活动的通知";
        String content2 = "为深入学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，总结交流和宣传过渡校区学风建设的创新方法和成功经验，推进学风建设创新发展、凝练特色和提高成效，激励同学们奋发努力，健康成长。根据过渡校区实际，决定开展学风建设创新案例征集评选活动。现就有关事项通知如下:一、指导思想以习近平新时代中国特色社会主义思想为指导，认真学习贯彻习近平总书记关于教育的重要论述，全面贯彻党的教育方针，坚持党对教育工作的全面领导，以学风建设为重点，不断改进、探索更有效的学风建设内容与形式，积极调动大学生学习的积极性、主动性，在学生中形成勤奋学习、刻苦钻研、探索进取、开拓创新的良好学习风气二、征集范围过渡校区各班级组织的特色鲜明、符合实际、成效明显的学风建设创新案例以及组织实施情况，可以是已经开展或准备开展的项目，包括但不限于以下形式.促进学科专业学习的有效方法和组织实施情况,提高同学们学习热情和学习效率的创新做法和组织实施情况,加强学风管理的有效做法和经验,明确专业方向，树立专业信心的有效做法和经验.请各班级于 2021 年 12 月3日前，将《过渡校区学风建设创新案例申报表》（附件1），发送到邮箱：zzyao@stu.edu.cn，文档名称修改为2021+班级名称+学风建设创新案例申报表，本次申报只需要提交附件1申报表";
        HashMap<String, Object> options2 = new HashMap();
        JSONObject res2 = client.keyword(title2, content2, options2);
        JSONArray keywordArr = res2.getJSONArray("items");

        for(int i = 0; i < keywordArr.length(); ++i) {
            JSONObject keyword = keywordArr.getJSONObject(i);
            System.out.print("keyword：");
            System.out.println(keyword.getString("tag"));
        }

    }
}
