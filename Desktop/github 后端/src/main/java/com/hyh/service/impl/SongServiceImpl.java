package com.hyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyh.dao.MusicListDao;
import com.hyh.dao.SingerDao;
import com.hyh.dao.SongDao;
import com.hyh.domain.*;
import com.hyh.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDao songDao;

    @Autowired
    private SingerDao singerDao;

    @Autowired
    private MusicListDao musicListDao;

    @Autowired
    private com.hyh.dao.ListSong listSongDao;
    //查询歌手的信息
    @Override
    public List<Singer> selectSingerAll() {
        return singerDao.selectList(null);
    }

    //根据id来查询歌手信息
    @Override
    public Singer selectById(Integer id) {
        return singerDao.selectById(id);
    }

    //根据歌手来查询歌手的歌曲
    @Override
    public List<Song> ByNameSelectSong(String singerName) {
        return songDao.ByNameSelectSong(singerName);
    }

    @Override
    public List<SongDTO> likeSong(String search) {

        return songDao.selectBySongName(search);
    }

    //根据歌单查询应的歌曲
    @Override
    public List<ListSong> ByListSelectSongs(Integer id) {
        return songDao.ByListSelectSong(id);
    }

    //查询歌单的信息
    @Override
    public List<MusicList> selectLists() {
        return musicListDao.selectList(null);
    }


    //根据歌手查询自己的信息
    @Override
    public Singer selectSingerName(String singerName) {
        QueryWrapper<Singer> qw = new QueryWrapper<>();
        QueryWrapper<Singer> username1 = qw.eq("singer_name", singerName);
        return singerDao.selectOne(username1);
    }

//    获取歌曲的url
    @Override
    public Song selectSongUrl(Integer id) {

        return songDao.selectById(id);
    }
}