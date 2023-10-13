package com.hyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.dao.SongDTODao;
import com.hyh.dao.UserDao;
import com.hyh.domain.PageResult;
import com.hyh.domain.Song;
import com.hyh.domain.SongDTO;
import com.hyh.domain.User;
import com.hyh.service.SongSingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongSingerServiceImpl implements SongSingerService {

    @Autowired
    private com.hyh.dao.SongDao songDao;

    @Autowired
    private SongDTODao songDTODao;

    @Autowired
    private UserDao userDao;
    //  下一首
    @Override
    public SongDTO selectSongUrlDown(Integer id) {
        Integer idNext = id + 1;
        System.out.println(songDao);
        return songDao.selectByIdAll(idNext);

    }

    // 上一首
    @Override
    public SongDTO selectSongUrlUp(Integer id) {

        Integer idUp = id - 1;
        return songDao.selectByIdAll(idUp);
    }

    // 查询所有song表的歌曲
    @Override
    public List<Song> selectAllSong() {
        return songDao.selectList(null);
    }

    @Override
    public List<SongDTO> SelectAll() {
       return songDao.selectAll();
    }

    //添加歌曲到歌单


    @Override
    public boolean addSongToList(SongDTO songDTO) {
        // 构建查询条件
        QueryWrapper<SongDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_name", songDTO.getSongName())
                .eq("singer_name", songDTO.getSingerName());

        // 查询歌曲是否已经存在
        SongDTO existingSong = songDTODao.selectOne(queryWrapper);
        if (existingSong != null) {
            return false; // 若已经存在，则返回添加失败
        }
        // 否则进行添加操作
        return songDTODao.insert(songDTO) > 0;
    }

    // 分页查询喜爱的歌单表
    @Override
    public PageResult<SongDTO> selectLoveSong(int page, int size,int userID) {
        QueryWrapper<SongDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userID);
        IPage<SongDTO> page1 = new Page<>(page, size);
        songDTODao.selectPage(page1, queryWrapper);
        PageResult<SongDTO> pageResult = new PageResult<>();
        pageResult.setTotal((int) page1.getTotal());
        System.out.println(pageResult.getTotal());
        pageResult.setData(page1.getRecords());
        return pageResult;
    }
    //分页查询用户信息
    @Override
    public PageResult<User> selectAllUser(int page, int size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> page1 = new Page<>(page, size);
        userDao.selectPage(page1, queryWrapper);
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setTotal((int) page1.getTotal());
        System.out.println(pageResult.getTotal());
        pageResult.setData(page1.getRecords());
        return pageResult;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        int count = userDao.deleteById(id);
        return count > 0;
    }
}
