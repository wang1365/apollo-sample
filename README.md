# apollo-sample
分布式配置中心apollo试用

## 简介
本工程主要用来尝试如何使用 `Apollo Client API` 来实现应用配置线上变更和发布。

[Apollo](https://github.com/ctripcorp/apollo) 是携程开发的开源分布式配置中心，它支持按环境、集群、应用、namespace等维度进行配置隔离，
配置变更的发布支持准实时级别推送到各个应用，并且支持配置回滚（通过版本号控制）。

## Apollo安装与部署
Apollo服务端需要在JAVA环境下运行，唯一以来的外部组件是MySQL。
* JRE
版本需要是1.8+
* MySQL
版本需要是5.6.5+，方便起见，windows上推荐使用[WampServer](http://www.wampserver.com/en/)
WampServer安装完成后，登陆phpAdmin，初始用户名为root，初始密码为空。

* Apollo Server安装
  - 下载 [Server](https://github.com/nobodyiam/apollo-build-scripts), `git checkout`或者https下载都可以
  - 数据库初始化
    1. 执行下载后的SQL脚本，`apollo-build-scripts-master\sql\apolloportaldb.sql`,
  执行完成后，使用
  ```SQL
  select `Id`, `AppId`, `Name` from ApolloPortalDB.App
  ```
    进行验证，结果应为有一条为 `SampleApp` 的记录。
    2. 执行 `apollo-build-scripts-master\sql\apolloportaldb.sql`，使用
  ```SQL
  select `NamespaceId`, `Key`, `Value`, `Comment` from ApolloConfigDB.Item;
  ```
    进行验证，结果为 `1	timeout	100	sample timeout配置`
  - Apollo Server配置

 ## docker网络设置
 从宿主机无法PING通docker内网IP, 需要添加路由打通网络，参考 [docker for windows 容器内网通过独立IP直接访问的方法](https://www.cnblogs.com/brock0624/p/9788710.html)
 `route -p add 172.18.0.0 mask 255.255.255.0 10.0.75.1`

