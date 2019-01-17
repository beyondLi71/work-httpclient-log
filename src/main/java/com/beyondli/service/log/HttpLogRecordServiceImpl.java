package com.beyondli.service.log;

import com.beyondli.entity.po.httplog.HttpLogOutPO;
import com.beyondli.repository.httplog.HttpLogCUDMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by beyondLi
 * Date 2019/1/17 16:29
 * Desc .
 */
@Service
public class HttpLogRecordServiceImpl implements HttpLogRecordService{
    @Resource
    HttpLogCUDMapper httpLogCUDMapper;

    @Async
    public void addHttpLogOut(HttpLogOutPO httpLogOutPO) {
        httpLogCUDMapper.addHttpLogOut(httpLogOutPO);
    }
}
