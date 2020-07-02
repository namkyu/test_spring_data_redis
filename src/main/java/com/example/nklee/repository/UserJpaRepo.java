package com.example.nklee.repository;

import com.example.nklee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
public interface UserJpaRepo extends JpaRepository<User, Long> {

    Optional<User> findByUid(String email);
}
