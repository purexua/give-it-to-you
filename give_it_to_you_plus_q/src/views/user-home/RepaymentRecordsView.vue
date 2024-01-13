<template>
    <div class="main">
        <el-card style="height: 50px;">
            <el-button type="text" style="margin-top: -50px;" @click="report()">一键生成记录报表</el-button>
        </el-card><br>
        <el-table :data="tableData" style="width: 100%;" border v-loading="loading">
            <el-table-column type="index" label="序号" width="180">
            </el-table-column>
            <el-table-column prop="userId" label="用户ID" width="180">
            </el-table-column>
            <el-table-column prop="paymentMethod" label="支付方式" width="180">
            </el-table-column>
            <el-table-column prop="paymentAmount" label="已还金额" width="180">
            </el-table-column>
            <el-table-column prop="paymentTime" label="支付日期" :formatter="formatDate" width="180">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <i class="el-icon-delete" @click="deleteRecord(scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="position: relative;margin-left: 80%;margin-top: 30px;" background layout="prev, pager, next"
            :total="total" @current-change="handleCurrentChange" :current-page="currentPage">
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
import icon from 'uview-ui/libs/config/props/icon';
export default {
    name: "RepaymentRecordsView",
    methods: {
        report() {
            axios({
                url: 'http://localhost:3919/serve8080/report',
                method: 'GET',
                responseType: 'blob'
            })
                .then(response => {
                    // 获取响应头中的文件名
                    const contentDisposition = response.headers['content-disposition'];
                    const fileName = contentDisposition.split('filename=')[1];
                    // 创建一个下载链接，并模拟点击
                    const blob = new Blob([response.data], { type: 'application/octet-stream' });
                    const link = document.createElement('a');
                    link.href = window.URL.createObjectURL(blob);
                    link.download = fileName || 'report.xlsx';
                    link.click();
                    this.$notify({
                        title: '报表生成成功',
                        type: 'success'
                    });
                })
                .catch(error => {
                    console.error(error);
                });
        },
        deleteRecord(obj) {
            axios.delete('http://localhost:3919/serve8080/deleteRecord', {
                params: {
                    planID: obj.planId,
                    recordId: obj.recordId
                }
            })
                .then(res => {
                    if (res.data.code === 201)
                        this.$message.error("请确保该记录对应的还款已全部还清")
                    this.findAllRecords()
                })
                .catch(error => {
                    console.log(error);
                });
        },
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
                    size: 10
                }
            })
                .then(response => {
                    this.loading = false;
                    this.tableData = response.data.data.records;
                    this.total = response.data.data.total / 10;
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
            loading: true,
            tableData: [],
            total: 1,
            currentPage: 1,
            pageSize: 10,
        };
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user;
        }
    },
    mounted() {
        this.findAllRecords();
    },
    components: { icon }
}
</script>