package com.zhangd.music.service;

import com.zhangd.music.bean.Singer;

import java.util.List;

/**
 * @Title: 歌手
 * @Author: dzhang
 * @Date: 2021/7/21 21:11
 * @Description:
 */
public interface SingerService {

    /**
     * 增加歌手
     */
    public boolean addSinger(Singer singers);

    /**
     * 删除歌手
     */
    public boolean delSingerById(Integer id);

    /**
     * 更新歌手
     */
    public boolean updateSinger(Singer singers);

    /**
     * 根据主键查找歌手
     */
    public Singer searchSingerById(int id);

    /**
     * 根据歌手名模糊查找歌手
     */
    public List<Singer> searchSingerByName(String name);

    /**
     * 查找歌手列表
     */
    public List<Singer> searchSinger();
}
