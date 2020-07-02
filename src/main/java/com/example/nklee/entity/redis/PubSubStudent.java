package com.example.nklee.entity.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
@Getter
@Builder
@RedisHash("student")
public class PubSubStudent {

    @Id
    private long studentId;
    private String name;

    public void update(String name) {
        this.name = name;
    }
}
