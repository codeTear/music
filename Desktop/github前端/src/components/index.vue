<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <!-- 头部 -->
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1"
            ><i
              class="iconfont icon-tubiaozhizuomobanyihuifu-"
              style="font-size: 42px; color: #333"
            ></i
          ></el-menu-item>

          <el-menu-item
            index="1"
            style="
              font-size: 30px;
              color: #333;
              font-style: italic;
              font-weight: bold;
            "
            >star音乐</el-menu-item
          >
          <el-menu-item index="1">首页</el-menu-item>
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
                >
                  <template v-slot:suffix>
                    <i
                      class="el-icon-search"
                      @click="handleIconClick"
                      style="cursor: pointer"
                    ></i>
                  </template>
                </el-input>
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
          <!-- 管理员显示 -->
          <el-menu-item>
            <div class="admin">
             <span style="font-size: 14px;color: red;" id="judgeAdmin"  @click="admin">管理员</span>
            </div>
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
      <el-main>
        <h2 style="line-height: 70px">推荐歌手</h2>
        <coverflow
          :coverList="coverList"
          :coverWidth="260"
          :index="2"
          @change="handleChange"
          style="width: 1080px; margin-left: 35px"
        ></coverflow>

        <!-- 推荐歌单 -->
        <h2 style="line-height: 70px">推荐歌单</h2>
        <!-- 动态生成推荐歌单 -->
        <el-row
          :gutter="20"
          style="margin-left: 0; margin-right: 0; height: 224px"
        >
          <el-col v-for="(img, index) in imgUrl" :key="index" :span="6">
            <div
              class="grid-content bg-purple"
              @click="
                goToSong(
                  id[index],
                  title[index],
                  playCount[index],
                  favCount[index],
                  imgUrl[index],
                  introduction[index]
                )
              "
              style="height: 224px"
            >
              <el-image
                style="width: 224px; height: 224px"
                :src="img"
              ></el-image>
            </div>
            <div class="song_title">{{ title[index] }}</div>
            <div class="playcount">播放量: {{ playCount[index] }}</div>
          </el-col>
        </el-row>
      </el-main>
      <!-- 底部 -->
      <el-footer>
        <!-- audio element -->
        <vue-plyr ref="plyr">
          <audio controls crossorigin playsinline>
            <source :src="musicUrl" type="audio/mp3" />
            <source :src="audioUrl" type="audio/ogg" />
          </audio>
        </vue-plyr>
        <div class="switch">
          <!-- 下一首 -->
          <div class="next" @click="next">
            <i
              class="iconfont icon-1_music82"
              style="font-size: 31px; color: #333"
            ></i>
          </div>
          <!-- 上一首 -->
          <div class="pre" @click="pre">
            <i
              class="iconfont icon-1_music83"
              style="font-size: 31px; color: #333"
            ></i>
          </div>
          <div class="icon">
            <i
              class="iconfont icon-music1"
              style="font-size: 36px; color: #333"
            ></i>
          </div>

          <div class="list" @click="drawer = true">
            <i
              class="iconfont icon-yinleliebiao-"
              style="font-size: 31px; color: #333"
            ></i>
          </div>

          <div class="love" @click="getSong">
            <i
              id="loveFont"
              class="iconfont icon-xiai"
              style="font-size: 31px; color: #333"
            ></i>
          </div>
          <div class="song">
            <span id="moren">她说 林俊杰</span>
            {{ tableSongData.songname }} {{ tableSongData.singerName }}
          </div>
        </div>
      </el-footer>
    </el-container>
    <el-drawer
      title=""
      :visible.sync="drawer"
      :with-header="false"
      class="songlist"
    >
      <h2 style="font-style: italic; color: #606266; margin-left: 5px">
        播放队列
      </h2>
      <el-table ref="singleTable" :data="tableSongData" style="width: 100%">
        <el-table-column type="index" width="50"> </el-table-column>
        <el-table-column property="songName" label="歌曲" width="0">
        </el-table-column>
        
        <el-table-column
          property="singerName"
          label="歌手"
          width="0"
          align="center"
        >
        </el-table-column>
        <el-table-column property="timeInterval" label="时长" align="center">
        </el-table-column>
        <el-table-column label="">
          <template slot-scope="scope">
            <el-button
              size="mini"
              style="border: none"
              @click="handleEdit(scope.$index, scope.row)"
            >
              <i
                class="iconfont icon-bofanganniu"
                style="font-size: 31px; color: "
              ></i
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "index",

  data() {
    // 轮播图
    return {
      // loveid显示
      loveid: "",
      // 歌单列表的数据绑定对象
      id: [],
      imgUrl: [],
      title: [],
      playCount: [],
      favCount: [],
      introduction: [],
      url: "",
      musicUrl: "",
      audioUrl: "",
      currentId: "",
      clickCount: 0,
      tableSongData: [
        {
          songName: "",
          singerName: "",
          timeInterval: "",
        },
      ],
      coverList: [
        {
          cover: require("../assets/images/jl.jpg"),
          title: "周杰伦",
        },
        {
          cover: require("../assets/images/ljj.jpg"),
          title: "林俊杰",
        },
        {
          cover: require("../assets/images/dzq.jpg"),
          title: "邓紫棋",
        },
        {
          cover: require("../assets/images/wsl.jpg"),
          title: "汪苏泷",
        },
        {
          cover: require("../assets/images/syc.jpg"),
          title: "单依纯",
        },
        {
          cover: require("../assets/images/xzq.jpg"),
          title: "薛之谦",
        },
        {
          cover: require("../assets/images/ycl.jpg"),
          title: "杨丞琳",
        },
        {
          cover: require("../assets/images/syz.jpg"),
          title: "孙燕姿",
        },
      ],

      form: {
        search: "content",
      },
      drawer: false,
      userid:"",
    };
  },
  // 定义钩子函数
  mounted() {
    this.$http.get("http://159.65.4.58:8080/api/songs/list").then((res) => {
      let data = res["data"]["data"];
      // 声明数组存放data域里面的值
      let ids = [];
      let titles = [];
      let imgurls = [];
      let playcopunts = [];
      let favcounts = [];
      let introductions = [];
      for (let item of data) {
        ids.push(item.id);
        titles.push(item.title);
        imgurls.push(item.imgUrl);
        playcopunts.push(item.plyCount);
        favcounts.push(item.favCount);
        introductions.push(item.introduction);
      }
      this.imgUrl = imgurls;
      this.title = titles;
      this.playCount = playcopunts;
      this.favCount = favcounts;
      this.id = ids;
      this.introduction = introductions;
      console.log(data);
    });

    // 判断是否为管理员
    if (window.sessionStorage.getItem("username") != "admin") {
      let username = window.sessionStorage.getItem("username");
      let admin = document.getElementById("judgeAdmin");
      admin.style.display = "none";
      
    }
    // 判断是否登录
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

    // 在这个界面发起根据歌单查询歌曲请求
    async function fetchData() {
      for (let id = 1; id < 8; id++) {
        let url = "http://159.65.4.58:8080/api/songs/list/" + id;
        let res = await this.$http.get(url);
        let data = res["data"]["data"];
        // 处理表格数据 将数据进行格式化
        let formattedData = data.map((song) => {
          let minutes = Math.floor(parseInt(song.timeInterval) / 60);
          let seconds = parseInt(song.timeInterval) % 60;
          song.timeInterval = `${minutes.toString().padStart(2, "0")}:${seconds
            .toString()
            .padStart(2, "0")}`;
          return song;
        });
        this.tableSongData = this.tableSongData.concat(formattedData);
      }
    }
    fetchData.call(this);

    // 默认加载她说

    this.musicUrl = "http://159.65.4.58/music/music_list/她说.mp3";
    this.currentId = 14;
    // 自动切换歌曲
    this.$refs.plyr.player.on("ended", () => {
      this.next();
    });
  },
  // 重新加载musicurl
  watch: {
    musicUrl() {
      this.$refs.plyr.player.source = {
        type: "audio",
        title: "Example title",
        sources: [
          {
            src: this.musicUrl,
            type: "audio/mp3",
          },
        ],
      };
    },
  },

  // 轮播图
  methods: {
    handleChange(index) {
      // eslint-disable-next-line
      console.log(`[Demo 1] You have click cover ${index}`);
    },
    // 搜索表单验证
    handleIconClick() {
      this.$router.push({
        name: "search",
        params: {
          content: this.form.content,
        },
      });
    },
    // 跳转到歌手界面
    router1() {
      this.$router.push("/singerlist");
    },
    // 我的音乐
    mymusic() {
      this.$router.push("/user");
    },
    admin(){
      this.$router.push("/admin");
    },
    // 每个歌单所对应的详情页
    goToSong(id, title, playCount, favCount, imgUrl, introduction) {
      localStorage.setItem("id", id);
      localStorage.setItem("title", title);
      localStorage.setItem("playCount", playCount);
      localStorage.setItem("favCount", favCount);
      localStorage.setItem("imgUrl", imgUrl);
      localStorage.setItem("introduction", introduction);
      // 使用编程式导航跳转到 songlist 页面
      this.$router.push({
        name: "songlist",
        params: {
          imgUrl: imgUrl,
        },
      });
    },
    handleEdit(index, row) {
      // 传递id的值
      this.loveid = row.id+1;
      this.currentId = row.id;
      let moren = document.querySelector("#moren");
      moren.style.display = "none";
      console.log(moren);
      this.tableSongData.songname = row.songName;
      this.tableSongData.singerName = row.singerName;
      console.log(row.id);
      let url = "http://159.65.4.58:8080/api/songs/listUrl/" + row.id;
      this.$http.get(url).then((res) => {
        this.musicUrl = res.data.data.purl;
        this.$nextTick(() => {
          this.loveid=this.loveid+1
          this.$refs.plyr.player.play(); // 播放音频
          this.drawer = false;
        });
        console.log(this.musicUrl);
      });
    },
    // 下一首
    next() {
      this.loveid = this.currentId + 1
      let nexturl =
        "http://159.65.4.58:8080/api/songs/url/next/" + this.currentId;
      this.$http.get(nexturl).then((res) => {
        this.musicUrl = res.data.data.purl;
        let moren = document.querySelector("#moren");
        moren.style.display = "none";
        this.tableSongData.songname = res.data.data.songName;
        this.tableSongData.singerName = res.data.data.singerName;
        this.currentId = res.data.data.id;
        this.$nextTick(() => {
          this.$refs.plyr.player.play(); // 播放音频
        });
      });
    },
    //上一首
    pre() {
      this.loveid = this.currentId 
      let preurl = "http://159.65.4.58:8080/api/songs/url/up/" + this.currentId;
      this.$http.get(preurl).then((res) => {
        this.musicUrl = res.data.data.purl;
        let moren = document.querySelector("#moren");
        moren.style.display = "none";
        this.tableSongData.songname = res.data.data.songName;
        this.tableSongData.singerName = res.data.data.singerName;
        this.currentId = res.data.data.id;
        this.$nextTick(() => {
          this.$refs.plyr.player.play(); // 播放音频
        });
      });
    },
    
    // 获取当前播放的相关信息
    getSong() {
      if(window.sessionStorage.getItem("username") == null){
        this.$message({
          message: "请先登录",
          type: "warning",
        });
        this.$router.push("/login");
      }
      // 查询用户id
      let username = window.sessionStorage.getItem("username");
      let userurl = "http://159.65.4.58:8080/api/users/info?username=" + username;
      this.$http.get(userurl).then((res) => {
        this.userid = res.data.data.id;
        console.log(this.userid);
      });
      // 添加信息到喜欢表单
      let url = "http://159.65.4.58:8080/api/songs/url/up/" + this.loveid;
      this.$http.get(url).then((res) => {
        console.log(res.data.data);
        
        this.$http.post("http://159.65.4.58:8080/api/songs/addSongToList",{
          userId: this.userid,
          songId: res.data.data.id,
          songName: res.data.data.songName,
          singerName: res.data.data.singerName,
          purl: res.data.data.purl,
          timeInterval: res.data.data.timeInterval,
          subtitle: res.data.data.subtitle,
          albumTitle: res.data.data.albumTitle,
        }).then((res)=>{
          console.log(res.data);
          if(res.data.code!=200){
            this.$message({
              message: "已经添加过了",
              type: "warning",
            });
          }else{
            this.$message.success("添加成功");
          }
        })
      });
    }
  },
};
</script>

