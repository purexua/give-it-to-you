<template>
    <div>
    <el-table
    :data="tableData"
      style="width: 100%"
      ref="multipleTable"
      @selection-change="handleSelectionChange()"
      :row-class-name="tableRowClassName">
      <el-table-column
      type="selection"
      width="55">
    </el-table-column>
      <el-table-column
        label="序号"
        type = "index">
      </el-table-column>
      <el-table-column
        label="申请Id">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.applicationId }}</el-tag>
          </div>
      </template>
      </el-table-column>
      <el-table-column
        label="分期号"
        sortable
        prop="installment">
      </el-table-column>
      <el-table-column
        label="截止日期"
        sortable
        prop="dueDate"
        :formatter="formatDate">
      </el-table-column>
      <el-table-column
        label="还款金额"
        sortable>
        <template slot-scope="scope">
          <div>
            <span>{{ scope.row.amountDue }}</span>
          </div>
      </template>
      </el-table-column>
      <el-table-column
        label="还款状态"
        >
        <template slot-scope="scope">
          <div>
            <el-tag effect="plain" :type="'danger'">{{ scope.row.paymentStatus }}</el-tag>
          </div>
      </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          @click="findProductInfo(scope.row,scope.$index)"
          >详情</el-button>
          <el-button
          size="mini"
          @click="addInstallment(scope.row,scope.$index)"
          >我要还款</el-button>
      </template>
      </el-table-column>
    </el-table>
   <div style="position: relative;margin-top: 20px;margin-left: 80%;">
    <el-button type = "success" @click="toggleSelection()">批量还款</el-button>
   </div>
    <div class="block" style="position: relative;margin-left: 20%;">
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
        </el-pagination>
    </div>
    <el-dialog
      title="产品信息"
      :visible.sync="centerDialogVisible"
      width="45%">
      <el-descriptions class="margin-top" :column="3" :size="size" border >
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-shopping-bag-2"></i>
            产品类型
          </template>
          <span>{{ productInfo.productType }}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-pie-chart"></i>
            总期数
          </template>
          <span>{{ productInfo.term }}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-bank-card"></i>
            总金额
          </template>
          <span>{{ productInfo.requestedAmount }}</span>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-data-line"></i>
            利率
          </template>
          <el-tag size="small"><span>{{ productInfo.interestRate }}</span></el-tag>
        </el-descriptions-item>
  </el-descriptions>
    </el-dialog>
   </div>
  </template>
  
  <script>
  import dayjs from 'dayjs';
  import 'dayjs/locale/zh-cn'; // 导入中文语言包
  import axios from "axios"
    export default {
        name: "RepaymentPlanView",
      data() {
        return {
        centerDialogVisible: false,
        multipleSelection: [],
        search: null,
        tableData: [],
        total:null,
        currentPage:1,
        pageSize:10,
        productInfo:{
            productType:null,
            term:null,
            requestedAmount:null,
            interestRate:null
        },
        }
      },
      methods: {
        formatDate(row, column, cellValue) {
          return dayjs(cellValue).format('YYYY-MM-DD');
        },
        toggleSelection() {
          var balance = this.getBalance();
          const selectedRows = this.$refs.multipleTable.selection; 
          var num = 0;
          selectedRows.forEach(row => {
                num += row.amountDue; 
          });
          if(balance < num)
          {
            this.$message.error('无法全部还款');
            return;
          }
          selectedRows.forEach(row => {
            this.addInstallment(row, row.$index);
          });
          this.$message.success('部分还款成功');
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      updateOtherRecord(val){
        const date = new Date().toISOString();
        axios.get('http://localhost:3919/serve8080/updateRecords',{
        params: {
            paymentDate: date,
            applicationId : val.applicationId,
        }
       })
        .then(response => { 
            console.log(response.data.data)
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error =>  {
            console.log(error);
        });
      },
      insertFirstRecord(val){
        const date = new Date().toISOString();
        axios.get('http://localhost:3919/serve8080/insertRecords',{
        params: {
            date:date,
            applicationId : val.applicationId,
            amount:val.amountDue,
        }
       })
        .then(response => { 
            console.log(response.data.data)
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error =>  {
            console.log(error);
        });
      },
      addRecords(row){
        const amount = row.amountDue;
          if(row.installment === 1)
          {
            this.insertFirstRecord(row);
          }else{
            this.updateOtherRecord(row);
          }
          this.$store.dispatch('creditInfo/addLimitAmountAfterRepayment',{
            userId:this.user.userId,
            amount:row.amountDue
          });
          this.$store.dispatch('creditInfo/subBalanceAfterRepament',
            {
              userId:this.user.userId,
              balance:amount
            }
          );
      },
      addInstallment(val, index){
        axios.get('http://localhost:3919/serve8080/changeInstallment',{
        params: {
            applicationId : val.applicationId,
            userId: this.user.userId,
            due: val.amountDue
        }
       })
        .then(response => { 
            console.log(response.data.data)
            if(response.data.message === '余额不足')
            {
              this.$message.error('余额不足');
              return;
            }
            if(response.data.status === 1)
            {
              this.$message.error('哎呦~出错啦');
              return ;
            }
            if(response.data.message === "1")
            {
              this.$message({
                showClose: true,
                message: '恭喜你,已全部还清！',
                type: 'success'
              });
              this.tableData.splice(index, 1);
            }else{
              this.addRecords(val);
              this.findAllPage();
            }
        })
        .catch(error =>  {
            console.log(error);
        });
      },
        handleSizeChange(val) {
        this.pageSize = val;
        this.findAllPage();
      },
      handleCurrentChange(val) {
       this.currentPage = val;
        this.findAllPage();
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      findProductInfo(row,index){
        const applicationId = row.applicationId;
        axios.get('http://localhost:3919/serve8080/findProductInfoByApplicationId',{
        params: {
            applicationId : applicationId
        }
       })
        .then(response => { 
            this.productInfo = response.data.data;
            this.centerDialogVisible = true;
            console.log(response.data.data);
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error =>  {
            console.log(error);
        });
      },
      getBalance()
      {
        axios.get('http://localhost:3919/serve8080/getRealBalance',{
        params: {
            userId: this.user.userId
        }
       })
        .then(response => { 
            console.log(response.data.data)
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
            return response.data.data;
        })
        .catch(error =>  {
            console.log(error);
        });
      },
      findAllPage(){
        const userId = this.user.userId;
        const current = this.currentPage;
        const size = this.pageSize;
        axios.get('http://localhost:3919/serve8080/getAllPlans',{
        params: {
            userId: userId,
            current: current,
            size:size
        }
       })
        .then(response => { 
            this.tableData = response.data.data.records;
            this.total = response.data.data.total;
            console.log(response.data.data);
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error =>  {
            console.log(error);
        });
      },  tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      
    }},
    mounted(){
        this.findAllPage();
    },
    computed:{
        user()
        {
            return this.$store.state.userInfo.user;
        },
        userCreditScore()
        {
            return this.$store.state.creditInfo.userCreditScore;
        }
    },
    beforeDestroy() {
    }
    }
  </script>
  <style scoped>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
  a{
    text-decoration: none;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>