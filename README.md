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

```mysql
CREATE TABLE user
(
    user_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_name    VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    real_name    VARCHAR(255) NOT NULL,
    id_card      VARCHAR(255) NOT NULL,
    email        VARCHAR(100),
    address      VARCHAR(255),
    phone_number VARCHAR(20),
    balance      INT         DEFAULT 0,
    role         VARCHAR(20) DEFAULT 'client'
);
```

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

```mysql
CREATE TABLE loan_application
(
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT,
    amount         DECIMAL(10, 2),
    term           INT,
    interest_rate  INT,
    status         VARCHAR(20),
    approval_date  DATETIME,
    approver_id    INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (approver_id) REFERENCES user(user_id)
);
```

3. **还款计划表（Repayment_Schedule）:**

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

```mysql
CREATE TABLE repayment_schedule
(
    schedule_id         INT AUTO_INCREMENT PRIMARY KEY,
    application_id      INT,
    installment_number  INT,
    due_date            DATETIME,
    principal           DECIMAL(10, 2),
    interest            DECIMAL(10, 2),
    status              VARCHAR(20),
    FOREIGN KEY (application_id) REFERENCES loan_application(application_id)
);
```

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

```mysql
CREATE TABLE loan_contract
(
    contract_id        INT AUTO_INCREMENT PRIMARY KEY,
    application_id     INT,
    user_id            INT,
    amount             DECIMAL(10, 2),
    term               INT,
    interest_rate      INT,
    start_date         DATETIME,
    end_date           DATETIME,
    contract_number    VARCHAR(50) UNIQUE,
    attachment_path    VARCHAR(255),
    FOREIGN KEY (application_id) REFERENCES loan_application(application_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);
```

5. **还款记录表（Repayment_Record）**

- 还款记录ID (`record_id`): 主键。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 借款申请ID (`application_id`): 外键关联到借款申请表的借款申请ID。
- 实际还款日期 (`repayment_date`): 日期时间。
- 实际还款金额 (`repayment_amount`): 十进制数。
- 剩余本金 (`remaining_principal`): 十进制数。
- 已还利息 (`repaid_interest`): 十进制数。
- 还款状态 (`repayment_flag`): 字符串。

```mysql
CREATE TABLE repayment_record
(
    record_id           INT AUTO_INCREMENT PRIMARY KEY,
    user_id             INT,
    application_id      INT,
    repayment_date      DATETIME,
    repayment_amount    DECIMAL(10, 2),
    remaining_principal DECIMAL(10, 2),
    repaid_interest     DECIMAL(10, 2),
    repayment_flag      VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (application_id) REFERENCES loan_application(application_id)
);
```

6. **用户信用评分表（Credit_Score）**

- 信用评分ID (`score_id`): 主键。
- 用户ID (`user_id`): 外键关联到用户表的用户ID。
- 信用评分 (`credit_score`): 整数。

| Field        | Type | Null | Key  | Default | Extra          |
| ------------ | ---- | ---- | ---- | ------- | -------------- |
| score_id     | INT  | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id      | INT  | NO   | MUL  | NULL    |                |
| credit_score | INT  | NO   |      | NULL    |                |

```mysql
CREATE TABLE credit_score
(
    score_id    INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    credit_score INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);
```

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

```mysql
CREATE TABLE interest_rate
(
    rate_id       INT AUTO_INCREMENT PRIMARY KEY,
    product_type  VARCHAR(50),
    term          INT,
    interest_rate INT
);
```

