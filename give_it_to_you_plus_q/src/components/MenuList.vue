<template>
    <div>
        <el-menu  class="el-menu-demo" mode="horizontal" background-color="Transparent"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1"><router-link to="/">首页</router-link></el-menu-item>
            <el-submenu index="2">
                <template slot="title">业务与服务</template>
                <el-menu-item index="2-1"><router-link :to="{
                                name: 'login',
                            }">给你呗信贷</router-link></el-menu-item>
                <el-menu-item index="2-2"><router-link :to="{
                                name: 'login',
                            }">给你呗理财</router-link></el-menu-item>
                <el-menu-item index="2-3"><router-link :to="{
                                name: 'login',
                            }">给你呗保险</router-link></el-menu-item>
            </el-submenu>
            <el-menu-item index="4"><router-link :to="{
                                name: 'login',
                            }">企业社会责任</router-link></el-menu-item>
            <el-menu-item index="5"><router-link :to="{
                                name: 'login',
                            }">关于我们</router-link></el-menu-item>
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