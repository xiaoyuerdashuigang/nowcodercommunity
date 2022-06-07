package com.pudongdevelopmentbank.service.impl;

import com.pudongdevelopmentbank.dao.DiscussPostMapper;
import com.pudongdevelopmentbank.entity.DiscussPost;
import com.pudongdevelopmentbank.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPost> selectDiscussPosts(Integer userId, Integer offset, Integer limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    @Override
    public int selectDiscussPostRows(Integer userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
