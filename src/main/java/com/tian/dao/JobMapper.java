package com.tian.dao;

import com.tian.model.Job;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {



    List <Job> select();


}