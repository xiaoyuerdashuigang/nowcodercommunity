package com.pudongdevelopmentbank.service;

import com.pudongdevelopmentbank.entity.User;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
public interface UserService {

    // 根据用户Id查询
    User selectById(int userId);

    // 根据用户姓名进行查询
    User selectByName(String userName);

    // 根据用户邮箱查询
    User selectByEmail(String userEmail);

    // 插入用户
    int insertUser(User user);

    // 更新用户状态
    int updateStatus(int userId, int userStatus);

    // 更新用户的headerUrl
    int updateHeader(int userId, String userHeaderUrl);

    // 更新用户密码
    int updatePassword(int userId, String userPassword);
}
