<template>
    <div class="container">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户 Id" prop="userId">
                <el-input v-model="ruleForm.userId" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="借款金额" prop="amount">
                <el-input type="number" v-model.number="ruleForm.amount" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="借款期限(天)" prop="term">
                <el-input type="number" v-model.number="ruleForm.term" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="产品类型" prop="region">
                <el-select v-model="ruleForm.region" placeholder="请选择产品类型">
                    <el-option label="类型一" value="a"></el-option>
                    <el-option label="类型二" value="b"></el-option>
                </el-select>
            </el-form-item>

            <!-- <el-form-item label="利率" prop="interestRate">
                <el-input v-model="ruleForm.interestRate" autocomplete="off" disabled></el-input>
            </el-form-item> -->

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'LoanApplication',
    data() {
        var checkAmount = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入借款金额'));
            }
            if (value < 1000) {
                callback(new Error('借款金额不能小于1000'));
            } else {
                callback();
            }
        };

        var checkTerm = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入借款期限'));
            }
            if (value < 7) {
                callback(new Error('借款期限不能小于7天'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                applicationId: '',
                userId: '',
                amount: '',
                term: '',
                interestRate: '',
                status: '',
                applicationDate: '',
                approvalId: '',
                type: '',
            },
            rules: {
                amount: [
                    { validator: checkAmount, trigger: 'blur' }
                ],
                term: [
                    { validator: checkTerm, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                    console.log(this.ruleForm);
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
    computed: {
        user() {
            return this.$store.state.userInfo.user;
        }
    },
    mounted() {
        this.ruleForm.userId = this.user.userId;
        this.ruleForm.status = '待审核';
    }
}
</script>

<style scoped>
.container {
    margin-top: 20%;
}
</style>