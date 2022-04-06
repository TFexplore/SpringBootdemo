package com.example.demo.Dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    User getUserById(int id);
    User findUserByName(String name);
    List<User> queryAll(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
