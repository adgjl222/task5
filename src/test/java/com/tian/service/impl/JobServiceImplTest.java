package com.tian.service.impl;

import com.tian.dao.JobMapper;
import com.tian.model.Job;
import com.tian.service.JobService;
import com.tian.util.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)//让测试在spring容器环境下执行
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class JobServiceImplTest {


    private  static final Logger log = LoggerFactory.getLogger(JobServiceImplTest.class);
    @Resource
    private JobService jobService;


    @Test
    public void testSelect(){
        List<Job> jobList = jobService.select();
        log.info(""+jobList);
      /*  for (Job job:jobList){
            System.out.println(job);
        }*/
      for (int i = 0; i<jobList.size(); i++ ){
          System.out.println(jobList.get(i));
      }


    }



}