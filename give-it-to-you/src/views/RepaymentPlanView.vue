<template>
    <div>
    <el-table
    :data="tableData"
      style="width: 100%">
      <el-table-column type="expand">
        <template>
          <el-form label-position="left" inline class="demo-table-expand" style="position: relative;text-align: left;margin-left: 10%;">
            <el-form-item label="产品类别">
              <!-- <span>{{ props.row.name }}</span> -->
            </el-form-item>
            <el-form-item label="期限">
              <!-- <span>{{ props.row.shop }}</span> -->
            </el-form-item>
            <el-form-item label="申请金额">
              <!-- <span>{{ props.row.id }}</span> -->
            </el-form-item>
            <el-form-item label="利率">
              <!-- <span>{{ props.row.shopId }}</span> -->
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="序号"
        type="index">
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
        prop="dueDate">
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
      <el-table-column
      >
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">详情</el-button>
        <el-button
          size="mini"
          type="success"
          @click="handleDelete(scope.$index, scope.row)">我要还款</el-button>
      </template>
    </el-table-column>
    </el-table>
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
  import axios from "axios"
    export default {
        name: "RepaymentPlanView",
      data() {
        return {
        search: null,
        tableData: [],
        total:null,
        currentPage:1,
        pageSize:10,
        }
      },
      methods: {
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
      }
    },
    mounted(){
        this.findAllPage();
    },
    computed:{
        user()
        {
            return this.$store.state.userInfo.user;
        }
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