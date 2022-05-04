package com.zhangd.music.service.impl;

import com.zhangd.music.bean.RespPageEntit;
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
    public boolean updateSingerPic(Singer singers){
        return singerDao.updateSingerPic(singers)>0;
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

    @Override
    public RespPageEntit getAllPageSinger(Integer page, Integer size){
        RespPageEntit respPageEntit = new RespPageEntit();
        if(page!=null && size!=null){
            page = (page-1)*size;
        }
        List<Singer> singers = singerDao.getAllPageSinger(page,size);
        respPageEntit.setData(singers);//查询出来数据放入实体
        //获取总数
        Long total = singerDao.getTotal();
        respPageEntit.setTotal(total);
        return respPageEntit;
    }
}
