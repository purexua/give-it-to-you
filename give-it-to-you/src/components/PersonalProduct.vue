<template>
    <div class="personal-loan-page">
        <h1>个性化贷款页面</h1>
        <span>你的额度：{{ userLimit }} 元</span>

        <el-form :model="loanForm" ref="loanForm" label-position="top">
            <el-form-item label="贷款金额" prop="loanAmount">
                <el-input v-model="loanForm.loanAmount" placeholder="请输入贷款金额" />
            </el-form-item>

            <el-form-item label="分期期数" prop="installments">
                <el-select v-model="loanForm.installments" placeholder="请选择分期期数" @change="updateLoanDetails">
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
export default {
    data() {
        return {
            loanForm: {
                loanAmount: null,
                installments: '3',
                interestRate: 0.05,
            },
            loanDetails: {
                installmentAmount: null,
                totalAmount: null,
            },
            userLimit: 10000, // 用户最高额度，示例值，根据实际情况设置
        };
    },
    created() {
        this.updateLoanDetails();
    },
    methods: {
        submitForm() {
            // Check if the loan amount exceeds the user limit
            const loanAmount = parseFloat(this.loanForm.loanAmount);
            if (!isNaN(loanAmount) && loanAmount > this.userLimit) {
                this.$message.error('贷款金额超过用户额度，无法提交。');
                return;
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
            // Add logic to update loan details based on loan amount, interest rate, and installments
            const loanAmount = parseFloat(this.loanForm.loanAmount);
            const interestRate = parseFloat(this.loanForm.interestRate);
            const selectedInstallments = parseFloat(this.loanForm.installments);

            if (!isNaN(loanAmount) && !isNaN(interestRate) && !isNaN(selectedInstallments)) {
                const monthlyInterestRate = interestRate / 12;
                const totalAmount = loanAmount * (1 + interestRate);
                const installmentAmount = totalAmount / selectedInstallments;

                this.loanDetails.totalAmount = totalAmount.toFixed(2);
                this.loanDetails.installmentAmount = installmentAmount.toFixed(2);
            } else {
                this.loanDetails.totalAmount = null;
                this.loanDetails.installmentAmount = null;
            }
        },
        updateInterestRate() {
            // Add logic to update interest rate based on selected installments
            const installmentRates = {
                3: 0.05,
                6: 0.08,
                12: 0.1,
                24: 0.15,
            };

            const selectedInstallments = this.loanForm.installments;
            this.loanForm.interestRate = installmentRates[selectedInstallments];
        },
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
  