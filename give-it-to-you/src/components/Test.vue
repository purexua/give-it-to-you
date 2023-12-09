<template>
    <div>
        <el-page-header @back="goBack" ></el-page-header>
    </div>
</template>
<script>
export default{
    name:'test',
    data(){
        return{

        }
    },
    methods:{
        goBack() {
        this.$router.go(-1);
        }
    }
}
</script>