const express = require('express')
// 创建路由对象
const router = express.Router()

// 导入路由处理函数模块
const songHandler = require("../router_handler/song")

// 查询所有歌曲
router.get('/songs', songHandler.selectAllSongs)

// 查询所有歌手
router.get('/singers', songHandler.selectAllSingers)

// 根据id查询歌手信息
router.get('/singer/:id', songHandler.selectSingerById)

// 根据歌手名查询歌曲信息
router.get('/singer', songHandler.selectSingerByName)

// 模糊查询对应的歌曲
router.get('/song', songHandler.selectDimSongs)

// 根据id查询歌曲purl 
router.get('/song/purl/:id', songHandler.selectPurlById)

// 根据id查询下一首歌曲信息
router.get('/songs/url/next/:id', songHandler.selectNextSong)

// 根据id查询上一首歌曲信息
router.get('/songs/url/up/:id', songHandler.selectUptSong)

// 将路由对象共享出去
module.exports = router