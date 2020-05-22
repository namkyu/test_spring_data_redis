package com.example.nklee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
