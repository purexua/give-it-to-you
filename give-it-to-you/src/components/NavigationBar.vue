<template>
    <div>
     <div class="header">
      <img src="../assets/logo-plus.png" style="height: 75px; position: relative; left: 100px; margin-right: 230px;">
      <MenuList/>
      <el-button  icon="el-icon-service" type = "text" class="button-onlie">在线客服</el-button>
      <el-button  icon="el-icon-download" type = "text" class="button-download">下载</el-button>
      <el-button  icon="el-icon-user-solid" type = "text" class="button-admin" @click="show">登录</el-button>
    </div>
    <div>
      <el-dialog
          title="用户登录"
          :visible.sync="outerVisible"
          width="25%">
          <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="name">
                  <el-input v-model="ruleForm.id"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input v-model="ruleForm.password" type="password"></el-input>
                </el-form-item>
            </el-form>
          </div>
          <div>
          <el-dialog
              width="30%"
              title="用户注册"
              :visible.sync="innerVisible"
              append-to-body>
          </el-dialog>
        </div>
          <span slot="footer" class="dialog-footer" style="display: flex;flex-direction: column;margin-top: -40px;">
            <el-button type="text" @click="innerVisible = true">没有账号去注册</el-button>
            <el-button type="danger" @click="dialogVisible = false" style="position: relative;left: 35%;width: 100px;">登 录</el-button>
        </span>
        </el-dialog>
    </div>
</div>
</template>
<script>
import MenuList from './MenuList.vue'
export default {
    name:'NavigationBar',
    data(){
        return {
            outerVisible: false,
            innerVisible: false,
            name:'',
            password:'',
            ruleForm: {
                id: '',
                password:''
                },
                rules: {
                name: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                }
        }
    },
    components:{
        MenuList
    },
    methods:{
        show(){
        this.outerVisible = true;
      },
        userAdmin(){
          const userName = this.name;
          axios.get('http://localhost:3919/serve8080/login', userName)
            .then(response => {
               console.log(response.data);
              const token = response.data;
              localStorage.setItem('token', token);
              this.$message({ message: '登录成功', type: 'success' });
              localStorage.setItem('userName', userName);
            })
            .catch(()=> {
              this.$message.error('哎呦~出错啦');
            })
            .finally(() => {
              
            });
        }
    },

}
</script>
<style scoped>
.header {
  display: flex;
  width: 90%;
  position: absolute;
  top: 75px;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.7);
  padding: 20px;
  text-align: center;
  z-index: 999; 
}
.button-admin{
  position: absolute;
  top: 25px;
  left: 89%;
  color: white;
  font-size: 20px;
}
.button-download{
  position: absolute;
  top: 25px;
  left: 82%;
  color: white;
  font-size: 20px;
}
.button-onlie{
  position: absolute;
  top: 25px;
  left: 73%;
  color: white;
  font-size: 20px;
}
</style>