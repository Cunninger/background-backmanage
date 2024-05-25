package cn.yam.backmanage.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能：
 * 日期：2024/5/25 下午4:11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens;
    private boolean stream;
    private String accessToken;
}