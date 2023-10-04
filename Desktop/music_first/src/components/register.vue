<template>
  <!-- 登录组件 -->
  <div id="app">
    <div class="register_container">
      <div class="register">
        <div class="register_center">
          <h4 style="font-style: italic;font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;font-size: 18px;">Yinmusic Register</h4>
          <!-- 登录表单部分 -->
          <el-form
            ref="registerFormRef"
            :rules="loginRules"
            :model="registerForm"
            label-width="0"
          >
            <el-form-item prop="username">
              <el-input
                v-model="registerForm.username"
                placeholder="username"
                prefix-icon="el-icon-user-solid"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="registerForm.password"
                type="password"
                placeholder="password"
                class="pwd"
                prefix-icon="el-icon-lock"
              ></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input
                v-model="registerForm.email"
                placeholder="邮箱号"
                class="email"
                prefix-icon="el-icon-s-promotion"
              ></el-input>
              <el-button
                type="primary"
                @click="sendEmail"
                style="margin-left: 10px"
                :disabled="isDisabled"
                >{{ buttonText }}</el-button
              >
            </el-form-item>
            <el-form-item prop="cheCode">
              <el-input
                v-model="registerForm.cheCode"
                placeholder="邮箱验证码"
                class="ch"
              ></el-input>
            </el-form-item>
            <el-form-item class="button">
              <el-button type="primary" @click="onSubmit">注册</el-button>
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
import axios from "axios";
export default {
  data() {
    return {
      registerForm: {
        username: "",
        password: "",
        cheCode: "",
        email: "",
      },
      //   定时器按钮
      isDisabled: false,
      buttonText: "发送",
      seconds: 60,
      //   验证规则
      loginRules: {
        // 用户名的验证
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        //   密码的验证
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        //   校验码的验证],

        cheCode: [{ required: true, message: "请输入验证码", trigger: "blur" }],

        //   邮箱的验证
        email: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },

  methods: {
    // 表单提交按钮
    onSubmit() {
      console.log(this.registerForm);
      this.$refs.registerFormRef.validate(async (validate) => {
        if (!validate) return;
        try {
          let response = await this.$http.post(
            "http://159.65.4.58:8080/api/users/register",
            this.registerForm,
            {
              headers: {
                "Content-Type": "application/json",
              },
              xhrFields: {
                withCredentials: true, //允许带上凭据
              },
              credentials: "include",
            }
          );
          //   成功或者失败的处理方法
          if (response.data.code != 200) {
            console.log("验证码错误");
          } else {
            this.$message.success("注册成功");
            this.$router.push("/login");
          }
          console.log(response.data);
        } catch (error) {
          console.log(error);
        }
      });
    },
    // 邮箱码的获取
    async sendEmail() {
      const email = this.registerForm.email;
      let res = await this.$http.post("http://159.65.4.58:8080/api/mail", { email });
      if ((res.data = "success")) {
        this.$message.success("发送成功");
      } else {
        this.$message.error("发送失败");
      }
      this.isDisabled = true;

      // 设置定时器，60 秒后恢复按钮状态
      let timer = setInterval(() => {
        if (this.seconds > 0) {
          this.seconds--;
          this.buttonText = `${this.seconds}秒`;
        } else {
          // 还原按钮状态
          clearInterval(timer);
          this.isDisabled = false;
          this.buttonText = "发送";
          this.seconds = 60;
        }
      }, 1000);
    },
  },
};
</script>
  
  <!-- 样式 -->
  <style lang="less" scoped>
#app {
  height: 100%;
  background-image: url('../assets/images/R.jpg');
}
.register_container {
  position: relative;
  top: 18%;
  height: 600px;
  width: 900px;
  margin: auto;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  border-radius: 18px;
}
.register {
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
.register_center {
  margin: auto;
  margin-top: 30%;
  width: 300px;
}
.button {
  float: right;
}
.email {
  width: 210px;
}
</style>