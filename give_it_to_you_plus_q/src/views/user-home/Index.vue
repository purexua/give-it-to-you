<template>
    <el-container>
        <el-aside width="250px">
            <el-menu default-active="1" class="el-menu-vertical-demo">

                <el-menu-item index="0">
                    <div style="display: flex; align-items: center;">
                        <img :src="require('@/assets/logo-min-min.png')" class="rounded-image" style="width: 50px; height: 50px;" />
                        <div style="margin-left: 10px; font-weight: bold;">给你呗金融平台</div>
                    </div>
                </el-menu-item>

                <el-menu-item index="1">
                    <i class="el-icon-s-home"></i>
                    <span slot="title"><router-link to="/index/home">首页</router-link></span>
                </el-menu-item>

                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>贷款申请</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分类</template>
                        <el-menu-item index="2-1">
                            <router-link :to="{
                                name: 'productApplication',
                            }">贷款申请</router-link>
                        </el-menu-item>
                        <el-menu-item index="2-1">
                            <router-link :to="{
                                name: 'applicationRecord',
                            }">申请记录</router-link>
                        </el-menu-item>
                        <el-menu-item index="2-2">
                            <router-link :to="{
                               name:'personalProduct' 
                            }">个性化产品</router-link>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-menu-item index="3">
                    <i class="el-icon-document"></i>
                    <span slot="title">
                        <router-link :to="{
                                name: 'RepaymentRecords',
                            }">还款记录</router-link>
                    </span>
                </el-menu-item>

                <el-menu-item index="5">
                    <i class="el-icon-document"></i>
                    <span slot="title">
                        <router-link :to="{
                                name: 'RepaymentPlan',
                            }">还款计划</router-link>
                    </span>
                </el-menu-item>

                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>我的</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">设置</template>
                        <el-menu-item index="4-1">
                            <router-link :to="{
                                name: 'changePassword',
                            }">修改密码</router-link>
                        </el-menu-item>
                        <el-menu-item index="4-2">
                            <router-link :to="{
                                name: 'recharge',
                            }">充值</router-link>
                        </el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="4-2">
                        <template slot="title">信息</template>
                        <el-menu-item index="4-2-1">
                            <router-link :to="{
                                name: 'changeInfo',
                            }">修改信息</router-link>
                        </el-menu-item>
                        <el-menu-item index="4-2-2">
                            <router-link :to="{
                                name: 'creditInfo',
                            }">我的信用</router-link>
                        </el-menu-item>
                    </el-submenu>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header height="50px">
                <Header></Header>
            </el-header>
            <el-main><router-view></router-view></el-main>
        </el-container>
    </el-container>
</template>

<script>
import Header from '@/components/Header.vue';
import router from '@/router';
export default {
    name: 'Index',
    data() {
        return {
            activeIndex: '1',
        };
    },
    components: {
    Header,
    router
},
}
</script>

<style lang="less" scoped>
.el-header {
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 16px;
}

.el-aside {
    color: #333;
}

a {
    text-decoration: none;
}

</style>