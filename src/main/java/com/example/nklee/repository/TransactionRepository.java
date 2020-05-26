package com.example.nklee.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
public interface TransactionRepository extends CrudRepository {

    List findByFromAccountId(Long fromAccountId);

    List findByToAccountId(Long toAccountId);
}
