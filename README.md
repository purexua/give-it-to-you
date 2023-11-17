![](image/logo-plus.png)

公司：purehub

项目名：给你呗-网络小额贷款平台

logo：![give-it-to-give-it-to-you](image/logo-min-min.png)

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

1. **用户表（User）**

- 用户ID (`user_id`): 主键。
- 用户名 (`user_name`): 非空字符串。
- 密码 (`password`): 非空字符串。
- 真实姓名 (`real_name`): 非空字符串。
- 身份证号 (`id_card`): 非空字符串。
- 电子邮件 (`email`): 字符串。
- 地址 (`address`): 字符串。
- 电话号码 (`phone_number`): 字符串。
- 余额 (`balance`): 整数，默认为0。
- 角色 (`role`): 字符串，默认为'client'。

| Field        | Type         | Null | Key  | Default | Extra          |
| ------------ | ------------ | ---- | ---- | ------- | -------------- |
| user_id      | INT          | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_name    | VARCHAR(255) | NO   |      | NULL    |                |
| password     | VARCHAR(255) | NO   |      | NULL    |                |
| real_name    | VARCHAR(255) | NO   |      | NULL    |                |
| id_card      | VARCHAR(255) | NO   |      | NULL    |                |
| email        | VARCHAR(100) | YES  |      | NULL    |                |
| address      | VARCHAR(255) | YES  |      | NULL    |                |
| phone_number | VARCHAR(20)  | YES  |      | NULL    |                |
| balance      | INT          | NO   |      | 0       |                |
| role         | VARCHAR(20)  | NO   |      | client  |                |

2. **借款申请表（Loan_Application）**

- 借款申请ID (`application_id`): 主键。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 借款金额 (`amount`): 十进制数。
- 借款期限 (`term`): 整数。
- 利率 (`interest_rate`): 十进制数。
- 状态 (`status`): 字符串。
- 审批通过时间 (`approval_date`): 日期时间。
- 审批人员ID (`approver_id`): 外键关联到用户表的用户ID。

| Field          | Type          | Null | Key  | Default | Extra          |
| -------------- | ------------- | ---- | ---- | ------- | -------------- |
| application_id | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id        | INT           | NO   | MUL  | NULL    |                |
| amount         | DECIMAL(10,2) | NO   |      | NULL    |                |
| term           | INT           | NO   |      | NULL    |                |
| interest_rate  | INT           | NO   |      | NULL    |                |
| status         | VARCHAR(20)   | NO   |      | NULL    |                |
| approval_date  | DATETIME      | YES  |      | NULL    |                |
| approver_id    | INT           | YES  | MUL  | NULL    |                |

3. **还款计划表（Repayment_Schedule）**

- 还款计划ID (`schedule_id`): 主键。
- 借款申请ID (`application_id`): 外键关联到借款申请表的借款申请ID。
- 还款期数 (`installment_number`): 整数。
- 应还款日期 (`due_date`): 日期时间。
- 应还本金 (`principal`): 十进制数。
- 应还利息 (`interest`): 十进制数。
- 还款状态 (`status`): 字符串。

| Field              | Type          | Null | Key  | Default | Extra          |
| ------------------ | ------------- | ---- | ---- | ------- | -------------- |
| schedule_id        | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| application_id     | INT           | NO   | MUL  | NULL    |                |
| installment_number | INT           | NO   |      | NULL    |                |
| due_date           | DATETIME      | NO   |      | NULL    |                |
| principal          | DECIMAL(10,2) | NO   |      | NULL    |                |
| interest           | DECIMAL(10,2) | NO   |      | NULL    |                |
| status             | VARCHAR(20)   | NO   |      | NULL    |                |

4. **贷款合同表（Loan_Contract）**

- 贷款合同ID (`contract_id`): 主键。
- 借款申请ID (`application_id`): 外键关联到借款申请表的借款申请ID。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 贷款金额 (`amount`): 十进制数。
- 贷款期限 (`term`): 整数。
- 利率 (`interest_rate`): 十进制数。
- 合同起始日期 (`start_date`): 日期时间。
- 合同结束日期 (`end_date`): 日期时间。
- 合同编号 (`contract_number`): 字符串，唯一。
- 合同附件存储路径 (`attachment_path`): 字符串。

| Field           | Type          | Null | Key  | Default | Extra          |
| --------------- | ------------- | ---- | ---- | ------- | -------------- |
| contract_id     | INT           | NO   | PRI  | NULL    | AUTO_INCREMENT |
| application_id  | INT           | NO   | MUL  | NULL    |                |
| user_id         | INT           | NO   | MUL  | NULL    |                |
| amount          | DECIMAL(10,2) | NO   |      | NULL    |                |
| term            | INT           | NO   |      | NULL    |                |
| interest_rate   | INT           | NO   |      | NULL    |                |
| start_date      | DATETIME      | NO   |      | NULL    |                |
| end_date        | DATETIME      | NO   |      | NULL    |                |
| contract_number | VARCHAR(50)   | NO   | UNI  | NULL    |                |
| attachment_path | VARCHAR(255)  | YES  |      | NULL    |                |

5. **还款记录表（Repayment_Record）**

- 还款记录ID (`record_id`): 主键。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 借款申请ID (`application_id`): 外键关联到借款申请表的借款申请ID。
- 实际还款日期 (`repayment_date`): 日期时间。
- 实际还款金额 (`repayment_amount`): 十进制数。
- 剩余本金 (`remaining_principal`): 十进制数。
- 已还利息 (`repaid_interest`): 十进制数。
- 还款状态 (`repayment_flag`): 字符串。

6. **用户信用评分表（Credit_Score）**

- 信用评分ID (`score_id`): 主键。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 信用评分 (`credit_score`): 整数。

| Field        | Type | Null | Key  | Default | Extra          |
| ------------ | ---- | ---- | ---- | ------- | -------------- |
| score_id     | INT  | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id      | INT  | NO   | MUL  | NULL    |                |
| credit_score | INT  | NO   |      | NULL    |                |

7. **利率表（Interest_Rate）**

- 利率ID (`rate_id`): 主键。
- 贷款产品类型 (`product_type`): 字符串。
- 贷款期限 (`term`): 整数。
- 利率 (`interest_rate`): 整数。

| Field         | Type        | Null | Key  | Default | Extra          |
| ------------- | ----------- | ---- | ---- | ------- | -------------- |
| rate_id       | INT         | NO   | PRI  | NULL    | AUTO_INCREMENT |
| product_type  | VARCHAR(50) | NO   |      | NULL    |                |
| term          | INT         | NO   |      | NULL    |                |
| interest_rate | INT         | NO   |      | NULL    |                |

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
