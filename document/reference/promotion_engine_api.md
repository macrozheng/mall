# 营销规则引擎接口文档

## 一、概述

本文档描述了营销规则引擎的所有API接口，包括营销活动管理、优惠计算、最优组合试算等功能。

### 基础信息

- **基础URL**: `/promotion/activity`
- **Content-Type**: `application/json`
- **认证方式**: Bearer Token (JWT)

---

## 二、营销活动管理接口

### 2.1 创建营销活动

**接口地址**: `POST /promotion/activity/create`

**请求参数**:

```json
{
  "name": "双11满减活动",
  "type": 1,
  "platform": 0,
  "startTime": "2024-11-01T00:00:00",
  "endTime": "2024-11-11T23:59:59",
  "status": 1,
  "priority": 10,
  "stackable": true,
  "exclusiveWith": "2,3",
  "useType": 0,
  "minOrderAmount": 100.00,
  "maxDiscountAmount": 500.00,
  "perLimit": 3,
  "totalLimit": 10000,
  "description": "双11全场满减活动",
  "rules": [
    {
      "ruleType": 1,
      "threshold": 100.00,
      "discountValue": 10.00,
      "discountType": 1,
      "sort": 1
    },
    {
      "ruleType": 1,
      "threshold": 200.00,
      "discountValue": 30.00,
      "discountType": 1,
      "sort": 2
    },
    {
      "ruleType": 1,
      "threshold": 500.00,
      "discountValue": 100.00,
      "discountType": 1,
      "sort": 3
    }
  ]
}
```

**参数说明**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | String | 是 | 活动名称 |
| type | Integer | 是 | 活动类型：1->满减；2->满折；3->第N件优惠；4->套餐价；5->SKU特价；6->会员专享 |
| platform | Integer | 否 | 使用平台：0->全部；1->移动；2->PC，默认0 |
| startTime | Date | 是 | 活动开始时间 |
| endTime | Date | 是 | 活动结束时间 |
| status | Integer | 否 | 状态：0->禁用；1->启用，默认1 |
| priority | Integer | 否 | 优先级，数值越大优先级越高，默认0 |
| stackable | Boolean | 否 | 是否可叠加：0->不可叠加；1->可叠加，默认false |
| exclusiveWith | String | 否 | 互斥活动ID列表，逗号分隔 |
| useType | Integer | 否 | 适用范围：0->全场通用；1->指定分类；2->指定商品；3->指定品牌，默认0 |
| minOrderAmount | BigDecimal | 否 | 最低订单金额门槛 |
| maxDiscountAmount | BigDecimal | 否 | 最大优惠金额上限 |
| perLimit | Integer | 否 | 每人限享次数 |
| totalLimit | Integer | 否 | 活动总限享次数 |
| description | String | 否 | 活动描述 |
| rules | Array | 否 | 活动规则列表 |
| productIds | Array | 否 | 适用商品ID列表（useType=2时使用） |
| categoryIds | Array | 否 | 适用分类ID列表（useType=1时使用） |
| brandIds | Array | 否 | 适用品牌ID列表（useType=3时使用） |
| skuSpecialPrices | Array | 否 | SKU特价列表（type=5时使用） |
| memberPrices | Array | 否 | 会员价列表（type=6时使用） |
| packages | Array | 否 | 套餐列表（type=4时使用） |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": 1
}
```

---

### 2.2 更新营销活动

**接口地址**: `POST /promotion/activity/update/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Long | 是 | 活动ID |

**请求参数**: 同创建接口

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": 1
}
```

---

### 2.3 删除营销活动

**接口地址**: `POST /promotion/activity/delete/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Long | 是 | 活动ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": 1
}
```

---

### 2.4 批量删除营销活动

**接口地址**: `POST /promotion/activity/delete/batch`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| ids | List<Long> | 是 | 活动ID列表 |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": 3
}
```

---

### 2.5 获取营销活动详情

**接口地址**: `GET /promotion/activity/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Long | 是 | 活动ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "name": "双11满减活动",
    "type": 1,
    "platform": 0,
    "startTime": "2024-11-01T00:00:00",
    "endTime": "2024-11-11T23:59:59",
    "status": 1,
    "priority": 10,
    "stackable": true,
    "exclusiveWith": "2,3",
    "useType": 0,
    "minOrderAmount": 100.00,
    "maxDiscountAmount": 500.00,
    "perLimit": 3,
    "totalLimit": 10000,
    "usedCount": 1500,
    "description": "双11全场满减活动",
    "createTime": "2024-10-01T10:00:00",
    "updateTime": "2024-10-15T14:30:00"
  }
}
```

