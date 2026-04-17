-- ----------------------------
-- 拼团功能相关表
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_group_activity
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_activity`;
CREATE TABLE `sms_group_activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `activity_type` int(1) NULL DEFAULT 0 COMMENT '活动类型：0->普通拼团；1->老带新拼团；2->新人团',
  `start_time` datetime NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '活动结束时间',
  `group_count` int(11) NULL DEFAULT 2 COMMENT '成团人数',
  `group_valid_time` int(11) NULL DEFAULT 24 COMMENT '成团有效时间(小时)',
  `limit_count` int(11) NULL DEFAULT 1 COMMENT '每人限购数量',
  `use_limit_count` int(11) NULL DEFAULT 1 COMMENT '每人限参加次数',
  `max_join_count` int(11) NULL DEFAULT 0 COMMENT '最大参与人数：0->不限制',
  `min_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '最低消费金额',
  `status` int(1) NULL DEFAULT 0 COMMENT '活动状态：0->关闭；1->开启',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `note` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_group_activity
-- ----------------------------

-- ----------------------------
-- Table structure for sms_group_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_product_relation`;
CREATE TABLE `sms_group_product_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_activity_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团活动id',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `group_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '拼团价格',
  `original_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '原价',
  `group_stock` int(11) NULL DEFAULT 0 COMMENT '拼团库存',
  `lock_stock` int(11) NULL DEFAULT 0 COMMENT '锁定库存',
  `sold_stock` int(11) NULL DEFAULT 0 COMMENT '已售库存',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_product`(`group_activity_id`, `product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团商品关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_group_product_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sms_group_team
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_team`;
CREATE TABLE `sms_group_team`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_activity_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团活动id',
  `group_product_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团商品id',
  `team_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团号',
  `leader_id` bigint(20) NULL DEFAULT NULL COMMENT '团长id',
  `leader_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团长名称',
  `leader_icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团长头像',
  `group_count` int(11) NULL DEFAULT 2 COMMENT '成团人数',
  `join_count` int(11) NULL DEFAULT 1 COMMENT '当前参与人数',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态：0->进行中；1->已成团；2->已过期；3->已取消',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `complete_time` datetime NULL DEFAULT NULL COMMENT '成团时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_team_number`(`team_number`) USING BTREE,
  INDEX `idx_activity_id`(`group_activity_id`) USING BTREE,
  INDEX `idx_leader_id`(`leader_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团队伍表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_group_team
-- ----------------------------

-- ----------------------------
-- Table structure for sms_group_member
-- ----------------------------
DROP TABLE IF EXISTS `sms_group_member`;
CREATE TABLE `sms_group_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_team_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团队伍id',
  `group_activity_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团活动id',
  `group_product_id` bigint(20) NULL DEFAULT NULL COMMENT '拼团商品id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员名称',
  `member_icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `is_leader` tinyint(1) NULL DEFAULT 0 COMMENT '是否为团长：0->否；1->是',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态：0->待支付；1->已支付；2->已退款；3->已取消',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_team_id`(`group_team_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_activity_member`(`group_activity_id`, `member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_group_member
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
