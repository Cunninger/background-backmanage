package cn.yam.backmanage.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 日期：2024/5/25 下午4:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String role;
    private String content;

}