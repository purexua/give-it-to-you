<template>
  <div class="container">
    <p>欢迎使用，给你呗 - 网络小额贷款。</p>
    <el-dropdown class="icon" @command="handleCommand">
      <el-button type="text">
        <el-avatar icon="el-icon-user-solid"></el-avatar>
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
  top: 22px;
}
</style>