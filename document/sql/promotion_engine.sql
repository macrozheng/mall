-- =============================================
-- 营销规则引擎数据库表结构
-- 支持：满减、满折、优惠券叠加/互斥、会员价、SKU特价、第N件优惠、套餐价
-- =============================================

-- 营销活动主表
CREATE TABLE IF NOT EXISTS `sms_promotion_activity` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `name` varchar(200) NOT NULL COMMENT '活动名称',
    `type` int(1) NOT NULL COMMENT '活动类型：1->满减；2->满折；3->第N件优惠；4->套餐价；5->SKU特价；6->会员专享',
    `platform` int(1) DEFAULT 0 COMMENT '使用平台：0->全部；1->移动；2->PC',
    `start_time` datetime NOT NULL COMMENT '活动开始时间',
    `end_time` datetime NOT NULL COMMENT '活动结束时间',
    `status` int(1) DEFAULT 1 COMMENT '状态：0->禁用；1->启用',
    `priority` int(11) DEFAULT 0 COMMENT '优先级，数值越大优先级越高',
    `stackable` tinyint(1) DEFAULT 0 COMMENT '是否可叠加：0->不可叠加；1->可叠加',
    `exclusive_with` varchar(500) DEFAULT NULL COMMENT '互斥活动ID列表，逗号分隔',
    `use_type` int(1) DEFAULT 0 COMMENT '适用范围：0->全场通用；1->指定分类；2->指定商品；3->指定品牌',
    `min_order_amount` decimal(10,2) DEFAULT 0.00 COMMENT '最低订单金额门槛',
    `max_discount_amount` decimal(10,2) DEFAULT NULL COMMENT '最大优惠金额上限',
    `per_limit` int(11) DEFAULT NULL COMMENT '每人限享次数',
    `total_limit` int(11) DEFAULT NULL COMMENT '活动总限享次数',
    `used_count` int(11) DEFAULT 0 COMMENT '已使用次数',
    `description` varchar(1000) DEFAULT NULL COMMENT '活动描述',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_type` (`type`),
    KEY `idx_status` (`status`),
    KEY `idx_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动主表';

-- 营销活动规则表（存储具体的优惠规则）
CREATE TABLE IF NOT EXISTS `sms_promotion_rule` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规则ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `rule_type` int(1) NOT NULL COMMENT '规则类型：1->满减阶梯；2->满折阶梯；3->第N件优惠；4->固定金额；5->固定折扣',
    `threshold` decimal(10,2) DEFAULT NULL COMMENT '门槛值（金额或数量）',
    `discount_value` decimal(10,2) NOT NULL COMMENT '优惠值（金额或折扣比例）',
    `discount_type` int(1) NOT NULL COMMENT '优惠类型：1->金额减免；2->折扣比例',
    `sort` int(11) DEFAULT 0 COMMENT '排序',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动规则表';

