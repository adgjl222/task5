package com.tian.service.impl;

import com.tian.model.User;
import com.tian.service.UserService;
import com.tian.util.DataTag;
import com.tian.util.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)//让测试在spring容器环境下执行
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserServiceImplTest {
    private  static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Resource
    private UserService  userService;

    @Test
    public void testRegist() throws Exception {
        User user = new User();
        user.setUserName("王众");
        user.setUserPassword("4598tiaoenr");
        user.setCreatedAt(DateTime.getTime());
        userService.regist(user);
    }


    @Test
    public void login() throws Exception {
        String userName = "王众";
        String userPassword ="4598tiaoenr";
        userService.login(userName,userPassword);

    }

}