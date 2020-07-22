/*
 Navicat Premium Data Transfer

 Source Server         : 阿里
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 47.100.45.101:3306
 Source Schema         : file_db

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/07/2020 14:35:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_tb
-- ----------------------------
DROP TABLE IF EXISTS `file_tb`;
CREATE TABLE `file_tb`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `f_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件唯一标识',
  `f_index` bigint(20) DEFAULT NULL COMMENT '第几个分片',
  `f_total` int(11) DEFAULT NULL COMMENT '共有几个分片',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名称，后面可以返回出去',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
