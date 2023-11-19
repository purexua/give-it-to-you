<template>
    <div>
        <h1>Loan Application Product Form</h1>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户ID" prop="userId">
                <el-input v-model="ruleForm.userId" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="productCategory">
                <el-select v-model="ruleForm.productCategory" placeholder="请选择产品类型">
                    <el-option label="产品一" value="shanghai"></el-option>
                    <el-option label="产品二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="分期" prop="term">
                <el-select v-model.number="ruleForm.term" placeholder="请选择分期">
                    <el-option label="6 期" value="6"></el-option>
                    <el-option label="12 期" value="12"></el-option>
                    <el-option label="24 期" value="24"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="贷款金额" prop="requestedAmount">
                <el-input type="number" v-model.number="ruleForm.requestedAmount" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="利率(%)" prop="interestRate">
                <el-input v-model.number="ruleForm.interestRate" autocomplete="off" disabled></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name: 'LoanApplicationProductForm',
    data() {
        var validateproductCategory = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请选择产品类型'));
            } else {
                callback();
            }
        };
        var validateTerm = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请选择分期'));
            } else {
                callback();
            }
        };
        var validateRequestedAmount = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入贷款金额'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                userId: '',
                productCategory: '',
                term: '',
                requestedAmount: '',
                interestRate: ''
            },
            rules: {
                productType: [
                    { validator: validateproductCategory, trigger: 'change' }
                ],
                term: [
                    { validator: validateTerm, trigger: 'change' }
                ],
                requestedAmount: [
                    { validator: validateRequestedAmount, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
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