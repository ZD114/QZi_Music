<template>
  <div class="table">
    <!--添加歌手按钮，点击后弹出添加框-->
    <div class="container">
      <div class="handle-box">
        <el-button
          type="primary"
          size="mini"
          @click="delAll"
        >批量删除</el-button>
        <el-input
          v-model="querySingerName"
          placeholder="请输入歌手名称"
          size="mini"
          class="handle-input"
        ></el-input>
        <el-button
          type="primary"
          size="mini"
          @click="centerDialogVisible = true"
        >添加歌手</el-button>
      </div>
    </div>
    <!--表格列表-->
    <el-table
      :data="data"
      size="mini"
      border
      style="width: 100%"
      height="550px"
      @selection-change="hanleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="40"
      ></el-table-column>
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
        align="center"
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
          {{changeDate(scope.row.birth)}}
        </template>
      </el-table-column>
      <el-table-column
        label="地区"
        prop="location"
        align="center"
        width="200"
      ></el-table-column>
      <el-table-column
        label="简介"
        align="center"
      >
        <template slot-scope="scope">
          <p style="height: 100px;overflow:scroll;">{{scope.row.introduction}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="200"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="modify(scope.row)"
          >修改</el-button>
          <el-button
            type="primary"
            size="mini"
            @click="handleDel(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页选择区-->
    <div class="pagination-style">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5,10,15,20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length"
      >
      </el-pagination>
    </div>
    <!--新增弹出框，默认不显示-->
    <el-dialog
      title="添加歌手"
      :visible="centerDialogVisible"
      width="400px"
      center
      :show-close="false"
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
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">组合</el-radio>
            <el-radio :label="3">未知</el-radio>
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
    <!--修改弹窗框-->
    <el-dialog
      title="修改歌手信息"
      :visible="modifyDialogVisible"
      width="400px"
      center
      :show-close="false"
    >
      <el-form
        :model="singerModify"
        ref="singerModify"
        label-width="80px"
      >
        <el-form-item
          label="歌手名"
          prop="name"
          size="mini"
        >
          <el-input
            v-model="singerModify.name"
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
          <el-radio-group v-model="singerModify.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">组合</el-radio>
            <el-radio :label="3">未知</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="生日"
          prop="birth"
          size="mini"
        >
          <!--日期选择器-->
          <el-date-picker
            v-model="singerModify.birth"
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
            v-model="singerModify.location"
            placeholder="地区"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="简介"
          prop="introduction"
          size="mini"
        >
          <el-input
            v-model="singerModify.introduction"
            placeholder="简介"
            type="textarea"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button
          type="mini"
          @click="modifySave"
        >确定</el-button>
        <el-button
          type="mini"
          @click="modifyDialogVisible = false"
        >取消</el-button>
      </span>
    </el-dialog>
    <!--删除弹框-->
    <el-dialog
      title="删除歌手"
      :visible="delVisible"
      width="300px"
      center
      :show-close="false"
    >
      <div align="center">删除不可恢复，是否确认删除？</div>
      <span slot="footer">
        <el-button
          type="mini"
          @click="delRow"
        >确定</el-button>
        <el-button
          type="mini"
          @click="delVisible = false"
        >取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getAllSinger, setSinger, saveSinger, delSingerById } from '../api/index'
import { mixin } from '../mixins/index'

export default {
  mixins: [mixin],
  data () {
    return {
      modifyDialogVisible: false, // 修改弹窗是否显示
      centerDialogVisible: false, // 新增弹窗是否显示
      delVisible: false, // 删除弹窗是否显示
      singer: { // 新增框信息
        name: '',
        sex: '',
        birth: '',
        location: '',
        introduction: ''
      },
      singerModify: { // 修改框信息
        id: '',
        name: '',
        sex: '',
        birth: '',
        location: '',
        introduction: ''
      },
      tempData: [], // 根据查询条件查回的数据
      querySingerName: '', // 输入要查询的歌手名字
      tableData: [], // 查询回来的数据
      totalSize: 0, // 数据总条数
      currentPage: 1, // 当前页码
      pageSize: 5, // 每页条数
      offset: 0, // 起始行数
      indx: -1, // 当前选择项
      multipleSelection: [] // 哪些项已经打钩
    }
  },
  computed: {
    // 计算当前分页页面数据量
    data () {
      return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  watch: { // 监听（当歌手名查询条件改变时调用）
    querySingerName: function () {
      if (this.tempData === '') {
        this.tableData = this.tempData
      } else {
        this.tableData = []
        for (let item of this.tempData) {
          if (item.name.includes(this.querySingerName)) { // 把存在的放入显示列表
            this.currentPage = '1'
            this.tableData.push(item)
          }
        }
      }
    }
  },
  created () {
    this.getData()
  },
  methods: {
    // 添加歌手
    addSinger () {
      let birth = this.singer.birth
      // let datetime = birth.getFullYear() + '-' + (birth.getMonth() + 1) + '-' + birth.getDate()
      let params = new URLSearchParams()
      params.append('name', this.singer.name)
      params.append('sex', this.singer.sex)
      // 默认图片为后端图片路径
      params.append('pic', 'img/singerPic/头像1.jpg')
      params.append('birth', birth)
      params.append('location', this.singer.location)
      params.append('introduction', this.singer.introduction)
      // 传参入后台，返回值
      setSinger(params).then(res => {
        if (res.code === 1) {
          // 自定义提示
          this.notify('添加成功', 'success')
          this.getData()
          // this.$message.success('添加成功')
        } else {
          this.$message.error('添加失败')
        }
      }).catch(err => {
        console.log(err)
      })
      this.centerDialogVisible = false
    },
    // 修改歌手
    modify (row) {
      this.modifyDialogVisible = true
      this.singerModify = {
        id: row.id,
        name: row.name,
        sex: row.sex,
        birth: row.birth,
        location: row.location,
        introduction: row.introduction
      }
    },
    // 保存修改歌手
    modifySave () {
      let id = this.singerModify.id
      let name = this.singerModify.name
      let sex = this.singerModify.sex
      let birth = this.singerModify.birth
      let location = this.singerModify.location
      let introduction = this.singerModify.introduction
      let params = new URLSearchParams()
      params.append('id', id)
      params.append('name', name)
      params.append('sex', sex)
      params.append('birth', birth)
      params.append('location', location)
      params.append('introduction', introduction)
      // 调用后台方法
      saveSinger(params).then(res => {
        if (res.code === 1) {
          // 自定义提示
          this.notify('修改成功', 'success')
          this.getData()
          // this.$message.success('添加成功')
        } else {
          this.$message.error('修改失败')
        }
      }).catch(err => {
        console.log(err)
      })
      this.modifyDialogVisible = false
    },
    // 删除一名歌手
    delRow () {
      delSingerById(this.indx).then(res => {
        if (res) {
          this.notify('删除成功', 'success')
          this.getData()
        } else {
          this.$message.error('删除失败')
        }
      }).catch(err => {
        console.log(err)
      })
      this.delVisible = false
    },
    // 查询所有歌手
    getData () {
      this.tempData = []
      this.tableData = []
      getAllSinger().then(res => {
        this.tempData = res
        this.tableData = res
      })
    },
    // 更新图片
    uploadUrl (id) {
      return `${this.$store.state.HOST}/singer/updateImg?id=${id}`
    },
    handleSizeChange (size) {
      this.pageSize = size
    },
    handleCurrentChange (page) {
      this.currentPage = page
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
.singer-img {
  width: 100%;
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
.handle-input {
  width: 300px;
  display: inline-block;
}
.pagination-style {
  display: flex;
  justify-content: center;
}
</style>
