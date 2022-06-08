package com.pudongdevelopmentbank;

import com.pudongdevelopmentbank.dao.DiscussPostMapper;
import com.pudongdevelopmentbank.dao.UserMapper;
import com.pudongdevelopmentbank.entity.DiscussPost;
import com.pudongdevelopmentbank.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/***
 *@Description 进行单元测试
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(150);
        System.out.println("===========================查询到的结果为==========================");
        System.out.println(user);
    }

    @Test
    public void testSelectByName() {
        User user = userMapper.selectByName("nowcoder23");
        System.out.println("===========================查询到的结果为==========================");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("Tom3");
        user.setUserPassword("1234567890");
        user.setUserSalt("derfgfr");
        user.setUserEmail("defgfrgty@qq.com");
        user.setUserHeaderUrl("http://www.nowcoder.com/109.png");
        user.setUserCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println("**********************************");
        System.out.println(user.getUserId());
    }

    @Test
    public void testSelectDiscussPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 10, 10);
        for (DiscussPost discussPost : list) {
            System.out.println(discussPost);
        }
        System.out.println("=============================================================");
        int rows = discussPostMapper.selectDiscussPostRows(111);
        System.out.println("查询到的总的行数为: " + rows);
    }
}
