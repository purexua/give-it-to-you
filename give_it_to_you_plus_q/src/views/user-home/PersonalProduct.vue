<template>
    <div class="personal-loan-page">
        <h1>个性化贷款页面</h1>
        <span>你的额度：{{ this.userLimit }} 元</span>

        <el-form :model="loanForm" ref="loanForm" label-position="top">
            <el-form-item label="贷款金额" prop="requestedAmount">
                <el-input v-model="loanForm.requestedAmount" placeholder="请输入贷款金额" @input="updateLoanDetails" />
            </el-form-item>

            <el-form-item label="分期期数" prop="term">
                <el-input v-model="loanForm.term" placeholder="请选择分期期数" @input="updateLoanDetails"></el-input>
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
            defaultInterestRate:null,
            loanForm: {
                requestedAmount: null,  //贷款金额
                term: null,  //贷款期数
                interestRate: 0.05,  //期数利率
            },
            installmentRates: {}, // 存放后端返回的分期利率数据
            productType: {},//存放后端返回的产品类型
            loanDetails: {
                installmentAmount: null,
                interestPerInstallment: null,
                totalAmount: null,
            },
            productRates: [],
            userLimit: 5000, // 用户最高额度，示例值，根据实际情况设置
        };
    },

    methods: {

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
                    interestRate: this.loanForm.interestRate*100
                });
                axios({
                    method: 'post',
                    url: 'http://localhost:3919/serve8080/application/personal',
                    data: {
                        userId: this.user.userId,
                        loanType: "个性化贷款",
                        term: this.loanForm.term,
                        requestedAmount: this.loanForm.requestedAmount,
                        interestRate: this.loanForm.interestRate*100
                    }
                }).then(res => {
                    if (res.data == 'success') {
                        this.$message({
                            type: 'success',
                            message: '贷款成功'
                        })
                        this.$store.dispatch('creditInfo/getCreditScoreInfo', this.user.userId);
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
            // 根据输入的贷款金额和期数，调用后端接口获取计算结果
            // 这里的示例中，使用了假设的计算函数，你需要替换为实际的后端接口调用
            this.calculateLoanDetails(this.loanForm.requestedAmount, this.loanForm.term);
        },
        calculateLoanDetails(requestedAmount, term) {
            const calculateLoanDetailsApi = (requestedAmount, term) => {
                // 在这里模拟后端计算逻辑，实际应用中应调用后端接口
                var interestRate = this.defaultInterestRate; // 初始利率
                interestRate = parseFloat(interestRate);
                let requestedPercent = requestedAmount /this.creditScoreInfo.limitAmount //申请占比
                console.log(requestedPercent);
            
                console.log(interestRate)
            
                if(requestedPercent<0.10){
                    console.log("贷款占比小，利率降低0.01")
                    //
                    interestRate = interestRate-0.01;
                }else if(0.10<=requestedPercent && requestedPercent<0.3){
                    console.log("贷款占比较小，利率降低0.005")
                    //
                    interestRate = interestRate-0.005;
                }else if(0.30<=requestedPercent && requestedPercent<0.5){
                    //
                    console.log("贷款占比较大，利率增加0.005")
                    interestRate = interestRate+0.005;
                }else if(0.50<=requestedPercent && requestedPercent<0.8){
                    //
                    console.log("贷款占比大，利率增加0.01")
                    interestRate = interestRate+0.01;
                }else{
                    //
                    console.log("占比过大利率增加0.03")
                    interestRate = interestRate+0.03;
                }

                // const installmentAmount = (requestedAmount * interestRate) / (1 - Math.pow(1 + interestRate, -term));
                const installmentAmount = (requestedAmount/term) * (1+interestRate)
                const interestPerInstallment = (requestedAmount/term)*interestRate; // 每期利息
                const totalAmount = installmentAmount * term;

                interestRate = interestRate.toFixed(4);
                this.loanForm.interestRate = interestRate;

                return {
                    installmentAmount: installmentAmount.toFixed(2),
                    interestPerInstallment: interestPerInstallment.toFixed(2),
                    totalAmount: totalAmount.toFixed(2),
                };
            };

            const result = calculateLoanDetailsApi(requestedAmount, term);

            // 更新loanDetails
            this.loanDetails = {
                ...this.loanDetails,
                ...result,
            };
        },
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user
        },
        creditScoreInfo() {
            return this.$store.state.creditInfo.creditScoreInfo
        },
        // 计算属性，监测loanDetails的变化并实时更新视图
        // 在需要动态计算的情况下，使用计算属性是一个好的选择
        loanDetails() {
            return this.calculateLoanDetails(this.loanForm.requestedAmount, this.loanForm.term);
        },
    },
    mounted() {
        console.log(this.creditScoreInfo.limitAmount);
        this.userLimit = this.creditScoreInfo.limitAmount;

        let interestRate = 0.05; // 初始利率为5%


        //动态算取利率
         const userDetail = {
                    userId:this.user.userId,
                    email: this.user.email,  //用户邮箱
                    address: this.user.address, //联系地址
                    phoneNumber: this.user.phoneNumber, //联系电话
                    healthStatus: this.user.healthStatus,//健康状态
                    creditStatus: this.user.creditStatus, //信用状态
                    
                };
                //根据用户的账户判断风险，提高和减少利率
                if(userDetail.email=='' || userDetail.address==''){
                    console.log("用户的邮箱和联系地址只要有一个为空，风险提高，利率提高0.02")
                    interestRate = interestRate+0.02;
                }
                if(userDetail.phoneNumber==''){
                    console.log("用户的手机为空，风险提高，利率提高0.04")
                    interestRate = interestRate+0.04;
                }
                if(userDetail.healthStatus=='Good'){
                    console.log("身体健康优秀，分险降低，利率降0.02")
                    //
                    interestRate = interestRate-0.02;
                }
                if(userDetail.creditStatus=='Fair'){
                    console.log("信用差，利率增高0.02")
                    //
                    interestRate = interestRate+0.02;
                }else if(userDetail.creditStatus=='Good'){
                    console.log("信用好利率降低0.02")
                    //
                    interestRate = interestRate-0.02;
                }
                console.log(userDetail);

                interestRate = (interestRate.toFixed(4));
                this.defaultInterestRate = interestRate;
                this.loanForm.interestRate = this.defaultInterestRate;
        // axios({
        //     method: 'get',
        //     url: `http://localhost:3919/serve8080/rate/personalProduct`,
        // }).then(res => {
        //     console.log(res.data.data);
        //     this.productRates = res.data.data

        //     this.installmentRates = {};

        //     this.productRates.forEach((rate) => {
        //         this.installmentRates[rate.term] = rate.interestRate;
        //         this.productType[rate.term] = rate.productType;
        //     });

        //     this.loanForm.interestRate = this.installmentRates[3]; //将页面初始化利率设置为默认3期的利率
        // })
    },
};
</script>
  
<style scoped>
.personal-loan-page {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    background-color: rgb(238, 226, 226);
}

/* Style the interest rate text as needed */
.el-form-item__label {
    font-weight: bold;
}
</style>
  