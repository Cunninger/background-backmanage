package cn.yam.backmanage.service.impl;

import cn.yam.backmanage.entity.algo.LuoGuData;
import cn.yam.backmanage.service.LuoGuService;
import cn.yam.backmanage.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：
 * 日期：2024/4/29 下午9:54
 */
@Service
public class LuoGuServiceImpl extends BaseServiceImpl implements LuoGuService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public LuoGuData getLuoGuInfo(String username) throws Exception {
        LuoGuData luoGuData = new LuoGuData();

        String userId = null;
        try {
            userId = getUserId(username);
        }catch (Exception e){
            throw new RuntimeException("获取洛谷用户ID失败");
        }
        if (userId==null){
            System.out.println("No match found.");
        }
        else {
            luoGuData.setFans(getFans(userId));
            luoGuData.setHasSumbitted(getHasSumbitted(userId));
            luoGuData.setHasAccepted(getHasAccepted(userId));
            luoGuData.setRanking(getRanking(userId));
            System.out.println("Matched text: " + luoGuData.toString());
        }


        return luoGuData;
    }





    public static String getUserId(String name) throws Exception {

        String html = sendRequest("https://www.luogu.com.cn/api/user/search?keyword=" + name);
        // 匹配正则表达式
        //   "uid": 985610,
        return matchRegex("\"uid\":(\\d+),", html);
    }

    public static String getFans(String userId) throws Exception {

        String html = sendRequest("https://www.luogu.com.cn/user/" + userId);
        // 匹配followerCount%22%3A后面的数字
        return matchRegex("followerCount%22%3A(\\d+)", html);

    }

    public static String getHasSumbitted(String userId) throws Exception {


        String html = sendRequest("https://www.luogu.com.cn/user/" + userId);
        // 匹配submittedProblemCount%22%3A后面的数字
        return matchRegex("submittedProblemCount%22%3A(\\d+)", html);

    }

    public static String getHasAccepted(String userId) throws Exception {
        // 发送HTTP请求

        String html = sendRequest("https://www.luogu.com.cn/user/" + userId);
        // 匹配passedProblemCount%22%3A 29
        return matchRegex("passedProblemCount%22%3A(\\d+)", html);
    }

    public static String getRanking(String userId) throws Exception {
        // 发送HTTP请求

        String html = sendRequest("https://www.luogu.com.cn/user/" + userId);
        // 匹配rank%22%3A后面的数字
        return matchRegex("ranking%22%3A(\\d+)", html);
    }




}