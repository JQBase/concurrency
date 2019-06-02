package com.jqbase.concurrency.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/17 23:10
 */
@Component
@Slf4j
public class RedisClient {

    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    public void set(String key,String value) throws Exception{
        log.info("into set");
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key,value);
            log.info("redis {}",jedis.expire("key",10));
        }  finally {
            if (jedis!=null){
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = jedisPool.getResource();
        String value = "";
        try {
            value = jedis.get(key);
        }  finally {
            if (jedis!=null) {
                jedis.close();
            }
        }
        return value;
    }

}
