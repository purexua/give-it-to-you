<template>
  <div>
    <div class="header">
      <img src="../assets/logo-plus.png" style="height: 75px; position: relative; left: 100px; margin-right: 230px;">
      <MenuList />
      <el-button icon="el-icon-service" type="text" class="button-onlie">在线客服</el-button>
      <el-button icon="el-icon-download" type="text" class="button-download">下载</el-button>
      <el-button icon="el-icon-user-solid" type="text" class="button-admin" @click="show">登录</el-button>
    </div>
    <div>
      <el-dialog title="用户登录" :visible.sync="outerVisible" width="25%">
        <div>
          <div>
            <span style="position: relative;margin-left: 46%; color: red;">{{ whetherAdmin }}</span>
            <el-button type="text" v-show="isAdmin" @click="outUser()"
              style="position: relative; margin-left: 20px;">退出</el-button>
          </div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="ruleForm.password" type="password"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-dialog width="44%" title="用户注册" :visible.sync="innerVisible" append-to-body>
            <div>
              <el-form :model="ruleForm_sign" :rules="rules" ref="ruleForm_sign" label-width="100px" class="demo-ruleForm"
                :inline="true">
                <el-form-item label="用户名" prop="name_sign">
                  <el-input v-model="ruleForm_sign.name_sign"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password_sign">
                  <el-input v-model="ruleForm_sign.password_sign" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="password_sign_check">
                  <el-input type="password" v-model="ruleForm_sign.password_sign_check"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名" prop="real_name_sign">
                  <el-input v-model="ruleForm_sign.real_name_sign"></el-input>
                </el-form-item>
                <el-form-item label="身份证" prop="id_card_sign">
                  <el-input v-model="ruleForm_sign.id_card_sign"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email_sign">
                  <el-input v-model="ruleForm_sign.email_sign"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address_sign">
                  <el-input v-model="ruleForm_sign.address_sign"></el-input>
                </el-form-item>
                <el-form-item label="电话号码" prop="phon_number_sign">
                  <el-input v-model="ruleForm_sign.phon_number_sign"></el-input>
                </el-form-item>
                <el-button type="success" @click="userSign()" style="position: relative;left: 80%;width: 120px;">登
                  录</el-button>
              </el-form>
            </div>
          </el-dialog>
        </div>
        <span slot="footer" class="dialog-footer" style="display: flex;flex-direction: column;margin-top: -40px;">
          <el-button type="text" @click="innerVisible = true">没有账号去注册</el-button>
          <el-button type="danger" @click="userAdmin()" style="position: relative;left: 35%;width: 100px;">登 录</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import MenuList from './MenuList.vue'
export default {
  name: 'NavigationBar',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm_sign.password_sign_check !== '') {
          this.$refs.ruleForm_sign.validateField('password_sign_check');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm_sign.password_sign) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      isAdmin: false,
      whetherAdmin: '未登录',
      outerVisible: false,
      innerVisible: false,
      name: '',
      password: '',
      ruleForm_sign: {
        name_sign: '',
        password_sign: '',
        password_sign_check: '',
        real_name_sign: '',
        id_card_sign: '',
        email_sign: '',
        address_sign: '',
        phon_number_sign: '',
      },
      ruleForm: {
        name: 'purexu',
        password: '123456'
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        name_sign: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password_sign: [
          { validator: validatePass, trigger: 'blur' }
        ],
        password_sign_check: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        real_name_sign: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        id_card_sign: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { min: 3, max: 10, message: '长度为十八位', trigger: 'blur' }
        ],
        email_sign: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        address_sign: [
          { required: true, message: '请输入真实地址', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
        ],
        phon_number_sign: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  components: {
    MenuList
  },
  methods: {
    outUser() {
      this.$confirm('是否退出当前用户?', '温馨提醒', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.isAdmin = false;
        this.whetherAdmin = '未登录';
        this.$message({
          type: 'success',
          message: '已退出!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出操作'
        });
      });
    },
    show() {
      this.outerVisible = true;
    },
    userAdmin() {
      axios({
        method: 'get',
        url: 'http://localhost:3919/serve8080/login',
        params: {
          userName: this.ruleForm.name,
        }
      }).then(response => {
        if (response.data.password === this.ruleForm.password) {
          this.$message({ message: '登录成功', type: 'success' });
          this.$store.commit('userInfo/SAVEUSERINFO', response.data);
          this.outerVisible = false;
          this.whetherAdmin = '已登录';
          this.isAdmin = true;
          this.innerVisible = false;
        } else {
          this.$message.error('密码错误~');
          this.ruleForm.name = '';
          this.ruleForm.password = '';
        }
      })
        .catch(() => {
          this.$message.error('哎呦~出错啦');
        })
        .finally(() => {
        });
    },
    userSign() {
      const user = {
        userName: this.ruleForm_sign.name_sign,
        password: this.ruleForm_sign.password_sign,
        realName: this.ruleForm_sign.real_name_sign,
        idCard: this.ruleForm_sign.id_card_sign,
        email: this.ruleForm_sign.email_sign,
        address: this.ruleForm_sign.address_sign,
        phoneNumber: this.ruleForm_sign.phon_number_sign,
      }
      axios.post('http://localhost:3919/serve8080/register', user)
        .then(response => {
          console.log(response.data);
          if (response.data === 'success') {
            this.$message({ message: '注册成功', type: 'success' });
            this.outerVisible = false;
            this.innerVisible = false;
          } else {
            this.$message.error('用户已存在~请重新注册或登录');
          }
        })
        .catch(() => {
          this.$message.error('哎呀~出错啦');
        })
        .finally(() => {

        });
    }
  },
  mounted() {
    if (localStorage.getItem('user') !== null) {
      this.whetherAdmin = '已登录';
      this.isAdmin = true;
    }
  }

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

.button-admin {
  position: absolute;
  top: 25px;
  left: 89%;
  color: white;
  font-size: 20px;
}

.button-download {
  position: absolute;
  top: 25px;
  left: 82%;
  color: white;
  font-size: 20px;
}

.button-onlie {
  position: absolute;
  top: 25px;
  left: 73%;
  color: white;
  font-size: 20px;
}
</style>