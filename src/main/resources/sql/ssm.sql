/*
Navicat MySQL Data Transfer

Source Server         : 本地链接
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : ssm_20161215

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-04-14 13:57:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` varchar(11) NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', '测试名d', 'sfasgfaf', '24');
INSERT INTO `user_t` VALUES ('3', 'admin', 'admin', '22');
INSERT INTO `user_t` VALUES ('4', '事物1', 'adminadmin', '22');
