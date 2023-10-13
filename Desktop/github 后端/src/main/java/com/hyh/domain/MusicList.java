package com.hyh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("musiclist")
public class MusicList {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    @TableField(value = "play_count")
    private String plyCount;
    @TableField(value = "favorite_count")
    private String favCount;
    @TableField(value = "image_path")
    private String imgUrl;

    @Override
    public String toString() {
        return "MusicList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", plyCount='" + plyCount + '\'' +
                ", favCount='" + favCount + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @TableField(value = "introduction")
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlyCount() {
        return plyCount;
    }

    public void setPlyCount(String plyCount) {
        this.plyCount = plyCount;
    }

    public String getFavCount() {
        return favCount;
    }

    public void setFavCount(String favCount) {
        this.favCount = favCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
