package cn.yam.backmanage.controller;

import cn.yam.backmanage.entity.chat.ChatRequest;
import cn.yam.backmanage.entity.chat.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * 功能：
 * 日期：2024/5/25 下午4:05
 */
@RestController
public class ChatController {

    @Value("${ollama.api.url}")
    private String apiUrl;

    @Value("${ollama.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public ChatController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/chatollama")
    public ResponseEntity<String> chat(@RequestBody ChatRequest requestPayload) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + requestPayload.getAccessToken());
        headers.set("Content-Type", "application/json");

        HttpEntity<ChatRequest> entity = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}