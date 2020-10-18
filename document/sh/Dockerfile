# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD mall-admin-1.0-SNAPSHOT.jar /mall-admin-1.0-SNAPSHOT.jar
# 声明服务运行在8088端口
EXPOSE 8080
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/mall-admin-1.0-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER macro