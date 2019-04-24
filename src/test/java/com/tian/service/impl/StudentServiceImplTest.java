package com.tian.service.impl;

import com.tian.model.Student;
import com.tian.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)//让测试在spring容器环境下执行
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class StudentServiceImplTest {


    private  static final Logger log = LoggerFactory.getLogger(StudentServiceImplTest.class);

    @Resource
    private StudentService studentService;

    @Test
    public void testSelect(){
        List<Student> studentList = studentService.select();
        for ( Student student:studentList){
            System.out.println(student);
        }
    }

}