-- 营销活动适用范围关联表（商品）
CREATE TABLE IF NOT EXISTS `sms_promotion_product_relation` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `product_id` bigint(20) NOT NULL COMMENT '商品ID',
    `product_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
    `product_sn` varchar(64) DEFAULT NULL COMMENT '商品货号',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动商品关联表';

-- 营销活动适用范围关联表（分类）
CREATE TABLE IF NOT EXISTS `sms_promotion_category_relation` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `product_category_id` bigint(20) NOT NULL COMMENT '商品分类ID',
    `category_name` varchar(200) DEFAULT NULL COMMENT '分类名称',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_category_id` (`product_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动分类关联表';

-- 营销活动适用范围关联表（品牌）
CREATE TABLE IF NOT EXISTS `sms_promotion_brand_relation` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `brand_id` bigint(20) NOT NULL COMMENT '品牌ID',
    `brand_name` varchar(200) DEFAULT NULL COMMENT '品牌名称',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_brand_id` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动品牌关联表';

-- SKU特价表
CREATE TABLE IF NOT EXISTS `sms_sku_special_price` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `product_id` bigint(20) NOT NULL COMMENT '商品ID',
    `sku_id` bigint(20) NOT NULL COMMENT 'SKU ID',
    `sku_code` varchar(64) DEFAULT NULL COMMENT 'SKU编码',
    `original_price` decimal(10,2) NOT NULL COMMENT '原价',
    `special_price` decimal(10,2) NOT NULL COMMENT '特价',
    `limit_count` int(11) DEFAULT NULL COMMENT '限购数量',
    `sold_count` int(11) DEFAULT 0 COMMENT '已售数量',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SKU特价表';

-- 套餐商品关联表
CREATE TABLE IF NOT EXISTS `sms_promotion_package` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `name` varchar(200) NOT NULL COMMENT '套餐名称',
    `package_price` decimal(10,2) NOT NULL COMMENT '套餐价格',
    `original_total_price` decimal(10,2) NOT NULL COMMENT '原价总和',
    `limit_count` int(11) DEFAULT NULL COMMENT '限购数量',
    `sold_count` int(11) DEFAULT 0 COMMENT '已售数量',
    `sort` int(11) DEFAULT 0 COMMENT '排序',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐主表';

-- 套餐商品明细
CREATE TABLE IF NOT EXISTS `sms_promotion_package_item` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '明细ID',
    `package_id` bigint(20) NOT NULL COMMENT '套餐ID',
    `product_id` bigint(20) NOT NULL COMMENT '商品ID',
    `sku_id` bigint(20) DEFAULT NULL COMMENT 'SKU ID（可选，为空表示该商品所有SKU）',
    `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
    `unit_price` decimal(10,2) NOT NULL COMMENT '单价',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_package_id` (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐商品明细表';

-- 会员价表
CREATE TABLE IF NOT EXISTS `sms_member_price` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `product_id` bigint(20) NOT NULL COMMENT '商品ID',
    `sku_id` bigint(20) DEFAULT NULL COMMENT 'SKU ID（可选）',
    `member_level_id` bigint(20) NOT NULL COMMENT '会员等级ID',
    `member_level_name` varchar(100) DEFAULT NULL COMMENT '会员等级名称',
    `original_price` decimal(10,2) NOT NULL COMMENT '原价',
    `member_price` decimal(10,2) NOT NULL COMMENT '会员价',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_member_level` (`member_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员价表';

-- 优惠券扩展表（支持叠加/互斥配置）
CREATE TABLE IF NOT EXISTS `sms_coupon_extension` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `coupon_id` bigint(20) NOT NULL COMMENT '优惠券ID',
    `stackable` tinyint(1) DEFAULT 0 COMMENT '是否可叠加：0->不可叠加；1->可叠加',
    `exclusive_with_coupons` varchar(500) DEFAULT NULL COMMENT '互斥优惠券ID列表，逗号分隔',
    `exclusive_with_promotions` varchar(500) DEFAULT NULL COMMENT '互斥营销活动ID列表，逗号分隔',
    `can_use_with_promotion` tinyint(1) DEFAULT 1 COMMENT '是否可与营销活动共用：0->不可；1->可以',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_coupon_id` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券扩展表';

-- 优惠计算记录表（用于记录每次计算的详细信息）
CREATE TABLE IF NOT EXISTS `sms_promotion_calc_log` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
    `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
    `calc_type` int(1) NOT NULL COMMENT '计算类型：1->试算；2->下单计算',
    `original_amount` decimal(10,2) NOT NULL COMMENT '原始金额',
    `final_amount` decimal(10,2) NOT NULL COMMENT '最终金额',
    `total_discount` decimal(10,2) NOT NULL COMMENT '总优惠金额',
    `calc_detail` text COMMENT '计算详情（JSON格式）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_sn` (`order_sn`),
    KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠计算记录表';

-- 订单优惠明细表（记录订单中每个优惠的使用情况）
CREATE TABLE IF NOT EXISTS `sms_order_promotion` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id` bigint(20) NOT NULL COMMENT '订单ID',
    `order_sn` varchar(64) NOT NULL COMMENT '订单编号',
    `promotion_type` int(1) NOT NULL COMMENT '优惠类型：1->满减；2->满折；3->第N件优惠；4->套餐价；5->SKU特价；6->会员价；7->优惠券',
    `promotion_id` bigint(20) DEFAULT NULL COMMENT '优惠活动/优惠券ID',
    `promotion_name` varchar(200) DEFAULT NULL COMMENT '优惠名称',
    `discount_amount` decimal(10,2) NOT NULL COMMENT '优惠金额',
    `apply_detail` text COMMENT '应用详情（JSON格式，记录哪些商品享受了该优惠）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_order_sn` (`order_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单优惠明细表';