---

### 2.6 分页查询营销活动列表

**接口地址**: `GET /promotion/activity/list`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | String | 否 | 活动名称（模糊查询） |
| type | Integer | 否 | 活动类型 |
| status | Integer | 否 | 状态 |
| pageNum | Integer | 否 | 页码，默认1 |
| pageSize | Integer | 否 | 每页数量，默认10 |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "pageNum": 1,
    "pageSize": 10,
    "total": 25,
    "totalPage": 3,
    "list": [
      {
        "id": 1,
        "name": "双11满减活动",
        "type": 1,
        "status": 1,
        "startTime": "2024-11-01T00:00:00",
        "endTime": "2024-11-11T23:59:59"
      }
    ]
  }
}
```

---

### 2.7 更新营销活动状态

**接口地址**: `POST /promotion/activity/update/status/{id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Long | 是 | 活动ID |

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| status | Integer | 是 | 状态：0->禁用；1->启用 |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": 1
}
```

---

## 三、优惠计算接口

### 3.1 计算优惠

**接口地址**: `POST /promotion/activity/calculate`

**请求参数**:

```json
{
  "memberId": 1001,
  "memberLevelId": 2,
  "platform": 1,
  "selectedCouponIds": [1, 2],
  "cartItems": [
    {
      "id": 1,
      "productId": 101,
      "productSkuId": 201,
      "productSkuCode": "SKU001",
      "productName": "iPhone 15 Pro",
      "productPic": "http://example.com/iphone.jpg",
      "price": 7999.00,
      "quantity": 2,
      "productCategoryId": 10,
      "brandId": 1,
      "spData": "{\"颜色\":\"黑色\",\"容量\":\"256GB\"}"
    },
    {
      "id": 2,
      "productId": 102,
      "productSkuId": 202,
      "productSkuCode": "SKU002",
      "productName": "AirPods Pro",
      "productPic": "http://example.com/airpods.jpg",
      "price": 1999.00,
      "quantity": 1,
      "productCategoryId": 10,
      "brandId": 1,
      "spData": "{\"颜色\":\"白色\"}"
    }
  ]
}
```

**参数说明**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| memberId | Long | 否 | 会员ID |
| memberLevelId | Long | 否 | 会员等级ID |
| platform | Integer | 否 | 使用平台：0->全部；1->移动；2->PC |
| selectedCouponIds | List<Long> | 否 | 选中的优惠券ID列表 |
| cartItems | List | 是 | 购物车商品列表 |

**购物车商品参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| productId | Long | 是 | 商品ID |
| productSkuId | Long | 是 | SKU ID |
| productSkuCode | String | 否 | SKU编码 |
| productName | String | 否 | 商品名称 |
| price | BigDecimal | 是 | 商品价格 |
| quantity | Integer | 是 | 购买数量 |
| productCategoryId | Long | 否 | 商品分类ID |
| brandId | Long | 否 | 品牌ID |
| spData | String | 否 | 商品销售属性（JSON格式） |

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "originalAmount": 17997.00,
    "finalAmount": 15897.00,
    "totalDiscount": 2100.00,
    "appliedPromotions": [
      {
        "promotionId": 1,
        "promotionName": "双11满减活动",
        "promotionType": 1,
        "promotionTypeDesc": "满减",
        "discountAmount": 1000.00,
        "discountDesc": "满减优惠 1000 元",
        "appliedProductIds": [101, 102],
        "appliedSkuIds": [201, 202]
      },
      {
        "promotionId": 5,
        "promotionName": "会员专享95折",
        "promotionType": 6,
        "promotionTypeDesc": "会员专享",
        "discountAmount": 899.85,
        "discountDesc": "会员价优惠 899.85 元",
        "appliedProductIds": [101],
        "appliedSkuIds": [201]
      },
      {
        "promotionId": 10,
        "promotionName": "新人优惠券",
        "promotionType": 7,
        "promotionTypeDesc": "优惠券",
        "discountAmount": 200.00,
        "discountDesc": "优惠券优惠 200 元",
        "appliedProductIds": [101, 102],
        "appliedSkuIds": [201, 202]
      }
    ],
    "itemResults": [
      {
        "productId": 101,
        "skuId": 201,
        "productName": "iPhone 15 Pro",
        "skuCode": "SKU001",
        "originalPrice": 7999.00,
        "finalPrice": 7199.10,
        "quantity": 2,
        "originalSubtotal": 15998.00,
        "finalSubtotal": 14398.20,
        "totalDiscount": 1599.80,
        "discountDetail": "双11满减活动: 优惠800; 会员专享95折: 优惠799.80;"
      },
      {
        "productId": 102,
        "skuId": 202,
        "productName": "AirPods Pro",
        "skuCode": "SKU002",
        "originalPrice": 1999.00,
        "finalPrice": 1498.80,
        "quantity": 1,
        "originalSubtotal": 1999.00,
        "finalSubtotal": 1498.80,
        "totalDiscount": 500.20,
        "discountDetail": "双11满减活动: 优惠200; 新人优惠券: 优惠300.20;"
      }
    ],
    "calcExplanation": "【价格计算说明】\n商品原价：17997.00 元\n优惠明细：\n  - 双11满减活动：满减优惠 1000 元\n  - 会员专享95折：会员价优惠 899.85 元\n  - 新人优惠券：优惠券优惠 200 元\n总优惠金额：2100.00 元\n应付金额：15897.00 元"
  }
}
```

