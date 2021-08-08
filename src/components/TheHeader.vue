<template>
  <div class="header">
    <!--折叠图片按钮-->
    <div
      class="collapse-btn"
      @click="collapseChange"
    >
      <i class="el-icon-menu"></i>
    </div>
    <!--后台管理-->
    <div class="logo">music后台管理</div>
    <!--切换全屏-->
    <div class="header-right">
      <!--用户头像和信息-->
      <div class="user-avator">
        <img src="../assets/img/头像1.jpg" />
      </div>
      <!--下拉菜单-->
      <el-dropdown
        class="user-name"
        trigger="click"
        @command="hadleCommand"
      >
        <!--computed中获取的值userName-->
        <span class="el-dropdown-link">
          {{userName}}
          <i class="el-icon-caret-bottom"></i>
        </span>
        <!--插槽slot-->
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <!--切换全屏图标-->
      <div
        class="btn-fullscreen"
        @click="handleFullScreen"
      >
        <el-tooltip
          :content="fullscreen?'取消全屏':'全屏'"
          placement="bottom"
        >
          <i class="el-icon-rank"></i>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
import bus from '../assets/js/bus'

export default {
  data () {
    return {
      collapse: false,
      fullscreen: false
    }
  },
  computed: {
    userName () {
      return localStorage.getItem('userName') // 获取Login.vue中塞入缓存的值
    }
  },
  methods: {
    // 改变左侧菜单收缩方法,bus.$emit指提交信息
    collapseChange () {
      this.collapse = !this.collapse
      bus.$emit('collapse', this.collapse)
    },
    // 全屏事件
    handleFullScreen () {
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen()
        } else if (document.webkitCancelFullScreen) { // safari、chrome浏览器
          document.webkitCancelFullScreen()
        } else if (document.mozCancelFullScreen) { // firefox
          document.mozCancelFullScreen()
        } else if (document.msExitFullScreen) { // ie
          document.msExitFullScreen()
        }
      } else {
        let element = document.documentElement
        if (element.requestFullscreen) {
          element.requestFullscreen()
        } else if (element.webkitCancelFullScreen) {
          element.webkitCancelFullScreen()
        } else if (element.mozCancelFullScreen) {
          element.mozCancelFullScreen()
        } else if (element.msExitFullScreen) {
          element.msExitFullScreen()
        }
      }
      this.fullscreen = !this.fullscreen
    },
    hadleCommand (command) {
      if (command === 'logout') { // 退出登录
        localStorage.removeItem('userName') // 清理缓存值
        this.$router.push('/')
      }
    }
  }
}
</script>

<style scoped>
.header {
  position: absolute;
  background-color: #253041;
  box-sizing: border-box;
  width: 100%;
  left: 0;
  height: 64px;
  font-size: 22px;
  color: #ffffff;
}
.header .logo {
  position: relative;
  line-height: 64px;
  float: left;
  margin-left: 10px;
}
.collapse-btn {
  float: left;
  padding: 0px;
  cursor: pointer;
  line-height: 70px;
  margin-left: 20px;
}
.header-right {
  float: right;
  padding-right: 50px;
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 0px;
  font-size: 24px;
}

.user-avator {
  margin-right: 10px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.user-name {
  margin-right: 25px;
}

.el-dropdown-link {
  color: #ffffff;
  cursor: pointer;
}
</style>
