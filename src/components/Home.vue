<template>
  <div>
    <the-header></the-header>
    <the-aside></the-aside>
    <div
      class="content-box"
      :class="{'content-collapse':collapse}"
    >
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import TheHeader from './TheHeader.vue'
import TheAside from './TheAside.vue'
import bus from '../assets/js/bus'

export default {
  components: {
    TheHeader,
    TheAside
  },
  data () {
    return {
      collapse: false
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
