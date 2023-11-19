<template>
  <div id="app">
    <router-view/>
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
  data(){
      return {
      }
  },
  methods: {
  },
  mounted(){
    
  },
  beforeDestroy() {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
  }
}
</script>

<style>

</style>
