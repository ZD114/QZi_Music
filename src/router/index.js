import Vue from 'vue'
import Router from 'vue-router'
import Login from '../pages/Login.vue'
import PageInfo from '../pages/InfoPage.vue'
import Home from '../components/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'music_manage',
      component: Login
    },
    {
      path: '/Home',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '/Info',
          name: 'PageInfo',
          component: PageInfo
        },
        {
          path: '/Consumer',
          // 另外一种定义路径写法
          component: resolve => require(['../pages/ConsumerPage.vue'], resolve)
        },
        {
          path: '/Singer',
          component: resolve => require(['../pages/SingerPage.vue'], resolve)
        },
        {
          path: '/SongList',
          component: resolve => require(['../pages/SongListPage.vue'], resolve)
        }
      ]
    }
  ]
})
