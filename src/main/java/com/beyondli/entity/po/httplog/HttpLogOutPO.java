package com.beyondli.entity.po.httplog;


import java.time.LocalDateTime;

/**
 * @author beyondLi
 * @date 2018/9/10
 * @desc 系统日志字段信息
 */
public class HttpLogOutPO {

    //日志编号
    private int id;

    //请求地址
    private String reqUrl;

    //请求方式
    private String reqMethod;

    //请求类型
    private String reqType;

    //请求参数
    private String reqParams;

    //报错系统级别的错误：0为没有，其他为异常
    private String respCode;

    //响应错误消息体
    private String respResult;

    //响应时间
    private long respTime;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    //状态值
    private String stat;

    public HttpLogOutPO() {
    }

    public HttpLogOutPO(int id, String reqUrl, String reqMethod, String reqType, String reqParams, String respCode, String respResult, long respTime, LocalDateTime createTime, LocalDateTime updateTime, String stat) {
        this.id = id;
        this.reqUrl = reqUrl;
        this.reqMethod = reqMethod;
        this.reqType = reqType;
        this.reqParams = reqParams;
        this.respCode = respCode;
        this.respResult = respResult;
        this.respTime = respTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stat = stat;
    }

    public HttpLogOutPO(String reqUrl, String reqMethod, String reqType, String reqParams, String respCode, String respResult, long respTime, LocalDateTime createTime, LocalDateTime updateTime, String stat) {
        this.reqUrl = reqUrl;
        this.reqMethod = reqMethod;
        this.reqType = reqType;
        this.reqParams = reqParams;
        this.respCode = respCode;
        this.respResult = respResult;
        this.respTime = respTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespResult() {
        return respResult;
    }

    public void setRespResult(String respResult) {
        this.respResult = respResult;
    }

    public long getRespTime() {
        return respTime;
    }

    public void setRespTime(long respTime) {
        this.respTime = respTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
