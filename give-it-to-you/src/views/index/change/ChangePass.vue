<template>
    <div>
        <h1>修改密码</h1>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="旧密码" prop="oldPass">
                <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPass">
                <el-input type="password" v-model="ruleForm.newPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
 
<script>
import axios from 'axios'
export default {
    name: 'ChangePass',
    data() {
        var validateOldPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入旧密码'));
            } else {
                callback();
            }
        };

        var validateNewPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入新密码'));
            } else {
                callback();
            }
        };

        var validateCheckPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.newPass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                oldPass: '',
                newPass: '',
                checkPass: ''
            },
            rules: {
                oldPass: [
                    { validator: validateOldPass, trigger: 'blur' }
                ],
                newPass: [
                    { validator: validateNewPass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validateCheckPass, trigger: 'blur' }
                ]
            }
        }
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    if (this.ruleForm.oldPass != this.user.password) {
                        this.$message({
                            message: '旧密码错误',
                            type: 'error'
                        });
                        return false;
                    } else {
                        axios({
                            method: 'PUT',
                            url: 'http://localhost:3919/serve8080/user/password',
                            params: {
                                userId: this.user.userId,
                                password: this.ruleForm.newPass
                            }
                        }).then(res => {
                            this.$message({
                                message: '修改成功',
                                type: 'success'
                            });
                            this.$store.commit('userInfo/CHANGEPASSWORD', this.ruleForm.newPass)
                        }).catch(err => {
                            console.log(err)
                        })
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>
  
<style></style>
  