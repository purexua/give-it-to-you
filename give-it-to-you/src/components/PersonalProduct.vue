<template>
    <div class="personal-loan-page">
        <h1>个性化贷款页面</h1>
        <span>你的额度：{{ this.userLimit }} 元</span>

        <el-form :model="loanForm" ref="loanForm" label-position="top">
            <el-form-item label="贷款金额" prop="requestedAmount">
                <el-input v-model="loanForm.requestedAmount" placeholder="请输入贷款金额" @input="updateLoanDetails" />
            </el-form-item>

            <el-form-item label="分期期数" prop="term">
                <el-select v-model="loanForm.term" placeholder="请选择分期期数" @change="handleSelectChange">
                    <el-option label="3期" value="3" />
                    <el-option label="6期" value="6" />
                    <el-option label="12期" value="12" />
                    <el-option label="24期" value="24" />
                </el-select>
            </el-form-item>

            <el-form-item label="利率" prop="interestRate">
                <span>{{ loanForm.interestRate }}</span>
            </el-form-item>

            <el-form-item label="每期需还金额" prop="installmentAmount">
                <span>{{ loanDetails.installmentAmount }}</span>
                <span v-if="loanDetails.interestPerInstallment">（每期利息：{{ loanDetails.interestPerInstallment }}）</span>
            </el-form-item>

            <el-form-item label="总共需要还的金额" prop="totalAmount">
                <span>{{ loanDetails.totalAmount }}</span>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
                <el-button @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
  
<script>
import axios from 'axios';
export default {
    data() {
        return {
            loanForm: {
                requestedAmount: null,  //贷款金额
                term: '3',  //贷款期数
                interestRate: '0.01',  //期数利率
            },
            installmentRates: {}, // 存放后端返回的分期利率数据
            productType: {},//存放后端返回的产品类型
            loanDetails: {
                installmentAmount: null,
                interestPerInstallment: null,
                totalAmount: null,
            },
            productRates: [],
            userLimit: 10000, // 用户最高额度，示例值，根据实际情况设置
        };
    },
    created() {
        this.updateLoanDetails();
    },
    methods: {
        handleSelectChange() {
            this.updateInterestRate();
            this.updateLoanDetails();
        },
        submitForm() {
            // 检查是否超出用户额度
            const loanAmount = parseFloat(this.loanForm.requestedAmount);
            console.log(this.userLimit)
            if (!isNaN(loanAmount) && loanAmount > this.userLimit) {
                this.$message.error('贷款金额超过用户额度，无法提交。');
                return;
            } else {
                console.log('前端发送的数据:', {
                    userId: this.user.userId,
                    productType: this.productType[this.loanForm.term],
                    term: this.loanForm.term,
                    requestedAmount: this.loanForm.requestedAmount,
                    interestRate: this.loanForm.interestRate
                });
                axios({
                    method: 'post',
                    url: 'http://localhost:3919/serve8080/rate/insertPersonal',
                    data: {
                        userId: this.user.userId,
                        productType: this.productType[this.loanForm.term],
                        term: this.loanForm.term,
                        requestedAmount: this.loanForm.requestedAmount,
                        interestRate: this.loanForm.interestRate
                    }
                }).then(res => {
                    if (res.data.code == 1) {
                        this.$message({
                            type: 'success',
                            message: '贷款成功'
                        })
                        this.$store.dispatch('creditInfo/updateLimitAmountAfterLoanApplication', {
                            userId: this.user.userId,
                            amount: this.loanForm.requestedAmount
                        });
                        this.resetForm();//刷新
                    }
                    else {
                        this.$message({
                            type: 'waring',
                            message: '贷款失败'
                        })
                    }
                })
            }

            // Add logic to handle form submission
            console.log('Submitted:', this.loanForm);
        },
        resetForm() {
            // Reset form fields
            this.$refs.loanForm.resetFields();
            // Recalculate loan details when resetting the form
            this.updateLoanDetails();
        },
        updateLoanDetails() {
            const loanAmount = parseFloat(this.loanForm.requestedAmount);
            const interestRate = parseFloat(this.loanForm.interestRate);
            const selectedInstallments = parseFloat(this.loanForm.term);

            if (!isNaN(loanAmount) && !isNaN(interestRate) && !isNaN(selectedInstallments)) {
                const monthlyInterestRate = interestRate / 12;
                const totalAmount = loanAmount * (1 + interestRate);
                const installmentAmount = totalAmount / selectedInstallments;
                const interestPerInstallment = (loanAmount * monthlyInterestRate).toFixed(2);

                this.loanDetails.totalAmount = totalAmount.toFixed(2);
                this.loanDetails.installmentAmount = installmentAmount.toFixed(2);
                this.loanDetails.interestPerInstallment = interestPerInstallment;
            } else {
                this.loanDetails.totalAmount = null;
                this.loanDetails.installmentAmount = null;
                this.loanDetails.interestPerInstallment = null;
            }
        },
        updateInterestRate() {
            const installmentRates = this.installmentRates

            const selectedInstallments = this.loanForm.term;
            this.loanForm.interestRate = installmentRates[selectedInstallments];
        },
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user
        },
        userCreditScore() {
            return this.$store.state.creditInfo.userCreditScore
        }
    },
    mounted() {
        this.userLimit = this.userCreditScore.limitAmount;
        axios({
            method: 'get',
            url: `http://localhost:3919/serve8080/rate/personalProduct`,
        }).then(res => {
            console.log(res.data.data);
            this.productRates = res.data.data

            this.installmentRates = {};

            this.productRates.forEach((rate) => {
                this.installmentRates[rate.term] = rate.interestRate;
                this.productType[rate.term] = rate.productType;
            });

            this.loanForm.interestRate = this.installmentRates[3]; //将页面初始化利率设置为默认3期的利率
        })
    },
};
</script>
  
<style scoped>
.personal-loan-page {
    max-width: 400px;
    margin: auto;
    padding: 20px;
}

/* Style the interest rate text as needed */
.el-form-item__label {
    font-weight: bold;
}
</style>
  