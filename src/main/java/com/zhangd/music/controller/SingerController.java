package com.zhangd.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangd.music.bean.Singer;
import com.zhangd.music.dao.SingerDao;
import com.zhangd.music.service.SingerService;
import com.zhangd.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @Title: 歌手管理控制层
 * @Author: dzhang
 * @Date: 2021/7/21 21:38
 * @Description:
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * @Title: 新增歌手
     * @Author: dzhang
     * @Date: 2021/7/21 22:03
     * @Description:
     * @Params：[singer]
     * @Return：java.lang.Object
     */
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Singer singer = new Singer();
        String sex = request.getParameter("sex");
        singer.setName(request.getParameter("name"));
        singer.setSex(new Byte(sex));
        singer.setPic(request.getParameter("pic"));
        singer.setLocation(request.getParameter("location"));
        singer.setIntroduction(request.getParameter("introduction"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(request.getParameter("birth").trim());
        }catch (ParseException e){
            e.printStackTrace();
        }
        singer.setBirth(birthDate);
        boolean flag = singerService.addSinger(singer);
        if(flag){//保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * @Title: 删除歌手
     * @Author: dzhang
     * @Date: 2021/7/21 22:13
     * @Description:
     * @Params：[id]
     * @Return：java.lang.Object
     */
    @RequestMapping("/del")
    public Object delSinger(int id){
        boolean flag = singerService.delSingerById(id);
        return flag;
    }

    /**
     * @Title: 修改歌手
     * @Author: dzhang
     * @Date: 2021/7/21 22:03
     * @Description:
     * @Params：[singer]
     * @Return：java.lang.Object
     */
    @RequestMapping("/update")
    public Object updateSinger(@ModelAttribute Singer singer){
        JSONObject jsonObject = new JSONObject();
        boolean flag = singerService.updateSinger(singer);
        if(flag){//保存成功
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * @Title: 根据主键查找歌手
     * @Author: dzhang
     * @Date: 2021/7/21 22:21
     * @Description:
     * @Params：[id]
     * @Return：java.lang.Object
     */
    @RequestMapping("/search")
    public Object searchSinger(int id){
        return singerService.searchSingerById(id);
    }

    /**
     * @Title: 查询所有歌手
     * @Author: dzhang
     * @Date: 2021/7/25 16:17
     * @Description:
     * @Params：[]
     * @Return：java.util.List<com.zhangd.music.bean.Singer>
     */
    @PostMapping("/searchAll")
    public List<Singer> searchSingerAll(){
        return singerService.searchSinger();
    }

    @PostMapping("/updateImg")
    public Object updateImgFile(@RequestParam("file") MultipartFile imgFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(imgFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传图片失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来文件名
        String fileName = System.currentTimeMillis()+imgFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        File file = new File(filePath);
        //若不存在文件路径自己创建一个
        if(!file.exists()){
            file.mkdir();
        }
        //实际文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库中的相对文件地址
        String storeAvatorPath = "/img/singerPic/"+fileName;
        try {
            imgFile.transferTo(dest);//上传到项目中
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.updateSinger(singer);//更新相对路径到数据库中
            if(flag){//上传成功
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传图片成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传图片失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传图片失败"+e.getMessage());
        } finally {
            return jsonObject;
        }
    }
}
