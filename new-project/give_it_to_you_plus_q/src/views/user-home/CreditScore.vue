<template>
    <el-descriptions class="margin-top" title="我的信用信息" :column="3" border>
        <template slot="extra">
            <el-button type="primary" size="small" @click="update">更新一下</el-button>
        </template>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-user"></i>
                ID
            </template>
            {{ creditScoreInfo.userId }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                信用分
            </template>
            <el-tag type="success">{{ creditScoreInfo.creditScore }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-bank-card"></i>
                月收入
            </template>
            {{ creditScoreInfo.monthlyIncome }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-goods"></i>
                月支出
            </template>
            {{ creditScoreInfo.expenses }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-suitcase-1"></i>
                就业状态
            </template>
            {{ creditScoreInfo.employmentStatus }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-timer"></i>
                工作时长
            </template>
            {{ creditScoreInfo.jobDuration }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-school"></i>
                教育水平
            </template>
            {{ creditScoreInfo.educationLevel }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-document-delete"></i>
                逾期记录
            </template>
            {{ creditScoreInfo.overdueRecords }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-office-building"></i>
                不动资产价值
            </template>
            {{ creditScoreInfo.assetsValue }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-s-comment"></i>
                社交关系数
            </template>
            {{ creditScoreInfo.socialConnections }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-s-check"></i>
                法律纠纷数
            </template>
            {{ creditScoreInfo.legalDisputes }}
        </el-descriptions-item>
        <el-descriptions-item>
            <template slot="label">
                <i class="el-icon-magic-stick"></i>
                行为分析
            </template>
            {{ creditScoreInfo.behaviorAnalysis }}
        </el-descriptions-item>
    </el-descriptions>
</template>
  
<script>
export default {
    name: 'CreditInfo',
    computed: {
        user() {
            return this.$store.state.userInfo.user;
        },
        creditScoreInfo() {
            return this.$store.state.creditInfo.creditScoreInfo;
        }
    },
    methods: {
        update() {
            console.log('update ...');
        }
    },
    mounted() {
        this.$store.dispatch('creditInfo/getCreditScoreInfo', this.user.userId);
    },

}
</script>
  
<style></style>