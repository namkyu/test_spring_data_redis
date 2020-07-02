package com.example.nklee.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String body = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            RoomMessage roomMessage = objectMapper.readValue(body, RoomMessage.class);
            log.info("Room - Message : {}", roomMessage.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}