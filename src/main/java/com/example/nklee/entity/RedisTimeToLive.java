package com.example.nklee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Data
@AllArgsConstructor
@RedisHash(value = "ttl")
public class RedisTimeToLive {

    @Id
    private Long id;

    @TimeToLive
    private Long ttl;
}
