<template>
    <div class="main">
        <el-table :data="applicationList" style="width: 100%" v-loading="loading" border>
            <el-table-column label="申请 ID" width="100" sortable>
                <template slot-scope="scope">
                    <span>{{ scope.row.applicationId }}</span>
                </template>
            </el-table-column>
            <el-table-column label="贷款类型" width="140">
                <template slot-scope="scope">
                    <span>{{ scope.row.loanType }}</span>
                </template>
            </el-table-column>
            <el-table-column label="贷款期数" width="140" sortable>
                <template slot-scope="scope">
                    <span>{{ scope.row.term + '期' }}</span>
                </template>
            </el-table-column>
            <el-table-column label="申请金额" width="140">
                <template slot-scope="scope">
                    <span>{{ scope.row.requestedAmount + '元' }}</span>
                </template>
            </el-table-column>
            <el-table-column label="利率" width="80" sortable>
                <template slot-scope="scope">
                    <span>{{ scope.row.interestRate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="申请时间" width="160" sortable>
                <template slot-scope="scope">
                    <span>{{ scope.row.applicationTime | timeFormat() }}</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" width="140">
                <template slot-scope="scope">
                    <el-tag style="display: flex; justify-content: center; align-items: center;">
                        {{ scope.row.status != 'Approved' ? "Pending" : scope.row.status }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="160">
                <template slot-scope="scope">
                    <el-button type="success" :disabled="scope.row.status === 'Approved'"
                        @click="deleteRecord(scope.row.applicationId)">撤销申请</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="footer">
            <el-pagination @size-change="handleCurrentChange" @current-change="handleCurrentChange"
                :current-page.sync="pageInfo.pageNum" :page-sizes="[5, 8, 10]" :page-size.sync="pageInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="pageInfo.total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import dayjs from 'dayjs';

export default {
    name: 'applicationRecord',
    data() {
        return {
            loading: true,
            applicationList: null,
            pageInfo: {
                pageSize: 5,
                pageNum: 1,
                total: 0,
            },
        }
    },
    methods: {
        deleteRecord(val) {
            axios.delete('http://localhost:3919/serve8080/application/deleterecord', {
                params: {
                    applicationId: val
                }
            }).then(response => {
                this.findAllRecord();
            })
                .catch(error => {
                    console.log(error);
                });
        },
        handleCurrentChange() {
            this.findAllRecord();
        },
        findAllRecord() {
            let userId = this.user.userId;
            let current = this.pageInfo.pageNum;
            let size = this.pageInfo.pageSize;
            console.log(this.pageInfo)
            axios.get('http://localhost:3919/serve8080/application/record', {
                params: {
                    userId: userId,
                    current: current,
                    size: size
                }
            })
                .then(response => {
                    this.loading = false;
                    this.applicationList = response.data.data.records;
                    this.pageInfo.total = response.data.data.total;
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    mounted() {
        this.findAllRecord()
    },
    filters: {
        timeFormat: function (value, str = 'YYYY-MM-DD HH:mm:ss') {
            return dayjs(value).format(str)
        }
    },
    computed: {
        user() {
            return this.$store.state.userInfo.user;
        }
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

.footer {
    display: flex;
    justify-content: center;
    margin: 20px 0;
}
</style>