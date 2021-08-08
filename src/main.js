// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VCharts from 'v-charts'
import axios from 'axios'
import './assets/css/mian.css'
import qs from 'qs'
import store from './store/index.js' // 引入qs
Vue.prototype.$qs = qs

Vue.config.productionTip = false
Vue.prototype.$axios = axios
// 设置基本路径
axios.defaults.baseURL = '/MusicManage'
Vue.use(ElementUI)
Vue.use(VCharts)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
