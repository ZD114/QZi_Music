package com.zhangd.music.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: 歌手实体类
 * @Author: dzhang
 * @Date: 2021/7/20 20:17
 */
@Data
public class Singer implements Serializable {
    //主键
    private Integer id;

    //用户名
    private String name;

    //年龄
    private Byte sex;

    //图片
    private String pic;

    //生日
    private Date birth;

    //地区
    private String location;

    //介绍
    private String introduction;

}
