package com.jqbase.concurrency.controller;


import com.jqbase.concurrency.entity.Girl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/1 22:58
 */

@RestController
@Slf4j
public class GirlController {

    @Autowired
    private Girl girl;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test() {
        return "hello" + girl;
    }

    @RequestMapping(value = "/getname/{id}", method = RequestMethod.GET)
    public String getname(@PathVariable("id") Integer myId) {
        return "id:" + myId;
    }

    @RequestMapping(value = "/getage", method = RequestMethod.GET)
    public String getAge(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id:" + myId;
    }

    @GetMapping(value = "/getAll")
    public String getAll(@RequestParam("id") Integer myId) {
        return "id:" + myId;
    }
}
