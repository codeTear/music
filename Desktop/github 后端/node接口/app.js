// 导入 express 模块
const express = require('express')
// 创建 express 的服务器实例
const app = express()

// 导入 cors 中间件
const cors = require('cors')
// 将 cors 注册为全局中间件
app.use(cors())
app.use(express.urlencoded({ extended: false }))


// 导入歌曲模块
const songRouter = require('./router/song')

// 导入歌单模块
const listSongRouter = require('./router/listSong')


// 注册理由模块
app.use('/api', songRouter)
app.use('/api', listSongRouter)

// 调用 app.listen 方法，指定端口号并启动web服务器
app.listen(8080, function () {
  console.log('api server running at http://127.0.0.1:8080')
})