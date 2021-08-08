package com.zhangd.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/7/3 16:52
 * @Description:
 */
@Mapper
public interface AdminDao {
    public boolean verifyLogin(@Param("username") String username, @Param("password") String password);
}
