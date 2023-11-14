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

1. user表

```
CREATE TABLE user
(
    user_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_name    VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    real_name    VARCHAR(255) NOT NULL,
    id_cart      VARCHAR(255) NOT NULL,
    email        VARCHAR(100),
    address      VARCHAR(255),
    phone_number VARCHAR(20),
    balance DECIMAL(10, 2) NOT NULL
);
```

2. 
