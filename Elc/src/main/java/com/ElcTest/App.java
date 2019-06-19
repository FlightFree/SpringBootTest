package com.ElcTest;

import com.ElcTest.DataObject.User;
import com.ElcTest.dao.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.ElcTest"})
@RestController
@MapperScan("com.ElcTest.dao")
public class App 
{
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String home(){
        User user= userMapper.selectByPrimaryKey(1);
        if(user==null){
            return "用户对象不存在";
        }else{
            return user.getName();
        }

    }
    public static void main( String[] args )
    {
        LocalDate t=LocalDate.now();
        System.out.println( t );
        SpringApplication.run(App.class,args);
    }

}
