package com.example.nklee;


import com.example.nklee.entity.Student;
import com.example.nklee.repository.StudentRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-05-22
 * @Author : nklee
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisKeyValueTemplate redisKeyValueTemplate;

    @Before
    public void init() {
        Student student1 = new Student("20200522_1", "nklee1", Student.Gender.MALE, 1, LocalDateTime.now(), 100L);
        Student student2 = new Student("20200522_2", "nklee2", Student.Gender.MALE, 1, LocalDateTime.now(), 100L);
        Student student3 = new Student("20200522_3", "nklee3", Student.Gender.FEMALE, 2, LocalDateTime.now(), 100L);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }

    @Test
    public void test_select() {
        Student retrievedStudent = studentRepository.findById("20200522_1").get();
        assertThat("nklee1", is(retrievedStudent.getName()));
    }

    @Test
    public void test_partial_update() {
        Student entity = studentRepository.findById("20200522_1").get();
        entity.setName("nklee_update_redisKeyValueTemplate");
        redisKeyValueTemplate.update(entity);
    }

    @Test
    public void test_change() {
        Student retrievedStudent = studentRepository.findById("20200522_1").get();
        retrievedStudent.setName("nklee_update");
        studentRepository.save(retrievedStudent);
    }

    @Test
    public void test_findAll() {
        studentRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void test_findByName() {
        Student student = studentRepository.findByName("nklee1");
        MatcherAssert.assertThat("nklee1", is(student.getName()));
    }

    @Test
    public void test_findByGender() {
        List<Student> students = studentRepository.findByGender(Student.Gender.MALE);
        System.out.println(students);
    }

    @Test
    public void test_delete_entity() {
        Student student = studentRepository.findByName("nklee1");
        studentRepository.delete(student);
        Student removedEntity = studentRepository.findByName("nklee1");
        MatcherAssert.assertThat(removedEntity, is(nullValue()));
    }


}