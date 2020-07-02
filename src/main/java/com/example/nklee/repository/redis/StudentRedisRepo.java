package com.example.nklee.repository.redis;

import com.example.nklee.entity.redis.PubSubStudent;
import org.springframework.data.repository.CrudRepository;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
public interface StudentRedisRepo extends CrudRepository<PubSubStudent, Long> {
}
