package com.ElcTest.controller;

import com.ElcTest.controller.ViewObject.UserVO;
import com.ElcTest.error.BusinessException;
import com.ElcTest.error.EmBusinessError;
import com.ElcTest.response.CommonReturnType;
import com.ElcTest.service.UserService;
import com.ElcTest.service.impl.UserServiceImpl;
import com.ElcTest.service.model.UserModel;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
       //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel=userService.getUserById(id);
        //若获取的对应用户信息不存在
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO tUserV0= getcontentFromUserModel(userModel);
        //返回通用对象
        return CommonReturnType.create(tUserV0);

    }
    public UserVO getcontentFromUserModel(UserModel userModel){
        if(userModel==null){
            return null;
        }else{
            UserVO userVO=new UserVO();
            BeanUtils.copyProperties(userModel,userVO);
            return userVO;
        }
    }

    //定义exceptionhandler解决未被controller层吸收的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request,Exception ex){
        BusinessException businessException=(BusinessException)ex;
        Map<String,Object> responsebody= new  HashMap<String,Object>();
        responsebody.put("errCode",businessException.getErrCode());
        responsebody.put("errMsg",businessException.getErrMsg());
        return CommonReturnType.create(responsebody,"fail");
    }
}
