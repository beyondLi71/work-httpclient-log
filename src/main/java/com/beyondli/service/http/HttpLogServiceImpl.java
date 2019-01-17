package com.beyondli.service.http;

import com.beyondli.common.utils.NewRequestUtils;
import com.beyondli.entity.po.http.HttpResult;
import com.beyondli.repository.httplog.HttpLogCUDMapper;
import com.beyondli.service.log.HttpLogRecordService;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: beyondLi
 * @Date: 2018/9/11
 * @Description: 日志记录service层
 */
@Service
public class HttpLogServiceImpl implements HttpLogService {

/*    @Resource
    HttpLogCUDMapper httpLogCUDMapper;*/
    @Resource
    HttpLogRecordService httpLogRecordService;

    @Override
    public <T> T executeGet(String url, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doGet(url);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    @Override
    public <T> T executePostWithForm(String url, Map param, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doPostWithForm(url, param);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    @Override
    public <T> T executePost(String url, String param, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doPost(url, param);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    @Override
    public <T> T executePost(String url, String param, Map<String, String> headerMaps, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doPost(url, param, headerMaps);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    @Override
    public <T> T executePut(String url, String param, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doPut(url, param);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    @Override
    public <T> T executePut(String url, String param, Map<String, String> headerMap, Class<T> clazz) {
            //网络请求
            HttpResult httpResult = NewRequestUtils.doPut(url, param, headerMap);
            //添加http日志记录
            httpLogRecordService.addHttpLogOut(httpResult.getHttpLogOutPO());
            //判断状态码以及数据类型转换
            return verifyStatusCodeAndJsonFormat(httpResult, clazz);
    }

    public <T> T verifyStatusCodeAndJsonFormat(HttpResult httpResult, Class<T> clazz) {
        if (httpResult.getStatusCode().contains("404") || httpResult.getStatusCode().contains("504") || httpResult.getStatusCode().contains("EXC")) {
            //根据业务需求进行异常处理
            throw new RuntimeException("http-error");
            //throw exceptionManager.createByCode("HTTP_0002");//http请求异常
        }
        try {
            System.out.println(httpResult.getContent());
            return JSONObject.parseObject(httpResult.getContent(), clazz);
        }catch (JSONException e) {
            //根据项目架构进行异常抛出
            throw new RuntimeException("json-error");
            //throw exceptionManager.createByCode("HTTP_0002");//Json转换异常
        }
    }
}