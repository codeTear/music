<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <!-- 头部 -->
      <el-header> </el-header>
      <!-- 中间部分 -->
      <el-main>
        <div class="audio">
          <div class="echolizer"></div>
          <div class="colorizer"></div>
          <div class="disk"></div>
          <img class="singerimg" src="../assets/play/img/cover.jpg" />
        </div>
        <div class="song_content">
          <h2>
            {{ tableSongData.songname
            }}<span class="moun">{{ this.songname }} </span>
          </h2>
          <div class="singer">
            歌手 {{ tableSongData.singerName }}
            <span class="moun">{{ this.singerName }}</span>
          </div>
          <div class="album">
            专辑 {{ tableSongData.albumTitle }}
            <span class="moun">{{ this.albumTitle }}</span>
          </div>
  
          <div class="lyric" v-html="this.lyric.replace(/\n/g, '</br>')"></div>

      
        </div>
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
          <div class="song">
            <span id="moren">她说 林俊杰</span>
            {{ tableSongData.songname }} {{ tableSongData.singerName }}
            <span class="moun">{{ this.songname }} {{ this.singerName }} </span>
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
        <el-table-column property="singerName" label="歌手" width="0" align="center">
          </el-table-column>
        <el-table-column
          property="albumTitle"
          label="专辑"
          width="0"
          align="center"
        >
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
  	<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
      <script>
window.jQuery ||
  document.write(
    '<script src="../assets/play/js/jquery-2.1.1.min.js"></script>'
  );
</script>
      <script type="text/javascript" src="../assets/play/js/colorizer.js"></script>
      <script type="text/javascript">
