package com.hyh.service.impl;

import com.hyh.dao.SongDao;
import com.hyh.domain.ListSong;
import com.hyh.domain.Song;
import com.hyh.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private com.hyh.dao.ListSong listSongDao;

    @Autowired
    private SongDao songDao;


    @Override
    public ListSong selectListSongUrl(Integer id) {
        return listSongDao.selectById(id);
    }


//    上一首
    @Override
    public ListSong selectSongUrlUp(Integer id) {
        Integer idUp = id - 1;
        return listSongDao.selectById(idUp);
    }

//    下一首
    @Override
    public ListSong selectSongUrlDown(Integer id) {
        Integer idNext = id + 1;
        return listSongDao.selectById(idNext);
    }

}
