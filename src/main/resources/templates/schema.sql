/*
 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : oa_manager

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 17/11/2021 14:19:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_oa
-- ----------------------------
DROP TABLE IF EXISTS `tb_oa`;
CREATE TABLE `tb_oa` (
    `id` bigint(20) auto_increment primary key comment '主键 ID',
    `title` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '' comment 'DOCSUBJECT',
    `content` mediumtext CHARACTER SET utf8mb4 NULL comment 'DOCCONTENT',
    `timestamp` timestamp NULL DEFAULT NULL comment 'DOCVALIDDATE + " " + DOCVALIDTIME',
    `login_id` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT '' comment 'LOGINID',
    `last_name` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT '' comment 'LASTNAME',
    `subcompany_name` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT '' comment 'SUBCOMPANYNAME',
    `department_name` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT '' comment 'DEPARTMENTNAME',
    `keywords` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
    `favored_count` int NULL DEFAULT 0 comment '收藏数',
    `read_count` int NULL DEFAULT 0 comment '阅读量',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_oa
-- ----------------------------
insert into tb_oa(title, timestamp) values('关于举办理学院教师教学创新大赛的通知', '2021-11-17 11:58:31');

-- ----------------------------
-- Table structure for tb_keyword
-- ----------------------------
DROP TABLE IF EXISTS `tb_keyword`;
CREATE TABLE `tb_keyword` (
    `id` bigint(20) auto_increment primary key comment '主键 ID',
    `keyword` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT '',
    `createTime` datetime NULL DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for tb_oa_keyword
-- ----------------------------
DROP TABLE IF EXISTS `tb_oa_keyword`;
CREATE TABLE `tb_oa_keyword` (
    `id` bigint(20) auto_increment primary key comment '主键 ID',
    `oa_id` bigint(20) not null default 0 comment 'tb_oa.id',
    `keyword_id` bigint(20) not null default 0 comment 'tb_keyword.id',
    `createTime` datetime NULL DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    INDEX `6`(`keyword_id`) USING BTREE,
    INDEX `7`(`oa_id`) USING BTREE,
    CONSTRAINT `6` FOREIGN KEY (`keyword_id`) REFERENCES `tb_keyword` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `7` FOREIGN KEY (`oa_id`) REFERENCES `tb_oa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_oa_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
    `id` bigint(20) auto_increment primary key comment '主键 ID',
    `account` varchar(255) CHARACTER SET utf8mb4 not null unique DEFAULT '' comment '校园网账号',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user`(account) VALUES ('19jhluo');

-- ----------------------------
-- Table structure for tb_user_favorites
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_favorites`;
CREATE TABLE `tb_user_favorites` (
    `id` bigint(20) auto_increment primary key comment '主键 ID',
    `user_id` bigint(20) not null DEFAULT 0 comment 'tb_user.id',
    `oa_id` bigint(20) not null default 0 comment 'tb_oa.id',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    INDEX `2`(`user_id`) USING BTREE,
    INDEX `3`(`oa_id`) USING BTREE,
    CONSTRAINT `2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `3` FOREIGN KEY (`oa_id`) REFERENCES `tb_oa` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_favorites
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
