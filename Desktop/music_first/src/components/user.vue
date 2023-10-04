<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <!-- 头部 -->
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1" @click="shouye">首页</el-menu-item>
          <el-menu-item index="1" @click="router1">歌手</el-menu-item>
          <el-menu-item index="1">歌单</el-menu-item>
          <el-menu-item index="1" >我的音乐</el-menu-item>
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
        </el-menu>
      </el-header>
      <div class="img">
        <div class="user_img">
          <el-upload
            class="avatar-uploader"
            action="http://159.65.4.58:8080/api/users/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :data="{username:username}"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <div class="username">{{ username }}</div>
        <span href="" class="detail" @click="skip">编辑详细资料</span>
      </div>
      <!-- 中间部分 -->
      <el-main>
        <div class="fav">
          <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1">我的喜欢</el-menu-item>
          <el-menu-item index="1" @click="router1">歌手</el-menu-item>
          <el-menu-item index="1">歌单</el-menu-item>
          <el-menu-item index="1">我的音乐</el-menu-item>
        </el-menu>
        </div>

        <el-table
          stripe
          ref="singleTable"
          :data="tableSongData"
          style="width: 100%; float: left; white-space: nowrap ;overflow: hidden ;text-overflow: ellipsis ;"
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
        </el-table>
      </el-main>
      <!-- 底部 -->
      <el-footer> </el-footer>
    </el-container>
  </div>
</template>
  
  <script>
export default {
   name: "index",
  data() {
    // 轮播图
    return {
      form: {
        search: "content",
      },
      username: "",
      // 头像
      imageUrl: "",
      username: "",
      tableSongData: [
        {
          songName: "",
          singerName: "",
          albumTitle: "",
          timeInterval: "",
        },
      ],
    };
  },
  mounted() {
    this.username=window.sessionStorage.getItem("username")
    let url = "http://159.65.4.58:8080/api/users/info?username=" + this.username;
    this.$http.get(url).then((res)=>{
      this.imageUrl=res.data.data.userImg
    })
  },
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
    // 用户所对应的详情页
    skip() {
      this.$router.push("/userdetail");
    },
    // 首页
    shouye() {
      this.$router.push("/index");
    },
    // 歌手
    router1() {
      this.$router.push("/singerlist");
    },

    handleAvatarSuccess(res, file) {
      console.log(res);
      this.username = res.data.username;
      this.imageUrl = URL.createObjectURL(file.raw);
      this.imageUrl = res.data.userImg
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
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
.img {
  width: 100%;
  height: 376px;
  background-image: url("../assets/images/userBgk.jpg");
}
.user_img {
  width: 110px;
    height: 110px;
    border-radius: 64px;
    margin-left: 801px;
    margin-top: 100px;
    margin-bottom: 100px;
  border-radius: 64px;
}
.username {
  color: #fff;
  font-size: 26px;
  margin: o auto;
  margin-left: 816px;
  margin-top: -87px;
}
.detail {
  margin-left: 806px;
  margin-top: 4px;
  text-decoration: none;
  color: #fff;
  font-size: 16px;
  cursor: default;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 112px;
  height: 112px;
  line-height: 112px;
  text-align: center;
}
.avatar {
  width: 112px;
  height: 112px;
  display: block;
  border-radius: 60px;
  background-color: #ccc;
}
.avatar-uploader-icon[data-v-7b4b534a] {
  font-size: 28px;
  color: #8c939d;
  width: 112px;
  height: 112px;
  line-height: 112px;
  text-align: center;
  background-color: #ccc;
}
.avatar-uploader-icon[data-v-37da89b8] {
    font-size: 28px;
    color: #8c939d;
    width: 112px;
    height: 112px;
    line-height: 112px;
    text-align: center;
}
.fav .el-menu-demo[data-v-7b4b534a] {
    padding-left: 0px;
}
.fav .el-menu {
    background-color:rgb(249, 249, 249);
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
</style>
  