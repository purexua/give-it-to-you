<template>
    <el-container>
        <el-aside width="250px" style="background-color: #545c64;">
            <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff"
                active-text-color="#ffd04b">
                <el-menu-item index="1">
                    <i class="el-icon-s-home"></i>
                    <span slot="title">
                        <router-link :to="{ name: 'adminHome' }">首页</router-link>
                    </span>
                </el-menu-item>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>贷款管理</span>
                    </template>
                    <el-menu-item index="2-1">
                        <router-link :to="{ name: 'loanApproval' }">贷款审批</router-link>
                    </el-menu-item>
                    <el-menu-item index="2-2">贷款产品管理</el-menu-item>
                </el-submenu>
                <el-menu-item index="3">
                    <i class="el-icon-setting"></i>
                    <span slot="title">导航三</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header height="80px" class="container-header">
                <p>给你呗 - 后台管理</p>
            </el-header>
            <el-main><router-view></router-view></el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
    name: 'AdminIndex',
    methods: {
    }
}
</script>

<style scoped>
.container-header {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    background-color: #545c64;
    color: #fff;
}

.container-header p {
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    margin: 10px 0;
}
</style>
