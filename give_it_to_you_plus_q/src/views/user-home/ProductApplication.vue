
<template>
  <div class="product-application">
    <h1 class="title">产品申请</h1>
    <p class="credit-limit">你的额度为{{ creditScoreInfo.limitAmount }}</p>
    <div class="form-container">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="用户 ID" prop="userId">
          <el-input type="text" v-model="ruleForm.userId" autocomplete="off" disabled></el-input>
        </el-form-item>

        <el-form-item label="产品类型" prop="productType">
          <el-select v-model="ruleForm.productType" placeholder="请选择活动区域" @change="updateFields">
            <el-option v-for="item in productInterestRate" :key="item.prateId" :label="item.productType"
              :value="item.productType">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="分期" prop="term">
          <el-input type="text" v-model.number="ruleForm.term" autocomplete="off" disabled></el-input>
        </el-form-item>

        <el-form-item label="贷款金额" prop="requestAmount">
          <el-input v-model.number="ruleForm.requestedAmount" autocomplete="off" disabled></el-input>
        </el-form-item>

        <el-form-item label="贷款利率" prop="interestRate">
          <el-input v-model.number="ruleForm.interestRate" autocomplete="off" disabled></el-input>
        </el-form-item>

        <el-form-item label="产品描述" prop="productDescription">
          <el-input v-model.number="ruleForm.productDescription" autocomplete="off" disabled></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'ProductApplication',
  data() {
    var checkProductType = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请选择产品类型'));
      } else if (this.creditScoreInfo.limitAmount < this.ruleForm.requestedAmount) {
        callback(new Error('申请失败，超过了你的可用额度'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        userId: '',
        productType: '',
        term: '',
        requestedAmount: '',
        interestRate: '',
        productDescription: '',
      },
      rules: {
        productType: [
          { validator: checkProductType, trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios({
            method: 'post',
            url: 'http://localhost:3919/serve8080/application/product',
            data: {
              userId: this.user.userId,
              loanType: this.ruleForm.productType,
              term: this.ruleForm.term,
              requestedAmount: this.ruleForm.requestedAmount,
              interestRate: this.ruleForm.interestRate,
            }
          }).then(res => {
            if (res.data === 'success') {
              console.log('application product - success submit!!');
              this.$message({
                message: '申请成功',
                type: 'success'
              });
              this.$store.dispatch('creditInfo/getCreditScoreInfo', this.user.userId);
            } else {
              console.log('application product - error submit!!');
              this.$message({
                message: '申请失败',
                type: 'error'
              });
            }
          }).catch(err => {
            console.log(err);
          });
        } else {
          console.log('application product - error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    updateFields() {
      const selectedCategory = this.ruleForm.productType;
      const selectedProduct = this.productInterestRate.find(item => item.productType === selectedCategory);
      if (selectedProduct) {
        this.ruleForm.term = selectedProduct.term;
        this.ruleForm.requestedAmount = selectedProduct.loanAmount;
        this.ruleForm.interestRate = selectedProduct.interestRate;
        this.ruleForm.productDescription = selectedProduct.productDescription;
      } else {
        this.ruleForm.term = '';
        this.ruleForm.requestedAmount = '';
        this.ruleForm.interestRate = '';
        this.ruleForm.productDescription = '';
      }
    }
  },
  computed: {
    productInterestRate() {
      return this.$store.state.rateInfo.productInterestRate
    },
    user() {
      return this.$store.state.userInfo.user
    },
    creditScoreInfo() {
      return this.$store.state.creditInfo.creditScoreInfo
    },
  },
  mounted() {
    this.$store.dispatch('rateInfo/getAllProductInterestRate');
    this.$store.dispatch('creditInfo/getCreditScoreInfo', this.user.userId);
    this.ruleForm.userId = this.user.userId;
  },
}
</script>


<style scoped>
.product-application {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 5px;
}

.title {
  font-size: 24px;
  margin-bottom: 20px;
}

.credit-limit {
  font-size: 16px;
  margin-bottom: 20px;
}

.form-container {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
}

.demo-ruleForm .el-form-item__label {
  width: 100px;
}

.demo-ruleForm .el-form-item__content {
  margin-left: 120px;
}

.el-button {
  margin-right: 10px;
}
</style>