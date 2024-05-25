package cn.yam.backmanage.service;

import cn.yam.backmanage.entity.chat.saveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {

    @Autowired
    @Qualifier("saveMessageRedisTemplate")
    private RedisTemplate<String, saveMessage> redisTemplate;

    private ValueOperations<String, saveMessage> valueOperations;

    @PostConstruct
    private void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    public void saveMessage(saveMessage message) {
        valueOperations.set("msg:" + message.getId(), message, 10, TimeUnit.DAYS); // 保存消息，设置过期时间为10天
    }

    public saveMessage getMessage(String id) {
        return valueOperations.get("msg:" + id);
    }
}
