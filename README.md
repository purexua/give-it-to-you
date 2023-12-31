![](./image/logo-plus.png)

公司：purehub

项目名：给你呗-网络小额贷款平台

logo：![give-it-to-give-it-to-you](./image/logo-min-min.png)

## 关于项目功能

- [x] issue - 1: 用户的登录
- [x] issue - 2: 用户注册
- [x] issue - 3: 管理员登录
- [x] issue - 4: 用户信息修改
- [x] issue - 5: 用户密码修改
- [x] issue - 6: 用户钱包充值
- [x] issue - 7: 贷款申请 - 产品
- [ ] issue - 8: 贷款申请 - 个性化
- [x] issue - 9: 用户信用信息查询
- [ ] issue - 10: 用户还款
- [ ] issue - 11:用户查询还款记录
- [ ] issue - 12:用户查询贷款申请记录
- [ ] issue - 13:管理员新增贷款产品
- [x] issue - 14:管理员审批贷款申请
- [ ] ...

## 接口

### 用户登录

url: `http://localhost:3919/login`

method: `GET`

type: `params`

|  参数名  | 参数含义 | 类型/必须 |       备注       |
| :------: | :------: | :-------: | :--------------: |
| userName |  用户名  | String/Y  | 按用户名查询用户 |

### 用户注册

url: `http://localhost:3919/register`

method: `POST`

type: `JSON`

|   参数名    |   参数含义    | 类型/必须 |       备注       |
| :---------: | :-----------: | :-------: | :--------------: |
|  userName   |    用户名     | String/Y  | 按用户名查询用户 |
|  password   |     密码      | String/Y  |                  |
|  realName   | 用户真实姓名  | String/Y  |                  |
|   idCard    |   身份证号    |  String   |   可选择性传参   |
|    email    |     邮箱      |  String   |   可选择性传参   |
|   address   |     地址      |  String   |   可选择性传参   |
| phoneNumber | 电话/联系方式 |  String   |   可选择性传参   |

### 获取用户信息

url: `http://localhost:3919/user/info`

method: `GET`

type: `params`

| 参数名 | 参数含义 | 类型/必须 |          备注          |
| :----: | :------: | :-------: | :--------------------: |
| userId | 用户 ID  | Integer/Y | 按用户 ID 查询用户信息 |

### 用户修改密码

url: `http://localhost:3919/user/change/password`

method: `PUT`

type: `params`

|  参数名  | 参数含义 | 类型/必须 |          备注          |
| :------: | :------: | :-------: | :--------------------: |
|  userId  | 用户 ID  | Integer/Y | 按用户 ID 查询用户信息 |
| password |   密码   | String/Y  |         新密码         |

### 用户修改个人信息

url: `http://localhost:3919/user/change/info`

method: `PUT`

type: `JSON`

|   参数名    |   参数含义    | 类型/必须 |     备注     |
| :---------: | :-----------: | :-------: | :----------: |
|  realName   | 用户真实姓名  |  String   | 可选择性传参 |
|   idCard    |   身份证号    |  String   | 可选择性传参 |
|    email    |     邮箱      |  String   | 可选择性传参 |
|   address   |     地址      |  String   | 可选择性传参 |
| phoneNumber | 电话/联系方式 |  String   | 可选择性传参 |

### 用户钱包充值

url: `http://localhost:3919/user/recharge`

method: `PUT`

type: `params`

|    参数名    | 参数含义 | 类型/必须 |          备注          |
| :----------: | :------: | :-------: | :--------------------: |
|    userId    | 用户 ID  | Integer/Y | 按用户 ID 查询用户信息 |
| walletAmount |   金额   | String/Y  |        充值金额        |

### 用户贷款申请 - 产品

url: `http://localhost:3919/application/product`

method: `POST`

type: `JSON`

|     参数名      |   参数含义   |  类型/必须   |     备注      |
| :-------------: | :----------: | :----------: | :-----------: |
|     userId      |   用户 ID    |  Integer/Y   | 贷款申请人 ID |
|   productType   |   产品类型   |   String/Y   |               |
|      term       |   贷款期限   |  Integer/Y   |  单位（天）   |
| requestedAmount | 请求贷款金额 | Decimal(8,2) |               |
|  interestRate   |   贷款利率   | Decimal(5,2) |               |

