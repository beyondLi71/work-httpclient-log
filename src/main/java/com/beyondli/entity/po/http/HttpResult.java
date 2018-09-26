package com.beyondli.entity.po.http;


import com.beyondli.entity.po.httplog.HttpLogOutPO;

/**
 * @Auther: beyondLi
 * @Date: 2018/9/12 0012 00:31
 * @Description:
 */
public class HttpResult {

    //网络返回码
    private String statusCode;

    //返回内容
    private String Content;

    //记录日志集合
    private HttpLogOutPO httpLogOutPO;

    public HttpResult() {
    }

    public HttpResult(String statusCode, String content, HttpLogOutPO httpLogOutPO) {
        this.statusCode = statusCode;
        Content = content;
        this.httpLogOutPO = httpLogOutPO;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public HttpLogOutPO getHttpLogOutPO() {
        return httpLogOutPO;
    }

    public void setHttpLogOutPO(HttpLogOutPO httpLogOutPO) {
        this.httpLogOutPO = httpLogOutPO;
    }
}
