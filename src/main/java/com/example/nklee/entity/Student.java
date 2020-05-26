package com.example.nklee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@Data
@AllArgsConstructor
@RedisHash("Student")
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    private String id;
    private String name;
    private Gender gender;
    private int grade;
}