<style lang="less" scoped>
.el-container {
  padding: 0;
}

.el-header,
.el-footer {
  position: relative;
  color: #3333;
  text-align: center;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-width: 1200px;
  padding: 0;
}
.el-footer{
  box-shadow: none !important;
}
.el-aside {
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
  text-align: center;
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
  right: -225px;
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
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
}
.bg-purple {
  height: 240px;
}
.bg-purple-light {
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
}
.el-breadcrumb__item:last-child .el-breadcrumb__inner,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover,
.el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
  font-weight: 400;
  color: #606266;
  cursor: default;
}
.song_title {
  margin-top: 5px;
  width: 224px;
  color: #000;
  font-size: 14px;
  text-align: left;
  margin-left: 24px;
}
.playcount {
  width: 224px;
  width: 224px;
  color: #999;
  font-size: 14px;
  text-align: left;
  margin-left: 24px;
}
.bg-purple[data-v-47323bf2] {
  background: none;
  height: opx;
}
.el-breadcrumb__item:last-child .el-breadcrumb__inner,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover,
.el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
  font-weight: 400;
  color: #606266;
  cursor: default !important;
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


/deep/ .plyr__controls {
    position: relative;
    align-items: center;
    display: flex;
    justify-content: flex-end;
    text-align: center;
}

