# Linux常用命令

## 系统服务管理

### systemctl

- 启动服务：systemctl start httpd.service
- 关闭服务：systemctl stop httpd.service
- 重启服务（不管是否在运行）：systemctl restart httpd.service
- 重新载入配置（不中断服务）：systemctl reload httpd.service
- 查看运行状态：systemctl status httpd.service
- 设置开机启动：systemctl enable httpd.service
- 禁止开机启动：systemctl disable httpd.service
- 查看系统安装的服务：systemctl list-units --type=service

## 文件管理

### ls
列出/home目录下的子目录：ls -l /home
列出当前文件夹下所有文件夹及文件大小：ls -lht 

### pwd
显示当前工作目录

### cd
切换目录： cd /usr/local

### date
以指定格式显示日期；date '+date:%x time:%X'

### passwd
修改root密码：passwd root

### su
普通用户切换到超级用户：su -

### clear
清除屏幕信息

### man
查看ls命令的帮助信息：man ls

### who
- 查看当前运行级别：who -r
- 显示用的登录详情：who -buT

### free
以MB显示内存使用状态：free -m

### ps
查看系统所有进程：ps -ef
查看运行的java进程： ps -ef | grep java

### top
查看系统当前活跃进程信息

### mkdir
创建目录

### more 

分页查看  
每10行显示一屏查看：more -c -10 

### cat
查看config文件：cat -Ab config

### rm
- 删除文件：rm a.txt
- 删除文件夹： rm -rf a/

### touch
创建一个文件：touch a.txt

### cp
将目录a的文件拷贝到目录b: cp -r /home/a /home/b

### mv
移动或覆盖文件：mv a.txt b.txt

## 压缩与解压

### tar
- 打包文件夹到单独的文件：tar -cvf /opt/etc.tar /etc
- 压缩文件夹到压缩文件（gzip）：tar -zcvf /opt/etc.tar.gz /etc
- 压缩文件夹到压缩文件（bzip2）：tar -jcvf /opt/etc.tar.bz2 /etc
- 查阅压缩包中内容（gzip）：tar -ztvf /opt/etc.tar.gz /etc
- 解压文件到当前目录（gzip）：tar -zxvf /opt/etc.tar.gz

## 磁盘和网络管理

### df
查看磁盘占用情况：df -hT

### ifconfig
查看当前网络接口状态

### netstat

- 查看路由信息：netstat -rn
- 查看所有有效TCP连接：netstat -an
- 查看系统中启动的监听服务：netstat -tulnp
- 查看处于连接状态的系统资源信息：netstat -atunp

### wget
从网络上下载软件

## 软件的安装与管理

### rpm

- 安装软件包：rpm -ivh nginx-1.12.2-2.el7.x86_64.rpm
- 模糊搜索软件包：rpm -qa | grep nginx
- 精确查找软件包：rpm -qa nginx
- 查询软件包的安装路径：rpm -ql nginx-1.12.2-2.el7.x86_64
- 查看软件包的概要信息：rpm -qi nginx-1.12.2-2.el7.x86_64
- 验证软件包内容和安装文件是否一致：rpm -V nginx-1.12.2-2.el7.x86_64
- 更新软件包：rpm -Uvh nginx-1.12.2-2.el7.x86_64
- 删除软件包：rpm -e nginx-1.12.2-2.el7.x86_64

### yum

- 安装软件包： yum install nginx
- 检查可以更新的软件包：yum check-update
- 更新指定的软件包：yum update nginx
- 在资源库中查找软件包信息：yum info nginx*
- 列出已经安装的所有软件包：yum info installed
- 列出软件包名称：yum list redis*
- 模糊搜索软件包：yum search redis

## 网络安全

### iptables

- 开启防火墙：systemctl start iptables.service
- 关闭防火墙：systemctl stop iptables.service
- 查看防火墙状态：systemctl status iptables.service
- 设置开机启动：systemctl enable iptables.service
- 禁用开机启动：systemctl disable iptables.service
- 查看filter表的链信息：iptables -L -n
- 查看NAT表的链信息：iptables -t nat -L -n
- 清除防火墙所有规则：iptables -F;iptables -X;iptables -Z;
- 添加过滤规则（开发80端口）：iptables -I INPUT -p tcp --dport 80 -j ACCEPT
- 查找规则所做行号：iptables -L INPUT --line-numbers -n
- 根据行号删除过滤规则：iptables -D INPUT 1



