# apollo-sample
分布式配置中心apollo试用

## 简介
本工程主要用来尝试如何使用 `Apollo Client API` 来实现应用配置线上变更和发布。

[Apollo](https://github.com/ctripcorp/apollo) 是携程开发的开源分布式配置中心，它支持按环境、集群、应用、namespace等维度进行配置隔离，
配置变更的发布支持准实时级别推送到各个应用，并且支持配置回滚（通过版本号控制）。

## Apollo安装与部署
Apollo服务端需要在JAVA环境下运行，唯一依赖的外部组件是MySQL。
* JRE
版本需要是1.8+
* MySQL
版本需要是5.6.5+，方便起见，windows上推荐使用[WampServer](http://www.wampserver.com/en/)
WampServer安装完成后，登陆phpAdmin，初始用户名为root，初始密码为空。

* Apollo Server安装
方便起见，使用windows docker进行安装，docker不支持win10家庭版，为解决这个问题需要手动
启动 `hyper-v` 和手动修改注册表中的 `windows NT` 版本，具体方法可自行百度.
使用docker安装时无需单独安装JRE和MySQL.
  - 下载镜像启动脚本，脚本在源码仓库中，需要下载源码
  `wget https://github.com/ctripcorp/apollo.git`
  - 切换到目录 `\apollo\scripts\docker-quick-start`
  - 启动镜像，`docker-compose up`


## 网络设置
 从宿主机无法PING通docker容器内网IP, 需要添加路由打通网络，参考 [docker for windows 容器内网通过独立IP直接访问的方法](https://www.cnblogs.com/brock0624/p/9788710.html)
 `route -p add 172.18.0.0 mask 255.255.255.0 10.0.75.1`


## 进入docker镜像
* 查看镜像ID `docker ps`
```bash
bash-4.3# exit
PS C:\Users\Administrator> docker ps
CONTAINER ID        IMAGE                          COMMAND                  CREATED             STATUS              PORTS                                            NAMES
84e9e122b914        nobodyiam/apollo-quick-start   "/apollo-quick-start…"   4 days ago          Up 31 minutes       0.0.0.0:8070->8070/tcp, 0.0.0.0:8080->8080/tcp   apollo-quick-start
ce4ae0405ef8        mysql:5.7                      "docker-entrypoint.s…"   4 days ago          Up 31 minutes       33060/tcp, 0.0.0.0:13306->3306/tcp               apollo-db
PS C:\Users\Administrator>
```
* 进入镜像shell，`docker exec -it 84e9e122b914 /bin/bash`

## 使用客户端API开发应用
以下为几个关键配置
1. 应用ID
2. meta-server地址
3. 集群名

对于一个spring boot应用来说，需要如下配置：
1. `pom` 中加入依赖:
```xml
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-client</artifactId>
    <version>1.1.0</version>
</dependency>
```

2. 配置app id:
该ID对应Apollo配置中如下截图的App ID:
> ![Apollo](./static/s1.png)

应用用有多种方式可以配置该参数，本应用采用如下方式：
  * 在 `resources\META-INF` 中新建 `app.properties`
  * 在 `app.properties` 新建配置项 `app.id=apollo-sample`

3. 配置 `meta server`:
在 `application.properties` 中新建配置项 `apollo.meta=http://localhost:8080`
> 注意：**8080** 端口被本地apollo server占用，需要修改应用端口，如 `server.port=9090`

3. 配置集群cluster:
  集群需要与Apollo中使用的集群保持一致，Apollo中没有自定义集群的话使用默认default集群；
在 `application.properties` 中新建配置项 `apollo.cluster=GUANGZHOU`

4. 环境env配置:
  如果Apollo中使用 `dev` 环境，需要配置应用的vm启动参数 `-Denv=dev`