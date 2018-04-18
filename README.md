# mall

## 技术选型

### 后端技术
技术 | 名称 
----|----
Spring Boot | 容器+MVC框架
Spring Security | 认证和授权框架
MyBatis | ORM框架  
MyBatisGenerator | 代码生成  
PageHelper | MyBatis物理分页插件  
Thymeleaf | 模板引擎   
Swagger-UI | 文档生产工具
Hibernator-Validator | 验证框架
Vue | 前端框架
AdminLte | 前端模版

### 框架搭建
功能 | 完成 
----|----
集成MyBatis | ✔
集成MyBatisGenerator | ✔
集成SpringSecurity | ✔
集成Swagger-UI | ✔
集成Hibernator-Validator | ✔
对通用返回结果进行封装 | ✔
crud操作demo | ✔
添加分页查询功能 | ✔
集成日志功能 | ✔
集成监控功能 | ✔
包结构调整 | ✔
SpringSecurity登录改为Restful形式 |
SpringAOP通用日志处理 | 

### 功能完善

#### 后台登录功能

#### 商品管理

##### 商品属性分类管理

- 添加商品属性分类（名称）
- 分页查询全部商品属性分类
- 删除单个商品属性分类
- 修改单个属性分类名称
- 查询单个属性分类信息

##### 商品属性管理

- 根据分类查询属性列表或参数列表（分页，支持类型）
- 添加商品属性
- 查询单个商品属性
- 编辑商品属性
- 批量删除商品属性
- 分页查询全部商品属性

#### 促销管理

#### 内容管理

#### 用户管理

#### 订单管理
