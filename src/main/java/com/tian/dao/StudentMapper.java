package com.tian.dao;

import com.tian.model.Student;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {


    List<Student>  select();

}