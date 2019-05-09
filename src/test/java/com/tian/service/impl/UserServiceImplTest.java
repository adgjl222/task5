package com.tian.service.impl;

import com.tian.model.User;
import com.tian.service.UserService;
<<<<<<< Updated upstream
import com.tian.util.DataTag;
import com.tian.util.DateTime;
=======
import com.tian.util.*;
>>>>>>> Stashed changes
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)//让测试在spring容器环境下执行
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserServiceImplTest {
    private  static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Resource
    private UserService  userService;
    ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");

<<<<<<< Updated upstream
    @Test
    public void testRegist() throws Exception {
        User user = new User();
        user.setUserName("王众");
=======

    @Resource
    private RedisCache redisCache;
    SMS sms = (SMS) context.getBean("smsSDK");

/*    @Test
    public void testRegist() throws Exception {
        User user = new User();
        user.setUserName("8ht");
>>>>>>> Stashed changes
        user.setUserPassword("4598tiaoenr");
        user.setEmail("162806744@qq.com");
        user.setCreatedAt(DateTime.getTime());
        log.info("user  is "+user+user.getEmail());

        String msgCode ="22222";
        int i = userService.sendEmai("162806744q.com",msgCode);
        log.info(" i = "+ i);
//        int c = userService.checkEmailCode("162806744q.com",msgCode);
//        log.info(" c = "+ c);
//        if (c == 0){
//            userService.regist(user);
//        }

    }


    @Test
    public void login() throws Exception {
        String userName = "王众";
        String userPassword ="4598tiaoenr";
        userService.login(userName,userPassword);

    }

<<<<<<< Updated upstream
=======
  *//*  @Test
    public void test(){
        Jedis jedis =new Jedis("127.0.0.1");
        jedis.auth("twr950222");
        System.out.println(jedis.ping());

    }*//*

  @Test
  public void testUpdate(){
      String img = "http://sweetsalt.oss-cn-beijing.aliyuncs.com/ss/ba95170315c3426b82c95fee7783a63f1557395472620.jpg";
      User user = new User();
      user.setId(42);
      user.setSalt("22");
      user.setUpdatedAt(DateTime.getTime());
      log.info(""+user);
      userService.updateUser(user);


  }

    @Test
    public  void sendMessage() {
        // 生成6位随机验证码
        String msgCode = DataUtils.getNumber(6);
        String tel = "18538522246";
        sms.sendMessage(tel,msgCode);
    }*/


    @Test
    public  void sendE() {
        // 生成6位随机验证码
        String msgCode = "222222";
        String email = "1938742950@qq.com";
        userService.sendEmai(email,msgCode);
        log.info(""+userService.checkEmailCode(email,msgCode));
        log.info(""+redisCache.getS("msgCode89"));


       User user = new User();
       user.setUserName("a");
       String a = "2";
       redisCache.setS("a",user);
    log.info("jdfkj"+redisCache.getS("a"));


    }
>>>>>>> Stashed changes
}