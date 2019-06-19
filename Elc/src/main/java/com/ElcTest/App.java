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
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

}
