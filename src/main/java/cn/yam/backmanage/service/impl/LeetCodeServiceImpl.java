//package cn.yam.backmanage.service.impl;
//
//import cn.yam.backmanage.entity.algo.LeetCodeData;
//import cn.yam.backmanage.service.LeetCodeService;
//import cn.yam.backmanage.service.impl.BaseServiceImpl;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.followjs.util.DifficultyCount;
//import com.followjs.util.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Base64;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
///**
// * 功能：
// * 日期：2024/4/14 下午3:44
// */
//@Service
//public class LeetCodeServiceImpl extends BaseServiceImpl implements LeetCodeService {
//
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    private String getAvatar(String username) throws Exception {
//        // 设置ChromeDriver的路径
//        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//        // 创建一个ChromeDriver的实例
//        WebDriver driver = new ChromeDriver();
//// 打开一个网页
//        driver.get("https://leetcode.cn/u/"+username+"/");
//        // 等待页面加载完成
//        Thread.sleep(5000);
//        // 选择第一个<source>元素
//        WebElement sourceElement = driver.findElement(By.tagName("source"));
//
//        // 获取srcset属性
//        String srcset = sourceElement.getAttribute("srcset");
//
//        // 打印srcset属性
//        System.out.println(srcset);
//
//        // 关闭浏览器
//        driver.quit();
//
//
//
//
//        byte[] imageBytes = restTemplate.getForObject(srcset, byte[].class);
//
//        String avatar = Base64.getEncoder().encodeToString(imageBytes);
//
//        return avatar;
//    }
//
//    @Override
//    public LeetCodeData getLeetCodeInfo(String username) throws Exception {
//        String url = "https://leetcode.cn/graphql/";
//
//        // Set headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.ALL));
//        headers.set("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en-GB;q=0.7,en;q=0.6,zh-TW;q=0.5,de-DE;q=0.4,de;q=0.3");
//        headers.set("authorization", ""); // Set this to your authorization token if needed
//        headers.set("content-type", "application/json");
//        headers.set("origin", "https://leetcode.cn");
//        headers.set("referer", "https://leetcode.cn/u/" + username + "/");
//        headers.set("sec-ch-ua", "\"Microsoft Edge\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";v=\"123\"");
//        headers.set("sec-ch-ua-mobile", "?0");
//        headers.set("sec-ch-ua-platform", "\"Windows\"");
//        headers.set("sec-fetch-dest", "empty");
//        headers.set("sec-fetch-mode", "cors");
//        headers.set("sec-fetch-site", "same-origin");
//        headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.0");
//        headers.set("x-csrftoken", "oEJQrTKNN8ekDqXp9CqTot5C4RY3qm85OPWSaTx3TAvPIPokP9GMPdibNUvfN3bW");
//        headers.set("Cookie", "csrftoken=oEJQrTKNN8ekDqXp9CqTot5C4RY3qm85OPWSaTx3TAvPIPokP9GMPdibNUvfN3bW");
//
//        // Set request body
//        Map<String, Object> map = new HashMap<>();
//        map.put("query", "query userQuestionProgress($userSlug: String!) { userProfileUserQuestionProgress(userSlug: $userSlug) { numAcceptedQuestions { difficulty count } numFailedQuestions { difficulty count } numUntouchedQuestions { difficulty count } } }");
//        Map<String, String> variables = new HashMap<>();
//        variables.put("userSlug", username);
//        map.put("variables", variables);
//
//        // Build the request
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
//
//        // Send the request and get the response
//        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
//
//        // Parse the response body
//        String jsonString = response.getBody();
//        ObjectMapper mapper = new ObjectMapper();
//        Response responseObj = null;
//        try {
//            responseObj = mapper.readValue(jsonString, Response.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        LeetCodeData leetCodeData = new LeetCodeData();
//        for (DifficultyCount dc : responseObj.getData().getUserProfileUserQuestionProgress().getNumAcceptedQuestions()) {
//            switch (dc.getDifficulty()) {
//                case "EASY":
//                    leetCodeData.setEasy_solved(dc.getCount());
//                    break;
//                case "MEDIUM":
//                    leetCodeData.setMedium_solved(dc.getCount());
//                    break;
//                case "HARD":
//                    leetCodeData.setHard_solved(dc.getCount());
//                    break;
//            }
//            leetCodeData.setTotal_solved(leetCodeData.getTotal_solved() + dc.getCount());
//        }
//
//        leetCodeData.avatar = getAvatar(username);
//
//
//        return leetCodeData;
//
//    }
//
//
//}