package com.beyondli.service.httptest;

import com.beyondli.entity.vo.test.TestResponse;
import com.beyondli.service.http.HttpLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by beyondLi
 * Date 2018/9/25 14:28
 * Desc .
 */
@Service
public class HttpTestServiceImpl implements HttpTestService {

    @Resource
    HttpLogService httpLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getTime() {
        TestResponse testResponse = httpLogService.executeGet("http://127.0.0.1:7166/test/get/now/time", TestResponse.class);
        if ( !Objects.equals(testResponse.getCode(), "0") ) {
            //根据项目架构进行异常抛出
            throw new RuntimeException("date-error");
            //throw exceptionManager.createByCode("HTTP_0002");//返回状态非法
        }
        return "获取到的数据为: " + testResponse.toString();
    }
}
