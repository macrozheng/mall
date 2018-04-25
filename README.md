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
Element | 前端模版

### 框架搭建
功能 | 完成 
----|----
集成MyBatis | ✔
集成MyBatisGenerator | ✔
集成SpringSecurity | ✔
集成Swagger-UI | ✔
集成Hibernator-Validator | ✔
集成日志功能 | ✔
集成监控功能 | ✔
crud操作demo | ✔
合理规划包结构 | ✔
SpringAOP通用日志处理 | ✔
SpringAOP通用验证失败结果返回 | ✔
CommonResult对通用返回结果进行封装 | ✔
SpringSecurity登录改为Restful形式 | ✔
JWT登录、注册、获取token | ✔
JTA事务处理 |
集成单元测试 | ✔

### 功能完善

#### 后台登录功能（完成）

- 后台用户注册功能
- 后台用户登录后获取token
- 刷新token功能

#### 商品管理

##### 商品属性分类管理(完成)

- 添加商品属性分类（名称）
- 分页查询全部商品属性分类
- 删除单个商品属性分类
- 修改单个属性分类名称
- 查询单个属性分类信息

##### 商品属性管理(完成)

- 根据分类查询属性列表或参数列表（分页，支持类型）
- 添加商品属性
- 查询单个商品属性
- 编辑商品属性
- 批量删除商品属性
- 分页查询全部商品属性

##### 商品管理

###### 添加商品
- 选择商品分类：根据商品分类id查找分类
- 选择品牌：查询全部品牌
- 选择运费模版：查询全部运费模版
- 设置会员价格：查询所有会员等级，传入List<PmsMemberPrice>
- 添加阶梯价格: 参数传入List<PmsProductLadder>
- 设置满减价格: 参数传入List<PmsProductFullReduction>
- 选择商品属性类别:获取所有商品属性分类，根据商品属性分类的id获取规格和参数(type=0->规格；type=1->参数)
- 选择规格并生成库存信息：前端实现
- 添加sku库存信息：参数传入List<PmsSkuStock>
- 设置属性图片：设置到pic和album_pics字段中去
- 添加商品参数：参数传入List<PmsProductAttributeValue>
- 添加自定义商品规格：参数传入List<PmsProductAttributeValue>
- 关联专题:参数传入List<CmsSubjectProductRelation>关系
- 关联优选:参数传入List<CmsPrefrenceAreaProductRelation>关系

#### 促销管理

#### 内容管理

#### 用户管理

#### 订单管理
