![](./image/logo-plus.png)

公司：purehub

项目名：给你呗-网络小额贷款平台

logo：![give-it-to-give-it-to-you](./image/logo-min-min.png)

## 关于项目

给你呗-网络小额贷款平台，旨在为需要短期或小额资金的个人和小微企业提供便捷的在线借贷服务。该平台的主要功能和亮点包括:

1. 在线申请和审批流程简单快速 - 借款人只需通过网页提交基本信息和资料,无须线下运行,一般可在1-3个工作日内放款。
2. 全自动风控体系 - 平台通过数据分析,构建科学合理的信用评估和反欺诈模型,准确判断风险。
3. 多元灵活的产品组合 - 根据不同客户需求,提供多种期限(7天、15天、30天等)和额度的产品。利率透明合理。
4. 自动拉取数据、智能还款提醒 - 通过授权拉取借款人信用和交易数据,并通过短信、邮件主动提醒还款。
5. 直观易操作的用户界面 - 借款人和投资人都可以通过网页便捷管理。
6. 严格的数据安全系统 - 平台采用~~加密~~、~~SSL证书~~、~~防火墙~~、数据备份等前沿技术保证信息安全。

本项目作为科技金融课程的应用实践，由 purehub 公司的五个人，对整个项目进行全过程开发。
公司成员如下：钟俊博 （purezhong）、龚圆康（puregong）、龚安（puregong2）、张炳洋（purezhang）、徐健豪（purexu）

## 数据库

### 用户表（user）

| Field        | Explanation | Type         | Null | Key  | Default | Extra          |
| ------------ | ----------- | ------------ | ---- | ---- | ------- | -------------- |
| user_id      | 用户ID      | INT          | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_name    | 用户名      | VARCHAR(255) | NO   |      | NULL    |                |
| password     | 密码        | VARCHAR(255) | NO   |      | NULL    |                |
| real_name    | 真实姓名    | VARCHAR(255) | NO   |      | NULL    |                |
| id_card      | 身份证号    | VARCHAR(255) | NO   |      | NULL    |                |
| email        | 电子邮件    | VARCHAR(100) | YES  |      | NULL    |                |
| address      | 地址        | VARCHAR(255) | YES  |      | NULL    |                |
| phone_number | 电话号码    | VARCHAR(20)  | YES  |      | NULL    |                |
| balance      | 余额        | INT          | NO   |      | 0       |                |
| role         | 角色        | VARCHAR(20)  | NO   |      | client  |                |

### 借款申请表，与产品利率表关联 (Product_Loan_Application)

| Field            | Explanation | Type          | Null | Key  | Default | Extra          |
| ---------------- | ----------- | ------------- | ---- | ---- | ------- | -------------- |
| application_id   | 申请ID      | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id          | 用户ID      | INT           | YES  | MUL  | NULL    |                |
| product_category | 产品类别    | VARCHAR(50)   | NO   | MUL  | NULL    |                |
| term             | 期限        | INT           | NO   |      | NULL    |                |
| requested_amount | 申请金额    | DECIMAL(10,2) | NO   |      | NULL    |                |
| interest_rate    | 利率        | INT           | NO   |      | NULL    |                |

### 借款申请表，与利率表关联 (Generic_Loan_Application)

| Field            | Explanation | Type          | Null | Key  | Default | Extra          |
| ---------------- | ----------- | ------------- | ---- | ---- | ------- | -------------- |
| application_id   | 申请ID      | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id          | 用户ID      | INT           | YES  | MUL  | NULL    |                |
| product_type     | 产品类型    | VARCHAR(50)   | NO   | MUL  | NULL    |                |
| term             | 期限        | INT           | NO   |      | NULL    |                |
| requested_amount | 申请金额    | DECIMAL(10,2) | NO   |      | NULL    |                |
| interest_rate    | 利率        | INT           | NO   |      | NULL    |                |

### 还款计划表（Repayment_Schedule）

| Field          | Explanation | Type          | Null | Key  | Default | Extra          |
| -------------- | ----------- | ------------- | ---- | ---- | ------- | -------------- |
| plan_id        | 计划ID      | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| application_id | 申请ID      | INT           | YES  | MUL  | NULL    |                |
| installment    | 分期号      | INT           | NO   |      | NULL    |                |
| due_date       | 到期日期    | DATE          | NO   |      | NULL    |                |
| amount_due     | 应付金额    | DECIMAL(10,2) | NO   |      | NULL    |                |
| payment_status | 付款状态    | VARCHAR(20)   | NO   |      | Pending |                |

### 还款记录表（Repayment_Record）

| Field         | Explanation  | Type          | Null | Key  | Default | Extra          |
| ------------- | ------------ | ------------- | ---- | ---- | ------- | -------------- |
| record_id     | 记录ID       | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| plan_id       | 计划ID       | INT           | YES  | MUL  | NULL    |                |
| payment_date  | 付款日期     | DATE          | NO   |      | NULL    |                |
| actual_amount | 实际付款金额 | DECIMAL(10,2) | NO   |      | NULL    |                |

### 贷款合同表（Loan_Contract）

