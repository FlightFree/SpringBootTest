package com.ElcTest.service;

import com.ElcTest.service.model.UserModel;


public interface UserService {
    //通过用户id获取用户的方法
    UserModel getUserById(Integer id);
}
