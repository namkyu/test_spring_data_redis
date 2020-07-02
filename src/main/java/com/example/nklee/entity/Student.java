package com.example.nklee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Student")
public class Student  {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    private String id;
    @Indexed
    private String name;
    @Indexed
    private Gender gender;
    private int grade;
    private LocalDateTime localDateTime;
//    @TimeToLive
    private Long expiration;
}