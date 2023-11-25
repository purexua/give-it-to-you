<template>
    <div>
        <div class="main">
            <el-page-header @back="goBack" title="返回首页"></el-page-header>
            <h1>Loan Application</h1>
            <el-button type="primary" @click="show1">选择我们推出的产品</el-button>
            <el-button type="success" @click="show2">也可以选择个性化</el-button>
            <div class="form-view">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'LoanApplication',
    methods: {
        show1() {
            if (this.$router.currentRoute.name !== 'applicationProductForm') {
                this.$router.replace({ name: 'applicationProductForm' });
            }
        },
        show2() {
            if (this.$router.currentRoute.name !== 'applicationGenericForm') {
                this.$router.replace({ name: 'applicationGenericForm' });
            }
        },
        goBack() {
            if (this.$router.currentRoute.path !== '/') {
                this.$router.push('/');
            }
        }
    }
}

</script>

<style scoped>
.form-view {
    margin-top: 50px;
}

.main {
    position: absolute;
    width: 80%;
    margin-left: 10%;
    text-align: center;
}
</style>