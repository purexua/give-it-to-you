<template>
  <div class="main">
    <el-table :data="tableData" style="width: 100%;">
      <el-table-column type="index" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="userId" label="用户ID" width="180">
      </el-table-column>
      <el-table-column prop="planId" label="计划ID" width="180">
      </el-table-column>
      <el-table-column prop="paymentMethod" label="支付方式" width="180">
      </el-table-column>
      <el-table-column prop="paymentAmount" label="已还金额" width="180">
      </el-table-column>
      <el-table-column prop="paymentTime" label="支付日期" :formatter="formatDate" width="180">
      </el-table-column>
    </el-table>
    <el-pagination
      style="position: relative;margin-left: 80%;margin-top: 30px;"
      background
      layout="prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange" :current-page="currentPage">
    </el-pagination>
  </div>
</template>
  
<style scoped>
.main {
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
}
</style>
  
<script>
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn'; // 导入中文语言包
import axios from 'axios';
export default {
  name: "RepaymentRecordsView",
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val;
      this.findAllRecords();
    },
    formatDate(row, column, cellValue) {
      return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss');
    },
    findAllRecords() {
      const userId = this.user.userId;
      axios.get('http://localhost:3919/serve8080/findAllRepaymentRecords', {
        params: {
          userId: userId,
          current: this.currentPage,
          size:10
        }
      })
        .then(response => {
          this.tableData = response.data.data.records;
          this.total = response.data.data.total / 10
          if (response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  data() {
    return {
      tableData: [],
      total: 1,
      currentPage: 1,
      pageSize: 10,
    }
  },
  computed: {
    user() {
      return this.$store.state.userInfo.user;
    }
  },
  mounted() {
    this.findAllRecords();
  }
}
</script>