package com.example.demo.Service.impl;

import com.example.demo.Dao.UserDao;
import com.example.demo.Service.UserService;
import com.example.demo.bean.User;
import com.example.demo.config.HashPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    HashPasswordEncoder encoder=new HashPasswordEncoder();

    @Override
    public User findUserByName(String name) {
        System.out.println("find");
        return userDao.findUserByName(name);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

         userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }




}
