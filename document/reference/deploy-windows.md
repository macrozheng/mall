# windows下环境搭建

## IDEA

- 关于IDEA的安装与使用具体参考[https://github.com/judasn/IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)
- 搜索插件仓库，安装插件lombok

## Eclipse

- 导入项目，以maven项目形式导入  
    ![eclipse_import_1.png](https://github.com/macrozheng/mall/blob/master/document/resource/eclipse_import_1.png)  
    ![eclipse_import_2.png](https://github.com/macrozheng/mall/blob/master/document/resource/eclipse_import_2.png)
- 安装lombok插件，下载地址：https://projectlombok.org/downloads/lombok.jar  
- 下载完后双击，使用java程序打开
- 按照提示选择eclipe.exe的安装路径安装插件，完成后重启Eclipse
- 启动项目：右击com.macro.mall.MallAdminApplication的main方法，选择run as Java Application

## mysql

- 下载地址：https://dev.mysql.com/downloads/mysql/5.7.html#downloads
- 下载后按提示进行安装
- 导入document/sql下的mall.sql文件

## redis

- 下载地址：https://github.com/MicrosoftArchive/redis/releases
- 下载后按提示进行安装

## elasticsearch

- 下载地址：https://www.elastic.co/downloads/past-releases/elasticsearch-2-4-6
- 下载.zip文件，解压到指定目录
- 安装head插件，在elasticsearch-2.4.6\bin目录下执行以下命令：plugin install mobz/elasticsearch-head
- 安装中文分词插件：https://github.com/medcl/elasticsearch-analysis-ik
- 下载地址：https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v1.10.6/elasticsearch-analysis-ik-1.10.6.zip
- 解压并拷贝到elasticsearch-2.4.6\plugins目录下
- 启动elasticsearch:运行elasticsearch-2.4.6\bin\elasticsearch.bat
- 打开该地址测试是否安装成功：http://localhost:9200/_plugin/head/

## mongodb

- 下载地址：https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-ssl-3.2.21-signed.msi
- 选择路径（c:\mongodb\）进行安装，在安装路径下创建data\db和data\log两个文件夹
- 服务端运行程序：mongodb\bin\mongod.exe
- 客户端运行程序：mongodb\bin\mongo.exe
- 创建配置文件：mongodb\mongod.cfg
    ``` lua
    systemLog:
        destination: file
        path: c:\mongodb\data\log\mongod.log
    storage:
        dbPath: c:\mongodb\data\db
    ```
- 安装为服务（运行命令需要用管理员权限）：C:\mongodb\bin\mongod.exe --config "C:\mongodb\mongod.cfg" --install
- 启动服务：net start MongoDB
- 关闭服务：net stop MongoDB
- 移除服务：C:\mongodb\bin\mongod.exe --remove
- 下载客户端程序：https://download.robomongo.org/1.2.1/windows/robo3t-1.2.1-windows-x86_64-3e50a65.zip
- 解压到指定目录，打开robo3t.exe并连接到localhost:27017

## rabbitmq

- 安装Erlang：http://www.erlang.org/download/otp_win64_17.3.exe
- 下载rabbitmq:http://www.rabbitmq.com/releases/rabbitmq-server/v3.4.1/rabbitmq-server-3.4.1.exe
- 按照提示进行安装，安装完成后左下角搜索rabbitmq，点击如下命令进行安装  
    ![rabbitmq_install_1.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_1.png)
- 输入命令启用管理工具：rabbitmq-plugins enable rabbitmq_management
- 访问地址查看是否安装成功：http://127.0.0.1:15672/
- 输入账号密码登录：guest guest
- 创建用户并设置其角色为管理员：mall mall
    ![rabbitmq_install_2.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_2.png)
- 创建virtual host:/mall
    ![rabbitmq_install_3.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_3.png)
- 给mall用户配置范围该virtual host的权限
    ![rabbitmq_install_4.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_4.png)
    
## OSS

- 该项目文件上传采用OSS，需要自行注册OSS账号并配置
- 首先将mall-admin\src\main\resources\application.properties文件中以aliyun.oss.开头的配置改为你自己的配置
- OSS上传文件需要配置跨域资源共享(CORS)规则，参考文档：https://help.aliyun.com/document_detail/31928.html
- 上传方式采用服务端签名后直传的形式，参考文档：https://help.aliyun.com/document_detail/31926.html

## mall-admin

- 启动项目：直接运行com.macro.mall.MallAdminApplication的main方法即可
- 接口文档地址：http://localhost:8080/swagger-ui.html

## mall-search

- 启动项目：直接运行com.macro.mall.search.MallSearchApplication的main方法即可
- 接口文档地址：http://localhost:8081/swagger-ui.html
- 使用前需要先调用接口导入数据；http://localhost:8081/esProduct/importAll
- 如出现无法启动的问题，可以先删除elasticsearch里面的数据再启动

## mall-portal

- 启动mall-portal项目：直接运行com.macro.mall.portal.MallPortalApplication的main方法即可
- 接口文档地址：http://localhost:8085/swagger-ui.html
