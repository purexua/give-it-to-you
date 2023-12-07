<template>
    <div>
        <h1>充值系统</h1>
        <h1 class="info">您当前的账户余额为 <span>{{ user.balance }}</span></h1>
        <div class="container">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="充值金额" prop="money">
                    <el-input type="number" placeholder="请输入充值金额" v-model.number="ruleForm.money" style="width: 600px;">
                        <template slot="prepend">￥</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="充值方式">
                    <el-button-group>
                        <el-button :type="selectedPaymentMethod === '微信' ? 'success' : ''" icon="el-icon-chat-dot-round"
                            style="width: 300px;" @click="selectPaymentMethod('微信')">微信</el-button>
                        <el-button :type="selectedPaymentMethod === '支付宝' ? 'primary' : ''" icon="el-icon-shopping-bag-2"
                            style="width: 300px;" @click="selectPaymentMethod('支付宝')">支付宝</el-button>
                    </el-button-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即支付</el-button>
                </el-form-item>
            </el-form>
        </div>
        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
            <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
                <img :src="this.ruleForm.way === '支付宝' ? require('@/assets/images/alipay.jpg') : require('@/assets/images/wechat.jpg')"
                    style="width: 250px; height: 100%; object-fit: cover; " />
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="exist()">取 消</el-button>
                <el-button type="primary" @click="save()">我已支付</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Recharge',
    data() {
        var checkMoney = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入充值金额'));
            } else {
                if (value < 0) {
                    return callback(new Error('充值金额不能为负数'));
                }
                callback();
            }
        };
        return {
            ruleForm: {
                money: 1,
                way: '微信'
            },
            rules: {
                money: [
                    { validator: checkMoney, trigger: 'blur' }
                ],
            },
            selectedPaymentMethod: '微信',
            dialogVisible: false
        };
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user;
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogVisible = true;
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        selectPaymentMethod(method) {
            this.selectedPaymentMethod = method;
            this.ruleForm.way = method;
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => { });
        },
        exist() {
            this.dialogVisible = false;
        },
        save() {
            this.dialogVisible = false;
            this.$store.dispatch('userInfo/recharge', this.ruleForm.money);
            this.ruleForm.money = 1;
        }
    }
}
</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-top: 50px;
}

.info {
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    margin: 10px 0;
    color: #666;
}

.info span {
    color: #f56c6c;
}
</style>