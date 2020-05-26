package com.example.nklee.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
@Data
@RedisHash("customer")
public class Customer {

    @Id
    private Long id;

    @Indexed
    private String externalId;

    private String name;

    private List<Account> accounts = new ArrayList<>();

    public Customer(Long id, String externalId, String name) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
