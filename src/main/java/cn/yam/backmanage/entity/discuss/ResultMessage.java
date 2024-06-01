package cn.yam.backmanage.entity.discuss;

/**
 * 功能：
 * 日期：2024/5/29 下午2:29
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultMessage {
    private boolean isSystem;
    private String fromName;
    private Object message;//如果是系统消息是数组
}

