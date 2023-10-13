package com.hyh.controller;

import com.hyh.domain.*;
import com.hyh.service.SongService;
import com.hyh.service.SongListService;
import com.hyh.service.SongSingerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private SongListService songService1;

    @Autowired
    private SongSingerService songSingerService;

    /*
     * @description: 查询所有歌手的信息
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 11:16
     */
    @GetMapping
    public Result selectAllSingers() {
        List<Singer> singers = songService.selectSingerAll();
        Integer code = singers != null ? Code.Ok : Code.Err;
        String msg = singers != null ? "" : "数据查询失败 请重试";
        return new Result(code, singers, msg);
    }

    /*
     * @description: 根据id来查询
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 11:17
     */
    @GetMapping("/{id}")
    public Result selectByIdName(@PathVariable Integer id) {
        Singer singer = songService.selectById(id);
        Integer code = singer != null ? Code.Ok : Code.Err;
        String msg = singer != null ? "" : "数据查询失败 请重试";
        return new Result(code, singer, msg);
    }


    /*
     * @description: 根据歌手来查询歌手的歌曲
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 11:17
     */
    @GetMapping("/singer")
    public Result ByNameSelectSong(String singerName) {
        System.out.println(singerName);
        List<Song> songs = songService.ByNameSelectSong(singerName);
        System.out.println(songs);
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }

    /*
     * @description: 模糊查询对应的歌曲
     * @param:  song对象
     * @return:
     * @author
     * @date: 2023/9/28 11:17
     */
    @GetMapping("/search")
    public Result LikeSong(String search) {
        List<SongDTO> songs = songService.likeSong(search);
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }

    /*
     * @description: 根据歌单查询应的歌曲
     * @param:  id
     * @return:
     * @author
     * @date: 2023/9/28 11:18
     */
    @GetMapping("/list/{id}")
    public Result ByListSelectSongs(@PathVariable Integer id) {
        List<ListSong> songs = songService.ByListSelectSongs(id);
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }

    /*
     * @description: 查询歌单的信息
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 11:18
     */
    @GetMapping("/list")
    public Result selectLists() {
        List<MusicList> musicListDaos = songService.selectLists();
        Integer code = musicListDaos != null ? Code.Ok : Code.Err;
        String msg = musicListDaos != null ? "" : "数据查询失败 请重试";
        return new Result(code, musicListDaos, msg);
    }

    /*
     * @description: 查询歌手的单个信息
     * @param:  singerName
     * @return:
     * @author
     * @date: 2023/9/28 11:18
     */
    @GetMapping("/singer/detail")
    public Result selectSingleDetail(String singerName) {
        Singer singer = songService.selectSingerName(singerName);
        Integer code = singer != null ? Code.GET_OK : Code.GET_ERR;
        String msg = singer != null ? "" : "数据查询失败 请重试";
        return new Result(code, singer, msg);
    }

    /*
     * @description: 根据id查询歌曲purl
     * @param:
     * @return:
     * @author
     * @date: 2023/10/1 14:26
     */
    @GetMapping("/url/{id}")
    public Result selectSongUrl(@PathVariable Integer id) {
        Song song = songService.selectSongUrl(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }


    /*
     * @description: 获取下一首歌曲的url
     * @param:
     * @return:
     * @author
     * @date: 2023/10/2 14:23
     */

    @GetMapping("/url/next/{id}")
    public Result selectSongUrlNext(@PathVariable Integer id) {
        ListSong song = songService1.selectSongUrlDown(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }


     /*
      * @description: 获取上一首歌曲的url
      * @param:
      * @return: com.hyh.controller.Result
      * @author
      * @date: 2023/10/2 14:25
      */
    @GetMapping("/url/up/{id}")
    public Result selectSongUrlUp(@PathVariable Integer id) {
        ListSong song = songService1.selectSongUrlUp(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }

    /*
     * @description: 查询所有list歌曲url
     * @param:
     * @return:
     * @author
     * @date: 2023/10/1 23:04
     */
    @GetMapping("/listUrl/{id}")
    public Result selectListSongUrl(@PathVariable Integer id) {
        ListSong song = songService1.selectListSongUrl(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }


    /*
     * @description: song的切歌 上一首
     * @param:
     * @return:
     * @author
     * @date: 2023/10/3 23:12
     */
    @GetMapping("/songUrl/up/{id}")
    public Result selectSongUrlUps(@PathVariable Integer id) {
        SongDTO song = songSingerService.selectSongUrlUp(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }

    /*
     * @description: song的切歌 下一首
     * @param:
     * @return:
     * @author
     * @date: 2023/10/3 23:12
     */
    @GetMapping("/songUrl/down/{id}")
    public Result selectSongUrlDown(@PathVariable Integer id) {
        SongDTO song = songSingerService.selectSongUrlDown(id);
        Integer code = song != null ? Code.Ok : Code.Err;
        String msg = song != null ? "" : "数据查询失败 请重试";
        String filePath = song.getPurl();
        String replacedPath = filePath.replace("\\", "/");
        String purl = "http://159.65.4.58/" + replacedPath;
        song.setPurl(purl);
        return new Result(code, song, msg);
    }


    /*
     * @description: 查询song表的所有歌曲
     * @param:
     * @return:
     * @author
     * @date: 2023/10/4 0:19
     */
    @GetMapping("/songAll")
    public Result selectAllSong() {
        List<Song> songs = songSingerService.selectAllSong();
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }


    /*
     * @description: 歌手和歌曲的信息
     * @param:
     * @return:
     * @author
     * @date: 2023/10/4 1:30
     */
    @GetMapping("/songSinger")
    public Result SelectAll() {
        List<SongDTO> songs = songSingerService.SelectAll();
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }

    /*
     * @description: 添加歌曲到歌单
     * @param:
     * @return: com.hyh.controller.Result
     * @date: 2023/10/4 1:30
     */
    @PostMapping("/addSongToList")
    public Result addSongToList(@RequestBody SongDTO songDTO) {
        boolean flag = songSingerService.addSongToList(songDTO);
        return new Result(flag?Code.Ok:Code.Err,flag,"添加成功");
    }

    /*
     * @description: 查询喜爱的歌单表
     * @param:
     * @return: com.hyh.controller.Result
     * @date: 2023/10/4 1:30
     */
    @GetMapping("/loveSongPage")
    public Result selectLoveSong(@Param("page") int page, @Param("size") int size,@Param("userId") int userId) {
        PageResult<SongDTO> songs = songSingerService.selectLoveSong(page,size,userId);
        Integer code = songs != null ? Code.Ok : Code.Err;
        String msg = songs != null ? "" : "数据查询失败 请重试";
        return new Result(code, songs, msg);
    }
}
