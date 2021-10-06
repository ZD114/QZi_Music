package com.zhangd.music.bean;

import lombok.Data;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/9/12 10:03
 * @Description:
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String sex;

    private String phone_num;

    private String email;

    private String birth;

    private String introduction;

    private String location;

    private String avator;

    private String create_time;

    private String update_time;
}
