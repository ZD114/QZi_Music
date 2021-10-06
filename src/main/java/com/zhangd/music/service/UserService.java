package com.zhangd.music.service;


import com.zhangd.music.bean.User;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/9/12 10:16
 * @Description:
 */
public interface UserService {

    /**
     * 查找用户列表
     */
    public List<User> searchUser();
}
