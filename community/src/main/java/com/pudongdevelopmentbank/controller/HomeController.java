package com.pudongdevelopmentbank.controller;

import com.pudongdevelopmentbank.entity.DiscussPost;
import com.pudongdevelopmentbank.entity.Page;
import com.pudongdevelopmentbank.entity.User;
import com.pudongdevelopmentbank.service.DiscussPostService;
import com.pudongdevelopmentbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *@Description TODO
 *@Author:Lihuiming
 *@Date:2022/6/7
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    /**
     * 实现分页的功能
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPages(Model model, Page page) {
        /**
         * getIndexPages()方法调用时,SpringMVC会自动实例化model和page,并将page注入给model
         * 所以在使用Thymeleaf模板时可以直接访问page中的数据
         */
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");
        // 因为查询得到的discussPost只要userId,所以根据userId查询得到的user也输出出来
        List<DiscussPost> list = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost discussPost : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("discussPosts", discussPost);
                Integer userId = discussPost.getUserId();
                User user = userService.selectById(userId);
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        // 把最终要展示的结果装在model中
        model.addAttribute("discussPosts", discussPosts);
        // 返回对应的模板
        return "/index";
    }
}
