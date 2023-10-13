package com.hyh.controller;


//定义一个result的类 用于对数据的封装
public class Result {
    private Object data;
    private Integer code;//返回请求的状态
    private  String msg;//返回请求的信息

    public Result() {

    }

    public Result(Integer code,Object data ) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
