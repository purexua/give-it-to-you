<template>
    <div class="container">
    <el-dropdown class="icon" @command="handleCommand">
      <el-button type="text">
        <el-avatar icon="el-icon-user-solid" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      </el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="a">修改信息</el-dropdown-item>
        <el-dropdown-item command="b">修改密码</el-dropdown-item>
        <el-dropdown-item command="c">我的信用</el-dropdown-item>
        <el-dropdown-item command="d">登出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
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