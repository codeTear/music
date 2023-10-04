<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <!-- 头部 -->
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="shouye">首页</el-menu-item>
          <el-menu-item index="1" @click="router1">歌手</el-menu-item>
          <el-menu-item index="1">歌单</el-menu-item>
          <el-menu-item index="1" @click="mymusic">我的音乐</el-menu-item>
          <el-menu-item index="1">
            <!-- 搜索 -->
            <el-form ref="form" :model="form" label-width="0px">
              <el-form-item class="search">
                <el-input
                  v-model="form.content"
                  placeholder="歌手 歌单 歌曲"
                  size="medium"
                  suffix-icon="el-icon-search"
                ></el-input>
              </el-form-item>
            </el-form>
          </el-menu-item>
          <!-- 登录 -->
          <el-menu-item index="1" >
            <el-breadcrumb separator="/">
              <el-breadcrumb-item
                :to="{ path: '/login' }"
                class="login"
                style="cursor: default; position: absolute; left: 350px"
                ><span style="cursor: default" id="judgelogin">登录</span></el-breadcrumb-item
              >
            </el-breadcrumb>
          </el-menu-item>
          <!-- 头像 -->
          <el-menu-item>
            <div class="user">
              <el-avatar
                :src=url
              ></el-avatar>
            </div>
          </el-menu-item>
        </el-menu>
      </el-header>
      <!-- 中间部分 -->
      <div class="div" style="margin-top: 1px">
        <div class="div_1">
          <el-menu class="el-menu-demo" mode="horizontal">
            <el-menu-item index="1">歌曲</el-menu-item>
            <el-menu-item index="1">歌手</el-menu-item>
            <el-menu-item index="1">分类·歌单</el-menu-item>
            <el-menu-item index="1">MV</el-menu-item>
          </el-menu>
        </div>
      </div>
      <!-- 中间部分 -->
      <el-main>
        <div class="main_header">
          <div
            class="img" v-if="imgUrl"
          > <el-image
                style="width: 224px; height: 224px"
                :src="imgurl"
              ></el-image>
        </div>
          <div class="content">
            <div class="c_content">
              <h1
                style="
                  height: 40px;
                  line-height: 0;
                  text-align: left;
                  margin-left: 20px;
                  font-size: 31px;
                "
              >
                {{ title }}
              </h1>
            </div>
            <div class="type">标签： 流行</div>
            <div class="play">播放量： {{ playCount }}</div>
            <div class="fav">收藏量： {{ favCount }}</div>
          </div>
        </div>
        <div class="main_c">
          <h2 style="text-align: left; line-height: 2; margin-bottom: 1px">
            热门歌曲
          </h2>
        </div>
        <el-table
          stripe
          ref="singleTable"
          :data="tableSongData"
          style="width: 80%; float: left; margin-right: 20px; white-space: nowrap ;overflow: hidden ;text-overflow: ellipsis ;"
        >
          <el-table-column type="index" width="50"> </el-table-column>
          <el-table-column property="songName" label="歌曲" width="0">
          </el-table-column>
          <el-table-column property="singerName" label="歌手" width="0" align="center">
          </el-table-column>
          <el-table-column
            property="albumTitle"
            label="专辑"
            width="0"
            align="center"
          >
          </el-table-column>
          <el-table-column property="timeInterval" label="时长" align="center">
          </el-table-column>
          <el-table-column label="">
      <template slot-scope="scope">
        <el-button style="border: none;"
          size="mini"
          @click="handleEdit(scope.$index, scope.row)"><i
              class="iconfont icon-bofanganniu"
              style="font-size: 31px;color: rgb(96, 98, 102);"
            ></i></el-button>
      </template>
    </el-table-column>
          
          
        </el-table>
        <div class="intro">
          <p>简介</p>
            {{ introduction}}
        </div>
      </el-main>
      <!-- 底部 -->

    </el-container>
  </div>
</template>
    
  <script>
import router from '@/router';

