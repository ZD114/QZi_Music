package com.zhangd.music.dao;

import com.zhangd.music.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/9/12 10:13
 * @Description:
 */
@Mapper
public interface UserDao {
    /**
     * 查找用户列表
     */
    public List<User> searchUser();

    /**
     * 新增用户
     * @param consumer
     * @return
     */
    public int addUser(User consumer);
}
