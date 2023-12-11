<template>
    <div>
        <el-table :data="applicationList" style="width: 100%">
            <el-table-column label="申请 ID" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.applicationId }}</span>
                </template>
            </el-table-column>
            <el-table-column label="用户 ID" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.userId }}</span>
                </template>
            </el-table-column>
            <el-table-column label="贷款类型" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.loanType }}</span>
                </template>
            </el-table-column>
            <el-table-column label="贷款期限" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.term }}</span>
                </template>
            </el-table-column>
            <el-table-column label="申请金额" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.requestedAmount }}</span>
                </template>
            </el-table-column>
            <el-table-column label="利率" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.interestRate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="申请时间" width="160">
                <template slot-scope="scope">
                    <span>{{ scope.row.applicationTime | timeFormat() }}</span>
                </template>
            </el-table-column>
            <el-table-column label="审批时间" width="160">
                <template slot-scope="scope">
                    <span v-if="scope.row.approvalTime">{{ scope.row.approvalTime | timeFormat() }}</span>
                    <span v-else style="display: flex; justify-content: center; align-items: center;">-</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" width="160">
                <template slot-scope="scope">
                    <span v-if="scope.row.status === 'Pending'">
                        <el-button size="mini" type="success"
                            @click="handleApproved(scope.$index, scope.row)">通过</el-button>
                        <el-button size="mini" type="danger" @click="handleRejected(scope.$index, scope.row)">拒绝</el-button>
                    </span>
                    <el-tag type="info" v-else style="display: flex; justify-content: center; align-items: center;">{{
                        scope.row.status }}</el-tag>
                </template>
            </el-table-column>
        </el-table>
        <div class="footer">
            <el-pagination background layout="prev, pager, next" :total="pageInfo.total" :page-size="pageInfo.pageSize"
                @current-change="handleCurrentChange">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import dayjs from 'dayjs';

export default {
    name: 'LoanApproval',
    data() {
        return {
            pageInfo: {
                pageSize: 10,
                pageNum: 1,
                total: 0,
            },
        }
    },
    computed: {
        applicationList() {
            return this.$store.state.applicationInfo.applicationList;
        },
        applicationListCount() {
            return this.$store.state.applicationInfo.applicationListCount;
        },
        creditScoreInfo() {
            return this.$store.state.creditInfo.creditScoreInfo;
        }
    },
    methods: {
        async handleApproved(index, row) {
            await this.$store.dispatch('creditInfo/getCreditScoreInfo', row.userId);
            console.log('##', this.creditScoreInfo.limitAmount);
            if (row.requestedAmount > this.creditScoreInfo.limitAmount) {
                this.$message({
                    message: '申请金额超过信用额度',
                    type: 'error',
                });
            } else {
                // 申请通过
                this.$store.dispatch('applicationInfo/updateLoanApplication', {
                    applicationId: row.applicationId,
                    status: 'Approved',
                    pageNum: this.pageInfo.pageNum,
                    pageSize: this.pageInfo.pageSize,
                });
                // 生成还款计划
                this.$store.dispatch('planInfo/addRepaymentPlan', {
                    userId: row.userId,
                    applicationId: row.applicationId,
                    term: row.term,
                    totalAmount: row.requestedAmount * (1 + row.interestRate * 0.01),
                });
                // 更新信用额度
                this.$store.dispatch('creditInfo/updateCreditScoreLimitAmountAfterLoan', {
                    userId: row.userId,
                    loanAmount: row.requestedAmount,
                });
                this.$message({
                    message: '审批通过',
                    type: 'success',
                });
            }

        },
        handleRejected(index, row) {
            this.$store.dispatch('applicationInfo/updateLoanApplication', {
                applicationId: row.applicationId,
                status: 'Rejected',
                pageNum: this.pageInfo.pageNum,
                pageSize: this.pageInfo.pageSize,
            });
        },
        handleCurrentChange(val) {
            this.pageInfo.pageNum = val;
            this.$store.dispatch('applicationInfo/getApplicationList', {
                pageNum: this.pageInfo.pageNum,
                pageSize: this.pageInfo.pageSize,
            });
        },
    },
    mounted() {
        this.$store.dispatch('applicationInfo/getApplicationList', {
            pageNum: this.pageInfo.pageNum,
            pageSize: this.pageInfo.pageSize,
        });
        this.$store.dispatch('applicationInfo/getApplicationListCount', {
            pageNum: this.pageInfo.pageNum,
            pageSize: this.pageInfo.pageSize,
        });
        this.pageInfo.total = this.applicationListCount;
    },
    filters: {
        timeFormat: function (value, str = 'YYYY-MM-DD HH:mm:ss') {
            return dayjs(value).format(str)
        }
    },
    watch: {
        applicationListCount: function (val) {
            this.pageInfo.total = val;
        }
    }
}
</script>

<style scoped>
.footer {
    display: flex;
    justify-content: center;
    margin: 20px 0;
}
</style>