### 用户贷款申请 - 产品

url: `http://localhost:3919/application/personal`

method: `POST`

type: `JSON`

|     参数名      |   参数含义   |  类型/必须   |     备注      |
| :-------------: | :----------: | :----------: | :-----------: |
|     userId      |   用户 ID    |  Integer/Y   | 贷款申请人 ID |
|   productType   |   产品类型   |   String/Y   |               |
|      term       |   贷款期限   |  Integer/Y   |  单位（天）   |
| requestedAmount | 请求贷款金额 | Decimal(8,2) |               |
|  interestRate   |   贷款利率   | Decimal(5,2) |               |

## 数据库

### 用户表（User）

| Field         | explain  | Type           | Null | Key  | Default           | Extra             |
| ------------- | -------- | -------------- | ---- | ---- | ----------------- | ----------------- |
| user_id       | 用户ID   | INT            | NO   | PRI  | NULL              | AUTO_INCREMENT    |
| user_name     | 用户名   | VARCHAR(255)   | NO   |      | NULL              |                   |
| password      | 密码     | VARCHAR(255)   | NO   |      | NULL              |                   |
| real_name     | 真实姓名 | VARCHAR(255)   | NO   |      | NULL              |                   |
| id_card       | 身份证号 | VARCHAR(255)   |      |      | NULL              |                   |
| email         | 电子邮件 | VARCHAR(100)   | YES  |      | NULL              |                   |
| address       | 地址     | VARCHAR(255)   | YES  |      | NULL              |                   |
| phone_number  | 电话号码 | VARCHAR(20)    | YES  |      | NULL              |                   |
| health_status | 健康状况 | VARCHAR(20)    |      |      | 'Good'            |                   |
| credit_status | 信用状况 | VARCHAR(20)    |      |      | 'Fair'            |                   |
| role          | 角色     | VARCHAR(20)    | NO   |      | 'Client'          | 'Admin', 'Client' |
| wallet        | 钱包余额 | DECIMAL(10, 2) | NO   |      | 0                 |                   |
| register_time | 注册时间 | TIMESTAMP      |      |      | CURRENT_TIMESTAMP |                   |
| deleted       | 删除标识 | INT            |      |      | 0                 |                   |
| version       | 版本号   | INT            |      |      | 1                 |                   |

### 产品利率表 (Product_Interest_Rate)

| Field               | explain  | Type           | Null | Key  | Default                            | Extra          |
| ------------------- | -------- | -------------- | ---- | ---- | ---------------------------------- | -------------- |
| rate_id             | 利率ID   | INT            | NO   | PRI  | NULL                               | AUTO_INCREMENT |
| product_type        | 产品类型 | VARCHAR(50)    |      |      | 'Personal Loan'                    |                |
| term                | 期限     | INT            |      |      | NULL                               |                |
| interest_rate       | 利率     | DECIMAL(5, 2)  |      |      | NULL                               |                |
| loan_amount         | 贷款金额 | DECIMAL(10, 2) |      |      | NULL                               |                |
| product_description | 产品描述 | VARCHAR(255)   |      |      | 'No product description available' |                |

### 贷款申请表 (Loan_Application)

| Field            | explain  | Type           | Null | Key  | Default           | Extra                             |
| ---------------- | -------- | -------------- | ---- | ---- | ----------------- | --------------------------------- |
| application_id   | 申请ID   | INT            | NO   | PRI  | NULL              | AUTO_INCREMENT                    |
| user_id          | 用户ID   | INT            |      | MUL  | NULL              |                                   |
| loan_type        | 贷款类型 | VARCHAR(255)   |      |      | NULL              |                                   |
| term             | 期限     | INT            |      |      | NULL              |                                   |
| requested_amount | 申请金额 | DECIMAL(10, 2) |      |      | NULL              |                                   |
| interest_rate    | 利率     | DECIMAL(5, 2)  |      |      | NULL              |                                   |
| application_time | 申请时间 | TIMESTAMP      |      |      | CURRENT_TIMESTAMP |                                   |
| approval_time    | 审批时间 | TIMESTAMP      |      |      | NULL              |                                   |
| status           | 状态     | VARCHAR(50)    |      |      | 'Pending'         | 'Pending', 'Approved', 'Rejected' |

