//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
import Vuex from 'vuex'
//引入子模块
import userInfo from './user.js'
import rateInfo from './rate.js'
import creditInfo from './credit.js'
import applicationInfo from './application.js'
import planInfo from './plan.js'

//应用Vuex插件
Vue.use(Vuex)

//创建并暴露store
export default new Vuex.Store(
    {
        modules: {
            userInfo,
            rateInfo,
            creditInfo,
            applicationInfo,
            planInfo,
        }
    })