# 发表情后台

## 注意(非常重要)
由于避免隐私泄露，因此将application.yml重命名为了application.example.yml，因此运行时需要将此文件重新改为application.yml,并修改以下信息
```yaml
spring:
  datasource:
    url: 你的数据库url
    username: 你的数据库用户名
    password: 你的数据库密码
```
## 介绍
本项目的数据来源于一个Scrapy爬虫项目,该项目的地址是<https://github.com/samoy/fabiaoqing>,数据库建表文件也在该项目中，地址为
<https://github.com/Samoy/fabiaoqing/blob/master/database.sql>

## 环境与框架
* Jdk1.8
* Mysql5.7
* Redis
* Maven
* Springboot

## 打包及运行

1. 打包
`mvn clean package -Dmaven.test.skip=true`
2. 运行
`java -jar xxxx.jar`或者后台运行`nohup java -jar xxx.jar &`

## API文档
<https://documenter.getpostman.com/view/1150845/SWEE1Ete?version=latest>
