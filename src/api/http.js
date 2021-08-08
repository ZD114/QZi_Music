/* eslint-disable no-undef */
import axios from 'axios'
axios.defaults.timeout = 5000
// axios.defaults.withCredentials = true 由于在config/index.js中已经设置跨域，不需要重复设置
// Content-Type头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
// 基本url
// axios.defaults.baseURL = 'http://localhost:8090/MusicManage' 已经在main.js中设置全局，再设置报404

// 响应拦截器
axios.interceptors.response.use(
  response => {
    // 如果response里面的status为200，说明访问到了接口，否则报错
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  error => {
    if (error.response.status) {
      switch (error.response.status) {
        case 401: // 未登录，跳转回登录页面
          router.replace({
            path: '/',
            query: {
              redirect: router.currentRoute.fullPath
            }
          })
          break
        case 404: // 页面路径未找到
          break
      }
      return Promise.reject(error.response)
    }
  }
)

/** 封装get方法 */
export function get (url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, {params: params})
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

/** 封装post方法 */
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}
