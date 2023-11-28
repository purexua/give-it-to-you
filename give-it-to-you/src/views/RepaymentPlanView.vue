<template>
    <div>
    <el-table
    :data="tableData"
      style="width: 100%"
      ref="multipleTable"
      tooltip-effect="dark"
      @selection-change="handleSelectionChange()">
      <el-table-column
      type="selection"
      width="55">
    </el-table-column>
      <el-table-column
        label="序号"
        type = "index">
      </el-table-column>
      <el-table-column
        label="申请Id"
        prop="applicationId">
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
        sortable
        prop="amountDue">
      </el-table-column>
      <el-table-column
        label="还款状态"
        prop="paymentStatus">
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          >详情</el-button>
          <el-button
          size="mini"
          @click="addInstallment(scope.row)"
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
        }
        }
      },
      methods: {
        formatDate(row, column, cellValue) {
          return dayjs(cellValue).format('YYYY-MM-DD');
        },
        toggleSelection(row) {
        if (row) {
          this.multipleSelection.forEach(row => {
            this.handleSelectedRow(row);
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectedRow(row) {
        this.addInstallment(row);
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      addInstallment(val){
        // this.tableData[val].installment++;
        axios.get('http://localhost:3919/serve8080/changeInstallment',{
        params: {
            applicationId : val.applicationId
        }
       })
        .then(response => { 
            console.log(response.data.data)
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
            this.findAllPage();
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
      findProductInfo(row,expandedRows){
        const applicationId = row.applicationId;
        axios.get('http://localhost:3919/serve8080/findProductInfoByApplicationId',{
        params: {
            applicationId : applicationId
        }
       })
        .then(response => { 
            this.productInfo = response.data.data;
            console.log(response.data.data);
            if(response.data.status === 1)
            this.$message.error('哎呦~出错啦');
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
      },
    },
    mounted(){
        this.findAllPage();
    },
    computed:{
        user()
        {
            return this.$store.state.userInfo.user;
        }
    },
    beforeDestroy() {
    }
    }
  </script>
  <style scoped>
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