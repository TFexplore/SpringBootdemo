package com.example.demo.Service;

import com.example.demo.Dao.UserDao;
import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;



public interface UserService {

    User findUserByName(String name);

    public User getUserById(int id);


    public void addUser(User user);


    public void updateUser(User user);

    public void deleteUser(int id);
}