### 还款计划表 (Repayment_Plan)

| Field           | explain      | Type           | Null | Key  | Default           | Extra          |
| --------------- | ------------ | -------------- | ---- | ---- | ----------------- | -------------- |
| plan_id         | 计划ID       | INT            | NO   | PRI  | NULL              | AUTO_INCREMENT |
| user_id         | 用户ID       | INT            |      | MUL  | NULL              |                |
| application_id  | 申请ID       | INT            |      | MUL  | NULL              |                |
| term            | 总期数       | INT            |      |      | NULL              |                |
| current_term    | 当前期数     | INT            |      |      | 1                 |                |
| due_amount      | 应还金额     | DECIMAL(10, 2) |      |      | NULL              |                |
| total_amount    | 总金额       | DECIMAL(10, 2) |      |      | NULL              |                |
| plan_start_time | 计划开始时间 | TIMESTAMP      |      |      | CURRENT_TIMESTAMP |                |
| is_paid         | 是否已还款   | INT            |      |      | 0                 |                |

### 还款记录表 (Repayment_Record)

| Field          | explain  | Type           | Null | Key  | Default  | Extra                        |
| -------------- | -------- | -------------- | ---- | ---- | -------- | ---------------------------- |
| record_id      | 记录ID   | INT            | NO   | PRI  | NULL     | AUTO_INCREMENT               |
| user_id        | 用户ID   | INT            |      | MUL  | NULL     |                              |
| plan_id        | 计划ID   | INT            |      | MUL  | NULL     |                              |
| payment_time   | 还款时间 | TIMESTAMP      |      |      | NULL     |                              |
| payment_amount | 还款金额 | DECIMAL(10, 2) |      |      | NULL     |                              |
| payment_method | 还款方式 | VARCHAR(50)    |      |      | 'Wallet' | 'Wallet', 'Alipay', 'Wechat' |

### 用户信用评分表 (Credit_Score)

| Field              | explain    | Type           | Null | Key  | Default    | Extra                               |
| ------------------ | ---------- | -------------- | ---- | ---- | ---------- | ----------------------------------- |
| score_id           | 信用评分ID | INT            | NO   | PRI  | NULL       | AUTO_INCREMENT                      |
| user_id            | 用户ID     | INT            |      | MUL  | NULL       |                                     |
| credit_score       | 信用分数   | INT            |      |      | 500        |                                     |
| limit_amount       | 信用额度   | DECIMAL(10, 2) |      |      | 10000      |                                     |
| monthly_income     | 月收入     | DECIMAL(10, 2) |      |      | 0          |                                     |
| expenses           | 月支出     | DECIMAL(10, 2) |      |      | 0          |                                     |
| employment_status  | 就业状况   | VARCHAR(50)    |      |      | 'Employed' | 'Employed', 'Unemployed', 'Retired' |
| job_duration       | 工作时长   | INT            |      |      | 0          |                                     |
| education_level    | 教育水平   | VARCHAR(50)    |      |      | 'Bachelor' | 'Bachelor', 'Master', 'Doctor'      |
| overdue_records    | 逾期记录数 | INT            |      |      | 0          |                                     |
| assets_value       | 资产价值   | DECIMAL(15, 2) |      |      | 0          |                                     |
| social_connections | 社会关系   | INT            |      |      | 0          |                                     |
| legal_disputes     | 法律纠纷数 | INT            |      |      | 0          |                                     |
| behavior_analysis  | 行为分析   | VARCHAR(255)   |      |      | 'Stable'   | 'Stable', 'Unstable'                |

### 系统日志表 (System_Log)

| Field    | explain  | Type         | Null | Key  | Default | Extra          |
| -------- | -------- | ------------ | ---- | ---- | ------- | -------------- |
| log_id   | 日志ID   | INT          | NO   | PRI  | NULL    | AUTO_INCREMENT |
| user_id  | 用户ID   | INT          |      | MUL  | NULL    |                |
| log_date | 日志日期 | TIMESTAMP    |      |      | NULL    |                |
| action   | 操作     | VARCHAR(255) |      |      | NULL    |                |