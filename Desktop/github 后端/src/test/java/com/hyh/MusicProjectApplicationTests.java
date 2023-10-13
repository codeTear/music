package com.hyh;

import com.hyh.domain.*;
import com.hyh.service.SongService;
import com.hyh.service.SongListService;
import com.hyh.service.SongSingerService;
import com.hyh.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class MusicProjectApplicationTests {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private SongListService songService1;

    @Autowired
    private SongSingerService songSingerService;

    @Test
    void contextLoads() {
    }

    //查询歌手的测试
    @Test
    void selectAll() {
        List<Singer> singers = songService.selectSingerAll();
        System.out.println(singers);
    }

    //查询歌手的测试
    @Test
    void selectById() {
        Singer singer = new Singer();

        Singer singer1 = songService.selectById(2);
        System.out.println(singer1);
    }

    //查询歌手对应的歌曲
    @Test
    void ByNameSelectSong() {
        Singer singer = new Singer();
        List<Song> songs = songService.ByNameSelectSong("周杰伦");
        System.out.println(songs);
    }



    @Test
    void ByListSelectSongs() {
        List<ListSong> listSongs = songService.ByListSelectSongs(1);
        System.out.println(listSongs);
    }

    @Test
    void selectLists() {
        List<MusicList> musicListDaos = songService.selectLists();
        System.out.println(musicListDaos);
    }

    @Test
    void selectUserById() {
        User user = userService.selectById(3);
        System.out.println(user);
    }

    @Test
    void selectSingerByName() {
        Singer singer = songService.selectSingerName("林俊杰");
        System.out.println(singer);
    }

    @Test
    void selectSongUrl() {
        Song song = songService.selectSongUrl(3);
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        System.out.println(purl);
    }

    @Test
    void selectListSongUrl() {
        ListSong song = songService1.selectListSongUrl(3);
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        System.out.println(purl);
    }

    @Test
    void selectSongUrlSong() {
        SongDTO song = songSingerService.selectSongUrlDown(12);
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        System.out.println(purl);
    }
    @Test
    void selectAlls() {
        List<SongDTO> songDTOS = songSingerService.SelectAll();
        System.out.println(songDTOS);
    }
}

