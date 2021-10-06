<template>
  <div class="table">
    <!--添加歌手按钮，点击后弹出添加框-->
    <div class="container">
      <div class="handle-box">
        <el-button
          type="primary"
          size="mini"
          @click="centerDialogVisible = true"
        >添加歌手</el-button>
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
        label="歌手图片"
        width="100"
        align="center"
      >
        <!--scope指当前单元格-->
        <template slot-scope="scope">
          <div class="singer-img">
            <img
              :src="getUrl(scope.row.pic)"
              style="width: 100%"
            />
          </div>
          <!--更新歌手图片-->
          <el-upload
            :action="uploadUrl(scope.row.id)"
            :before-upload="beforeAvatorUpload"
            :on-success="handleAvatorSuccess"
          >
            <el-button size="mini">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column
        label="歌手"
        prop="name"
        width="120"
        align="centre"
      ></el-table-column>
      <el-table-column
        label="性别"
        width="60"
        align="center"
      >
        <!--性别转码触发-->
        <template slot-scope="scope">
          {{changeSex(scope.row.sex)}}
        </template>
      </el-table-column>
      <el-table-column
        label="生日"
        width="120"
        align="center"
      >
        <template slot-scope="scope">
          {{getBirth(scope.row.birth)}}
        </template>
      </el-table-column>
      <el-table-column
        label="地区"
        prop="location"
        align="center"
        width="200"
      ></el-table-column>
      <el-table-column label="简介">
        <template slot-scope="scope">
          <p style="height: 100px;overflow:scroll">{{scope.row.introduction}}</p>
        </template>
      </el-table-column>
    </el-table>
    <!--弹出框，默认不显示-->
    <el-dialog
      title="添加歌手"
      :visible="centerDialogVisible"
      width="400px"
      center
    >
      <el-form
        :model="singer"
        ref="singerForm"
        label-width="80px"
      >
        <el-form-item
          label="歌手名"
          prop="name"
          size="mini"
        >
          <el-input
            v-model="singer.name"
            placeholder="歌手名"
            prefix-icon="el-icon-edit"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="性别"
          prop="sex"
          size="mini"
        >
          <!--单选-->
          <el-radio-group v-model="singer.sex">
            <el-radio label="0">女</el-radio>
            <el-radio label="1">男</el-radio>
            <el-radio label="2">组合</el-radio>
            <el-radio label="3">未知</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="生日"
          prop="birth"
          size="mini"
        >
          <!--日期选择器-->
          <el-date-picker
            v-model="singer.birth"
            placeholder="选择日期"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="地区"
          prop="location"
          size="mini"
        >
          <el-input
            v-model="singer.location"
            placeholder="地区"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="简介"
          prop="introduction"
          size="mini"
        >
          <el-input
            v-model="singer.introduction"
            placeholder="简介"
            type="textarea"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button
          type="mini"
          @click="addSinger"
        >确定</el-button>
        <el-button
          type="mini"
          @click="centerDialogVisible = false"
        >取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getAllSinger, setSinger } from '../api/index'
import { mixin } from '../mixins/index'

export default {
  mixins: [mixin],
  data () {
    return {
      centerDialogVisible: false,
      singer: {
        name: '',
        sex: '',
        birth: '',
        location: '',
        introduction: ''
      },
      tableData: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    // 添加歌手
    addSinger () {
      let birth = this.singer.birth
      let datetime = birth.getFullYear() + '-' + (birth.getMonth() + 1) + '-' + birth.getDate()
      let params = new URLSearchParams()
      params.append('name', this.singer.name)
      params.append('sex', this.singer.sex)
      // 默认图片为后端图片路径
      params.append('pic', 'img/singerPic/头像1.jpg')
      params.append('birth', datetime)
      params.append('location', this.singer.location)
      params.append('introduction', this.singer.introduction)
      // 传参入后台，返回值
      setSinger(params).then(res => {
        if (res.code === 1) {
          // 自定义提示
          this.notify('添加成功', 'success')
          // this.$message.success('添加成功')
        } else {
          this.$message.error('添加失败')
        }
      }).catch(err => {
        console.log(err)
      })
      this.centerDialogVisible = false
    },
    // 查询所有歌手
    getData () {
      this.tableData = []
      getAllSinger().then(res => {
        this.tableData = res
      })
    },
    // 更新图片
    uploadUrl (id) {
      return `${this.$store.state.HOST}/singer/updateImg?id=${id}`
    }
  }
}
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.singer-img {
  width: 100%;
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
</style>
