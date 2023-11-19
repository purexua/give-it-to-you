import Vue from 'vue'
import App from './App.vue'

import axios from 'axios';
//引入store
import store from './store'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import router from './router'
// 完整引入
// 引入ElementUI组件库
import ElementUI from 'element-ui';
// 引入ElementUI全部样式
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false

Vue.use(ElementUI)
//使用路由器
Vue.use(VueRouter)

axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
