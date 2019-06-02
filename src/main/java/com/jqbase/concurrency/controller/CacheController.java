package com.jqbase.concurrency.controller;

import com.jqbase.concurrency.cache.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/17 23:18
 */
@RestController
@RequestMapping("cache")
@Slf4j
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("k") String k,
                      @RequestParam("v") String v) throws Exception {
        log.info("redis set {}->{}",k,v);
        redisClient.set(k,v);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("k") String k) throws Exception {
        return redisClient.get(k);
    }
}
