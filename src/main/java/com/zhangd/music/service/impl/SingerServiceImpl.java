package com.zhangd.music.service.impl;

import com.zhangd.music.bean.Singer;
import com.zhangd.music.dao.SingerDao;
import com.zhangd.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title:
 * @Author: dzhang
 * @Date: 2021/7/21 21:14
 * @Description:
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerDao singerDao;

    @Override
    public boolean addSinger(Singer singers) {
        return singerDao.addSinger(singers)>0;
    }

    @Override
    public boolean delSingerById(Integer id) {
        return singerDao.delSingerById(id)>0;
    }

    @Override
    public boolean updateSinger(Singer singers) {
        return singerDao.updateSinger(singers)>0;
    }

    @Override
    public Singer searchSingerById(int id) {
        return singerDao.searchSingerById(id);
    }

    @Override
    public List<Singer> searchSingerByName(String name) {
        return singerDao.searchSingerByName(name);
    }

    @Override
    public List<Singer> searchSinger() {
        return singerDao.searchSinger();
    }
}
