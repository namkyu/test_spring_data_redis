package com.example.nklee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
@Data
@AllArgsConstructor
public class Account {

    @Indexed
    private Long id;

    private String number;

    private int balance;
}