---

### 3.2 试算优惠

**接口地址**: `POST /promotion/activity/tryCalculate`

**请求参数**: 同计算优惠接口

**响应示例**: 同计算优惠接口

**说明**: 试算接口与计算接口的区别在于，试算接口不会记录计算日志，仅用于前端展示优惠预览。

---

### 3.3 查找最优优惠组合

**接口地址**: `POST /promotion/activity/optimal`

**请求参数**: 同计算优惠接口

**响应示例**: 同计算优惠接口

**说明**: 该接口会自动计算所有可能的优惠组合，返回优惠力度最大的组合。

---

### 3.4 查找TopN优惠组合

**接口地址**: `POST /promotion/activity/topN?n=5`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| n | Integer | 否 | 返回的组合数量，默认5 |

**请求体**: 同计算优惠接口

**响应示例**:

```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "originalAmount": 17997.00,
      "finalAmount": 15897.00,
      "totalDiscount": 2100.00,
      "appliedPromotions": [...],
      "itemResults": [...],
      "calcExplanation": "..."
    },
    {
      "originalAmount": 17997.00,
      "finalAmount": 16297.00,
      "totalDiscount": 1700.00,
      "appliedPromotions": [...],
      "itemResults": [...],
      "calcExplanation": "..."
    }
  ]
}
```

**说明**: 该接口返回优惠力度最大的前N个组合，供用户选择。

---

## 四、活动类型说明

### 4.1 满减活动 (type=1)

满减活动根据订单金额达到不同门槛，享受不同金额的减免。

**规则配置示例**:

```json
"rules": [
  {
    "ruleType": 1,
    "threshold": 100.00,
    "discountValue": 10.00,
    "discountType": 1,
    "sort": 1
  },
  {
    "ruleType": 1,
    "threshold": 200.00,
    "discountValue": 30.00,
    "discountType": 1,
    "sort": 2
  },
  {
    "ruleType": 1,
    "threshold": 500.00,
    "discountValue": 100.00,
    "discountType": 1,
    "sort": 3
  }
]
```

**计算逻辑**:
- 订单金额 >= 500元，减免100元
- 订单金额 >= 200元且 < 500元，减免30元
- 订单金额 >= 100元且 < 200元，减免10元

---

### 4.2 满折活动 (type=2)

满折活动根据订单金额达到不同门槛，享受不同折扣。

**规则配置示例**:

```json
"rules": [
  {
    "ruleType": 2,
    "threshold": 200.00,
    "discountValue": 0.9,
    "discountType": 2,
    "sort": 1
  },
  {
    "ruleType": 2,
    "threshold": 500.00,
    "discountValue": 0.85,
    "discountType": 2,
    "sort": 2
  },
  {
    "ruleType": 2,
    "threshold": 1000.00,
    "discountValue": 0.8,
    "discountType": 2,
    "sort": 3
  }
]
```

**计算逻辑**:
- 订单金额 >= 1000元，享受8折
- 订单金额 >= 500元且 < 1000元，享受85折
- 订单金额 >= 200元且 < 500元，享受9折

---

### 4.3 第N件优惠 (type=3)

第N件优惠活动针对购买数量达到N件的商品，第N件享受优惠。

**规则配置示例**:

```json
"rules": [
  {
    "ruleType": 3,
    "threshold": 2,
    "discountValue": 0.5,
    "discountType": 2,
    "sort": 1
  }
]
```

