package cn.yam.backmanage.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 日期：2024/5/25 下午5:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class saveMessage {
    private String content;
    private String role;
    private String id; // 使用 UUID 或时间戳
}