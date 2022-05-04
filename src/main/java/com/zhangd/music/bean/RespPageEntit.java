package com.zhangd.music.bean;

import lombok.Data;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/10/10 11:18
 * @Description:
 */
@Data
public class RespPageEntit {
    private List<?> data;
    private Long total;
}
