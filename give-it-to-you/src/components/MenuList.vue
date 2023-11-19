<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1"><router-link to="/">首页</router-link></el-menu-item>
            <el-submenu index="2">
                <template slot="title">业务与服务</template>
                <el-menu-item index="2-1"><router-link :to="{ name: 'application' }">贷款申请</router-link></el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">我的工作台</template>
                <el-menu-item index="3-1">选项1</el-menu-item>
                <el-menu-item index="3-2">选项2</el-menu-item>
                <el-menu-item index="3-3">选项3</el-menu-item>
            </el-submenu>
            <el-menu-item index="4"><router-link to="/test">企业社会责任</router-link></el-menu-item>
            <el-menu-item index="5"><a href="https://www.ele.me" target="_blank">关于我们</a></el-menu-item>
        </el-menu>
        <div class="line"></div>
    </div>
</template>
<script>
export default {
    name: 'MenuList',
    data() {
        return {
            activeIndex: '1',
            activeIndex2: '1'
        }
    }
}
</script>
<style scoped>
ul li {
    position: relative;
    left: 0px;
}

a {
    text-decoration: none;
    color: inherit;
}
</style>