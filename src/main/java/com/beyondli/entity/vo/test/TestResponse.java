package com.beyondli.entity.vo.test;

/**
 * Created by beyondLi
 * Date 2018/9/25 15:43
 * Desc .
 */
public class TestResponse {

    //code码
    private String code;
    //data数据
    private String data;

    public TestResponse() {
    }

    public TestResponse(String code, String data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestResponse{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
