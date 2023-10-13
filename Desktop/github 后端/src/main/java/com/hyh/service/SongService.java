package com.hyh.service;

import com.hyh.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SongService {
    //查询歌手的信息
    public List<Singer> selectSingerAll();

    //根据id查询歌手信息
    public Singer selectById(Integer id);

    //根据歌手查询对应的歌曲
    public List<Song> ByNameSelectSong(String singerName);

    //模糊查询对应的歌曲
    public List<SongDTO> likeSong(String search);

    //根据歌单查询应的歌曲
    public List<ListSong> ByListSelectSongs(Integer id);

    //查询歌单的信息
    public List<MusicList> selectLists();

    //根据歌手查询他自己的信息
    public Singer selectSingerName(String singerName);

    //获取歌曲的url
    public Song selectSongUrl(Integer id);


}

