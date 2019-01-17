package com.beyondli.service.log;

import com.beyondli.entity.po.httplog.HttpLogOutPO;

/**
 * Created by beyondLi
 * Date 2019/1/17 16:28
 * Desc .
 */
public interface HttpLogRecordService {
    //新增http日志接口
    void addHttpLogOut(HttpLogOutPO httpLogOutPO);
}
