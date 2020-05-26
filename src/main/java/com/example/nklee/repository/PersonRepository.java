package com.example.nklee.repository;

import com.example.nklee.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
