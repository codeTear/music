<template>
  <!-- 轮播图 -->
  <div id="app">
    <el-container>
      <el-header
        ><i
          class="iconfont icon-tubiaozhizuomobanyihuifu-"
          style="font-size: 42px; color: #fff"
        ></i>
        Star 音乐后台管理</el-header
      >
      <el-container>
        <el-aside width="200px">
          <!-- 侧边栏 -->
          <el-row class="tac">
            <el-col :span="12">
              <el-menu
                default-active="1"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
              >
                <el-menu-item index="1" @click="selectMenu(1)">
                  <i class="el-icon-user-solid"></i>
                  <span slot="title">用户管理</span>
                </el-menu-item>
                <el-menu-item index="2" @click="selectMenu(2)">
                  <i class="el-icon-menu"> </i>
                  <span slot="title">数据统计</span>
                </el-menu-item>
                <el-menu-item index="3" @click="selectMenu(3)">
                  <i class="el-icon-document"></i>
                  <span slot="title">歌曲管理</span>
                </el-menu-item>
                <el-menu-item index="4" @click="selectMenu(4)">
                  <i class="el-icon-setting"></i>
                  <span slot="title">mv管理</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <!-- 中间部分 -->
        <el-main>
          <!-- 用户列表 -->
          <template v-if="activeMenu === 1">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>

            <!-- 卡片 -->
            <el-card class="box-card">
              <div class="text item">
                <!-- 用户查询 -->
                <el-form
                  :inline="true"
                  :model="formInline"
                  class="demo-form-inline"
                  texalign="left"
                >
                  <el-form-item>
                    <el-input
                      v-model="formInline.user"
                      placeholder="请输入内容"
                    ></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                  </el-form-item>
                </el-form>
                <!-- 用户列表 -->
                <template>
                  <el-table :data="tableData" border style="width: 100%">
                    <el-table-column type="index" width="50"> </el-table-column>
                    <el-table-column prop="userImg" label="头像" width="120px">
                      <template slot-scope="scope1">
                        <div class="demo-type">
                          <el-avatar :size="60">
                            <img :src="scope1.row.userImg" />
                          </el-avatar>
                        </div>
                      </template>
                    </el-table-column>
                    <el-table-column prop="username" label="用户名">
                    </el-table-column>
                    <el-table-column prop="description" label="个性签名">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="sex" label="性别"> </el-table-column>
                    <el-table-column label="操作">
                      <template slot-scope="scope">
                        <el-button
                          type="primary"
                          icon="el-icon-edit"
                          circle
                          @click="handleEdit(scope.$index, scope.row)"
                        ></el-button>
                        <el-button
                          type="danger"
                          icon="el-icon-delete"
                          circle
                          @click="handleEdit1(scope.$index, scope.row)"
                        ></el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-dialog
                    title="提示"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose"
                  >
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
                        <el-input
                          type="textarea"
                          v-model="form.description"
                        ></el-input>
                      </el-form-item>
                      <el-form-item style="margin-right: 166px">
                        <el-button type="primary" @click="onSubmit"
                          >提交</el-button
                        >
                        <el-button @click="cancel">取消</el-button>
                      </el-form-item>
                    </el-form>
                  </el-dialog>
                  <div class="block" style="margin-top: 20px">
                    <el-pagination
                      @size-change="handleSizeChange"
                      @current-change="handleCurrentChange"
                      :current-page="currentPage"
                      :page-sizes="[5, 10, 15, 20]"
                      :page-size="5"
                      layout="total, sizes, prev, pager, next, jumper"
                      :total="totalPages"
                    >
                    </el-pagination>
                  </div>
                </template>
              </div>
            </el-card>
          </template>

          <!-- 数据统计 -->
          <template v-if="activeMenu === 2">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>数据统计</el-breadcrumb-item>
            </el-breadcrumb>

            <!-- 卡片 -->
            <el-card class="box-card">
              <div class="text item">
                <!-- 用户查询 -->
                <el-form
                  :inline="true"
                  :model="formInline"
                  class="demo-form-inline"
                  texalign="left"
                >
                  <el-form-item>
                    <el-input
                      v-model="formInline.user"
                      placeholder="请输入内容"
                    ></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                  </el-form-item>
                </el-form>
                <!-- 用户列表 -->
                <template>
                  <el-table :data="tableData" border style="width: 100%">
                    <el-table-column type="index" width="50"> </el-table-column>
                    <el-table-column prop="userImg" label="头像">
                    </el-table-column>
                    <el-table-column prop="username" label="用户名">
                    </el-table-column>
                    <el-table-column prop="description" label="个性签名">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="sex" label="性别"> </el-table-column>
                    <el-table-column label="操作">
                      <el-button
                        type="primary"
                        icon="el-icon-edit"
                        circle
                      ></el-button>
                      <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                      ></el-button>
                    </el-table-column>
                  </el-table>
                </template>
              </div>
            </el-card>
          </template>
          <!-- 歌曲统计 -->
          <template v-if="activeMenu === 3">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>歌曲统计</el-breadcrumb-item>
            </el-breadcrumb>

            <!-- 卡片 -->
            <el-card class="box-card">
              <div class="text item">
                <!-- 用户查询 -->
                <el-form
                  :inline="true"
                  :model="formInline"
                  class="demo-form-inline"
                  texalign="left"
                >
                  <el-form-item>
                    <el-input
                      v-model="formInline.user"
                      placeholder="请输入内容"
                    ></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                  </el-form-item>
                </el-form>
                <!-- 用户列表 -->
                <template>
                  <el-table :data="tableData" border style="width: 100%">
                    <el-table-column type="index" width="50"> </el-table-column>
                    <el-table-column prop="userImg" label="头像">
                    </el-table-column>
                    <el-table-column prop="username" label="用户名">
                    </el-table-column>
                    <el-table-column prop="description" label="个性签名">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="sex" label="性别"> </el-table-column>
                    <el-table-column label="操作">
                      <el-button
                        type="primary"
                        icon="el-icon-edit"
                        circle
                      ></el-button>
                      <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                      ></el-button>
                    </el-table-column>
                  </el-table>
                </template>
              </div>
            </el-card>
          </template>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "index",

  data() {
    // 轮播图
    return {
      // 分页
      totalPages: 100,

      currentPage: 1,

      pageSize: 5,

      dialogVisible: false,

      // 用户
      formInline: {
        user: "",
      },
      tableData: [
        {
          userImg: "",
          username: "",
          email: "",
          sex: "",
          description: "",
        },
      ],

      form: {
        id: "",
        username: "",
        email: "",
        date: "",
        sex: "",
        description: "",
      },

      activeMenu: 1,
    };
  },

  mounted() {
    // 查询分页用户
    this.selectAllPage();

    //获取对应id的用户
  },

  //   方法
  methods: {
    // 左侧导航栏
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    // 用户搜索
    onSubmit() {
      console.log("submit!");
    },

    // 导航栏对应内容
    selectMenu(index) {
      this.activeMenu = index;
      console.log(index);
    },

    // 获取分页用户
    selectAllPage() {
      // 分页获取喜爱歌单表
      let Pageurl =
        "http://159.65.4.58:8080/api/users/page?page=" +
        this.currentPage +
        "&size=" +
        this.pageSize +
        "";
      this.$http.get(Pageurl).then((res) => {
        console.log(res.data);
        this.totalPages = res.data.data.total;
        let data = res.data.data.data;
        this.tableData = data;
      });
    },
    // 分页
    handleSizeChange(val) {
      重新设置分页的条数;
      this.pageSize = val;
      this.selectAllPage();
    },
    handleCurrentChange(val) {
      // 重新设置分页的页码
      this.currentPage = val;
      this.selectAllPage();
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },

    handleEdit(index, row) {
      //修改页面显示
      this.dialogVisible = true;
      console.log(row.username);
      // 获取对应id的用户
      let url =
        "http://159.65.4.58:8080/api/users/info?username=" + row.username;
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
            this.dialogVisible = false;
            this.selectAllPage();
          } else {
            this.$message.error("修改失败");
            this.dialogVisible = false;
            this.selectAllPage();
          }
        });
    },
    // 取消按钮
    cancel() {
      this.dialogVisible = false;
    },

    handleEdit1(index, row) {
      // 删除用户
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let url = "http://159.65.4.58:8080/api/users/delete/" + row.id;
          this.$http.delete(url).then((res) => {
            if (res.data.code != 200) {
              this.$message({
                type: "success",
                message: "删除失败!",
              });
            } else {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            }
            this.selectAllPage();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>

<style lang="less" scoped>
.el-header,
.el-footer {
  background-color: rgb(53, 61, 64);
  color: #fff;
  text-align: center;
  line-height: 60px;
  font-size: 26px;
  font-style: italic;
  text-align: left;
}

.el-aside {
  background-color: rgb(84, 92, 100);
  color: #333;
  height: 1000px;
  text-align: center;
}

.el-main {
  background-color: #e9eef3;
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
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
.el-col-12 {
  width: 100%;
}
.tac {
  margin-top: 8px;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
  height: 900px;
  margin-top: 20px;
}
</style>
