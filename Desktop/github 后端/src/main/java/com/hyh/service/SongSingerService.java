package com.hyh.service;


import com.hyh.domain.PageResult;
import com.hyh.domain.Song;
import com.hyh.domain.SongDTO;
import com.hyh.domain.User;

import java.util.List;

public interface SongSingerService {
    //切歌上一首
    public SongDTO selectSongUrlUp(Integer id);

    //切歌下一首
    public SongDTO selectSongUrlDown(Integer id);

    // 查询所有song表的歌曲
    public List<Song> selectAllSong();

    //查询歌手和歌曲
    public List<SongDTO> SelectAll();

    //添加歌曲到歌单
    public boolean addSongToList(SongDTO songDTO);

//    分页查询喜爱的歌单表
    public PageResult<SongDTO> selectLoveSong(int page, int size,int userID);

    //分页查询用户信息
    public PageResult<User> selectAllUser(int page, int size);

    // 删除用户信息根据id
    public boolean deleteUserById(Integer id);
}

