package com.example.demo.Controller;

import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.bean.User;
import com.example.demo.config.HashPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    UserServiceImpl userService;
    @Resource
    HashPasswordEncoder encoder;

    @PostMapping("tologin")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        System.out.println("login ing");
        User user=userService.findUserByName(username);
        if (user==null){
            model.addAttribute("msg", "未注册");
            return "/common/index";
        }else if (user.getPassword().equals(encoder.encode(password))){
            return "/user/suc";
        }
        model.addAttribute("msg", "密码错误：\n"+user.getPassword()+"\n"+encoder.encode(password));
        return "/common/index";
    }
}

