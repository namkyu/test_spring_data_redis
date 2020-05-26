package com.example.nklee;


import com.example.nklee.entity.Person;
import com.example.nklee.entity.Student;
import com.example.nklee.repository.PersonRepository;
import com.example.nklee.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test_insert() {
        IntStream.range(1, 6)
            .mapToObj(String::valueOf)
            .forEach(id -> {
                Person person = new Person(id , "namkyu" + id, "lee");
                personRepository.save(person);
            });
    }



}