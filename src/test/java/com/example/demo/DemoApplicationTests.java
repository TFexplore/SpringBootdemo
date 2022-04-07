package com.example.demo;

import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {
    }
    @Test
    void maptest(){


       User user=userService.findUserByName("zhang");
       System.out.println(user.getPassword());
        //        User user=new User("zhang","123456","admin");
        //        userService.addUser(user);
        //        User user2=new User("Li","123456","user");
        //        userService.addUser(user2);
    }

}
