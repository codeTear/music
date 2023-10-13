package com.hyh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("singer")
public class Singer {
    @TableId(value = "singer_id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "singer_name")
    private String singerName;
    @TableField(value = "singer_details")
    private String singerDetail;

    @TableField(value = "singer_photo")
    private String singerPhoto;
    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", singerDetail='" + singerDetail + '\'' +
                ", singerPhoto='" + singerPhoto + '\'' +
                '}';
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerDetail() {
        return singerDetail;
    }

    public void setSingerDetail(String singerDetail) {
        this.singerDetail = singerDetail;
    }

    public String getSingerPhoto() {
        return singerPhoto;
    }

    public void setSingerPhoto(String singerPhoto) {
        this.singerPhoto = singerPhoto;
    }
}
