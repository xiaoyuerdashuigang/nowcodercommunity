package com.pudongdevelopmentbank.service.impl;

import com.pudongdevelopmentbank.dao.UserMapper;
import com.pudongdevelopmentbank.entity.User;
import com.pudongdevelopmentbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    public User selectByEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateStatus(int userId, int userStatus) {
        return userMapper.updateStatus(userId, userStatus);
    }

    @Override
    public int updateHeader(int userId, String userHeaderUrl) {
        return userMapper.updateHeader(userId, userHeaderUrl);
    }

    @Override
    public int updatePassword(int userId, String userPassword) {
        return userMapper.updatePassword(userId, userPassword);
    }
}
