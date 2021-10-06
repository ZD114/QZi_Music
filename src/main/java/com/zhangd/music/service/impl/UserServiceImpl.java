package com.zhangd.music.service.impl;

import com.zhangd.music.bean.User;
import com.zhangd.music.dao.UserDao;
import com.zhangd.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/9/12 10:18
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> searchUser() {
        return userDao.searchUser();
    }
}
