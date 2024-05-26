package cn.yam.backmanage.service.impl;

import cn.yam.backmanage.entity.algo.NowCoderData;
import cn.yam.backmanage.service.NowCoderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：
 * 日期：2024/4/15 上午8:42
 */
@Service
public class NowCoderServiceImpl extends BaseServiceImpl implements NowCoderService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public NowCoderData getNowCoderInfo(String name) throws Exception {
        NowCoderData nowCoderData = new NowCoderData();
        // 根据name先得到userId
        // 再根据userId得到数据
        String userId = null;
        try {
            userId = getUserId(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (userId == null) {
            System.out.println("No match found.");
        } else {

            nowCoderData.setRating(getRating(userId));
            nowCoderData.setRatingRank(getRatingRank(userId));
            nowCoderData.setCompetitionCount(getCompetitionCount(userId));
            nowCoderData.setHasPassedProblem(gethasPassedProblem(userId));
            nowCoderData.setAvatar(getAvatar(userId));

            System.out.println("Matched text: " + nowCoderData.toString());
        }
        return nowCoderData;

    }

    private String getAvatar(String userId) throws Exception {

        String html = sendRequest("https://ac.nowcoder.com/acm/contest/profile/" + userId);
        // 解析 HTML
        Document doc = Jsoup.parse(html);

        // 提取 src 属性以特定前缀开头的 img 标签
        Elements imgs = doc.select("img[src^=https://images.nowcoder.com/images]");
        System.out.println(imgs);
        //拿到第一个<img src =""
        String url = imgs.get(0).attr("src");
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);

        String avatar = Base64.getEncoder().encodeToString(imageBytes);

        return avatar;
    }


    public static String getUserId(String name) throws Exception {
        // 发送HTTP请求
        System.out.println("https://www.nowcoder.com/search/user?query=" + name + "&type=user&searchType=%E9%A1%B6%E9%83%A8%E5%AF%BC%E8%88%AA%E6%A0%8F&subType=0");
        String html = sendRequest("https://www.nowcoder.com/search/user?query=" + name + "&type=user&searchType=%E9%A1%B6%E9%83%A8%E5%AF%BC%E8%88%AA%E6%A0%8F&subType=0");
//https://www.nowcoder.com/search/user?query=goleex&type=user&searchType=%E6%90%9C%E7%B4%A2%E9%A1%B5%E8%BE%93%E5%85%A5%E6%A1%86&subType=0
        // 匹配正则表达式
        return matchRegex("\"userId\":(\\d+),", html);
//        return matchRegex("\"userId\":\\s*(\\d+)",html);
    }


    public String getRating(String userId) throws Exception {

        String url = "https://ac.nowcoder.com/acm/contest/profile/" + userId;
        System.out.println(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 获取HTML内容
        String html = response.body();

        // 定义正则表达式
//        Pattern pattern = Pattern.compile("<div class=\"state-num rate-score2\">(\\d+)");
        Pattern pattern = Pattern.compile("<a href=\"javascript:void\\(0\\);\" class=\"state-num rate-score\\d+\">(\\d+)</a>");


        // 创建Matcher对象
        Matcher matcher = pattern.matcher(html);
        // 查找匹配的字符串
        if (matcher.find()) {
            System.out.println("Matched text: " + matcher.group(1));

            return matcher.group(1);


        } else {
            System.out.println("No match found.");
            return null;
        }

    }


    public String getRatingRank(String userId) throws Exception {
        String url = "https://ac.nowcoder.com/acm/contest/profile/" + userId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 获取HTML内容
        String html = response.body();

        // 定义正则表达式
        Pattern pattern = Pattern.compile("<div class=\"state-num\">(\\d+)");


        // 创建Matcher对象
        Matcher matcher = pattern.matcher(html);
        // 查找匹配的字符串
        if (matcher.find()) {
            System.out.println("Matched text: " + matcher.group(1));
            return matcher.group(1);
        } else {
            System.out.println("No match found.");
            return null;
        }

    }


    public String getCompetitionCount(String userId) throws Exception {
        String url = "https://ac.nowcoder.com/acm/contest/profile/" + userId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 获取HTML内容
        String html = response.body();

        // 定义正则表达式
        Pattern pattern = Pattern.compile("<div class=\"state-num\">(\\d+)");


        // 创建Matcher对象
        Matcher matcher = pattern.matcher(html);
        // 如果你想获取第三次匹配的数据，你需要循环调用matcher.find()方法三次来找到第三个匹配
        int count = 0;
        while (matcher.find()) {
            count++;
            if (count == 3) {
                System.out.println("Matched text: " + matcher.group(1));
                return matcher.group(1);
            }
        }
        if (count < 3) {
            System.out.println("No match found.");

        }
        return null;
    }


    public String gethasPassedProblem(String userId) throws Exception {

        String url = "https://ac.nowcoder.com/acm/contest/profile/" + userId + "/practice-coding";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 获取HTML内容
        String html = response.body();

        // 定义正则表达式
        Pattern pattern = Pattern.compile("<div class=\"state-num\">(\\d+)");


        // 创建Matcher对象
        Matcher matcher = pattern.matcher(html);
        // 如果你想获取第三次匹配的数据，你需要循环调用matcher.find()方法三次来找到第三个匹配
        int count = 0;
        while (matcher.find()) {
            count++;
            if (count == 2) {
                System.out.println("Matched text: " + matcher.group(1));
                return matcher.group(1);
            }
        }
        if (count < 2) {
            System.out.println("No match found.");

        }
        return null;
    }




}