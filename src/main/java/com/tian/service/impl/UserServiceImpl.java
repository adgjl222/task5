package com.tian.service.impl;

import com.tian.dao.UserMapper;
import com.tian.model.Job;
import com.tian.model.User;
import com.tian.service.UserService;
import com.tian.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 业务层
 */

@Service
public class UserServiceImpl implements UserService {

    private  static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;
    MemCacheManager memCacheManager = MemCacheManager.getInstance();

    @Resource
    private RedisCache redisCache;

    DES des;

    {
        try {
            des = new DES();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
   /* @Override
    public int regist(User user) throws Exception {
        int regist = 0;
        user.setUserPassword(des.encrypt(user.getUserPassword()));
        log.info("注册人 ："+" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword());

        List<Integer>  idList = userMapper.selectName(user.getUserName());
        log.info(" 数据库中："+"姓名为 "+ user.getUserName() +" 的用户为 "+idList);

        //判断集合有无元素也可以使用idList.size() == 0    idList为 null时内存中没有为list集合分配空间 实际上不不存在
        if ( idList != null && idList.isEmpty()){
            regist = userMapper.insertSelective(user);
            String name = user.getUserName();
            memCacheManager.add(name,user);
            log.info(" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword()+" 的用户id为 "+ user.getId()+" ，该用户信息已存入缓存");
            log.info(name+"的用户缓存信息为"+memCacheManager.get(name));
        }else {

            log.info(" 昵称被占用 注册失败");
        }

        return regist;
    }*/

    /**
     * 使用redis进行缓存
     * @param user
     * @return
     * @throws Exception
     */
   @Override
    public int regist(User user) throws Exception {
        int regist = 0;
        user.setUserPassword(des.encrypt(user.getUserPassword()));
        log.info("注册人 ："+" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword());

        List<Integer>  idList = userMapper.selectName(user.getUserName());
        log.info(" 数据库中："+"姓名为 "+ user.getUserName() +" 的用户为 "+idList);

        //判断集合有无元素也可以使用idList.size() == 0    idList为 null时内存中没有为list集合分配空间 实际上不不存在
        if ( idList != null && idList.isEmpty()){
            regist = userMapper.insertSelective(user);
            String name = user.getUserName();
            redisCache.setS(name,user);
            log.info(" username is "+ user.getUserName()+" userpassword is "+ user.getUserPassword()+" 的用户id为 "+ user.getId()+" ，该用户信息已存入redis缓存");
            log.info(name+"的用户缓存信息为"+redisCache.getS(name));
        }else {

            log.info(" 昵称被占用 注册失败");
        }

        return regist;
    }


    /**
     * 校验用户名密码是否相同
     * @param userName
     * @return
     */
    @Override
    public boolean login(String userName,String userPassword) throws Exception {
        boolean login = false;
        userPassword = des.encrypt(userPassword);
        log.info(" 用户输入的userName is " + userName + "加密后的userPassword is " + userPassword);

        List<Integer>  idList = userMapper.selectName(userName);
        log.info(""+idList);
        if (idList != null && idList.isEmpty() ) {
            log.info("用户名不存在");
        } else {
            String userPasswordTrue = userMapper.findPwdByName(userName);
            log.info("userPasswordTrue is  " + userPasswordTrue);
            if (userPassword.equals(userPasswordTrue)) {
                login = true;
                log.info("密码是否相同 "+userPassword.equals(userPasswordTrue));
                log.info("登陆 " + login);

            } else {
                log.info("密码是否相同 "+userPassword.equals(userPasswordTrue));
                login = false;
                log.info(" 密码错误");
            }
        }

        log.info(""+login);
        return login;
    }
}
