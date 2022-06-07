package com.pudongdevelopmentbank.service;

import com.pudongdevelopmentbank.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
public interface DiscussPostService {

    // 实现分页查询
    List<DiscussPost> selectDiscussPosts(Integer userId, Integer offset, Integer limit);

    // 查询对应的userId没有被拉黑的总的数据
    int selectDiscussPostRows(@Param("userId") Integer userId);
}
