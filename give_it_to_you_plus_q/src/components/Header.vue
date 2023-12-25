<template>
  <div class="container">
    <el-button type="text" class="icon">
      <el-avatar icon="el-icon-user-solid"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
    </el-button>
  </div>
</template>

<script>
export default {
  name: 'Header',
}
</script>

<style scoped lang="less">
p {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  margin: 10px 0;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
}

.icon {
  position: absolute;
  right: 50px;
  top: 0px;
}
</style>