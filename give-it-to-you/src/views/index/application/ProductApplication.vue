<template>
  <div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="用户 ID" prop="userId">
        <el-input type="text" v-model="ruleForm.userId" autocomplete="off" disabled></el-input>
      </el-form-item>

      <el-form-item label="产品类型" prop="productCategory">
        <el-select v-model="ruleForm.productCategory" placeholder="请选择活动区域" @change="updateFields">
          <el-option v-for="item in productInterestRate" :key="item.prateId" :label="item.productCategory"
            :value="item.productCategory">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="分期" prop="term">
        <el-input type="text" v-model="ruleForm.term" autocomplete="off" disabled ></el-input>
      </el-form-item>

      <el-form-item label="贷款金额" prop="requestAmount">
        <el-input v-model.number="ruleForm.requestAmount" autocomplete="off" disabled></el-input>
      </el-form-item>

      <el-form-item label="贷款利率" prop="interestRate">
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
  name: 'ProductApplication',
  data() {
    var checkProductCategory = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请选择产品类型'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        userId: '',
        productCategory: '',
        term: '',
        requestAmount: '',
        interestRate: '',
      },
      rules: {
        productCategory: [
          { validator: checkProductCategory, trigger: 'change' }
        ],
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
    },
    updateFields() {
      const selectedCategory = this.ruleForm.productCategory;
      const selectedProduct = this.productInterestRate.find(item => item.productCategory === selectedCategory);
      if (selectedProduct) {
        this.ruleForm.term = selectedProduct.term;
        this.ruleForm.requestAmount = selectedProduct.loanAmount;
        this.ruleForm.interestRate = selectedProduct.interestRate;
      } else {
        this.ruleForm.term = '';
        this.ruleForm.requestAmount = '';
        this.ruleForm.interestRate = '';
      }
    }
  },
  computed: {
    productInterestRate() {
      return this.$store.state.rateInfo.productInterestRate
    },
    user() {
      return this.$store.state.userInfo.user
    }
  },
  mounted() {
    this.$store.dispatch('rateInfo/getAllProductInterestRate');
    this.ruleForm.userId = this.user.userId;
  },
}
</script>

<style></style>