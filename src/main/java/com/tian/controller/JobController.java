package com.tian.controller;

import com.tian.model.Job;
import com.tian.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class JobController {

    @Resource
    private JobService jobService;


    private  static final Logger log = LoggerFactory.getLogger(JobController.class);


    @RequestMapping(value = "/listjob",
            method = RequestMethod.GET)
    public ModelAndView testselectJob(){
        List<Job> jobList = jobService.select();
        log.info(""+jobList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("jobList",jobList);
        modelAndView.setViewName("jobBody");
        return  modelAndView;
    }




    @RequestMapping(value = "/rec",
            method = RequestMethod.GET)
    public ModelAndView recommend(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("recommend");
        return modelAndView;
    }



}
