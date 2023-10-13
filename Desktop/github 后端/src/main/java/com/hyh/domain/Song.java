package com.hyh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("song")
public class Song {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String songName;
    @TableField(value = "album_title")
    private String albumTitle;
    private String subtitle;

    public String getPurl() {
        return purl;
    }


    public void setPurl(String purl) {
        this.purl = purl;
    }

    @TableField(value = "time_interval")
    private String timeInterval;

    private String purl;

    private Integer song_singer;

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", timeInterval='" + timeInterval + '\'' +
                ", purl='" + purl + '\'' +
                ", song_singer=" + song_singer +
                ", search='" + search + '\'' +
                '}';
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @TableField(exist = false)
    private String search;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Integer getSong_singer() {
        return song_singer;
    }

    public void setSong_singer(Integer song_singer) {
        this.song_singer = song_singer;
    }
}
