package com.example.nklee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
@Data
@AllArgsConstructor
@RedisHash("Person")
public class Person {

    @Id
    private String id;
    @Indexed
    private String firstName;
    private String lastName;

}
