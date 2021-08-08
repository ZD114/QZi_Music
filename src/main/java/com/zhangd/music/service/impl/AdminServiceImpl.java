package com.zhangd.music.service.impl;

import com.zhangd.music.dao.AdminDao;
import com.zhangd.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/7/3 17:25
 * @Description:
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    public boolean verifyLogin(String username,String password){
        return adminDao.verifyLogin(username,password);
    }
}
