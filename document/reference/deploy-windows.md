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
- 按照提示选择eclipse.exe的安装路径安装插件，完成后重启Eclipse
- 启动项目：右击com.macro.mall.MallAdminApplication的main方法，选择run as Java Application

## mysql

- 下载地址：https://dev.mysql.com/downloads/mysql/5.7.html#downloads
- 下载后按提示进行安装
- 导入document/sql下的mall.sql文件

## redis

- 下载地址：https://github.com/MicrosoftArchive/redis/releases
- 下载后按提示进行安装
- 启动redis:redis-server.exe redis.windows.conf

## elasticsearch

- 下载地址：https://www.elastic.co/downloads/past-releases/elasticsearch-6-2-2
- 下载.zip文件，解压到指定目录
- 安装kibana，用于在浏览器中访问es,请下载6.2.2版本，具体参考：https://www.elastic.co/downloads/kibana
- 下载.zip包后解压即可，运行bin\kibana.bat，访问http://localhost:5601 查看是否安装成功
- 中文分词插件地址：https://github.com/medcl/elasticsearch-analysis-ik
- 安装中文分词插件，在elasticsearch-6.2.2\bin目录下执行以下命令：
elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.2.2/elasticsearch-analysis-ik-6.2.2.zip
- 启动elasticsearch:运行elasticsearch-6.2.2\bin\elasticsearch.bat
- 不使用kibana的可以安装head插件，具体参考：https://github.com/mobz/elasticsearch-head
- 注意：如果你修改了mall-search中的es的cluster-name: mall-es，你需要在elasticsearch-6.2.2\config\elasticsearch.yml文件中修改cluster.name: mall-es

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

- 安装Erlang：http://erlang.org/download/otp_win64_21.3.exe
- 下载rabbitmq：https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.14/rabbitmq-server-3.7.14.exe
- 按照提示进行安装，安装完成后进入rabbitmq的安装目录:D:\RabbitMQ Server\rabbitmq_server-3.7.14\sbin
- 在地址栏输入cmd并回车启动命令行输入以下命令：rabbitmq-plugins enable rabbitmq_management
- 访问地址查看是否安装成功：http://127.0.0.1:15672/
- 输入账号密码登录：guest guest
- 创建用户并设置其角色为管理员：mall mall
    ![rabbitmq_install_2.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_2.png)
- 创建virtual host:/mall
    ![rabbitmq_install_3.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_3.png)
- 给mall用户配置范围该virtual host的权限
    ![rabbitmq_install_4.png](https://github.com/macrozheng/mall/blob/master/document/resource/rabbitmq_install_4.png)
- rabbitmq安装延迟消息插件（可不装）：
    - 下载延迟消息插件（rabbitmq_delayed_message_exchange）：https://www.rabbitmq.com/community-plugins.html
    - 复制插件到插件目录：D:\RabbitMQ Server\rabbitmq_server-3.7.14\plugins
    - 在sbin目录下运行如下命令启用插件：rabbitmq-plugins enable rabbitmq_delayed_message_exchange
    
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
