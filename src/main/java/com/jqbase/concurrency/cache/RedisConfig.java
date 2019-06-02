package com.jqbase.concurrency.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/17 22:56
 */
@Configuration
public class RedisConfig {

    @Bean(name="jedisPool")
    public JedisPool jedisPool(@Value("${spring.redis.host}")String host, @Value("${spring.redis.port}")int port){
        return new JedisPool(host,port);
    }
}