/deep/ .plyr .plyr__controls {
  background-color: bga(249, 249, 249);
  background: var(--plyr-audio-controls-background, bga(249, 249, 249));
  border-radius: inherit;
  color: #4a5464;
  color: var(--plyr-audio-control-color, #4a5464);
  padding: 10px;
  padding: var(--plyr-control-spacing, 10px);
}
/deep/ .plyr__controls .plyr__controls__item.plyr__progress__container {
  width: 100%;
  padding-left: calc(10px / 4);
  padding-left: calc(var(--plyr-control-spacing, 10px) / 4);
  margin-top: -36px;
}
/deep/ .plyr__controls .plyr__controls__item:first-child {
    position: absolute;
    left: 795px;
    top: 24px;
    margin-left: 0;
    margin-right: auto;
    color: #333;
}
.next {
  float: left;
  position: absolute;
  left: 875px;
  top: -35px;
}
.next:hover {
  background-color: rgb(0, 179, 255);
}
.pre:hover {
  background-color: rgb(0, 179, 255);
}
.love i.icon-xiai:hover {
  color: red !important;
}
.pre {
  position: absolute;
  float: left;
  left: 720px;
  top: -36px;
}
.icon {
  position: absolute;
  float: left;
  left: 0;
  top: -40px;
}

.list {
  position: absolute;
  float: left;
  left: 1274px;
  top: -36px;
}

.love{
  position: absolute;
  float: left;
  left: 1204px;
  top: -36px;
}
.song {
  position: absolute;
  font-weight: bold;
  font-style: italic;
  color: #333;
  float: left;
  left: 60px;
  top: -35px;
}
.switch {
  position: relative;
}
/deep/ .el-drawer {
  width: 25% !important;
  position: absolute;
  box-sizing: border-box;
  background-color: rgb(249, 249, 249);
  display: flex;
  flex-direction: column;
  box-shadow: 0 8px 10px -5px rgba(0, 0, 0, 0.2),
    0 16px 24px 2px rgba(0, 0, 0, 0.14), 0 6px 30px 5px rgba(0, 0, 0, 0.12);
  overflow: hidden;
  outline: 0;
}

/deep/ [data-v-47323bf2] .plyr__controls .plyr__controls__item.plyr__time {
  left: 155px;
  position: relative;
  margin-top: -29px;
  padding: 0 calc(10px / 2);
  padding: 0 calc(var(--plyr-control-spacing, 10px) / 2);
}
/deep/ .plyr__menu {
     display: flex;
    position: absolute;
    top: 24px;
    right: 524px;
}


/deep/ [data-v-47323bf2] [data-v-47323bf2] .plyr__controls .plyr__controls__item.plyr__time {
    font-weight: bold;
    font-size: 16px;
    color: #333;
    position: absolute;
    padding: 0 calc(10px / 2);
    top: 51px;
    padding: 0 calc(var(--plyr-control-spacing, 10px) / 2);
}
/deep/ .plyr__volume {
  align-items: center;
    /* display: flex; */
    max-width: 110px;
    min-width: 80px;
    position: absolute;
    width: 20%;
    top: 24px;
    right: 570px;
}
/deep/ .plyr__controls .plyr__controls__item.plyr__progress__container {
  margin-right: -10px;
  margin-left: -44px;
  width: 100%;
  padding-left: calc(10px / 4);
  padding-left: calc(var(--plyr-control-spacing, 10px) / 4);
  margin-top: -15px;
}
</style>
