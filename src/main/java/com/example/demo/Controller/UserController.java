package com.example.demo.Controller;

import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.bean.User;
import com.example.demo.Securityconfig.HashPasswordEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@Api
public class UserController {

    @Resource
    UserServiceImpl userService;
    @Resource
    HashPasswordEncoder encoder;

    @PostMapping("tologin")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParam(name = "username", value = "string", required = true, dataType = "String", paramType = "query")
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

