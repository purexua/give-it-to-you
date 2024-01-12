<template>
  <div class="main">
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="8">
            <el-form-item label="ID">
              <el-input style="width: 95%" placeholder="ID" v-model = "uid"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="当前期数">
              <el-input style="width: 95%" placeholder="当前期数" v-model = "uterm"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="search">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="reset">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table v-loading="loading" :data="tableData" style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange()" border>
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-descriptions class="content-desc" title="详细信息" :column="3" :size="size" border>
            <template slot="extra">
            </template>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-shopping-bag-1"></i>
                商品ID
              </template>
              <span>{{ props.row.applicationId }}</span>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                总期数
              </template>
              <span>{{ props.row.term + '期' }}</span>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                当前期数
              </template>
              <span>{{ '第' + props.row.currentTerm + '期' }}</span>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                当前需还
              </template>
              <span>{{ props.row.dueAmount + '元' }}</span>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-office-building"></i>
                总金额
              </template>
              <span>{{ props.row.totalAmount + '元' }}</span>
            </el-descriptions-item>
          </el-descriptions>
        </template>
      </el-table-column>
      <!-- <el-table-column label="序号" type="index">
      </el-table-column> -->
      <el-table-column label="申请Id">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.applicationId }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="当前期数" sortable prop="currentTerm">
      </el-table-column>
      <el-table-column label="开始日期" sortable prop="planStartTime" :formatter="formatDate">
      </el-table-column>
      <el-table-column label="还款金额" sortable>
        <template slot-scope="scope">
          <div>
            <span>{{ scope.row.dueAmount + '元' }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="还款状态">
        <template slot-scope="scope">
          <div>
            <el-tag effect="plain" :type="'danger'">{{ scope.row.isPaid === 0 ? '待还款' : '已还' }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="showDialog(scope.row)">我要还款</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="position: relative;margin-top: 20px;margin-left: 80%;">
      <el-button type="success" @click="intermediary()">批量还款</el-button>
    </div>
    <div class="block" style="position: relative;margin-left: 20%;">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[5, 10]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <el-dialog title="请选择付款方式" :visible.sync="centerDialogVisible" width="30%" center>

      <el-button-group>
        <el-button :type="selectedPaymentMethod === '微信' ? 'success' : ''" icon="el-icon-chat-dot-round"
          style="width: 262px;" @click="selectPaymentMethod('微信')">微信</el-button>
        <el-button :type="selectedPaymentMethod === '支付宝' ? 'primary' : ''" icon="el-icon-shopping-bag-2"
          style="width: 262px;" @click="selectPaymentMethod('支付宝')">支付宝</el-button>
      </el-button-group>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="intermediary2(nowRow)">确 定</el-button>
      </span>


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
      uid: null,
      uterm: null,
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      loading:true,
      nowRow: null,
      centerDialogVisible: false,
      multipleSelection: [],
      tableData: [],
      total: null,
      currentPage: 1,
      pageSize: 5,
      selectedPaymentMethod: '微信',
      nowSelect: null
    }
  },
  methods: {
    search(){
      this.findAllPage()
    },
    selectPaymentMethod(method) {
      this.selectedPaymentMethod = method;
    },
    intermediary2(row) {
      if (this.nowSelect === 1) {
        this.addRecord(row);
      } else if (this.nowSelect === 2) {
        this.toggleSelection();
      }
    },
    intermediary() {
      this.centerDialogVisible = true;
      this.nowSelect = 2
    },
    showDialog(e) {
      this.centerDialogVisible = true;
      this.nowRow = e;
      this.nowSelect = 1
    },
    addRecordBatch(val){
      this.centerDialogVisible = false;
      const data = []
      val.forEach(item => 
      {
            let record = {
            userId: this.user.userId,
            planId: item.planId,
            paymentTime: Date.now(),
            paymentAmount: item.dueAmount,
            paymentMethod: this.selectedPaymentMethod
          }
          data.push(record)
      });
      axios.post('http://localhost:3919/serve8080/addRecordBatch',data)
        .then(response => {
          this.$notify({
          title: '成功',
          message: response.data.message,
          type: 'success'
        });
          this.findAllPage();
        })
        .catch(error => {
          console.error(error);
        });
    },
    reset(){
        this.uid = ''
        this.uterm = ''
    },
    addRecord(row) {
      this.centerDialogVisible = false;
      let record = {
        userId: this.user.userId,
        planId: row.planId,
        paymentTime: Date.now(),
        paymentAmount: row.dueAmount,
        paymentMethod: this.selectedPaymentMethod
      }
      axios.post('http://localhost:3919/serve8080/addRecord', record)
        .then(response => {
          this.$notify({
          title: '成功',
          message: response.data.message,
          type: 'success'
        });
          this.findAllPage();
        })
        .catch(error => {
          console.error(error);
        });
    },
    formatDate(row, column, cellValue) {
      return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss');
    },
    toggleSelection() {
      let selectedRows = this.$refs.multipleTable.selection;
      this.addRecordBatch(selectedRows)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
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
    findAllPage() {
      const userId = this.user.userId;
      const current = this.currentPage;
      const size = this.pageSize;
      axios.get('http://localhost:3919/serve8080/getAllPlans', {
        params: {
          userId: userId,
          current: current,
          size: size,
          currentTerm : this.uterm,
          applicationId : this.uid
        }
      })
        .then(response => {
          this.loading = false;
          this.tableData = response.data.data.records;
          this.total = response.data.data.total;
          if (response.data.status === 1)
            this.$message.error('哎呦~出错啦');
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  mounted() {
    this.findAllPage();
  },
  computed: {
    user() {
      return this.$store.state.userInfo.user;
    },
    userCreditScore() {
      return this.$store.state.creditInfo.userCreditScore;
    }
  },
  beforeDestroy() {
  }
}
</script>
<style scoped>
.main {
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
}

.content-desc {
  width: 80%;
  margin: 2px 100px;
}

a {
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