## java实现的简易文件系统

### 介绍

JAVA 实现一个基于 HTTP 协议的简易文件服务器 Server 端和 Client 端

Client

使用maven构建，使用HttpURLConnection模拟访问Server文件的上传，下载，获取元数据的接口，并提供单元测试。第三方库仅使用了junit提供单元测试。

Server

采用springboot构建，mapper层xml文件使用mybatis-generator创建

此外还提供了lombok，mybatis，fastJson等依赖



## 接口说明

- 文件上传：http://localhost:8080/file/upload
- 文件下载：http://localhost:8080/file/download
- 获取元数据：http://localhost:8080/file/getData



### 日志

日志地址

- info日志：\server\log\info
- error日志：\server\log\error

日志配置文件

logback-spring.xml
