package com.example.nklee.repository;

import com.example.nklee.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Customer findByExternalId(String externalId);

    List findByAccountsId(Long id);
}