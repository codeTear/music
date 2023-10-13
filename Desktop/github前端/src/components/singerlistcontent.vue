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
          <el-menu-item index="1">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item
                :to="{ path: '/login' }"
                class="login"
                style="cursor: default; position: absolute; left: 350px"
                ><span style="cursor: default" id="judgelogin"
                  >登录</span
                ></el-breadcrumb-item
              >
            </el-breadcrumb>
          </el-menu-item>
          <!-- 头像 -->
          <el-menu-item>
            <div class="user" @click="mymusic">
              <el-avatar :src="url"></el-avatar>
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
            class="img"
            :style="{ backgroundImage: 'url(' + imagePath + ')' }"
          ></div>
          <div class="content">
            <div class="c_content">
              <h2
                style="
                  height: 40px;
                  line-height: 0;
                  text-align: left;
                  margin-left: 20px;
                "
              >
                {{ singerName }}
              </h2>
            </div>
            <div class="c_content_1">
              {{ singerDetail }}
            </div>
            <span
              style="margin-left: 19px; color: #49b6e2"
              @click="dialogVisible = true"
              >查看更多</span
            >
            <el-dialog
              title="更多信息"
              :visible.sync="dialogVisible"
              width="30%"
              :before-close="handleClose"
            >
              <span>{{ singerDetail }}</span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false"
                  >确 定</el-button
                >
              </span>
            </el-dialog>
          </div>
        </div>
        <div class="main_c">
          <h2 style="text-align: left; line-height: 2; margin-bottom: 1px">
            热门歌曲
          </h2>
        </div>
        <el-table ref="singleTable" :data="tableSongData" style="width: 100%">
          <el-table-column type="index" width="50"> </el-table-column>
          <el-table-column property="songName" label="歌曲" width="0">
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
              <el-button
                style="border: none"
                size="mini"
                @click="handleEdit(scope.$index, scope.row)"
                ><i
                  class="iconfont icon-bofanganniu"
                  style="font-size: 31px"
                ></i
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <!-- 底部 -->
    </el-container>
  </div>
</template>
  
<script>
export default {
  name: "singerlistcontent",
  data() {
    // 轮播图
    return {
      form: {
        search: "content",
      },
      dialogVisible: false,
      // 图片
      imagePath: "",
      singerDetail: "",
      singerName: "",
      tableSongData: [
        {
          songName: "",
          albumTitle: "",
          timeInterval: "",
        },
      ],
      url: "",
      currenId: null,
    };
  },
  mounted() {
    // 获取从singerlist传过来的值
    this.singerName = this.$route.params.singerName;
    this.singerDetail = this.$route.params.singerDetail;
    this.imagePath = this.$route.params.singerImage;
    console.log(this.singerName);
    let url =
      "http://159.65.4.58:8080/api/songs/singer?singerName=" + this.singerName;
    this.$http.get(url).then((res) => {
      // this.tableSongData = res.data["data"];
      console.log(res.data["data"]);
      let data = res.data["data"];
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
    let url1 =
      "http://159.65.4.58:8080/api/songs/singer/detail?singerName=" +
      this.singerName;

    this.$http.get(url1).then((res) => {
      this.imagePath = res.data.data.singerPhoto;
      this.singerDetail = res.data.data.singerDetail;
    });
    if (window.sessionStorage.getItem("username") != null) {
      let username = window.sessionStorage.getItem("username");
      let login = document.getElementById("judgelogin");
      let url = "http://159.65.4.58:8080/api/users/info?username=" + username;
      login.style.display = "none";
      this.$http.get(url).then((res) => {
        this.url = res.data.data.userImg;
      });
      this.url = "http://159.65.4.58/uploads/3.jpg";
    }
  },
  // 轮播图
  methods: {
    // 导航栏
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleCurrentChange(val) {
      this.currentRow = val;
    },
    handleEdit(index, row) {
      // 获取参数进行路由转发
      this.currenId = row.id;
      this.tableSongData.songName = row.songName;
      this.tableSongData.albumTitle = row.albumTitle;
      this.tableSongData.timeInterval = row.timeInterval;
      this.url = row.purl;
      console.log(this.url);
      console.log(this.singerName);
      console.log(this.tableSongData.songName);
      console.log(this.imagePath);
      window.sessionStorage.setItem("singerName", this.singerName);
      // 进行转发
      let url = `/#/play?singerName=${encodeURIComponent(
        this.singerName
      )}&songName=${encodeURIComponent(
        this.tableSongData.songName
      )}&albumTitle=${encodeURIComponent(
        this.tableSongData.albumTitle
      )}&imagePath=${encodeURIComponent(
        this.imagePath
      )}&url=${encodeURIComponent(this.url)}&timeInterval=${encodeURIComponent(
        this.tableSongData.timeInterval
      )}&currentId=${encodeURIComponent(this.currenId)}`;
      window.open(url, "_blank");
    },
        // 跳转到歌手界面
        router1() {
      this.$router.push("/singerlist");
    },
    // 我的音乐
    mymusic() {
      this.$router.push("/user");
    },
    // 首页
    shouye() {
      this.$router.push("/index");
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
  border-radius: 140px;
  background-image: url("https://y.qq.com/music/photo_new/T001R300x300M0000025NhlN2yWrP4_8.jpg?max_age=2592000");
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
.c_content_1 {
  margin-left: 20px;
  text-align: left;
  font-size: 14px;
  height: 180px;
  width: 880px;
  line-height: 18px;
  margin-top: -12px;
  //   white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  word-wrap: break-word;
}

.main_c {
  margin-top: 20px;
}
.el-table__body-wrapper {
  color: #333;
  overflow: hidden;
  position: relative;
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
  