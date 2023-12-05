<template>
  <el-descriptions title="信用状况" border :column="3">
    <el-descriptions-item label="真实姓名">{{ user.realName }}</el-descriptions-item>
    <el-descriptions-item label="身份证">{{ user.idCard }}</el-descriptions-item>
    <el-descriptions-item label="邮箱">{{ user.email }}</el-descriptions-item>
    <el-descriptions-item label="信用状况">
      <el-tag size="small">{{ userCreditScore.limitAmount < 10000 ? '危险' : '安全' }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="联系地址">{{ user.address }}</el-descriptions-item>
    <el-descriptions-item label="信用分">
      <el-tag size="small" type="info">{{ userCreditScore.limitAmount }}</el-tag>
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
    userCreditScore() {
      return this.$store.state.creditInfo.userCreditScore;
    }
  },
  mounted() {
    this.$store.dispatch('creditInfo/getUserCreditScoreByUserId', this.user.userId);
  },

}
</script>

<style></style>