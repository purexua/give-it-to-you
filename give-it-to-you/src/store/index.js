//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'

//引入Vuex
import Vuex from 'vuex'
import userInfo from './user.js'
import rateInfo from './rate.js'
import creditInfo from './credit.js'

//应用Vuex插件
Vue.use(Vuex)

//创建并暴露store
export default new Vuex.Store(
    {
        modules: {
            userInfo,
            rateInfo,
            creditInfo
        }
    })