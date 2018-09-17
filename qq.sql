/*
Navicat MySQL Data Transfer

Source Server         : QQ
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : qq

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-19 10:08:27
*/
use qq;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for groupinfo
-- ----------------------------
DROP TABLE IF EXISTS `groupinfo`;
CREATE TABLE `groupinfo` (
  `G_no` int(2) NOT NULL,
  `G_name` varchar(10) NOT NULL,
  `G_date` date NOT NULL,
  PRIMARY KEY (`G_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groupinfo
-- ----------------------------
INSERT INTO `groupinfo` VALUES ('456', '自家人', '2018-06-11');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `U_isOnline` int(2) NOT NULL,
  `U_qq` int(5) NOT NULL,
  `U_name` varchar(10) NOT NULL,
  `U_pwd` varchar(10) NOT NULL,
  `U_photoID` int(2) NOT NULL,
  PRIMARY KEY (`U_qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('0', '100', '李白', '123123', '1');
INSERT INTO `users` VALUES ('0', '123', '小太阳', '123', '2');
INSERT INTO `users` VALUES ('0', '5555', 'zz', '123123', '1');
INSERT INTO `users` VALUES ('0', '23551538', '好的', '123123', '3');
INSERT INTO `users` VALUES ('0', '851572095', '肆意', '123123', '3');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `no` int(2) NOT NULL,
  `G_no` int(5) NOT NULL,
  `qq` int(5) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `G_no` (`G_no`),
  KEY `qq` (`qq`),
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`G_no`) REFERENCES `groupinfo` (`G_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_group_ibfk_2` FOREIGN KEY (`qq`) REFERENCES `users` (`U_qq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '456', '851572095');
INSERT INTO `user_group` VALUES ('2', '456', '23551538');
INSERT INTO `user_group` VALUES ('3', '456', '123');
