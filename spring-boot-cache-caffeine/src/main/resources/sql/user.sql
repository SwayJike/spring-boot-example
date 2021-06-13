/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 31/01/2021 21:05:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `mobile_phone_number` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `delete_state` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '用户状态，0表示未删除，1表示删除',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'JourWon', '123456', '13800000000', 'JourWon@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (2, '马云', '123456', '13800000011', 'JackMa@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (3, '马化腾', '123456', '13800000022', 'PonyMa@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (4, '李彦宏', '123456', '13800000033', 'RobinLee@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (5, '任正非', '123456', '13800000044', 'RenZhengfei@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (6, 'Jobs', '123456', '13800000055', 'Jobs@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (7, 'Bill Gates', '123456', '13800000066', 'Bill Gates@163.com', 0, NOW(), NULL);
INSERT INTO `user` VALUES (8, 'Buffett', '123456', '13800000077', 'Buffett@163.com', 0, NOW(), NULL);

SET FOREIGN_KEY_CHECKS = 1;
