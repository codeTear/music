package com.hyh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("song_list")
public class ListSong {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "singer_name")
    private String singerName;

    @Override
    public String toString() {
        return "ListSong{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", songName='" + songName + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", musicId=" + musicId +
                ", timeInterval='" + timeInterval + '\'' +
                ", purl='" + purl + '\'' +
                ", search='" + search + '\'' +
                '}';
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @TableField(value = "name")
    private String songName;
    @TableField(value = "album_title")
    private String albumTitle;
    private String subtitle;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    @TableField(value = "music_id")
    private Integer musicId;
    public String getPurl() {
        return purl;
    }


    public void setPurl(String purl) {
        this.purl = purl;
    }

    @TableField(value = "time_interval")
    private String timeInterval;

    private String purl;


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


}
