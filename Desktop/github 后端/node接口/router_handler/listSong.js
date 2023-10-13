// 导入数据库操作模块
const db = require('../db/index')

// 查询所有歌单处理函数
exports.selectAllSongList = (req, res) => {
    const sql = 'select * from musiclist'
    db.query(sql, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌单失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌单成功！', data: results })
    })
}

// 查询歌单里面对应的歌曲
exports.selectListSongs = (req, res) => {
    const sql = 'select * from song_list where music_id=?'
    db.query(sql, req.params.id, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌曲成功！', data: results })
    })
}