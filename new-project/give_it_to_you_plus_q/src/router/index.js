// 该文件专门用于创建整个应用的路由器
import Vue from 'vue'
import VueRouter from 'vue-router'

import HomePageView from '../views/index/HomePageView.vue'
import LoginView from '../views/user-home/LoginView.vue'
import Index from '../views/user-home/Index.vue'
import Home from '../views/user-home/Home.vue'
import ChangePass from '../views/user-home/ChangePass.vue'
import ChangeInfo from '../views/user-home/ChangeInfo.vue'
import Recharge from '../views/user-home/Recharge.vue'
import CreditScore from '../views/user-home/CreditScore.vue'
import ProductApplication from '../views/user-home/ProductApplication.vue'
import AdminIndex from '../views/admin-home/Index.vue'
import AdminHome from '../views/admin-home/AdminHome.vue'
import LoanApproval from '../views/admin-home/LoanApproval.vue'

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
         name: 'userIndex',
         path: '/index',
         component: Index,
         meta: {
            title: '给你呗'
         },
         children: [
            {
               name: 'home',
               path: 'home',
               component: Home,
               meta: {
                  title: '主页'
               }
            },
            {
               name: 'changePassword',
               path: 'change/password',
               component: ChangePass,
               meta: {
                  title: '修改密码'
               }
            },
            {
               name: 'changeInfo',
               path: 'change/info',
               component: ChangeInfo,
               meta: {
                  title: '修改信息'
               }
            },
            {
               name: 'recharge',
               path: 'recharge',
               component: Recharge,
               meta: {
                  title: '充值'
               }
            },
            {
               name: 'creditInfo',
               path: 'credit/info',
               component: CreditScore,
               meta: {
                  title: '我的信用信息'
               }
            },
            {
               name: 'productApplication',
               path: 'application/product',
               component: ProductApplication,
               meta: {
                  title: '产品申请'
               }
            }
         ]
      },
      {
         name: 'adminIndex',
         path: '/admin/index',
         component: AdminIndex,
         meta: {
            title: '后台管理'
         },
         children: [
            {
               name: 'adminHome',
               path: 'home',
               component: AdminHome,
               meta: {
                  title: '管理员主页'
               }
            },
            {
               name: 'loanApproval',
               path: 'loan/approval',
               component: LoanApproval,
               meta: {
                  title: '贷款审批'
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