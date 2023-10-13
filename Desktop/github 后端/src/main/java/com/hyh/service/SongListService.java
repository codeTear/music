package com.hyh.service;

import com.hyh.domain.ListSong;
import com.hyh.domain.Song;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SongListService {
    //查询歌单里的所有歌曲
    public ListSong selectListSongUrl(Integer id);

    //切歌上一首
    public ListSong selectSongUrlUp(Integer id);

    //切歌下一首
    public ListSong selectSongUrlDown(Integer id);


}


