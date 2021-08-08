<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#334256"
      text-color="#ffffff"
      active-text-color="#20a0ff"
      router
    >
      <template v-for="item in items">
        <template>
          <el-menu-item
            :index="item.index"
            :key="item.index"
          >
            <i :class="item.icon"></i>
            {{item.title}}
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from '../assets/js/bus'

export default {
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'el-icon-document',
          index: 'Info',
          title: '系统管理员'
        },
        {
          icon: 'el-icon-document',
          index: 'Consumer',
          title: '用户管理'
        },
        {
          icon: 'el-icon-document',
          index: 'Singer',
          title: '歌手管理'
        },
        {
          icon: 'el-icon-document',
          index: 'SongList',
          title: '歌单管理'
        }
      ]
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '')
    }
  },
  created () {
    // 通过bus进行组件之间通讯,左侧与头部互通bus.$on为接收信息
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>
<!-- scoped指样式只用于当前页面；el-menu中collapse为flase才展示宽度为150px-->
<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0px;
  background-color: #334256;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 150px;
}
.sidebar > ul {
  height: 100%;
}
</style>