| Field           | Explanation | Type         | Null | Key  | Default | Extra          |
| --------------- | ----------- | ------------ | ---- | ---- | ------- | -------------- |
| contract_id     | 合同ID      | INT          | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id         | 用户ID      | INT          | YES  | MUL  | NULL    |                |
| application_id  | 申请ID      | INT          | YES  | MUL  | NULL    |                |
| contract_number | 合同编号    | VARCHAR(50)  | NO   |      | NULL    |                |
| signing_date    | 签订日期    | DATE         | NO   |      | NULL    |                |
| loan_purpose    | 贷款目的    | VARCHAR(255) | NO   |      | NULL    |                |

### 用户信用评分表（Credit_Score）

| Field        | Explanation | Type | Null | Key  | Default | Extra          |
| ------------ | ----------- | ---- | ---- | ---- | ------- | -------------- |
| score_id     | 评分ID      | INT  | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id      | 用户ID      | INT  | YES  | MUL  | NULL    |                |
| credit_score | 信用评分    | INT  | NO   |      | NULL    |                |

### 产品利率表 (Product_Interest_Rate)

| Field            | Explanation | Type          | Null | Key  | Default | Extra          |
| ---------------- | ----------- | ------------- | ---- | ---- | ------- | -------------- |
| p_rate_id        | 利率ID      | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| product_category | 产品类别    | VARCHAR(50)   | NO   |      | NULL    |                |
| term             | 期限        | INT           | NO   |      | NULL    |                |
| interest_rate    | 利率        | INT           | NO   |      | NULL    |                |
| loan_amount      | 贷款金额    | DECIMAL(10,2) | NO   |      | NULL    |                |

### 利率表（Interest_Rate）

| Field         | Explanation | Type        | Null | Key  | Default | Extra          |
| ------------- | ----------- | ----------- | ---- | ---- | ------- | -------------- |
| rate_id       | 利率ID      | INT         | NO   | PRI  | NULL    | AUTO_INCREMENT |
| product_type  | 产品类型    | VARCHAR(50) | NO   |      | NULL    |                |
| term          | 期限        | INT         | NO   |      | NULL    |                |
| interest_rate | 利率        | INT         | NO   |      | NULL    |                |

### 系统日志表 (System_Log)

| Field    | Explanation | Type         | Null | Key  | Default | Extra          |
| -------- | ----------- | ------------ | ---- | ---- | ------- | -------------- |
| log_id   | 日志ID      | INT          | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id  | 用户ID      | INT          | YES  | MUL  | NULL    |                |
| log_date | 日志日期    | DATETIME     | NO   |      | NULL    |                |
| action   | 操作        | VARCHAR(255) | NO   |      | NULL    |                |

## 模块

1.

|     名称     |         描述         |
| :----------: | :------------------: |
|   模块名称   |         登录         |
|  模块负责人  | 龚安、徐健豪、龚圆康 |
| 文档提交日期 |      2023/11/16      |

修改记录

|  No  | 版本号 |       修改内容意见        |  修改日期  | 修改人 |
| :--: | :----: | :-----------------------: | :--------: | :----: |
|  1   | v1.0.0 |         初始版本          | 2023/11/16 | 徐健豪 |
|  2   | v1.1.0 | 加入 jwt 令牌进行登录校验 | 2023/11/16 | 龚圆康 |

2.

|     名称     |         描述         |
| :----------: | :------------------: |
|   模块名称   |         注册         |
|  模块负责人  | 龚安、徐健豪、龚圆康 |
| 文档提交日期 |      2023/11/16      |

修改记录

|  No  | 版本号 | 修改内容意见 |  修改日期  | 修改人 |
| :--: | :----: | :----------: | :--------: | :----: |
|  1   | v1.0.0 |   初始版本   | 2023/11/16 | 徐健豪 |

3.

|     名称     |    描述    |
| :----------: | :--------: |
|   模块名称   |    首页    |
|  模块负责人  |    龚安    |
| 文档提交日期 | 2023/11/16 |

修改记录

|  No  | 版本号 |         修改内容意见         |  修改日期  | 修改人 |
| :--: | :----: | :--------------------------: | :--------: | :----: |
|  1   | v1.0.0 |           初始版本           | 2023/11/16 |  龚安  |
|  2   | v2.0.0 | 首页2.0（符合 Vue 项目格式） | 2023/11/16 |  龚安  |

## 接口说明

### 用户登录

url: `http://localhost:3919/login`

method: `GET`

type: `params`

|  参数名  | 参数含义 | 类型/必须 |       备注       |
| :------: | :------: | :-------: | :--------------: |
| userName |  用户名  | String/Y  | 按用户名查询用户 |

接口流程：

输出结果：

### 用户注册

url: `http://localhost:3919/register`

method: `POST`

type: `params`

|   参数名    |   参数含义    | 类型/必须 |       备注       |
| :---------: | :-----------: | :-------: | :--------------: |
|  userName   |    用户名     | String/Y  | 按用户名查询用户 |
|  password   |     密码      | String/Y  |       加密       |
|  realName   | 用户真实姓名  | String/Y  |       加密       |
|   idCard    |   身份证号    | String/Y  |       加密       |
|    email    |     邮箱      |  String   |                  |
|   address   |     地址      |  String   |                  |
| phoneNumber | 电话/联系方式 |  String   |                  |
