/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : OA

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 04/11/2021 09:24:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Department
-- ----------------------------
DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department`  (
                               `departmentId` bigint NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `createTime` datetime NULL DEFAULT NULL,
                               PRIMARY KEY (`departmentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Department
-- ----------------------------

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword`  (
                            `keywordId` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `createTime` datetime NULL DEFAULT NULL,
                            PRIMARY KEY (`keywordId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of keyword
-- ----------------------------

-- ----------------------------
-- Table structure for OA
-- ----------------------------
DROP TABLE IF EXISTS `OA`;
CREATE TABLE `OA`  (
                       `OAId` bigint NOT NULL AUTO_INCREMENT,
                       `content` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                       `timestamp` datetime NULL DEFAULT NULL,
                       `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                       `departmentId` bigint NULL DEFAULT NULL COMMENT 'SUBCOMPANYNAME对应',
                       `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                       `subscribeNumber` int NULL DEFAULT NULL,
                       `collectNumber` int NULL DEFAULT NULL,
                       `readCount` int NULL DEFAULT NULL,
                       `DEPARTMENTNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                       `LOGINID` int NULL DEFAULT NULL,
                       `LASTNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                       PRIMARY KEY (`OAId`) USING BTREE,
                       INDEX `first`(`departmentId`) USING BTREE,
                       CONSTRAINT `first` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`departmentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of OA
-- ----------------------------

-- ----------------------------
-- Table structure for OAkeyword
-- ----------------------------
DROP TABLE IF EXISTS `OAkeyword`;
CREATE TABLE `OAkeyword`  (
                              `OAkeywordId` bigint NOT NULL AUTO_INCREMENT,
                              `OAId` bigint NULL DEFAULT NULL,
                              `keywordId` bigint NULL DEFAULT NULL,
                              `createTime` datetime NULL DEFAULT NULL,
                              PRIMARY KEY (`OAkeywordId`) USING BTREE,
                              INDEX `6`(`keywordId`) USING BTREE,
                              INDEX `7`(`OAId`) USING BTREE,
                              CONSTRAINT `6` FOREIGN KEY (`keywordId`) REFERENCES `keyword` (`keywordId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                              CONSTRAINT `7` FOREIGN KEY (`OAId`) REFERENCES `OA` (`OAId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of OAkeyword
-- ----------------------------

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
                         `userId` bigint NOT NULL AUTO_INCREMENT,
                         `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         `createTime` datetime NULL DEFAULT NULL,
                         PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES (3, NULL, 'ljh13926292266', NULL, '19jhluo');

-- ----------------------------
-- Table structure for userCollect
-- ----------------------------
DROP TABLE IF EXISTS `userCollect`;
CREATE TABLE `userCollect`  (
                                `userCollectId` bigint NOT NULL AUTO_INCREMENT COMMENT 'S',
                                `userId` bigint NULL DEFAULT NULL,
                                `OAId` bigint NULL DEFAULT NULL,
                                `time` datetime NULL DEFAULT NULL,
                                PRIMARY KEY (`userCollectId`) USING BTREE,
                                INDEX `2`(`userId`) USING BTREE,
                                INDEX `seand`(`OAId`) USING BTREE,
                                CONSTRAINT `2` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                CONSTRAINT `seand` FOREIGN KEY (`OAId`) REFERENCES `OA` (`OAId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userCollect
-- ----------------------------

-- ----------------------------
-- Table structure for UserDepartment
-- ----------------------------
DROP TABLE IF EXISTS `UserDepartment`;
CREATE TABLE `UserDepartment`  (
                                   `userDepartmentId` bigint NOT NULL AUTO_INCREMENT,
                                   `userId` bigint NULL DEFAULT NULL,
                                   `departmentId` bigint NULL DEFAULT NULL,
                                   `time` datetime NULL DEFAULT NULL,
                                   PRIMARY KEY (`userDepartmentId`) USING BTREE,
                                   INDEX `4`(`userId`) USING BTREE,
                                   INDEX `5`(`departmentId`) USING BTREE,
                                   CONSTRAINT `4` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                   CONSTRAINT `5` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`departmentId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of UserDepartment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
