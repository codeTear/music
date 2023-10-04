<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <!-- 头部 -->
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="shouye">首页</el-menu-item>
          <el-menu-item index="1">歌手</el-menu-item>
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
            <div class="user" @click="mymusic">
              <el-avatar
                :src=url
              ></el-avatar>
            </div>
          </el-menu-item>
        </el-menu>
      </el-header>
      <!-- 中间部分 -->
      <div class="div" style="margin-top: 1px">
        
      </div>
      <div class="img"></div>
      <!-- 主体部分 -->
      <el-main>
        <!-- 动态生成图片 -->
        <el-row :gutter="20" style="margin-left: 0; margin-right: 0">
          <el-col v-for="(img, index) in singerImages" :key="index" :span="4">
            <div class="grid-content bg-purple">
              <div class="singer_img"  @click="goToSingerList(singerNames[index], singerDetails[index],singerImages[index])">
                <el-image
                  style="width: 140px; height: 140px; border-radius: 90px"
                  :src="img"
                ></el-image>
              </div>
              <div class="singer_name">{{ singerNames[index] }}</div>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <!-- 底部 -->
    </el-container>
  </div>
</template>
  
<script>
export default {
  name: "singerlist",
  data() {
    // 轮播图
    return {
      form: {
        search: "content",
      },
      singerImages: [],
      singerNames: [],
      singerDetails: [],
      url: "",
    };
  },
  //   定义一个钩子
  mounted() {
    this.$http.get("http://159.65.4.58:8080/api/songs").then((res) => {
      let data = res.data["data"];
   
        // 声明数组存放data域里面的值
        let singerImages = [];
        let singerNames = [];
        let singerDetails=[];
        // 循环遍历请求到的值
        for (let item of data) {
          singerImages.push(item.singerPhoto);
          singerNames.push(item.singerName);
          singerDetails.push(item.singerDetail)
        }
        this.singerImages = singerImages;
        this.singerNames = singerNames;
        this.singerDetails = singerDetails;
        // console.log(this.singerDetails);
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
    // 默认加载她说

    this.musicUrl ='http://159.65.4.58/music/music_list/她说.mp3'
      this.currentId=14
  },
  // 页面跳转到详情页面
  methods: {
    goToSingerList(singerName, singerDetail,singerImage) {
    // 使用编程式导航跳转到 singer 页面
    this.$router.push({
        name:'singer',
        params: {
        singerName: singerName,
        singerDetail: singerDetail,
        singerImage:singerImage,
      },
    });
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
  text-align: center;
  line-height: 160px;
}
.el-main::-webkit-scrollbar {
  width: 0;
  height: 0;
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
.div .el-menu--horizontal > .el-menu-item {
  color: #333;
  font-size: 14px;
  padding-right: 20px;
}
.div_1 {
  width: 100%;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.div {
  background-color: #fff;
  width: 100%;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
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
  //   background: #99a9bf;
}
.bg-purple {
  //   background: #d3dce6;
  height: 240px;
}
.bg-purple-light {
  //   background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  //   background-color: #f9fafc;
}

.img {
  width: 100%;
  height: 376px;
  background-image: url("../assets/images/totalsinger.jpg");
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
  //   background: #99a9bf;
}
.bg-purple {
  //   background: #d3dce6;
}
.bg-purple-light {
  //   background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  //   background-color: #f9fafc;
}
.singer_img {
  position: relative;
  top: 30px;
  width: 140px;
  height: 140px;
  margin: 0 auto;
  background-color: pink;
  border-radius: 90px;
}
.el-col[data-v-2944c72a] {
  border-radius: 4px;
  width: 235px;
}
.singer_name {
  position: relative;
  left: 4px;
  line-height: 0;
  bottom: -54px;
}
.el-image__error,
.el-image__inner,
.el-image__placeholder {
  width: 100%;
  height: 100%;
  border-radius: 90px;
}
.el-breadcrumb__item:last-child .el-breadcrumb__inner,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a,
.el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover,
.el-breadcrumb__item:last-child .el-breadcrumb__inner:hover {
  font-weight: 400;
  color: #606266;
  cursor: default;
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
  