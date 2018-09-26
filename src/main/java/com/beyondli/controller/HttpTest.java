package com.beyondli.controller;

import com.beyondli.service.httptest.HttpTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by beyondLi
 * Date 2018/9/25 14:26
 * Desc .
 */
@RestController
@RequestMapping(value = "/http/client")
public class HttpTest {

    @Resource
    HttpTestService httpTestService;
    /**
     * 获取当前时间 http调用获取
     */
    @RequestMapping(value = "/get/time", method = RequestMethod.GET)
    public String getTime() {
        String info = httpTestService.getTime();
        return info;
    }
}
