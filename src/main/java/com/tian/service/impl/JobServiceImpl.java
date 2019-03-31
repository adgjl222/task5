package com.tian.service.impl;

import com.tian.dao.JobMapper;
import com.tian.model.Job;
import com.tian.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class JobServiceImpl implements JobService {
    private  static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
    @Resource
    private JobMapper jobMapper;




    @Override
    public List <Job> select() {
        return this.jobMapper.select();
    }

}
