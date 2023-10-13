// 导入数据库操作模块
const db = require('../db/index')

// 注册用户的处理函数
exports.regUser = (req, res) => {
    const sql = 'select * from song where id=8'
    db.query(sql, (err, results) => {
        // 执行 SQL 语句失败
        if (err) {
            return res.send({ status: 1, message: err.message })
        }
        // 执行 SQL 语句成功，打印数据
        // 执行 SQL 语句成功，打印数据
        res.send({ status: 0, message: '获取文章列表成功！', data: results })
    })
}

// 登录的处理函数
exports.login = (req, res) => {
    res.send('login OK')
}