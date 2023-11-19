<template>
    <div>
        <h1>Loan Application</h1>
        <el-button type="primary" @click="show1">选择我们推出的产品</el-button>
        <el-button type="success" @click="show2">也可以选择个性化</el-button>
        <div class="form-view">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: 'LoanApplication',
    methods: {
        show1() {
            this.$router.push({name : 'applicationProductForm'});
        },
        show2() {
            this.$router.push({name : 'applicationGenericForm'});
        }
    }
}

</script>

<style scoped>
.form-view {
    margin-top: 50px;
}
</style>