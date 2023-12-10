<template>
    <div>
        <el-table :data="productInterestRate" height="750" style="width: 100%">
            <el-table-column prop="rateId" label="产品利率 ID" width="160">
            </el-table-column>
            <el-table-column prop="productType" label="产品类型" width="160">
            </el-table-column>
            <el-table-column prop="term" label="总期数（天）" width="160">
            </el-table-column>
            <el-table-column prop="interestRate" label="利率(%)" width="160">
            </el-table-column>
            <el-table-column prop="loanAmount" label="贷款金额" width="160">
            </el-table-column>
            <el-table-column prop="productDescription" label="产品描述" width="200">
            </el-table-column>
            <el-table-column align="right">
                <template slot="header">
                    <el-button size="mini" type="primary" @click="handleAdd">添加</el-button>
                </template>
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="添加产品" :visible.sync="addDialogFormVisible">
            <el-form :model="addForm" status-icon :rules="rules" ref="addForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="产品类型" prop="productType" :label-width="formLabelWidth">
                    <el-input v-model.trim="addForm.productType"></el-input>
                </el-form-item>
                <el-form-item label="总期数（天）" prop="term" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="addForm.term"></el-input>
                </el-form-item>
                <el-form-item label="利率(%)" prop="interestRate" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="addForm.interestRate"></el-input>
                </el-form-item>
                <el-form-item label="贷款金额" prop="loanAmount" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="addForm.loanAmount"></el-input>
                </el-form-item>
                <el-form-item label="产品描述" prop="productDescription" :label-width="formLabelWidth">
                    <el-input v-model.trim="addForm.productDescription"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addCancel('addForm')">取 消</el-button>
                <el-button type="primary" @click="addConfirm('addForm')">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="编辑产品" :visible.sync="editDialogFormVisible">
            <el-form :model="editForm" status-icon :rules="rules" ref="editForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="产品利率 ID" :label-width="formLabelWidth">
                    <el-input v-model.trim="editForm.rateId" disabled></el-input>
                </el-form-item>
                <el-form-item label="产品类型" prop="productType" :label-width="formLabelWidth">
                    <el-input v-model.trim="editForm.productType"></el-input>
                </el-form-item>
                <el-form-item label="总期数（天）" prop="term" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="editForm.term"></el-input>
                </el-form-item>
                <el-form-item label="利率(%)" prop="interestRate" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="editForm.interestRate"></el-input>
                </el-form-item>
                <el-form-item label="贷款金额" prop="loanAmount" :label-width="formLabelWidth">
                    <el-input type="number" v-model.number="editForm.loanAmount"></el-input>
                </el-form-item>
                <el-form-item label="产品描述" prop="productDescription" :label-width="formLabelWidth">
                    <el-input v-model.trim="editForm.productDescription"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editCancel('editForm')">取 消</el-button>
                <el-button type="primary" @click="editConfirm('editForm')">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
export default {
    name: 'ProductInterestRate',
    data() {
        var checkProductType = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('产品类型不能为空'));
            }
            callback();
        };
        var checkTerm = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('总期数不能为空'));
            } else if (value < 0) {
                return callback(new Error('总期数不能小于0'));
            }
            callback();
        };
        var checkInterestRate = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('利率不能为空'));
            } else if (value < 0) {
                return callback(new Error('利率不能小于0'));
            } else if (value > 100) {
                return callback(new Error('利率不能大于100'));
            }
            callback();
        };
        var checkLoanAmount = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('贷款金额不能为空'));
            } else if (value < 0) {
                return callback(new Error('贷款金额不能小于0'));
            }
            callback();
        };
        var checkProductDescription = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('产品描述不能为空'));
            }
            callback();
        };
        return {
            addDialogFormVisible: false,
            editDialogFormVisible: false,
            editForm: {
                rateId: '',
                productType: '',
                term: '',
                interestRate: '',
                loanAmount: '',
                productDescription: ''
            },
            addForm: {
                productType: '',
                term: '',
                interestRate: '',
                loanAmount: '',
                productDescription: ''
            },
            rules: {
                productType: [{
                    validator: checkProductType,
                    trigger: 'blur'
                }],
                term: [{
                    validator: checkTerm,
                    trigger: 'blur'
                }],
                interestRate: [{
                    validator: checkInterestRate,
                    trigger: 'blur'
                }],
                loanAmount: [{
                    validator: checkLoanAmount,
                    trigger: 'blur'
                }],
                productDescription: [{
                    validator: checkProductDescription,
                    trigger: 'blur'
                }]
            },
            formLabelWidth: '120px'
        }
    },
    computed: {
        productInterestRate() {
            return this.$store.state.rateInfo.productInterestRate;
        }
    },
    methods: {
        handleAdd() {
            this.addDialogFormVisible = true;
        },
        handleEdit(index, row) {
            this.editForm.rateId = row.rateId;
            this.editForm.productType = row.productType;
            this.editForm.term = row.term;
            this.editForm.interestRate = row.interestRate;
            this.editForm.loanAmount = row.loanAmount;
            this.editForm.productDescription = row.productDescription;
            this.editDialogFormVisible = true;
        },
        handleDelete(index, row) {
            this.$store.dispatch('rateInfo/deleteProductInterestRate', row.rateId);
            this.$message({
                message: '成功删除 id 为 ' + row.rateId + ' 的产品利率',
                type: 'success',
            });
        },
        addConfirm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$store.dispatch('rateInfo/addProductInterestRate', this.form);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
            this.$refs[formName].resetFields();
            this.addDialogFormVisible = false;
        },
        addCancel(formName) {
            this.$refs[formName].resetFields();
            this.addDialogFormVisible = false;
        },
        editConfirm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$store.dispatch('rateInfo/updateProductInterestRate', this.editForm);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
            this.$refs[formName].resetFields();
            this.editDialogFormVisible = false;
        },
        editCancel(formName) {
            this.$refs[formName].resetFields();
            this.editDialogFormVisible = false;
        }
    },
    mounted() {
        this.$store.dispatch('rateInfo/getAllProductInterestRate');
    }
}


</script>

<style></style>