package com.zhangd.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangd.music.service.AdminService;
import com.zhangd.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Title: 管理员处理类
 * @Author: dzhang
 * @Date: 2021/7/3 17:26
 * @Description:
 */
@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/checkLogin")
    public Object verifyLogin(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = adminService.verifyLogin(username,password);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            session.setAttribute(Consts.NAME,username);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
