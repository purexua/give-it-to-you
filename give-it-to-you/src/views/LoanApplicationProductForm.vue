<template>
    <div>
        <h1>Loan Application Product Form</h1>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户ID" prop="userId">
                <el-input v-model="ruleForm.userId" autocomplete="off" disabled></el-input>
            </el-form-item>

            <el-form-item label="产品类型" prop="productCategory">
                <el-select v-model="ruleForm.productCategory" placeholder="请选择产品类型" @change="handleProductCategoryChange">
                    <el-option v-for="item in productInterestRate" :key="item.prateId" :value="item.productCategory"></el-option>
                </el-select>
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
        return {
            ruleForm: {
                userId: '',
                productCategory: '',
                term: '',
                requestedAmount: '',
                interestRate: '',
                productInterestRate: []
            },
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
        },
        handleProductCategoryChange(){
            
        }
    },
    computed: {
        userId() {
            return this.$store.state.userInfo.userId;
        },
        productInterestRate() {
            return this.$store.state.rateInfo.productInterestRate;
        }
    },
    mounted() {
        this.$store.commit('rateInfo/GETALLPRODUCTINTERESTRATE');
        this.ruleForm.productInterestRate = this.productInterestRate;
        console.log(this.ruleForm.productInterestRate);
    },
    watch: {
        productInterestRate: {
            immediate: true,
            handler(newValue) {
                this.ruleForm.productInterestRate = newValue;
            }
        }
    },
};
</script>

<style scoped>
.demo-ruleForm {
    position: absolute;
    text-align: left;
    width: 80%;
}
</style>
