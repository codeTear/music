const express = require('express')
// 创建路由对象
const router = express.Router()

// 导入路由处理函数模块
const listSongHandler = require("../router_handler/listSong")

// 查询所有歌单
router.get('/listSongs', listSongHandler.selectAllSongList)

// 查询歌单里面对应的歌曲
router.get('/listSong/:id', listSongHandler.selectListSongs)

// 将路由对象共享出去
module.exports = router