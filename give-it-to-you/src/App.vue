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
    this.$router.push('/');
  }
}
</script>

<style>

</style>
