package com.beyondli.controller;

import com.beyondli.service.http.HttpLogService;
import com.beyondli.service.httptest.HttpTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Resource
    HttpLogService httpLogService;
    /**
     * 获取当前时间 http调用获取
     */
    @GetMapping(value = "/get/time")
    public String getTime() {
        String info = httpTestService.getTime();
        return info;
    }

    /**
     * 测试executeGetWithBasic
     */
    @GetMapping(value = "/get/basic")
    public String getBasic() {
        Object o = httpLogService.executeGetWithBasic("http://39.105.231.218:18083/api/v3/connections/customer_id", "admin", "123456", Object.class);
        System.out.println(o);
        return o.toString();
    }
}
