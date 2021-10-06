/* eslint-disable no-unused-vars */
/* eslint-disable eol-last */
import {get, post} from './http'

// 判断管理员是否登录
export const getLoginStatus = (params) => post('/admin/checkLogin', params)

// 添加歌手
export const setSinger = (params) => post('singer/add', params)

// 查询所有歌手
export const getAllSinger = () => post('singer/searchAll')

// 查询所有用户
export const getAllUser = () => post('user/searchAll')
