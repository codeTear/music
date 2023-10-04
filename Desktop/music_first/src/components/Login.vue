<template>
  <!-- 登录组件 -->
  <div id="app">
    <div class="login_container">
      <div class="login">
        <div class="login_center">
          <h4 style="font-style: italic;font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;font-size: 18px;">Yinmusic</h4>
          <!-- 登录表单部分 -->
          <el-form
            ref="loginFormRef"
            :rules="loginRules"
            :model="loginForm"
            label-width="0"
          >
            <el-form-item prop="username">
              <el-input
                v-model="loginForm.username"
                placeholder="username"
                prefix-icon="el-icon-user-solid"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="password"
                class="pwd"
                prefix-icon="el-icon-lock"
              ></el-input>
            </el-form-item>
            <el-form-item prop="cheCode">
              <el-input
                v-model="loginForm.cheCode"
                placeholder="验证码"
                class="checode"
              ></el-input>
              <div style="float: right">
                <el-link @click="change" type="primary">看不清?</el-link>
              </div>
              <div class="che">
                <el-image
                  style="width: 80px; height: 50px"
                  :src="url"
                ></el-image>
              </div>
            </el-form-item>
            <el-form-item class="button">
              <el-button type="primary" @click="onSubmit">登录</el-button>
              <el-button @click="skip">注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="img"></div>
    </div>
  </div>
</template>

<!-- 行为 -->
<script>

export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        cheCode: "",
      },
      url: "http://159.65.4.58:8080/api/users/generate",

      //   验证规则
      loginRules: {
        // 用户名的验证
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        //   密码的验证
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        //   校验码的验证
        cheCode: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },

  methods: {
    // 表单提交按钮
    onSubmit() {
      console.log(this.loginForm);
      this.$refs.loginFormRef.validate(async (validate) => {
        if (!validate) return;
        try {
          let response = await this.$http.post(
            "http://159.65.4.58:8080/api/users/login",
            this.loginForm,
            {
              headers: {
                "Content-Type": "application/json",
              },
              // xhrFields: {
              //   withCredentials: true, //允许带上凭据
              // },
              credentials: "include",
            }
          );
        //   成功或者失败的处理方法
          if(response.data.code!=200){
            this.$message.error(response.data.msg)
          }else{
            // 处理token信息
            window.sessionStorage.setItem("token",response.data.data.token)
            window.sessionStorage.setItem("username",response.data.data.username)
            this.$message.success(response.data.msg)

            this.$router.push("/index")
          }
       
          console.log(response.data);
        } catch (error) {
          console.log(error);
        }
      });
    },
    // 验证码的变换
    change() {
      // console.log(document.getElementsByClassName("che"));
      this.url =
        "http://159.65.4.58:8080/api/users/generate?" + new Date().getMilliseconds();
    },
    skip(){
        this.$router.push("/register")
    }
  },
};
</script>

<!-- 样式 -->
<style lang="less" scoped>
#app {
  height: 100%;
  background-image: url('../assets/images/R.jpg');
}
.login_container {
  position: relative;
  top: 18%;
  height: 600px;
  width: 900px;
  margin: auto;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  border-radius: 18px;
}
.login {
  float: left;
  width: 450px;
  height: 600px;
  border-radius: 18px;
  background-color: rgb(250, 234, 200)
}
.img {
  width: 450px;
  height: 600px;
  float: left;
  background-image: url(../assets/images/login.jpg);
  background-repeat: no-repeat;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  border-top-right-radius: 16px;
  border-bottom-right-radius: 16px;
}
.login_center {
  margin: auto;
  margin-top: 30%;
  width: 300px;
}
.button {
  float: right;
}
.checode {
  width: 100px;
}
.che {
  float: right;
  margin-right: 20px;
}
</style>