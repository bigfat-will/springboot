package com.smallfat.demo.springboot.config;



import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisUtil {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key,String value,Long liveTime){
        if(liveTime == null){
            redisTemplate.opsForValue().set(key,value);
        }else{
            redisTemplate.opsForValue().set(key,value,liveTime,TimeUnit.SECONDS);
        }
    }
    /**
     *
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 
     * @param key
     */
    public void del(String key) {
    	redisTemplate.delete(key);
    }
}
