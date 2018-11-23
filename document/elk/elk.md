# windows下的elk环境搭建

## 下载
- 下载页面：https://www.elastic.co/downloads/past-releases
- ElasticSearch:https://www.elastic.co/downloads/past-releases/elasticsearch-2-4-6
- Logstash:https://www.elastic.co/downloads/past-releases/logstash-2-4-0
- Kibana:https://www.elastic.co/downloads/past-releases/kibana-4-6-0

## 安装
下载zip包并进行解压

## 运行

### logstash配置运行
- 添加logstash配置文件：logstash-springboot.conf
- 安装logstash-codec-json_lines插件：plugin install logstash-codec-json_lines
- 运行logstash命令：logstash -f logstash-springboot.conf

## SpringBoot整合logstash
- 引入依赖包：https://github.com/logstash/logstash-logback-encoder
- 添加配置文件：logback-spring.xml
