package com.example.nklee;


import com.example.nklee.entity.Student;
import com.example.nklee.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_insert() {
        Student student = new Student("20200522", "nklee", Student.Gender.MALE, 1);
        studentRepository.save(student);
    }

    @Test
    public void test_select() {
        Student retrievedStudent = studentRepository.findById("20200522").get();
        assertThat("nklee", is(retrievedStudent.getName()));
    }

    @Test
    public void test_change() {
        Student retrievedStudent = studentRepository.findById("20200522").get();
        retrievedStudent.setName("nklee2");
        studentRepository.save(retrievedStudent);
    }

    @Test
    public void test_findAll() {
        Student student1 = new Student("20200522_1", "nklee1", Student.Gender.MALE, 1);
        Student student2 = new Student("20200522_2", "nklee2", Student.Gender.MALE, 2);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.findAll().forEach(System.out::println);
    }

}