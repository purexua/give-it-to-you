<template>
    <el-container>
        <el-aside width="250px">
            <el-menu default-active="1" class="el-menu-vertical-demo">
                <el-menu-item index="1">
                    <i class="el-icon-s-home"></i>
                    <span slot="title"><router-link :to="{
                                name: 'home',
                            }">首页</router-link></span>
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
                                name: 'applicationproduct',
                            }">产品申请</router-link>
                        </el-menu-item>
                        <el-menu-item index="2-2">
                            <router-link to="/personalProduct">个性化产品</router-link>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <el-menu-item index="3">
                    <i class="el-icon-document"></i>
                    <span slot="title">
                        还款记录
                    </span>
                </el-menu-item>

                <el-menu-item index="5">
                    <i class="el-icon-document"></i>
                    <span slot="title">
                      <router-link to = "repaymentPlan" >还款计划</router-link>
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
                            修改密码
                        </el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="4-2">
                        <template slot="title">信息</template>
                        <el-menu-item index="4-2-1">
                            修改信息
                        </el-menu-item>
                        <el-menu-item index="4-2-2">
                            我的信用
                        </el-menu-item>
                    </el-submenu>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header height="80px">
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
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        }
    },
    components:{
    Header,
    router
}
}
</script>

<style lang="less">
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
}

.el-aside {
    color: #333;
}

a {
    text-decoration: none;
}
</style>