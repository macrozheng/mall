-- =============================================================
-- 拼团购业务 DDL
-- 1. sms_group_buy_activity      拼团活动主表
-- 2. sms_group_buy_product       拼团活动商品表(SKU级)
-- 3. sms_group_buy_team          拼团成团实例表
-- 4. sms_group_buy_record        拼团参团记录表
-- 5. sms_group_buy_log           拼团操作日志表
-- 6. ALTER oms_order             订单表新增拼团关联字段
-- =============================================================
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_group_buy_activity
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_buy_activity`;
CREATE TABLE `sms_group_buy_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '拼团活动ID',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动副标题/描述',
  `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动主图',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `group_size` int(11) NOT NULL DEFAULT 2 COMMENT '成团人数(含团长)',
  `valid_hours` int(11) NOT NULL DEFAULT 24 COMMENT '成团有效时长(小时),超时未成团则失败',
  `limit_per_member` int(11) NOT NULL DEFAULT 1 COMMENT '每个会员在本活动的参团次数限制,0=不限',
  `virtual_group_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否支持虚拟成团:0->否;1->是(到期未满员由系统补团)',
  `allow_leader_free` int(1) NOT NULL DEFAULT 0 COMMENT '团长是否免单:0->否;1->是',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '上下线状态:0->下线;1->上线',
  `total_group_count` int(11) NOT NULL DEFAULT 0 COMMENT '累计开团数(冗余统计)',
  `success_group_count` int(11) NOT NULL DEFAULT 0 COMMENT '累计成团数(冗余统计)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_status_time` (`status`, `start_time`, `end_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='拼团活动表' ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_group_buy_product
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_buy_product`;
CREATE TABLE `sms_group_buy_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL COMMENT '拼团活动ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID(pms_product)',
  `product_sku_id` bigint(20) NOT NULL COMMENT '商品SKU ID(pms_sku_stock)',
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余商品名称',
  `product_pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余商品图片',
  `sku_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余SKU编码',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价',
  `group_price` decimal(10,2) NOT NULL COMMENT '拼团价',
  `group_stock` int(11) NOT NULL DEFAULT 0 COMMENT '拼团活动库存',
  `locked_stock` int(11) NOT NULL DEFAULT 0 COMMENT '已锁定(进行中团的占用)库存',
  `sold_count` int(11) NOT NULL DEFAULT 0 COMMENT '已售数量(成团后累计)',
  `limit_per_order` int(11) NOT NULL DEFAULT 1 COMMENT '单次下单限购数量',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_activity_sku` (`activity_id`, `product_sku_id`) USING BTREE,
  KEY `idx_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='拼团活动商品表' ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_group_buy_team
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_buy_team`;
CREATE TABLE `sms_group_buy_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '团ID',
  `team_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团编号(对外分享)',
  `activity_id` bigint(20) NOT NULL COMMENT '拼团活动ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_sku_id` bigint(20) NOT NULL COMMENT '商品SKU ID',
  `group_price` decimal(10,2) NOT NULL COMMENT '成团时的拼团价(快照)',
  `leader_member_id` bigint(20) NOT NULL COMMENT '团长会员ID',
  `leader_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余团长昵称',
  `target_num` int(11) NOT NULL COMMENT '目标成团人数(快照)',
  `current_num` int(11) NOT NULL DEFAULT 0 COMMENT '当前已参团人数',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态:0->进行中;1->成团;2->失败(超时/人数不足);3->已关闭',
  `start_time` datetime NOT NULL COMMENT '开团时间',
  `expire_time` datetime NOT NULL COMMENT '成团截止时间(超时扫描用)',
  `success_time` datetime DEFAULT NULL COMMENT '成团时间',
  `close_time` datetime DEFAULT NULL COMMENT '关团时间',
  `virtual_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否虚拟成团:0->否;1->是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_team_no` (`team_no`) USING BTREE,
  KEY `idx_activity_status` (`activity_id`, `status`) USING BTREE,
  KEY `idx_leader` (`leader_member_id`) USING BTREE,
  KEY `idx_expire_scan` (`status`, `expire_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='拼团成团实例表' ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_group_buy_record
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_buy_record`;
CREATE TABLE `sms_group_buy_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(20) NOT NULL COMMENT '团ID(sms_group_buy_team.id)',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID(冗余,便于按活动聚合)',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `member_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余昵称',
  `member_icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余头像(用于拼团进度展示)',
  `is_leader` int(1) NOT NULL DEFAULT 0 COMMENT '是否团长:0->否;1->是',
  `order_id` bigint(20) DEFAULT NULL COMMENT '对应订单ID(oms_order)',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单编号',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '实付金额',
  `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '购买数量',
  `join_status` int(1) NOT NULL DEFAULT 0 COMMENT '参团状态:0->待支付;1->已支付待成团;2->成团成功;3->成团失败已退款;4->已取消',
  `join_time` datetime NOT NULL COMMENT '参团时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `finish_time` datetime DEFAULT NULL COMMENT '结算时间(成团或退款完成)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_team_member` (`team_id`, `member_id`) USING BTREE,
  KEY `idx_member` (`member_id`) USING BTREE,
  KEY `idx_activity_member` (`activity_id`, `member_id`) USING BTREE,
  KEY `idx_order` (`order_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='拼团参团记录表' ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sms_group_buy_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_buy_log`;
CREATE TABLE `sms_group_buy_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '操作会员ID(系统操作时为NULL)',
  `member_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冗余昵称',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `team_id` bigint(20) DEFAULT NULL COMMENT '团ID',
  `team_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '团编号',
  `record_id` bigint(20) DEFAULT NULL COMMENT '参团记录ID',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单编号',
  `operate_type` int(2) NOT NULL COMMENT '操作类型:1->开团;2->参团;3->支付成功;4->取消参团;5->成团;6->成团失败;7->退款完成;8->分享;9->系统补团;10->管理员强制关闭',
  `operate_source` int(1) NOT NULL DEFAULT 0 COMMENT '来源:0->用户;1->系统任务;2->支付回调;3->后台管理员',
  `before_status` int(2) DEFAULT NULL COMMENT '操作前状态(team/record)',
  `after_status` int(2) DEFAULT NULL COMMENT '操作后状态',
  `detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细描述/参数快照(JSON)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作IP',
  `create_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_member_time` (`member_id`, `create_time`) USING BTREE,
  KEY `idx_team` (`team_id`) USING BTREE,
  KEY `idx_activity_type` (`activity_id`, `operate_type`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='拼团操作日志表' ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- ALTER oms_order : 新增拼团关联字段
-- ----------------------------
ALTER TABLE `oms_order`
  ADD COLUMN `group_activity_id` bigint(20) DEFAULT NULL COMMENT '拼团活动ID(非拼团订单为NULL)' AFTER `order_type`,
  ADD COLUMN `group_team_id` bigint(20) DEFAULT NULL COMMENT '拼团团ID(非拼团订单为NULL)' AFTER `group_activity_id`,
  ADD KEY `idx_group_team` (`group_team_id`) USING BTREE;

SET FOREIGN_KEY_CHECKS = 1;
