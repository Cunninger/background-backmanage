package cn.yam.backmanage.service;

/**
 * 功能：
 * 日期：2024/6/2 上午11:56
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussRedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void saveMessage(String room, String message) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.rightPush("chat_room:" + room, message);
    }

    public List<String> getMessages(String room) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range("chat_room:" + room, 0, -1);
    }
}
