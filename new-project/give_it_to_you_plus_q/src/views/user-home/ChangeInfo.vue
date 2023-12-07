<template>
    <div>
        <h1>修改信息</h1>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户 ID" prop="userId">
                <el-input v-model="ruleForm.userId" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="userName" prop="userName">
                <el-input v-model="ruleForm.userName" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="真实姓名" prop="realName">
                <el-input v-model.trim="ruleForm.realName" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="身份证" prop="idCard">
                <el-input v-model.trim="ruleForm.idCard" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
                <el-input v-model.trim="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="地址" prop="address">
                <el-input v-model.trim="ruleForm.address" autocomplete="off"></el-input>

            </el-form-item>

            <el-form-item label="电话号码" prop="phoneNumber">
                <el-input v-model.trim="ruleForm.phoneNumber" autocomplete="off"></el-input>
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
    name: 'ChangeInfo',
    data() {
        var validateRealName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入真实姓名'));
            } else {
                callback();
            }
        };

        var validateIdCard = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入身份证号码'));
            } else {
                if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value)) {
                    callback(new Error('身份证号码格式不正确'));
                } else {
                    callback();
                }
            }
        };

        var validateEmail = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else {
                if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)) {
                    callback(new Error('邮箱格式不正确'));
                } else {
                    callback();
                }
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
            if (value === '') {
                callback(new Error('请输入电话号码'));
            } else {
                if (!/^[0-9]{11}$/.test(value)) {
                    callback(new Error('电话号码格式不正确'));
                } else {
                    callback();
                }
            }
        };
        return {
            ruleForm: {
                userId: '',
                userName: '',
                realName: '',
                idCard: '',
                email: '',
                address: '',
                phoneNumber: '',
            },
            rules: {
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
        };
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
                    this.$store.dispatch('userInfo/changeInfo', {
                        userId: this.ruleForm.userId,
                        userName: this.ruleForm.userName,
                        realName: this.ruleForm.realName,
                        idCard: this.ruleForm.idCard,
                        email: this.ruleForm.email,
                        address: this.ruleForm.address,
                        phoneNumber: this.ruleForm.phoneNumber,
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    mounted() {
        //仅显示的信息
        this.ruleForm.userId = this.user.userId
        this.ruleForm.userName = this.user.userName
        //可修改的信息
        this.ruleForm.realName = this.user.realName
        this.ruleForm.idCard = this.user.idCard
        this.ruleForm.email = this.user.email
        this.ruleForm.address = this.user.address
        this.ruleForm.phoneNumber = this.user.phoneNumber
    }
}
</script>

<style></style>