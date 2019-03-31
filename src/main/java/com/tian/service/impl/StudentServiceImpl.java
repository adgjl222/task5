package com.tian.service.impl;

import com.tian.controller.JobController;
import com.tian.dao.StudentMapper;
import com.tian.model.Student;
import com.tian.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private  static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Resource
    private StudentMapper studentMapper;



    /**
     * 查询全表并根据薪酬降序排列（优秀学员：按照薪酬由高到低显示4位）
     * @return
     */
    @Override
    public List<Student> select( ) {

        //查全表根据工资降序排列
        List<Student> studentList = studentMapper.select();

        //取全部结果的工资前4位放置新list中
        List<Student> students = new ArrayList <>();
        for(int i =0;i <4; i++){
            students.add(studentList.get(i));
        }
        log.info("列表长度为 "+ students.size() +" 工资排名前4的是 "+students);
        return students;

    }

}
