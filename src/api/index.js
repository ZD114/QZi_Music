/* eslint-disable no-unused-vars */
/* eslint-disable eol-last */
import {get, post} from './http'

// 判断管理员是否登录
export const getLoginStatus = (params) => post('/admin/checkLogin', params)

// 添加歌手
export const setSinger = (params) => post('singer/add', params)

// 查询所有歌手
export const getAllSinger = () => post('singer/searchAll')

// 保存修改后的歌手信息
export const saveSinger = (params) => post('singer/update', params)

// 根据歌手编号删除歌手
export const delSingerById = (id) => get(`singer/delSinger?id=${id}`)

// 查询所有用户
export const getAllUser = () => post('user/searchAll')

// 新增用户
export const addUser = (params) => post('user/addUser', params)