package com.zhangd.music.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/get/{key}")
    public Object getKey(@PathVariable("key") String key){
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/set/{key}/{value}")
    public Object setKey(@PathVariable("key") String key,
                         @PathVariable("value") String value){
        redisTemplate.opsForValue().set(key,value);
        return "set success";
    }
}
