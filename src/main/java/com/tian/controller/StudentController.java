package com.tian.controller;

import com.tian.model.Student;
import com.tian.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class StudentController {

    private  static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Resource
    private StudentService studentService;



    @RequestMapping(value = "/stu",
    method = RequestMethod.GET)
    public ModelAndView selectStudent(){

        log.info("进入首页");
        //查全表根据工资降序排列
        List<Student> studentList = studentService.select();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students",studentList);
        modelAndView.setViewName("studentBody");
        return modelAndView;
    }
}