$.colorizer("div.colorizer", {
  file: "Shahre_Man.mp3",
  shadow: ".colorizer",
  echolizer: ".echolizer",
});
</script>
<script>
export default {
  name: "index",

  data() {
    // 轮播图
    return {
      // 歌单列表的数据绑定对象
      lyric:"",
      songname: "",
      singerName: "",
      imagePath: "",
      albumTitle: "",
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
          albumTitle: "",
        },
      ],

      drawer: false,
    };
  },
  // 定义钩子函数
  mounted() {
    const urlParams = new URLSearchParams(window.location.hash.slice(1));

    // 获取参数值
    const singerName = window.sessionStorage.getItem("singerName");
    const songName = decodeURIComponent(urlParams.get("songName"));
    const albumTitle = decodeURIComponent(urlParams.get("albumTitle"));
    const imagePath = decodeURIComponent(urlParams.get("imagePath"));
    const url = decodeURIComponent(urlParams.get("url"));
    const timeInterval = decodeURIComponent(urlParams.get("timeInterval"));
    const currentId = decodeURIComponent(urlParams.get("currentId"));

    // 将参数值赋值给data中的变量

    let purl = "http://159.65.4.58:8080/api/songs/url/" + currentId;
    this.$http.get(purl).then((res) => {
      
      this.musicUrl = res.data.data.purl;
      let moren = document.querySelector("#moren");
      moren.style.display = "none";
      this.songname = songName;
      this.singerName = singerName;
      this.albumTitle = albumTitle;
      this.imagePath = imagePath;
      let img = document.querySelector(".singerimg");
      img.setAttribute("src", imagePath);
      this.$nextTick(() => {
        this.$refs.plyr.player.play(); // 播放音频
      });

      console.log(this.musicUrl);
    });



    // 获取歌单列表的数据

    this.$http.get("http://159.65.4.58:8080/api/songs/songSinger").then((res) => {
        // this.tableSongData = res.data["data"];
        console.log( res.data["data"]);
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
    // 跳转到歌手界面
    router1() {
      this.$router.push("/singerlist");
    },
    // 我的音乐
    mymusic() {
      this.$router.push("/user");
    },

    handleEdit(index, row) {
      console.log(row);
      // 传递id的值
      this.currentId = row.id;
      let moren = document.querySelector("#moren");
      moren.style.display = "none";
      let mouns = document.querySelectorAll(".moun");
      mouns.forEach((span) => {
        span.style.display = "none";
      });
     
      this.tableSongData.songname = row.songName;
      this.tableSongData.singerName = row.singerName;
      this.tableSongData.albumTitle = row.albumTitle;
      this.imagePath=row.singerPhoto
      let img = document.querySelector(".singerimg");
      img.setAttribute("src", this.imagePath);
      console.log(row.id);
      let url = "http://159.65.4.58:8080/api/songs/url/" + row.id;
      this.$http.get(url).then((res) => {
        this.musicUrl = res.data.data.purl;
        this.$nextTick(() => {
          this.$refs.plyr.player.play(); // 播放音频
          this.drawer = false;
        });
        console.log(this.musicUrl);
      });
    },
    // 下一首
    next() {
      let nexturl =
        "http://159.65.4.58:8080/api/songs/songUrl/down/" + this.currentId;
      this.$http.get(nexturl).then((res) => {
        this.musicUrl = res.data.data.purl;
        this.imagePath=res.data.data.singerPhoto
        let img = document.querySelector(".singerimg");
        img.setAttribute("src", this.imagePath);
        let moren = document.querySelector("#moren");
        moren.style.display = "none";
        let mouns = document.querySelectorAll(".moun");
        mouns.forEach((span) => {
          span.style.display = "none";
        });
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
      let preurl = "http://159.65.4.58:8080/api/songs/songUrl/up/" + this.currentId;
      this.$http.get(preurl).then((res) => {
        this.musicUrl = res.data.data.purl;
        let moren = document.querySelector("#moren");
        moren.style.display = "none";
        let mouns = document.querySelectorAll(".moun");
        this.imagePath=res.data.data.singerPhoto
        let img = document.querySelector(".singerimg");
        img.setAttribute("src", this.imagePath);
        mouns.forEach((span) => {
          span.style.display = "none";
        });
        this.tableSongData.songname = res.data.data.songName;
        this.tableSongData.singerName = res.data.data.singerName;
        this.currentId = res.data.data.id;
        this.$nextTick(() => {
          this.$refs.plyr.player.play(); // 播放音频
        });
      });
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
  width: 100%;
  min-width: 1200px;
  padding: 0;
}

.el-aside {
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  width: 1200px;
  min-width: 1200px;
  height: 829px;
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
  right: -465px;
  top: -3px;
}
.lyric{
  color: #000000;
  font-size: 14px;
  white-space: pre-line;
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
  left: 846px;
  top: 24px;
  margin-left: 0;
  margin-right: auto;
  color: #333;
}

.next {
  float: left;
  position: absolute;
  left: 915px;
  top: -35px;
}
.next:hover {
  background-color: rgb(0, 179, 255);
}
.pre:hover {
  background-color: rgb(0, 179, 255);
}
.pre {
  position: absolute;
  float: left;
  left: 770px;
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
  left: 1254px;
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
  position: relative;
  top: 14px;
  left: -450px;
}

/deep/ .plyr__controls .plyr__controls__item.plyr__time {
    left: 155px;
    position: relative;
    margin-top: -29px;
    padding: 0 calc(10px / 2);
    padding: 0 calc(var(--plyr-control-spacing, 10px) / 2);
}

/deep/ .plyr__volume {
  align-items: center;
  display: flex;
  max-width: 110px;
  min-width: 80px;
  position: relative;
  width: 20%;
  top: 14px;
  left: -450px;
}
/deep/ .plyr__controls .plyr__controls__item.plyr__progress__container {
  margin-right: -162px;
  margin-left: -44px;
  width: 100%;
  padding-left: calc(10px / 4);
  padding-left: calc(var(--plyr-control-spacing, 10px) / 4);
  margin-top: -36px;
}
body,
ul,
li {
  margin: 0;
  padding: 0;
}
body {
  background: #333;
}

.song_content {
  float: left;
  position: relative;
  top: 26px;
  left: 378px;
  width: 400px;
}

div.audio {
  float: left;
  position: relative;
  top: 100px;
  left: 100px;
  background: #eee;
  width: 400px;
  height: 400px;
  transition: all 0.3s;
  box-shadow: 33px 24px 10px 0 rgba(0, 0, 0, 0.2);
}
div.colorizer {
  position: absolute;
  left: 0;
  top: 0;
  width: 400px;
  height: 400px;
  transition: all 0.3s;
  z-index: 3;
}
div.echolizer {
  background: #fff;
  position: absolute;
  left: 0;
  bottom: 0;
  z-index: 10;
  width: 0;
  height: 4px;
  transition: all 0.3s;
}
div.audio img {
  width: 400px;
}
div.disk {
  background-image: url("../assets/play/img/cd.png");
  width: 350px;
  height: 350px;
  position: absolute;
  right: -140px;
  background-size: cover;
  z-index: 0;
  top: 34px;
  -webkit-animation: rotating 2s linear infinite;
  -moz-animation: rotating 2s linear infinite;
  -ms-animation: rotating 2s linear infinite;
  -o-animation: rotating 2s linear infinite;
  animation: rotating 2s linear infinite;
  border-radius: 50%;
}
@-webkit-keyframes rotating /* Safari and Chrome */ {
  from {
    -ms-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  to {
    -ms-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@keyframes rotating {
  from {
    -ms-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  to {
    -ms-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
</style>
  