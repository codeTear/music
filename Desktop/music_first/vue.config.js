const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer:{
    host:'localhost',  // 本地主机
      port:8080,  // 端口号的配置
      open:true,  // 自动打开浏览器
      proxy:{
        '/music': {   //  拦截以 /api 开头的接口
          target: 'http://159.65.4.58:80',//设置你调用的接口域名和端口号 别忘了加http
          changeOrigin: true,    //这里true表示实现跨域
          secure: false, // 如果是https接口，需要配置这个参数
        }
      }
    }
})
