<template>
  <div class="table">
    <!--添加用户按钮，点击弹出新增框-->
    <div class="container">
      <div class="handle-box">
        <el-button
          type="primary"
          size="mini"
          @click="centerDialogVisible = true"
        >添加用户</el-button>
      </div>
    </div>
    <!--表格列表-->
    <el-table
      :data="tableData"
      size="mini"
      border
      style="width: 100%"
      height="500px"
    >
      <el-table-column
        type="selection"
        width="40"
      ></el-table-column>
      <el-table-column
        label="用户头像"
        width="100"
        align="center"
      >
        <!--scope指当前单元格-->
        <template slot-scope="scope">
          <div class="singer-img">
            <img
              :src="getUrl(scope.row.avtor)"
              style="width: 100%"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="用户名"
        prop="username"
        width="120"
        align="centre"
      ></el-table-column>
      <el-table-column
        label="性别"
        width="60"
        align="centre"
      >
        <!--性别转码触发-->
        <template slot-scope="scope">
          {{changeSex(scope.row.sex)}}
        </template>
      </el-table-column>
      <el-table-column
        label="手机号"
        prop="phone_num"
        width="120"
        align="centre"
      ></el-table-column>
      <el-table-column
        label="邮箱"
        prop="email"
        width="120"
        align="centre"
      ></el-table-column>
      <el-table-column
        label="生日"
        prop="birth"
        width="120"
        align="centre"
      >
        <template slot-scope="scope">
          {{changeDate(scope.row.birth)}}
        </template>
      </el-table-column>
      <el-table-column label="签名">
        <template slot-scope="scope">
          <p style="height: 100px;overflow:scroll">{{scope.row.introduction}}</p>
        </template>
      </el-table-column>
      <el-table-column label="地址">
        <template slot-scope="scope">
          <p style="height: 100px;overflow:scroll">{{scope.row.location}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        prop="create_time"
        width="120"
        align="centre"
      >
      </el-table-column>
      <el-table-column
        label="更新时间"
        prop="update_time"
        width="120"
        align="centre"
      >
      </el-table-column>
    </el-table>
    <!--新增弹窗-->
    <el-dialog
      title="新增用户"
      :visible="centerDialogVisible"
      width="400px"
      center
      :show-close="false"
    >
      <el-form
        :model="consume"
        ref="consumForm"
        label-width="80px"
      >
        <el-form-item
          label="用户名称"
          prop="username"
          size="mini"
        >
          <el-input
            v-model="consume.username"
            placeholder="请输入用户名称"
            prefix-icon="el-icon-edit"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="性别"
          prop="sex"
          size="mini"
        >
          <!--单选框-->
          <el-radio-group v-model="consume.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
            <el-radio :label="3">不明</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="手机号码"
          prop="phone_num"
          size="mini"
          required="true"
        >
          <el-input
            v-model="consume.phone_num"
            placeholder="请输入手机号"
            prefix-icon="el-icon-edit"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="邮箱"
          prop="email"
          size="mini"
        >
          <el-input
            v-model="consume.email"
            placeholder="请输入邮箱号码"
            prefix-icon="el-icon-edit"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="生日"
          prop="birth"
          size="mini"
        >
          <!--日期选择器-->
          <el-date-picker
            v-model="consume.birth"
            placeholder="请选择日期"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="简介"
          prop="introduction"
          size="mini"
        >
          <el-input
            v-model="consume.introduction"
            placeholder="请输入简介内容"
            prefix-icon="el-icon-edit"
            type="textarea"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="地址"
          prop="location"
          size="mini"
        >
          <el-input
            v-model="consume.location"
            placeholder="请输入地址"
            prefix-icon="el-icon-edit"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button
          type="primary"
          @click="addConsume"
        >确定</el-button>
        <el-button
          type="primary"
          @click="centerDialogVisible=false"
        >取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { getAllUser, addUser } from '../api/index'
import { mixin } from '../mixins/index'

export default {
  mixins: [mixin],
  data () {
    return {
      centerDialogVisible: false,
      consume: {
        username: '',
        password: '',
        sex: '',
        phone_num: '',
        email: '',
        birth: '',
        introduction: '',
        location: '',
        create_time: '',
        update_time: ''
      },
      tableData: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    // 查询所有用户
    getData () {
      this.tableData = []
      getAllUser().then(res => {
        this.tableData = res
      })
    },
    // 新增用户
    addConsume () {
      let date = new Date().getDate()
      let birth = this.consume.birth
      let datetime = birth.getFullYear() + '-' + (birth.getMonth() + 1) + '-' + birth.getDate()
      let params = new URLSearchParams()
      params.append('username', this.consume.username)
      params.append('password', '123456')
      params.append('sex', this.consume.sex)
      params.append('phone_num', this.consume.phone_num)
      params.append('email', this.consume.email)
      params.append('introduction', this.consume.introduction)
      params.append('location', this.consume.location)
      params.append('birth', datetime)
      params.append('create_time', this.changeDate(date))
      addUser(params).then(res => {
        if (res.flag) {
          this.notify('新增成功', 'success')
          this.getData()
        } else {
          this.$message.error('新增失败')
        }
      })
      this.centerDialogVisible = false
    },
    changeDate (birth) {
      return this.$moment(birth).format('YYYY-MM-DD')
    }
  }
}
</script>
<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.handle-input {
  width: 300px;
  display: inline-block;
}
</style>
