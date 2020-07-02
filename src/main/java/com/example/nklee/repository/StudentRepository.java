package com.example.nklee.repository;

import com.example.nklee.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByName(String name);

    List<Student> findByGender(Student.Gender gender);
}
