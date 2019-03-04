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