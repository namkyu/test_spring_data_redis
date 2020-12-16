package com.example.nklee;

import com.example.nklee.entity.Account;
import com.example.nklee.entity.Customer;
import com.example.nklee.entity.RedisTimeToLive;
import com.example.nklee.repository.CustomerRepository;
import com.example.nklee.repository.RedisTimeToLiveRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-25
 * @Author : nklee
 * @Description :
 */

// https://dzone.com/articles/introduction-to-spring-data-redis
@RunWith(SpringRunner.class)
@DataRedisTest
public class RedisCustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Autowired
    RedisTimeToLiveRepository redisTimeToLiveRepository;

    @Test
    public void testAdd() {
        Customer customer = new Customer(1L, "80010121098", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000, 10L));
        customer.addAccount(new Account(2L, "1234567891", 4000, 10L));
        customer.addAccount(new Account(3L, "1234567892", 6000, 10L));
        customer = repository.save(customer);
        Assert.assertNotNull(customer);
    }

    @Test
    public void testTTL() {
        RedisTimeToLive redisTimeToLive = new RedisTimeToLive(1L, 20L);
        redisTimeToLiveRepository.save(redisTimeToLive);
    }

    @Test
    public void testFindByAccounts() {
        List<Customer> customers = repository.findByAccountsId(3L);
        Assert.assertEquals(1, customers.size());
        Customer customer = customers.get(0);
        Assert.assertNotNull(customer);
        Assert.assertEquals(1, customer.getId().longValue());
    }

    @Test
    public void testFindByExternal() {
        Customer customer = repository.findByExternalId("80010121098");
        Assert.assertNotNull(customer);
        Assert.assertEquals(1, customer.getId().longValue());
    }

}
