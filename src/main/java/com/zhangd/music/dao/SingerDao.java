package com.zhangd.music.dao;

import com.zhangd.music.bean.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/7/20 20:18
 * @Description:
 */
@Mapper
public interface SingerDao {

    /**
     * 增加歌手
     */
    public int addSinger(Singer singers);

    /**
     * 删除歌手
     */
    public int delSingerById(Integer id);

    /**
     * 更新歌手
     */
    public int updateSinger(Singer singers);

    /**
     * 更新歌手图片
     * @param singers
     * @return
     */
    public int updateSingerPic(Singer singers);

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

    /**
     * 进行歌手分页
     */
    public List<Singer> getAllPageSinger(Integer page, Integer size);

    /**
     * 获取总数
     */
    public Long getTotal();
}
