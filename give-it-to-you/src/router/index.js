// 该文件专门用于创建整个应用的路由器
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePageView from '../views/HomePageView.vue'
import LoginView from '../views/LoginView.vue'
import Index from '../views/Index.vue'
import Home from '../views/index/Home.vue'
import ProductApplication from '../views/index/application/ProductApplication.vue'

Vue.use(VueRouter)

const router = new VueRouter({
   mode: 'hash',
   routes: [
      {
         name: 'Home',
         path: '/',
         component: HomePageView,
         meta: {
            title: '给你呗官网'
         }
      },
      {
         name: 'login',
         path: '/login',
         component: LoginView,
         meta: {
            title: '登录'
         }
      },
      {
         name:'index',
         path:'/index',
         component: Index,
         meta:{
            title:'给你呗首页'
         },
         children:[
            {
               name:'home',
               path:'home',
               component:Home,
               meta:{
                  title:'主页'
               }
            },
            {
               name:'applicationproduct',
               path:'application/product',
               component: ProductApplication,
               meta:{
                  title:'产品申请'
               }
            }
         ]
      }
   ]
}
)

//全局前置路由守卫————初始化的时候被调用、每次路由切换之前被调用
router.beforeEach((to, from, next) => {
   next()
})

//全局后置路由守卫————初始化的时候被调用、每次路由切换之后被调用
router.afterEach((to, from) => {
   document.title = to.meta.title || '给你呗-网络小额贷款'
})

export default router