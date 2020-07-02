package com.example.nklee.controller;

import com.example.nklee.common.CacheKey;
import com.example.nklee.entity.User;
import com.example.nklee.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
@RequiredArgsConstructor
@RequestMapping("/redis")
@RestController
public class RedisController {

    private final UserJpaRepo userJpaRepo;

    @Cacheable(value = CacheKey.USER, key = "#msrl", unless = "#result == null")
    @GetMapping("/user/{msrl}")
    public User findOne(@PathVariable long msrl) {
        return userJpaRepo.findById(msrl).orElse(null);
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return userJpaRepo.save(user);
    }

    @CachePut(value = CacheKey.USER, key = "#user.msrl")
    @PutMapping("/user")
    public User putUser(@RequestBody User user) {
        return userJpaRepo.save(user);
    }

    @CacheEvict(value = CacheKey.USER, key = "#msrl")
    @DeleteMapping("/user/{msrl}")
    public boolean deleteUser(@PathVariable long msrl) {
        userJpaRepo.deleteById(msrl);
        return true;
    }
}
