package com.hyh.domain;

import java.util.List;

//分页查询结果
public class PageResult <T>{
    private int total;//总条数

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    private List<T> data;//当前页数据

}
