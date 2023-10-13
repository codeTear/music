package com.hyh.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyh.domain.ListSong;
import com.hyh.domain.Song;
import com.hyh.domain.SongDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongDao extends BaseMapper<Song> {
//    根据歌手查询歌曲
    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "song_singer",column = "song_singer")
    })
    @Select("\n" +
            "SELECT song.id ,song.name, song.album_title,song.subtitle,song.time_interval,song.purl,song.song_singer\n" +
            "FROM song\n" +
            "JOIN singer ON song.song_singer = singer.singer_id\n" +
            "WHERE singer.singer_name = #{singerName};")
    public List<Song> ByNameSelectSong(String singerName);


    //    根据歌单查询歌曲
    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "musicId",column = "music_id"),
    })
    @Select("\n" +
            "SELECT song_list.id ,song_list.name, song_list.album_title,song_list.subtitle,song_list.time_interval,song_list.purl,song_list.music_id,song_list.singer_name,musiclist.title,musiclist.play_count,musiclist.favorite_count,musiclist.image_path\n" +
            "FROM song_list\n" +
            "JOIN musiclist ON song_list.music_id = musiclist.id\n" +
            "WHERE musiclist.id = #{id};")
    public List<ListSong> ByListSelectSong(Integer id);

    //查询所有歌曲
    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "song_singer",column = "song_singer")
    })
    @Select("SELECT * FROM song")

    public List<Song> selectAllSongs();

    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "song_singer",column = "song_singer"),
            @Result(property = "singerName",column = "singer_name"),
            @Result(property = "singerDetail",column = "singer_details"),
            @Result(property = "singerPhoto",column = "singer_photo"),
            @Result(property = "singer_id",column = "singer_id"),
            @Result(property = "timeInterval",column = "time_interval"),
            @Result(property = "purl",column = "purl"),
            @Result(property = "lyric",column = "lyric"),
            @Result(property = "albumTitle",column = "album_title"),
            @Result(property = "subtitle",column = "subtitle"),
    })
    @Select("SELECT song.*, singer.*\n" +
            "FROM song\n" +
            "JOIN singer ON song.song_singer = singer.singer_id;")
    public List<SongDTO> selectAll();


    //根据id查询歌手歌曲信息
    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "song_singer",column = "song_singer"),
            @Result(property = "singerName",column = "singer_name"),
            @Result(property = "singerDetail",column = "singer_details"),
            @Result(property = "singerPhoto",column = "singer_photo"),
            @Result(property = "singer_id",column = "singer_id"),
            @Result(property = "timeInterval",column = "time_interval"),
            @Result(property = "purl",column = "purl"),
            @Result(property = "lyric",column = "lyric"),
            @Result(property = "albumTitle",column = "album_title"),
            @Result(property = "subtitle",column = "subtitle"),
    })
    @Select("SELECT song.*, singer.*\n" +
            "FROM song\n" +
            "JOIN singer ON song.song_singer = singer.singer_id\n" +
            "WHERE song.id = #{id};")
    public SongDTO selectByIdAll(Integer id);

    //根据歌名模糊搜索
    @Results({
            @Result(property = "songName",column = "name"),
            @Result(property = "song_singer",column = "song_singer"),
            @Result(property = "singerName",column = "singer_name"),
            @Result(property = "singerDetail",column = "singer_details"),
            @Result(property = "singerPhoto",column = "singer_photo"),
            @Result(property = "singer_id",column = "singer_id"),
            @Result(property = "timeInterval",column = "time_interval"),
            @Result(property = "purl",column = "purl"),
            @Result(property = "lyric",column = "lyric"),
            @Result(property = "albumTitle",column = "album_title"),
            @Result(property = "subtitle",column = "subtitle"),
    })
    @Select("SELECT song.*, singer.*\n" +
            "FROM song\n" +
            "JOIN singer ON song.song_singer = singer.singer_id\n" +
            "WHERE song.name LIKE CONCAT('%',#{search},'%');")
    public List<SongDTO> selectBySongName(String search);
}
