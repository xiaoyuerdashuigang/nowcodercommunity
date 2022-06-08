package com.pudongdevelopmentbank.service.impl;

import com.pudongdevelopmentbank.dao.UserMapper;
import com.pudongdevelopmentbank.entity.User;
import com.pudongdevelopmentbank.service.UserService;
import com.pudongdevelopmentbank.util.CommunityConstant;
import com.pudongdevelopmentbank.util.CommunityUtil;
import com.pudongdevelopmentbank.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
@Service
public class UserServiceImpl implements UserService, CommunityConstant {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Resource
    private TemplateEngine templateEngine;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

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

    /**
     * 注册方法
     *
     * @param user
     * @return
     */
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        // 对信息进行判断
        if (user == null) {
            throw new IllegalArgumentException("传入的参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            map.put("userNameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getUserPassword())) {
            map.put("userPasswordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getUserEmail())) {
            map.put("userEmailMsg", "邮箱不能为空!");
            return map;
        }
        // 验证账号
        User userByName = userMapper.selectByName(user.getUserName());
        if (userByName != null) {
            map.put("userNameMsg", "该账号已经存在!");
            return map;
        }
        // 验证邮箱
        User userByEmail = userMapper.selectByEmail(user.getUserEmail());
        if (userByEmail != null) {
            map.put("userEmailMsg", "该邮箱已经被注册!");
            return map;
        }
        // 注册用户
        user.setUserSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setUserPassword(user.getUserPassword() + user.getUserSalt());
        user.setUserType(0);
        user.setUserStatus(0);
        user.setUserActivationCode(CommunityUtil.generateUUID());
        // 设置头像的地址,牛客默认有1000张头像
        user.setUserHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setUserCreateTime(new Date());
        userMapper.insertUser(user);
        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getUserEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getUserId() + "/" + user.getUserActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getUserEmail(), "激活", content);
        return map;
    }

    /**
     * 激活状态的反馈
     *
     * @param userId
     * @param code
     * @return
     */
    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if (user.getUserStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getUserActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILURE;
        }
    }
}
