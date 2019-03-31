package com.tian.service;

import com.tian.model.User;

public interface UserService {

    //用户注册
    int regist(User user) throws Exception;

    //用户登陆
    boolean login(String userName,String userPassword) throws Exception;
}
