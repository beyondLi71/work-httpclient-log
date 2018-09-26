package com.beyondli.service.http;


import java.util.Map;

/**
 * @author zhongjf
 * @date 2018/9/10
 * @desc http请求Service层
 */
public interface HttpLogService {

    <T> T executeGet(String url, Class<T> clazz);

    <T> T executePostWithForm(String url, Map param, Class<T> clazz);

    <T> T executePost(String url, String param, Class<T> clazz);

    <T> T executePost(String url, String param, Map<String, String> headerMaps, Class<T> clazz);

    <T> T executePut(String url, String param, Class<T> clazz);

    <T> T executePut(String url, String param, Map<String, String> headerMap, Class<T> clazz);



}
