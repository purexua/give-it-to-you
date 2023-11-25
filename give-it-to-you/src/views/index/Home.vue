<template>
    <div>
        <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in 4" :key="item">
                <img :src="require(`@/assets/${item}.jpg`)" class="image-style">
            </el-carousel-item>
        </el-carousel>

        <div class="introduction">
            <h1>欢迎来到我们的网络小额贷款平台</h1>
            <p>开启财务自由之旅</p>
            <p>在我们的平台上，您可以轻松申请小额贷款，满足您的紧急资金需求</p>
            <p>我们提供简便、快捷的贷款服务，让您摆脱财务困境，实现梦想和计划</p>
        </div>

    </div>
</template>
  
<script>
export default {
    name: 'Home',
};
</script>
  
<style scoped>
.image-style {
    width: 100%;
    height: 300px;
    object-fit: cover;
}

.introduction {
    text-align: center;
    margin-top: 30px;
}

.introduction h1 {
    font-size: 2.5em;
    margin-bottom: 20px;
}

.introduction p {
    font-size: 1.2em;
    margin-bottom: 10px;
    color: #666;
}
</style>
  