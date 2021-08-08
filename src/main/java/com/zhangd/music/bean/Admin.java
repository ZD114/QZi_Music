package com.zhangd.music.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/7/3 16:43
 * @Description:
 */
@Data
public class Admin implements Serializable {
    //主键
    private Integer id;

    //用户名
    private String name;

    //密码
    private String password;

}
