import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import axios from 'axios';

Vue.config.productionTip = false

Vue.use(ElementUI)

axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
