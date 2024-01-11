<template>
    <div class="login_container">
        <el-row>
            <el-col :span="12" :xs="0"></el-col>
            <el-col :span="12" :xs="24">
                <el-form class="login_form" :model="loginForm" :label-position="labelPosition"
                    :label-width="formLabelWidth">
                    <h1>Hello</h1>
                    <h2>给你呗网络小额贷款</h2>
                    <el-form-item>
                        <el-input prefix-icon="el-icon-user" v-model="loginForm.username"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="password" prefix-icon="el-icon-lock" v-model="loginForm.password"
                            show-password></el-input>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-input v-model="loginForm.uuid" auto-complete="off" placeholder="验证码" style="width: 63%"
                            @keyup.enter.native="handleLogin">
                            <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
                        </el-input>
                        <div class="login-code">
                            <img :src="codeUrl" class="login-code-img" @click="refreshCode()" />
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-button :loading="loading" class="login_btn" type="success" size="default"
                            @click="login">登录</el-button>
                        <div class="btn_space"></div>
                        <el-popover placement="bottom" title="Welcome to use" width="200" trigger="hover"
                            content="梦想无阻，贷款无忧。轻松借，快速达成您的目标！">
                            <el-button slot="reference" class="login_btn" type="primary" size="default"
                                @click="registerButton">注册</el-button>
                        </el-popover>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-dialog title="欢迎注册" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

                <el-form-item label="用户名" prop="userName" :label-width="formLabelWidth">
                    <el-input v-model="ruleForm.userName" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off">
                    </el-input>
                </el-form-item>

                <el-form-item label="真实姓名" prop="realName" :label-width="formLabelWidth">
                    <el-input v-model.trim="ruleForm.realName" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="身份证" prop="idCard" :label-width="formLabelWidth">
                    <el-input v-model.trim="ruleForm.idCard" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth">
                    <el-input v-model.trim="ruleForm.email" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="地址" prop="address" :label-width="formLabelWidth">
                    <el-input v-model.trim="ruleForm.address" autocomplete="off"></el-input>

                </el-form-item>

                <el-form-item label="电话号码" prop="phoneNumber" :label-width="formLabelWidth">
                    <el-input v-model.trim="ruleForm.phoneNumber" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="register('ruleForm')">注册</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'LoginView',
    data() {
        var validateRealName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入真实姓名'));
            } else {
                callback();
            }
        };
        var validateEmail = (rule, value, callback) => {
            const emailRegex = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else if (!emailRegex.test(value)) {
                callback(new Error('请输入正确的邮箱格式'));
            } else {
                callback();
            }
        };
        var validateAddress = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入地址'));
            } else {
                callback();
            }
        };
        var validatePhoneNumber = (rule, value, callback) => {
            const phoneRegex = /^1[34578]\d{9}$/;
            if (value === '') {
                callback(new Error('请输入电话号码'));
            } else if (!phoneRegex.test(value)) {
                callback(new Error('请输入正确的电话号码格式'));
            } else {
                callback();
            }
        };
        var validateUserName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            } else {
                callback();
            }
        };
        var validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        var validateIdCard = (rule, value, callback) => {
            const idCardRegex = /^\d{18}$/;
            if (value === '') {
                callback(new Error('请输入身份证'));
            } else if (!idCardRegex.test(value)) {
                callback(new Error('身份证必须是18位数字'));
            } else {
                callback();
            }
        };
        return {
            code: ' ',
            codeUrl: ' ',
            loginForm: {
                username: '',
                password: '',
                uuid: ''
            },
            loading: false,
            dialogFormVisible: false,
            formLabelWidth: '120px',
            labelPosition: 'top',
            ruleForm: {
                userName: '',
                password: '',
                realName: '',
                idCard: '',
                email: '',
                address: '',
                phoneNumber: '',
            },
            rules: {
                userName: [
                    { validator: validateUserName, trigger: 'blur' }
                ],
                password: [
                    { validator: validatePassword, trigger: 'blur' }
                ],
                realName: [
                    { validator: validateRealName, trigger: 'blur' }
                ],
                idCard: [
                    { validator: validateIdCard, trigger: 'blur' }
                ],
                email: [
                    { validator: validateEmail, trigger: 'blur' }
                ],
                address: [
                    { validator: validateAddress, trigger: 'blur' }
                ],
                phoneNumber: [
                    { validator: validatePhoneNumber, trigger: 'blur' }
                ]
            }
        }
    },
    mounted() {
        this.getcode()
    },
    methods: {
        getcode() {
            axios({
                method: 'get',
                url: 'http://localhost:3919/serve8080/captcha',
            }).then(res => {
                console.log(res)
                if (res.data.code === 200) {
                    this.codeUrl = res.data.data.img;
                    this.code = res.data.data.captcha;
                }
            })
        },
        refreshCode() {
            this.getcode()
        },
        login() {
            axios({
                method: 'get',
                url: 'http://localhost:3919/serve8080/login',
                params: {
                    userName: this.loginForm.username,
                }
            }).then(res => {
                this.loading = true
                if (res.data === '') {
                    this.$message({
                        message: '用户名不存在-请先注册',
                        type: 'error'
                    })
                } else {
                    if(this.loginForm.uuid !== this.code)
                    {
                        this.$message({
                            message: '验证码错误',
                            type: 'error'
                        })
                        this.getcode()
                        return;
                    }
                    if (res.data.password === this.loginForm.password) {
                        this.$store.commit('userInfo/SAVEUSERINFO', res.data)
                        if (res.data.role === 'Admin') {
                            this.$router.replace('/admin/index/home')
                        } else {
                            this.$router.replace('/index/home')
                        }
                    } else {
                        this.$message({
                            message: '密码错误',
                            type: 'error'
                        })
                    }
                }
            }).catch(err => {
                console.log('哎呦~出错啦' + err)
            }).finally(() => {
                this.loading = false
            })
        },
        registerButton() {
            this.dialogFormVisible = true
        },
        register(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios({
                        method: 'post',
                        url: `http://localhost:3919/serve8080/register`,
                        data: {
                            userName: this.ruleForm.userName,
                            password: this.ruleForm.password,
                            realName: this.ruleForm.realName,
                            idCard: this.ruleForm.idCard,
                            email: this.ruleForm.email,
                            address: this.ruleForm.address,
                            phoneNumber: this.ruleForm.phoneNumber,
                        }
                    }).then(res => {
                        if (res.data === 'success') {
                            this.$message({
                                message: '注册成功',
                                type: 'success'
                            })
                        } else {
                            this.$message({
                                message: res.data,
                                type: 'error'
                            })
                        }
                    }).catch(err => {
                        console.log(err)
                    }).finally(() => {
                        this.dialogFormVisible = false
                        // 重置表单
                        this.$refs[formName].resetFields();
                    })
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>

<style lang="less" scoped>
.login-code-img {
    height: 38px;
}

.login-code {
    width: 33%;
    height: 38px;
    float: right;

    img {
        cursor: pointer;
        vertical-align: middle;
    }
}

.login_container {
    width: 100%;
    height: 100vh;
    background-image: url("../../assets/login-background.jpg");

    .login_form {
        position: relative;
        width: 80%;
        top: 20vh;
        left: 100%;
        background-color: #fff;
        padding: 40px;
        color: #333;

        h1 {
            color: #333;
            font-size: 40px;
        }

        h2 {
            font-size: 20px;
            color: #333;
            margin: 20px 0px;
        }

        .login_btn {
            display: inline-block;
            width: 45%;
            background-color: #409EFF;
            color: #fff;
            border-color: #409EFF;
        }

        .btn_space {
            display: inline-block;
            width: 10%;
        }
    }
}
</style>
