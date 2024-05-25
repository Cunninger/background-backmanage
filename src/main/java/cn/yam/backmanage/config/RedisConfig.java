package cn.yam.backmanage.config;

/**
 * 功能：
 * 日期：2024/5/25 下午5:58
 */
import cn.yam.backmanage.entity.chat.saveMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 序列化器设置
        template.setKeySerializer(new StringRedisSerializer()); // 设置键（key）的序列化采用StringRedisSerializer。
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class)); // 设置值（value）的序列化采用GenericToStringSerializer。
        return template;
    }

    @Bean
    public RedisTemplate<String, saveMessage> saveMessageRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, saveMessage> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 序列化器设置
        template.setKeySerializer(new StringRedisSerializer()); // 设置键（key）的序列化采用StringRedisSerializer。
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // 设置值（value）的序列化采用GenericJackson2JsonRedisSerializer。
        return template;
    }
}
