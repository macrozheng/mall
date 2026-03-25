#!/bin/bash

# Spring Boot 运行脚本
# 设置JAVA_HOME为JDK 17
export JAVA_HOME=/Users/chijing/javatool/jdk-17.0.3/Contents/Home

echo "使用JDK版本: $("$JAVA_HOME/bin/java" -version 2>&1 | head -1)"
echo "开始启动Spring Boot应用..."

# 执行Maven命令：先clean和install，然后启动应用
"/Users/chijing/javatool/maven-3.6.3-jdk17/maven-3.6.3/bin/mvn" clean install -pl mall-admin -am \
  -DskipTests \
  -Ddocker.skip=true \
  -Dmaven.compiler.source=17 \
  -Dmaven.compiler.target=17

echo "构建完成，开始启动应用..."

# 启动Spring Boot应用
"/Users/chijing/javatool/maven-3.6.3-jdk17/maven-3.6.3/bin/mvn" -pl mall-admin spring-boot:run \
  -Dfail-on-error=true \
  -DskipTests \
  -Dmaven.compiler.source=17 \
  -Dmaven.compiler.target=17 \
  -Dspring-boot.run.main-class=com.macro.mall.MallAdminApplication
