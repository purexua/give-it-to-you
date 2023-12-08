<template>
    <div>
        <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in 4" :key="item">
                <img :src="require(`@/assets/${item}.jpg`)" class="image-style">
            </el-carousel-item>
        </el-carousel>

        <div class="introduction">
            <h1>Welcome to our online microloan platform</h1>
            <p>Start your journey to financial freedom</p>
            <p>On our platform, you can easily apply for microloans to meet your urgent funding needs</p>
            <p>We provide simple and fast loan services to help you overcome financial difficulties and achieve your dreams and plans</p>
        </div>

    </div>
</template>
  
<script>
export default {
    name: 'AdminHome',
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
  