<template>
    <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">首页</el-menu-item>
    <el-menu-item index="2">业务与服务</el-menu-item>
    <el-submenu index="3">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
    </el-submenu>
    <el-menu-item index="4">企业社会责任</el-menu-item>
    <el-menu-item index="5"><a href="https://www.ele.me" target="_blank">关于我们</a></el-menu-item>
    </el-menu>
    <div class="line"></div>
</div>
</template>
<script>
    export default{
        name:'AdminView',
        data(){
            return {
                activeIndex: '1',
               activeIndex2: '1'
            }
        }
    }
</script>
<style scoped>
ul li{
  position: relative;
  left: 0px;
}
</style>