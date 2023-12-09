<template>
  <div id="app">
    <router-view></router-view>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import NavigationBar from './components/NavigationBar.vue';

export default {
  name: 'App',
  components: {
    NavigationBar,
  },
  mounted() {
  },
}
</script>

<style></style>
