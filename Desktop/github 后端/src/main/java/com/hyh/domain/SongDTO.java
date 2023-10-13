package com.hyh.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("favoriteplaylist")
public class SongDTO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "song_name")
    private String songName;
    @TableField(value = "album_title")
    private String albumTitle;
    private String subtitle;

    @TableField(value = "user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer song_singer;

//    不参与查询
    @TableField(exist = false)
    private Integer total;
    @Override
    public String toString() {
        return "SongDTO{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", song_singer=" + song_singer +
                ", singer_id=" + singer_id +
                ", timeInterval='" + timeInterval + '\'' +
                ", purl='" + purl + '\'' +
                ", lyric='" + lyric + '\'' +
                ", singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", singerDetail='" + singerDetail + '\'' +
                ", singerPhoto='" + singerPhoto + '\'' +
                '}';
    }

    public Integer getSong_singer() {
        return song_singer;
    }

    public void setSong_singer(Integer song_singer) {
        this.song_singer = song_singer;
    }

    public Integer getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Integer singer_id) {
        this.singer_id = singer_id;
    }

    private Integer singer_id;

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

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
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

    @TableField(value = "time_interval")
    private String timeInterval;
    private String purl;

    private String lyric;
    @TableField(value = "singer_id")
    private Integer singerId;
    @TableField(value = "singer_name")
    private String singerName;

    @TableField(value = "singer_details")
    private String singerDetail;

    @TableField(value = "singer_photo")
    private String singerPhoto;
}
