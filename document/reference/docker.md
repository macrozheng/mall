# Docker入门手册

## Docker 镜像常用命令
### 搜索镜像
docker search java
### 下载镜像
- docker pull java:8
- docker pull macro/eureka-server:0.0.1
### 列出镜像
docker images
### 删除镜像
- docker rmi java
- docker rmi -f java 
- docker rmi -f $(docker images)

## Docker 容器常用命令
### 新建并启动容器
docker run -d -p 91:80 nginx
### 列出容器
docker ps
### 停止容器
docker stop $ContainerId
### 强制停止容器
docker kill $ContainerId
### 启动已停止的容器
docker start $ContainerId
### 进入容器
- docker inspect --format "{{.State.Pid}}" $ContainerId
- nsenter --target "$pid" --mount --uts --ipc --net --pid
### 删除容器
- docker rm $ContainerId
- docker rm -f $(docker ps -a -q)
### 查看启动错误日志
docker logs $ContainerIdName(或者$ContainerId)
### 查看容器的IP地址（172.17.0.*）
docker inspect --format '{{ .NetworkSettings.IPAddress }}' $ContainerId
### 同步宿主机时间到容器
docker cp /etc/localtime $ContainerName:/etc/
### 在宿主机查看docker使用cpu、内存、网络、io情况
- 查看指定容器情况：docker stats $ContainerName
- 查看所有容器情况：docker stats -a
### 进入docker内部的bash
docker exec -it $ContainerName /bin/bash

## Docker Registry
### Docker Registry 2.0搭建
docker run -d -p 5000:5000 --restart=always --name registry2 registry:2
### 推送到私有仓库
docker push localhost:5000/macro/eureka-server:0.0.1
### 修改镜像标签
docker tag macro/eureka-server:0.0.1 localhost:5000/macro/eureka-server:0.0.1

## 使用maven构建Docker镜像
### 构建镜像
- command：mvn clean package docker:build
- tip：
    Linux服务器需要开启远程api:vi /usr/lib/systemd/system/docker.service
    修改为：ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock   
### 推送镜像到私有仓库
- command：mvn clean package docker:build -DpushImage
- tip：
    pom.xml修改<imageName>192.168.1.71:5000/macro/${project.artifactId}:${project.version}</imageName>
- tip：
    docker要支持http:echo '{ "insecure-registries":["39.98.190.128:5000"] }' > /etc/docker/daemon.json 
### 修改Docker镜像存放位置
1. 查看Docker的存放位置：docker info | grep "Docker Root Dir"（默认为/var/lib/docker）
2. 关闭Docker服务：systemctl stop docker
3. 移动目录到目标路径：mv /var/lib/docker /root/data/docker
4. 建立软连接：ln -s /root/data/docker /var/lib/docker

## Docker compose
### 安装
1. 下载地址：https://github.com/docker/compose/releases
2. 安装地址：/usr/local/bin/docker-compose
3. 设置为可执行：sudo chmod +x /usr/local/bin/docker-compose
4. 测试是否安装成功：docker-compose --version

### 安装命令补全工具
sudo curl -L https://raw.githubusercontent.com/docker/compose/1.22.0/contrib/completion/bash/docker-compose -o /etc/bash_completion.d/docker-compose

### 常用命令
- 构建、创建、启动相关容器：docker-compose up
- 列出所有容器：docker-compose ps
- 删除指定服务的容器：docker-compose rm eureka
- 对容器进行动态扩容：docker-compose scale eureka=3
- 停止相关容器：docker-compose stop eureka
- 启动相关容器：docker-compose start eureka

### 编排SpringCloud微服务
#### 所使用到的工程
- eureka-server
- hello-service
- feign-consumer
- api-gateway
#### 编排模式
1. 编排SpringCloud微服务：见eureka-server/docker-res/docker-compose.yml
2. 简化SpringCloud微服务编排：见eureka-server/docker-res/docker-compose-simple.yml
3. 编排高可用的注册中心：见eureka-server/docker-res/docker-compose-eureka.yml