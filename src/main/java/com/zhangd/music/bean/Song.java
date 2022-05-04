package com.zhangd.music.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Song implements Serializable {

    private int id;
    private int singerId;
    private String name;
    private String introduction;
    private Date createTime;
    private Date updateTime;
    private String pic;
    private String lyric;
    private String url;

}
