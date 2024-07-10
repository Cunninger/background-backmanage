package cn.yam.backmanage.controller;

import cn.yam.backmanage.entity.codeeditor.CodeRequest;
import cn.yam.backmanage.utils.ReflectionUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：
 * 日期：2024/7/5 下午11:10
 */
@RestController
public class CodeEditorController {
    // 定义一个正则表达式来匹配 ANSI 转义字符
    private static final Pattern ansiPattern = Pattern.compile("\\u001b\\[[0-9;]*m");

    @PostMapping("/codeeditor")
    public ResponseEntity<?> executeCode(@RequestBody CodeRequest codeRequest) {
        try {
            System.out.println("执行代码: " + codeRequest.getCode());
            String executionResult = executeUserCode(codeRequest.getCode(), codeRequest.getType(), codeRequest.getStdin());
            ResponseEntity<String> oked = ResponseEntity.ok(executionResult);

            System.out.println(ResponseEntity.ok(executionResult));
            return ResponseEntity.ok(executionResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("代码执行出错: " + e.getMessage());
        }
    }

    private String executeUserCode(String code, String type, String stdin) throws UnsupportedEncodingException {
        type = ReflectionUtils.mapType(type);

        // URL编码code
        String encodedCode = code.replace("\n", "%0A").replace(" ", "%20").replace("{", "%7B").replace("}", "%7D")
                .replace("\"", "%22").replace("\t", "");
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

        HttpEntity<String> entity = new HttpEntity<>(requestData, headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://rapi.xjq.icu/code/run";


        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
        return response.getBody();

    }

    public static String stripAnsi(String input) {
        if (input == null) {
            return null;
        }
        // 使用正则表达式替换 ANSI 转义字符
        Matcher matcher = ansiPattern.matcher(input);
        return matcher.replaceAll("");
    }
}