**计算逻辑**:
- 购买2件，第2件享受5折
- 购买4件，第2件和第4件各享受5折

---

### 4.4 SKU特价 (type=5)

SKU特价活动针对特定SKU设置特价价格。

**配置示例**:

```json
"skuSpecialPrices": [
  {
    "productId": 101,
    "skuId": 201,
    "skuCode": "SKU001",
    "originalPrice": 7999.00,
    "specialPrice": 6999.00,
    "limitCount": 2
  }
]
```

**计算逻辑**:
- 该SKU原价7999元，特价6999元
- 每人限购2件

---

### 4.5 会员专享价 (type=6)

会员专享价活动针对特定会员等级设置优惠价格。

**配置示例**:

```json
"memberPrices": [
  {
    "productId": 101,
    "skuId": 201,
    "memberLevelId": 2,
    "memberLevelName": "黄金会员",
    "originalPrice": 7999.00,
    "memberPrice": 7599.00
  }
]
```

**计算逻辑**:
- 黄金会员购买该商品享受会员价7599元

---

### 4.6 套餐价 (type=4)

套餐价活动将多个商品组合成套餐，以优惠价格出售。

**配置示例**:

```json
"packages": [
  {
    "name": "iPhone套装",
    "packagePrice": 9999.00,
    "originalTotalPrice": 10998.00,
    "limitCount": 1,
    "sort": 1,
    "items": [
      {
        "productId": 101,
        "skuId": 201,
        "quantity": 1,
        "unitPrice": 7999.00
      },
      {
        "productId": 102,
        "skuId": 202,
        "quantity": 1,
        "unitPrice": 1999.00
      }
    ]
  }
]
```

**计算逻辑**:
- iPhone 15 Pro + AirPods Pro 原价10998元
- 套餐价9999元，优惠999元

---

## 五、叠加与互斥规则

### 5.1 叠加规则

- `stackable = true`：活动可与其他活动叠加
- `stackable = false`：活动不可与其他活动叠加

### 5.2 互斥规则

- `exclusiveWith`：指定与哪些活动互斥，多个ID用逗号分隔
- 优惠券扩展表中的 `exclusiveWithCoupons` 和 `exclusiveWithPromotions` 分别指定与哪些优惠券和活动互斥

### 5.3 优先级规则

- 活动按 `priority` 字段排序，数值越大优先级越高
- 优先级高的活动先计算
- 不可叠加的活动只应用优先级最高的那个

---

## 六、优惠分摊规则

### 6.1 分摊策略

系统支持以下分摊策略：

1. **按金额比例分摊** (`BY_AMOUNT`)：根据商品金额占比分摊优惠
2. **按数量分摊** (`BY_QUANTITY`)：根据商品数量分摊优惠
3. **平均分摊** (`EQUALLY`)：平均分摊到每个商品
4. **比例分摊** (`PROPORTIONAL`)：按金额比例分摊（默认）

### 6.2 分摊示例

假设订单包含：
- 商品A：价格100元，数量2，小计200元
- 商品B：价格300元，数量1，小计300元
- 订单总金额：500元

享受满500减100优惠。

**按金额比例分摊**：
- 商品A分摊：100 * (200/500) = 40元
- 商品B分摊：100 * (300/500) = 60元

**按数量分摊**：
- 商品A分摊：100 * (2/3) ≈ 66.67元
- 商品B分摊：100 * (1/3) ≈ 33.33元

---

## 七、错误码说明

| 错误码 | 说明 |
|--------|------|
| 200 | 操作成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

## 八、附录

### 8.1 活动类型枚举

| 代码 | 说明 |
|------|------|
| 1 | 满减 |
| 2 | 满折 |
| 3 | 第N件优惠 |
| 4 | 套餐价 |
| 5 | SKU特价 |
| 6 | 会员专享 |
| 7 | 优惠券 |

### 8.2 适用范围枚举

| 代码 | 说明 |
|------|------|
| 0 | 全场通用 |
| 1 | 指定分类 |
| 2 | 指定商品 |
| 3 | 指定品牌 |

### 8.3 优惠类型枚举

| 代码 | 说明 |
|------|------|
| 1 | 金额减免 |
| 2 | 折扣比例 |

### 8.4 规则类型枚举

| 代码 | 说明 |
|------|------|
| 1 | 满减阶梯 |
| 2 | 满折阶梯 |
| 3 | 第N件优惠 |
| 4 | 固定金额 |
| 5 | 固定折扣 |
