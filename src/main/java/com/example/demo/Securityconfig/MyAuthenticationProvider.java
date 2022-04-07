package com.example.demo.Securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    // ~ Instance Fields
    // -----------------------------------------------------------------------------------------------------------------

    private UserDetailsServiceImpl userDetailsServiceImpl;

    private HashPasswordEncoder bCryptPasswordEncoder;

    // ~ Override Methods
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        // 获取封装用户信息的对象
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        // 进行密码的比对
        boolean flag = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
        // 校验通过
        if (flag) {
            // 将权限信息也封装进去
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }

        throw new AuthenticationException("用户密码错误") {
        };
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    // ~ Autowired
    // -----------------------------------------------------------------------------------------------------------------

    @Autowired
    public void setBCryptPasswordEncoder(HashPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
}
