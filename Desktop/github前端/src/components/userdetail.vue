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
        </el-menu>
      </el-header>
      <!-- 中间部分 -->
      <el-main>
        <div class="div">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="出生日期">
              <el-col :span="11">
                <el-date-picker
                  type="date"
                  placeholder="选择日期"
                  v-model="form.date"
                  style="width: 100%"
                ></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="个性签名">
              <el-input type="textarea" v-model="form.description"></el-input>
            </el-form-item>
            <el-form-item style="margin-right: 166px">
              <el-button type="primary" @click="onSubmit">提交</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
      <!-- 底部 -->
    </el-container>
  </div>
</template>
  
  <script>
export default {
  mounted() {},

  name: "index",
  data() {
    // 轮播图
    return {
      form: {
        id: "",
        username: "",
        email: "",
        date: "",
        sex: "",
        description: "",
      },
      url:'../assets/images/OIP.jpg'
    };
  },
  // 定义钩子函数
  mounted() {
    let username = window.sessionStorage.getItem("username");
    let url = "http://159.65.4.58:8080/api/users/info?username=" + username;
    this.$http.get(url).then((res) => {
      console.log(res.data);
      this.form.id = res.data.data.id;
      this.form.username = res.data.data.username;
      this.form.email = res.data.data.email;
      this.form.date = res.data.data.date;
      this.form.description = res.data.data.description;
      this.form.sex = res.data.data.sex;
    });
    
  },
  // 轮播图
  methods: {
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
    // 提交修改按钮
    onSubmit() {
      this.$http
        .post("http://159.65.4.58:8080/api/users/update", this.form)
        .then((res) => {
          console.log(this.form.desc);
          console.log(this.form);
          console.log(res.data);
          if (res.data.code === 200) {
            this.$message.success("修改成功");
          } else {
            this.$message.error("修改失败");
          }
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
.div {
  float: left;
  margin-top: 40px;
  width: 400px;
}
.el-radio-group {
  font-size: 0;
  margin-left: -167px;
}
.div .el-form-item__content {
  line-height: 40px;
  position: relative;
  font-size: 14px;
  padding-left: 120px;
}
</style>
  