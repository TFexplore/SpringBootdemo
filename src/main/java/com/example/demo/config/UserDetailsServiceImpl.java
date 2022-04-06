package com.example.demo.config;
import com.example.demo.Dao.UserDao;
import com.example.demo.Service.UserService;
import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.bean.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);


    HashPasswordEncoder passwordEncoder=new HashPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null) {
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String roleName =user.getRole();
        System.out.println("------------------------");

        if ("admin".equals(roleName)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        System.out.println("len: "+authorities.size());
        org.springframework.security.core.userdetails.User userdetail
                = new org.springframework.security.core.userdetails.User(
                        user.getName(),
               user.getPassword(),
                authorities);
        System.out.println("管理员信息："+user.getName()+"   "+
                passwordEncoder.encode(user.getPassword())+"  "
                +userdetail.getAuthorities());
        //通过客户端输入的用户名查询，若数据库有该用户名则查出该用户名对应的Hash密码，然后进行原字符加密(加密后字符不变),与客户端输入密码Hash后比较
        return userdetail;
    }
}
