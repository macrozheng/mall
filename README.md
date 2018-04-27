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
JTA事务处理 | ✔
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

###### 添加商品(完成)
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

###### 修改商品(完成)
- 根据商品id查询商品信息
- 查询商品基本信息：商品分类名称、品牌名称、运费模版名称
- 查询商品促销信息：商品的会员价格、阶梯价格、满减价格
- 查询商品属性信息：商品属性类别名称、sku库存信息、属性分类对应规格和参数值
- 查询商品关联信息：商品关联专题和关联优选
- 修改商品信息：商品属性分类及规格不可修改，只支持单个sku的修改、删除、新增；商品属性分类及规格可以修改：修改后同时显示原sku库存及属性分类

###### 商品分页查询
- 商品的状态：全部商品、已上架、未上架、待审核、未通过 (publishStatus verifyStatus)
- 商品名称(%name%)
- 商品货号(productSn)
- 商品分类id(productCategoryId)
- 商品品牌id(brandId)
- 批量操作：上下架、推荐、新品、转移分类、放入回收站、审核
- 查看记录：审核记录，操作日志
- sku:根据产品及sku编号获取sku信息，批量修改sku信息
#### 促销管理

#### 内容管理

#### 用户管理

#### 订单管理
