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
        label="用户头像"
        width="100"
        align="center"
      >
      </el-table-column>
      <el-table-column
        label="用户名"
        prop="username"
        width="120"
        align="centre"
      ></el-table-column>
      <el-table-column
        label="性别"
        width="120"
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
      ></el-table-column>
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
      ></el-table-column>
      <el-table-column
        label="更新时间"
        prop="update_time"
        width="120"
        align="centre"
      ></el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getAllUser } from '../api/index'
import { mixin } from '../mixins/index'

export default {
  mixins: [mixin],
  data () {
    return {
      centerDialogVisible: false,
      singer: {
        username: '',
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
    }
  }
}
</script>
<style scoped>
.handle-box {
  margin-bottom: 20px;
}
</style>
