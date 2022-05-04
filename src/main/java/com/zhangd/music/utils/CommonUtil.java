package com.zhangd.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/12/5 11:20
 * @Description:
 */
public class CommonUtil {
    /**
     * 生成32位编码,不含横线
     *
     * @return uuid串
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid.toUpperCase();
    }

    /**
     * 得到当前日期格式化后的字符串，格式：yyyy-MM-dd(年-月-日)
     * @return 当前日期格式化后的字符串
     */
    public static String getTodayStr(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
    }

}
