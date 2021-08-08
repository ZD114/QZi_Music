<template>
  <div class="login-wrap">
    <div class="ms-title">Music 后台管理登录</div>
    <div class="ms-login">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
      >
        <el-form-item prop="username">
          <el-input
            v-model="ruleForm.username"
            placeholder="用户名"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="ruleForm.password"
            type="password"
            placeholder="用户名"
          />
        </el-form-item>
      </el-form>
      <div class="login-btn">
        <el-button
          type="primary"
          @click="submitForm"
        >登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getLoginStatus } from '../api/index'

export default {
  data: function () {
    return {
      ruleForm: {
        username: 'admin',
        password: '123456'
      },
      rules: { // 校验必输
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm () {
      // debugger
      let params = new URLSearchParams()
      params.append('username', this.ruleForm.username)
      params.append('password', this.ruleForm.password)
      console.log(params.get('username') + ',' + params.get('password'))
      getLoginStatus(params)
        .then((res) => {
          console.log(res.code)
          if (res.code === 1) {
            localStorage.setItem('userName', this.ruleForm.username) // 放入缓存
            this.$router.push('/Info')
            this.$message.success('登录成功')
          } else {
            this.$message.error('登录失败')
          }
        })
    }
  }
}
</script>

<style>
.login-wrap {
  position: absolute;
  background: url("../assets/img/音乐管理界面.jpeg");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
  top: 0px; /*这里是设置与顶部的距离 目的是去除滑轮*/
  left: 0px; /*这里是设置与左边的距离*/
  bottom: 0px;
  right: 0px;
}
.ms-title {
  position: absolute;
  top: 20%;
  width: 100%;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: rgb(250, 245, 245);
}
.ms-login {
  position: absolute;
  left: 50%;
  top: 20%;
  width: 300px;
  height: 160px;
  margin-left: -190px;
  margin-top: 80px;
  padding: 40px;
  border-radius: 5px;
  background: rgb(238, 239, 242);
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
