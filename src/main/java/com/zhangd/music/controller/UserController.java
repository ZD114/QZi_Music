package com.zhangd.music.controller;

import com.zhangd.music.bean.User;
import com.zhangd.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/9/12 10:07
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Title: 查询所有歌手
     * @Author: dzhang
     * @Date: 2021/7/25 16:17
     * @Description:
     * @Params：[]
     * @Return：java.util.List<com.zhangd.music.bean.Singer>
     */
    @PostMapping("/searchAll")
    public List<User> searchUserAll(){
        return userService.searchUser();
    }
}
