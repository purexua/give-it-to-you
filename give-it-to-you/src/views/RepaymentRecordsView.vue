<template>
    <div>
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        type="index"
        label="序号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="applicationId"
        label="申请Id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="productType"
        label= "产品类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="installment"
        label="当前期数">
      </el-table-column>
      <el-table-column
        prop="actualAmount"
        label="已还金额"
        width="180">
      </el-table-column>
      <el-table-column
        prop="paymentDate"
        label="支付日期"
        :formatter="formatDate"
        width="180">
      </el-table-column>
      <el-table-column
        label="支付状态"
        >
        <span style="color: green;">已支付</span>
      </el-table-column>
    </el-table>
  </div>
  </template>
  
  <style scoped>
    .el-table .warning-row {
      background: oldlace;
    }
  
    .el-table .success-row {
      background: #f0f9eb;
    }
  </style>
  
  <script>
   import dayjs from 'dayjs';
  import 'dayjs/locale/zh-cn'; // 导入中文语言包
  import axios from 'axios';
    export default {
        name:"RepaymentRecordsView",
      methods: {
        formatDate(row, column, cellValue) {
          return dayjs(cellValue).format('YYYY-MM-DD');
        },
        tableRowClassName({row, rowIndex}) {
          if (rowIndex === 1) {
            return 'warning-row';
          } else if (rowIndex === 2) {
            return 'success-row';
          }
          return '';
        },
        findAllRecords(){
            const userId = this.user.userId;
            axios.get('http://localhost:3919/serve8080/findAllRepaymentRecords',{
            params: {
                userId: userId,
                }
            })
            .then(response => { 
                this.tableData = response.data.data;
                for (const item of this.tableData) {
                  item.installment = item.installment-1;
                }
                console.log(response.data.data);
                if(response.data.status === 1)
                this.$message.error('哎呦~出错啦');
            })
            .catch(error =>  {
                console.log(error);
            });
        }
      },
      data() {
        return {
          tableData: []
        }
      },
      computed:{
        user()
        {
            return this.$store.state.userInfo.user;
        }
        },
      mounted()
      {
        this.findAllRecords();
      }
    }
  </script>