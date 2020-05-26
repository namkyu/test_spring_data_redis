package com.example.nklee.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
@Data
@RedisHash("transaction")
public class Transaction {

    @Id
    private Long id;
    private int amount;
    private Date date;
    @Indexed
    private Long fromAccountId;
    @Indexed
    private Long toAccountId;

    public Transaction(Long id, int amount, Date date, Long fromAccountId, Long toAccountId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

}
