package cn.yam.backmanage.controller;

import cn.yam.backmanage.entity.codeeditor.CodeRequest;
import cn.yam.backmanage.utils.ReflectionUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 功能：
 * 日期：2024/7/5 下午11:10
 */
@RestController
public class CodeEditorController {

    @PostMapping("/codeeditor")
    public ResponseEntity<?> executeCode(@RequestBody CodeRequest codeRequest) {
        try {
            System.out.println("执行代码: " + codeRequest.getCode());
            String executionResult = executeUserCode(codeRequest.getCode(), codeRequest.getType(), codeRequest.getStdin());
            return ResponseEntity.ok(executionResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("代码执行出错: " + e.getMessage());
        }
    }

    private String executeUserCode(String code, String type, String stdin) throws UnsupportedEncodingException {
        // 写一个方法映射type
        type = ReflectionUtils.mapType(type);

        // URL编码code
        String encodedCode = code.replace("\n", "%0A").replace(" ", "%20").replace("{", "%7B").replace("}", "%7D")
                .replace("\"", "%22").replace("\t","");
        System.out.println(encodedCode);
        // 去除encodedCode中的空格，合并成一行


        // 构造请求数据
        String requestData = String.format("{\"code\": \"%s\", \"type\": \"%s\", \"stdin\": \"%s\"}",
                encodedCode, type, stdin);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("accept", "application/json, text/plain, */*");
        headers.add("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en-GB;q=0.7,en;q=0.6,zh-TW;q=0.5,de-DE;q=0.4,de;q=0.3");
        headers.add("origin", "https://r.xjq.icu");
        headers.add("priority", "u=1, i");
        headers.add("referer", "https://r.xjq.icu/");
        headers.add("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Microsoft Edge\";v=\"126\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-ch-ua-platform", "Windows");
        headers.add("sec-fetch-dest", "empty");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "same-site");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0");

        // 创建HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestData, headers);

        // 创建RestTemplate实例
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://rapi.xjq.icu/code/run";

        // 调用外部服务
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // 返回外部服务响应内容
        return response.getBody();
    }
}