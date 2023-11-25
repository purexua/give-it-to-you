<template>
    <div>
        <div class="header">
            <img src="../assets/logo-plus.png" style="height: 75px; position: relative; left: 100px; margin-right: 230px;">
            <MenuList />
            <el-button icon="el-icon-service" type="text" class="button-onlie">在线客服</el-button>
            <el-button icon="el-icon-download" type="text" class="button-download">下载</el-button>
            <el-button icon="el-icon-user-solid" type="text" class="button-admin" @click="show">登录</el-button>
        </div>
    </div>
</template>
<script>
import MenuList from './MenuList.vue'
export default {
    name: 'NavigationBar',
    data() {
        return {

        }
    },
    components: {
        MenuList
    },
    methods: {
        show() {
            this.$router.push('/login')
        }
    },
    mounted() {

    }

}
</script>
<style scoped>
.header {
    display: flex;
    width: 90%;
    position: absolute;
    top: 75px;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: rgba(255, 255, 255, 0.7);
    padding: 20px;
    text-align: center;
    z-index: 999;
}

.button-admin {
    position: absolute;
    top: 25px;
    left: 89%;
    color: white;
    font-size: 20px;
}

.button-download {
    position: absolute;
    top: 25px;
    left: 82%;
    color: white;
    font-size: 20px;
}

.button-onlie {
    position: absolute;
    top: 25px;
    left: 73%;
    color: white;
    font-size: 20px;
}
</style>