/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 28/01/2020 17:29:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for com.cctv.springbootdemo.model.user
-- ----------------------------
DROP TABLE IF EXISTS `com.cctv.springbootdemo.model.user`;
CREATE TABLE `user`  (
    `user_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_sexuality` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `account_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_birthday` date DEFAULT NULL,
  PRIMARY KEY (`user_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of com.cctv.springbootdemo.model.user
-- ----------------------------
INSERT INTO `user` VALUES ('00001', 'Eric', 'M', '445566', 'ericzya@outlook.com', '1995-11-29');

SET FOREIGN_KEY_CHECKS = 1;
