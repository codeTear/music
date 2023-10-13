// 导入数据库操作模块
const db = require('../db/index')

// 查询所有歌曲处理函数
exports.selectAllSongs = (req, res) => {
    const sql = "SELECT song.*, singer.*FROM song JOIN singer ON song.song_singer = singer.singer_id"
    db.query(sql, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌曲成功！', data: results })
    })
}

// 查询所有歌手处理函数
exports.selectAllSingers = (req, res) => {
    const sql = 'select * from singer'
    db.query(sql, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌曲成功！', data: results })
    })
}
//  根据id查询歌手信息   
exports.selectSingerById = (req, res) => {
    const sql = 'select * from singer where singer_id=?'
    db.query(sql, req.params.id, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌手失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌手成功！', data: results })
    })
}

// 根据歌手名查询歌曲信息
exports.selectSingerByName = (req, res)=>{
    const sql = 'select * from singer where singer_name=?'
    db.query(sql, req.query.singerName, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌手失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌手成功！', data: results })
    })
}

// 模糊查询对应的歌曲
exports.selectDimSongs = (req, res)=>{
    const sql = 'select * from song where name like ?'
    const songName = '%' + req.query.songName + '%'
    db.query(sql, songName, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 200, message: '获取歌曲成功！', data: results })
    })
}


// 根据id查询歌曲的purl
exports.selectPurlById = (req, res)=>{
    const sql = 'SELECT song.*, singer.* FROM song JOIN singer ON song.song_singer = singer.singer_id where song.id=?'
    db.query(sql, req.params.id, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }
        // 执行 SQL 语句成功，打印数据
        let purl = results[0].purl;
        purl = purl.replace(/\\/g, '/');
        purl = 'http://159.65.4.58/' + encodeURIComponent(purl);
        const purlData = {
            id: results[0].id,
            songName: results[0].name,
            singerName: results[0].singer_name,
            singerImage: results[0].singer_image,
            purl: purl
        } 
        res.send({ status: 200, message: '获取歌曲成功！', data: purlData })
    })	
}

//查找下一首歌曲
exports.selectNextSong = (req, res)=>{
    const sql = 'SELECT song.*, singer.* FROM song JOIN singer ON song.song_singer = singer.singer_id where song.id=?'
    db.query(sql,  Number(req.params.id)  + 1, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }

       // 执行 SQL 语句成功，打印数据
       let purl = results[0].purl;
        purl = purl.replace(/\\/g, '/');
        purl = 'http://159.65.4.58/' + encodeURIComponent(purl);
        const purlData = {
            id: results[0].id,
            songName: results[0].name,
            singerName: results[0].singer_name,
            singerImage: results[0].singer_image,
            purl: purl
        } 
        res.send({ status: 200, message: '获取歌曲成功！', data: purlData })
    })
}


//查找上一首歌曲
exports.selectUptSong = (req, res)=>{
    const sql = 'SELECT song.*, singer.* FROM song JOIN singer ON song.song_singer = singer.singer_id where song.id=?'
    db.query(sql, Number(req.params.id) - 1, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 201, message: '获取歌曲失败' })
        }

       // 执行 SQL 语句成功，打印数据
        let purl = results[0].purl;
        purl = purl.replace(/\\/g, '/');
        purl = 'http://159.65.4.58/' + encodeURIComponent(purl);
        const purlData = {
            id: results[0].id,
            songName: results[0].name,
            singerName: results[0].singer_name,
            singerImage: results[0].singer_image,
            purl: purl
        } 
        res.send({ status: 200, message: '获取歌曲成功！', data: purlData })
    })
}