export default {
  name: "singerlistcontent",
  data() {
    // 轮播图
    return {
      form: {
        search: "content",
      },
      imgurl:"",
      //songlist的数据
      id: "",
      imgUrl: "",
      title: "",
      playCount: "",
      favCount: "",
      introduction:"",
      tableSongData: [
        {
          songName: "",
          singerName: "",
          albumTitle: "",
          timeInterval: "",
        },
      ],
      url:"",
      currentRow: null,
    };
  },
  mounted() {
    // 获取从index传过来的值
    let id = localStorage.getItem("id");
    this.title = localStorage.getItem("title");
    this.playCount = localStorage.getItem("playCount");
    this.favCount = localStorage.getItem("favCount");
    this.imgUrl = localStorage.getItem("imgUrl");
    this.introduction=localStorage.getItem("introduction");
    this.imgurl=this.imgUrl
    // 在这个界面发起根据歌单查询歌曲请求
    let url = "http://159.65.4.58:8080/api/songs/list/" + id;
    console.log(url);
    this.$http.get(url).then((res) => {
      let data = res["data"]["data"];
      // 处理表格数据 将数据进行格式化
      this.tableSongData = data.map((song) => {
        song.songName += " " + song.subtitle; // 将 subtitle 拼接到 songName 后面
        let minutes = Math.floor(parseInt(song.timeInterval) / 60);
        let seconds = parseInt(song.timeInterval) % 60;
        song.timeInterval = `${minutes.toString().padStart(2, "0")}:${seconds
          .toString()
          .padStart(2, "0")}`;
        return song;
      });
    });
    if(window.sessionStorage.getItem("username")!=null){
      let username = window.sessionStorage.getItem("username")
      let login = document.getElementById("judgelogin");
      let url = "http://159.65.4.58:8080/api/users/info?username="+username
      login.style.display = "none";
      this.$http.get(url).then((res)=>{
        this.url = res.data.data.userImg
      })
      this.url = "http://159.65.4.58/uploads/3.jpg"
    }
  },
  // 轮播图
  methods: {
    handleChange(index) {
      // eslint-disable-next-line
      console.log(`[Demo 1] You have click cover ${index}`);
    },
    // 导航栏
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
     // 跳转到歌手界面
     router1() {
      this.$router.push("/singerlist");
    },
    // 跳转到首页
    shouye(){
        this.$router.push("/index");
    },
    // 我的音乐
    mymusic(){
        this.$router.push("/user");
    },
    handleCurrentChange(val) {
      this.currentRow = val;
    },
    handleEdit(index, row) {
        console.log(index, row);
      },
  },
};
</script>
    
    
    <style lang="less" scoped>
.el-container {
  padding: 0;
}

.el-header,
.el-footer {
  color: #3333;
  text-align: center;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-width: 1200px;
  padding: 0;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  width: 1200px;
  min-width: 1200px;
  margin: 0 auto;
  padding: 0%;
  color: #333;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-menu-demo {
  padding-left: 240px;
}

.search {
  padding: 8px 0;
}

.login {
  // position: relative;
  // margin-left: 460px;
  margin-top: 22px;
  right: -460px;
  cursor: default;
}

.user {
  position: absolute;
  cursor: default;
  right: -465px;
  top: -3px;
}


.el-menu--horizontal > .el-menu-item {
  color: #333;
  font-size: 16px;
}

.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-menu {
  background-color: rgb(249, 249, 249);
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
  height: 240px;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-breadcrumb__item:last-child .el-breadcrumb__inner,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover,
.el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
  font-weight: 400;
  color: #606266;
  cursor: default;
}
.div .el-menu--horizontal > .el-menu-item {
  color: #333;
  font-size: 14px;
  padding-right: 20px;
}
.div_1 {
  width: 100%;
}
.div .div_1 .el-menu.el-menu--horizontal {
  margin-left: 400px;
}
.div {
  width: 100%;
}
.main_header {
  width: 1200px;
  height: 250px;
  margin-top: 40px;
  //   background-color: pink;
}
.img {
  float: left;
  width: 250px;
  height: 250px;
  margin: 0 10px 20px;

}
.content {
  float: left;
  margin-left: -5px;
  height: 250px;
  width: 900px;
  //   background-color: skyblue;
}
.c_content {
  height: 50px;
  width: 880px;
}

.main_c {
  margin-top: 20px;
}
.el-table__body-wrapper {
  color: #333;
  overflow: hidden;
  position: relative;
}
.fav {
  margin-left: 21px;
  margin-bottom: 6px;
}
.play {
  margin-left: 21px;
  margin-bottom: 6px;
}
.type {
  margin-left: 21px;
  margin-bottom: 6px;
}
.intro {
  color: #000;
  font-size: 14px;
  margin-left: 10px;
}
.el-table .el-table__cell .cell {
    box-sizing: border-box;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    word-break: break-all;
    line-height: 23px;
    padding-left: 10px;
    padding-right: 10px;
    white-space: nowrap; /* 不换行 */
  overflow: hidden; /* 溢出隐藏 */
  text-overflow: ellipsis; /* 显示省略号 */
}
.el-avatar {
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    overflow: hidden;
    color: #fff;
    width: 60px;
    height: 60px;
    line-height: 40px;
    background: none;
}
</style>
    