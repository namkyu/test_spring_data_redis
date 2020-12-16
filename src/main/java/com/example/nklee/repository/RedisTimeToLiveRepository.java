package com.example.nklee.repository;

import com.example.nklee.entity.RedisTimeToLive;
import org.springframework.data.repository.CrudRepository;


public interface RedisTimeToLiveRepository extends CrudRepository<RedisTimeToLive, String> {
}