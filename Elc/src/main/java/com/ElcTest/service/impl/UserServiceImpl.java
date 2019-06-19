package com.ElcTest.service.impl;

import com.ElcTest.DataObject.User;
import com.ElcTest.DataObject.UserPassword;
import com.ElcTest.dao.UserMapper;
import com.ElcTest.dao.UserPasswordMapper;
import com.ElcTest.service.UserService;
import com.ElcTest.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Override
    public UserModel getUserById(Integer id) {
        //调用usermapper获取对应的dataobject
        User userData= userMapper.selectByPrimaryKey(id);
        if(userData==null){
            return null;
        }
        //调用用户id获取对应的用户加密密码信息
        UserPassword userPassword = userPasswordMapper.selectByUserId(userData.getId());
        return convertFromDataObject(userData,userPassword);
    }
    private UserModel convertFromDataObject(User user, UserPassword userPassword){
        if(user==null){
            return null;
        }
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(user,userModel);
        if(userPassword!=null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
