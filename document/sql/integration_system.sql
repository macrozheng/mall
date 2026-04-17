-- ----------------------------
-- 积分系统数据库表
-- ----------------------------
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 积分规则表
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_rule`;
CREATE TABLE `ums_integration_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_type` int(1) NOT NULL COMMENT '规则类型：0->消费积分；1->签到积分；2->评价积分；3->注册积分；4->分享积分；5->生日积分',
  `rule_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则名称',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态：0->禁用；1->启用',
  `min_amount` decimal(10,2) NULL DEFAULT 0.00 COMMENT '最低消费金额（消费积分规则用）',
  `amount_per_integration` decimal(10,2) NULL DEFAULT 1.00 COMMENT '每消费多少金额获得1积分',
  `integration_per_amount` int(11) NULL DEFAULT 1 COMMENT '每消费金额获得的积分数',
  `max_integration_per_order` int(11) NULL DEFAULT NULL COMMENT '每单最高积分（NULL表示不限制）',
  `base_integration` int(11) NULL DEFAULT 10 COMMENT '基础积分（签到/评价等用）',
  `continue_days_integration` int(11) NULL DEFAULT 0 COMMENT '连续签到额外积分',
  `max_continue_days` int(11) NULL DEFAULT NULL COMMENT '最大连续签到天数（NULL表示不限制）',
  `comment_image_integration` int(11) NULL DEFAULT 5 COMMENT '带图评价额外积分',
  `share_integration` int(11) NULL DEFAULT 5 COMMENT '分享获得积分',
  `birthday_integration` int(11) NULL DEFAULT 100 COMMENT '生日赠送积分',
  `register_integration` int(11) NULL DEFAULT 50 COMMENT '注册赠送积分',
  `start_time` datetime NULL DEFAULT NULL COMMENT '规则生效开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '规则生效结束时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_rule_type`(`rule_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分规则表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分过期配置表
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_expire_setting`;
CREATE TABLE `ums_integration_expire_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expire_type` int(1) NOT NULL COMMENT '过期类型：0->固定有效期；1->滚动有效期',
  `valid_days` int(11) NULL DEFAULT 365 COMMENT '有效天数',
  `expire_month` int(2) NULL DEFAULT 12 COMMENT '过期月份（固定有效期用）',
  `expire_day` int(2) NULL DEFAULT 31 COMMENT '过期日期（固定有效期用）',
  `expire_year_offset` int(11) NULL DEFAULT 1 COMMENT '过期年份偏移（固定有效期用，如1表示下一年）',
  `auto_expire` int(1) NULL DEFAULT 1 COMMENT '是否自动过期：0->否；1->是',
  `notify_before_days` int(11) NULL DEFAULT 7 COMMENT '过期前几天通知',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态：0->禁用；1->启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分过期配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分过期记录表
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_expire_record`;
CREATE TABLE `ums_integration_expire_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `integration` int(11) NOT NULL COMMENT '过期积分数',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `expire_type` int(1) NOT NULL COMMENT '过期类型：0->自动过期；1->手动清零',
  `source_id` bigint(20) NULL DEFAULT NULL COMMENT '来源ID（对应ums_integration_change_history.id）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_expire_time`(`expire_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分过期记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 会员签到记录表
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_sign_in`;
CREATE TABLE `ums_member_sign_in`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `sign_in_date` date NOT NULL COMMENT '签到日期',
  `integration` int(11) NOT NULL DEFAULT 10 COMMENT '获得积分数',
  `continue_days` int(11) NULL DEFAULT 1 COMMENT '连续签到天数',
  `is_extra` int(1) NULL DEFAULT 0 COMMENT '是否额外奖励：0->否；1->是',
  `extra_integration` int(11) NULL DEFAULT 0 COMMENT '额外奖励积分',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_member_date`(`member_id`, `sign_in_date`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_sign_in_date`(`sign_in_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员签到记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分等级表（基于积分的会员等级）
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_level`;
CREATE TABLE `ums_integration_level`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `min_integration` int(11) NOT NULL COMMENT '最低积分',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级图标',
  `background` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级背景图',
  `priviledge_discount` decimal(5,2) NULL DEFAULT 10.00 COMMENT '折扣特权（如9.5折，存储9.5）',
  `priviledge_integration_rate` decimal(5,2) NULL DEFAULT 1.00 COMMENT '积分倍率（如1.5倍，存储1.5）',
  `priviledge_birthday_integration` int(11) NULL DEFAULT 0 COMMENT '生日额外积分',
  `priviledge_free_shipping` int(1) NULL DEFAULT 0 COMMENT '是否免运费：0->否；1->是',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态：0->禁用；1->启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_min_integration`(`min_integration`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分等级表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分商城商品表
-- ----------------------------
DROP TABLE IF EXISTS `ums_point_mall_product`;
CREATE TABLE `ums_point_mall_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `pics` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品图片（JSON格式）',
  `price` decimal(10,2) NULL DEFAULT 0.00 COMMENT '商品原价',
  `point_price` int(11) NOT NULL COMMENT '所需积分',
  `cash_price` decimal(10,2) NULL DEFAULT 0.00 COMMENT '现金价格（可选）',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `low_stock` int(11) NULL DEFAULT 5 COMMENT '库存预警值',
  `unit` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '件' COMMENT '单位',
  `weight` decimal(10,2) NULL DEFAULT 0.00 COMMENT '商品重量',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `sale` int(11) NULL DEFAULT 0 COMMENT '销量',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类ID',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌ID',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '关联商品ID（如果是实物商品）',
  `exchange_limit` int(11) NULL DEFAULT NULL COMMENT '每人兑换限制（NULL表示不限制）',
  `is_new` int(1) NULL DEFAULT 0 COMMENT '是否新品：0->否；1->是',
  `is_hot` int(1) NULL DEFAULT 0 COMMENT '是否热门：0->否；1->是',
  `is_recommend` int(1) NULL DEFAULT 0 COMMENT '是否推荐：0->否；1->是',
  `show_status` int(1) NULL DEFAULT 1 COMMENT '显示状态：0->不显示；1->显示',
  `publish_status` int(1) NULL DEFAULT 1 COMMENT '上架状态：0->下架；1->上架',
  `start_time` datetime NULL DEFAULT NULL COMMENT '兑换开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '兑换结束时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category_id`(`category_id`) USING BTREE,
  INDEX `idx_product_sn`(`product_sn`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分商城商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分商城商品分类表
-- ----------------------------
DROP TABLE IF EXISTS `ums_point_mall_category`;
CREATE TABLE `ums_point_mall_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `level` int(1) NULL DEFAULT 0 COMMENT '分类级别：0->1级；1->2级',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `show_status` int(1) NULL DEFAULT 1 COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分商城商品分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分兑换记录表
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_exchange_record`;
CREATE TABLE `ums_integration_exchange_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兑换订单号',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `member_username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员用户名',
  `member_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `product_id` bigint(20) NOT NULL COMMENT '积分商品ID',
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_quantity` int(11) NULL DEFAULT 1 COMMENT '兑换数量',
  `point_price` int(11) NOT NULL COMMENT '每积分商品积分',
  `total_point` int(11) NOT NULL COMMENT '总积分',
  `cash_price` decimal(10,2) NULL DEFAULT 0.00 COMMENT '现金价格',
  `total_cash` decimal(10,2) NULL DEFAULT 0.00 COMMENT '总现金',
  `receiver_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `status` int(1) NULL DEFAULT 0 COMMENT '订单状态：0->待处理；1->已确认；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `order_type` int(1) NULL DEFAULT 0 COMMENT '订单类型：0->纯积分兑换；1->积分+现金兑换；2->优惠券兑换',
  `logistics_company` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司',
  `logistics_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime NULL DEFAULT NULL COMMENT '收货时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `delete_status` int(1) NULL DEFAULT 0 COMMENT '删除状态：0->未删除；1->已删除',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_order_sn`(`order_sn`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分兑换记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 积分变更历史表扩展（增强现有表）
-- ----------------------------
-- 注意：ums_integration_change_history表已存在，这里添加必要的扩展字段
-- ALTER TABLE `ums_integration_change_history` 
-- ADD COLUMN `order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联订单ID' AFTER `source_type`,
-- ADD COLUMN `order_sn` varchar(64) NULL DEFAULT NULL COMMENT '关联订单号' AFTER `order_id`,
-- ADD COLUMN `product_id` bigint(20) NULL DEFAULT NULL COMMENT '关联商品ID' AFTER `order_sn`,
-- ADD COLUMN `expire_time` datetime NULL DEFAULT NULL COMMENT '积分过期时间' AFTER `product_id`,
-- ADD COLUMN `is_expired` int(1) NULL DEFAULT 0 COMMENT '是否已过期：0->否；1->是' AFTER `expire_time`,
-- ADD INDEX `idx_order_id`(`order_id`) USING BTREE,
-- ADD INDEX `idx_expire_time`(`expire_time`) USING BTREE;

-- ----------------------------
-- 初始化数据
-- ----------------------------

-- 初始化积分规则
INSERT INTO `ums_integration_rule` (`rule_type`, `rule_name`, `status`, `min_amount`, `amount_per_integration`, `integration_per_amount`, `max_integration_per_order`, `base_integration`, `continue_days_integration`, `max_continue_days`, `comment_image_integration`, `share_integration`, `birthday_integration`, `register_integration`) 
VALUES 
(0, '消费积分规则', 1, 0.00, 1.00, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(1, '签到积分规则', 1, NULL, NULL, NULL, NULL, 10, 5, 30, NULL, NULL, NULL, NULL),
(2, '评价积分规则', 1, NULL, NULL, NULL, NULL, 20, NULL, NULL, 10, NULL, NULL, NULL),
(3, '注册积分规则', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50),
(4, '分享积分规则', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL),
(5, '生日积分规则', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 100, NULL);

-- 初始化积分过期配置
INSERT INTO `ums_integration_expire_setting` (`expire_type`, `valid_days`, `expire_month`, `expire_day`, `expire_year_offset`, `auto_expire`, `notify_before_days`, `status`) 
VALUES 
(1, 365, 12, 31, 1, 1, 7, 1);

-- 初始化积分等级
INSERT INTO `ums_integration_level` (`level_name`, `min_integration`, `icon`, `priviledge_discount`, `priviledge_integration_rate`, `priviledge_birthday_integration`, `priviledge_free_shipping`, `sort`, `status`) 
VALUES 
('普通会员', 0, NULL, 10.00, 1.00, 0, 0, 0, 1),
('白银会员', 1000, NULL, 9.80, 1.20, 50, 0, 1, 1),
('黄金会员', 5000, NULL, 9.50, 1.50, 100, 0, 2, 1),
('铂金会员', 10000, NULL, 9.00, 2.00, 200, 1, 3, 1),
('钻石会员', 50000, NULL, 8.50, 3.00, 500, 1, 4, 1);

-- 初始化积分商城分类
INSERT INTO `ums_point_mall_category` (`parent_id`, `name`, `level`, `sort`, `show_status`) 
VALUES 
(0, '全部', 0, 0, 1),
(0, '优惠券', 0, 1, 1),
(0, '实物商品', 0, 2, 1),
(0, '虚拟商品', 0, 3, 1),
(0, '会员权益', 0, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
