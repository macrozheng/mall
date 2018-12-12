# windows下环境安装

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

## mongo

## rabbitmq

## mall-admin

- 启动项目：直接运行com.macro.mall.MallAdminApplication的main方法即可
- 接口文档地址：http://localhost:8080/swagger-ui.html

## mall-search

- 启动项目：直接运行com.macro.mall.search.MallSearchApplication的main方法即可
- 接口文档地址：http://localhost:8081/swagger-ui.html
- 使用前需要先调用接口导入数据；http://localhost:8081/esProduct/importAll
- 如出现无法启动的问题，可以先删除elasticsearch里面的数据再启动

## mall-portal
