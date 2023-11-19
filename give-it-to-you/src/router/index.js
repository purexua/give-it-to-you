// 该文件专门用于创建整个应用的路由器
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePageView.vue'
import Test from '@/components/Test.vue'
import LoanApplication from '@/views/LoanApplication.vue'
import LoanApplicationProductForm from '@/views/LoanApplicationProductForm.vue'
import LoanApplicationGenericForm from '@/views/LoanApplicationGenericForm.vue'

Vue.use(VueRouter)

const router = new VueRouter({
   mode: 'hash',
   routes: [
      {
         name: 'Home',
         path: '/',
         component: HomePage,
         meta: {
            title: '给你呗官网首页'
         }
      },
      {
         name: 'test',
         path: '/test',
         component: Test,
         meta: {
            title: '测试页面'
         }
      },
      {
         name: 'application',
         path: '/application',
         component: LoanApplication,
         meta: {
            title: '贷款申请'
         },
         children: [
            {
               name: 'applicationProductForm',
               path: 'applicationProductForm',
               component: LoanApplicationProductForm
            },
            {
               name: 'applicationGenericForm',
               path: 'applicationGenericForm',
               component: LoanApplicationGenericForm
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