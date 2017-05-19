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
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(11) NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '测试名d', 'sfasgfaf', '24');
INSERT INTO `t_user` VALUES ('3', 'admin', 'admin', '22');
INSERT INTO `t_user` VALUES ('4', '事物1', 'adminadmin', '22');

-- 操作日志表
DROP TABLE IF EXISTS t_log;
CREATE TABLE t_log (
   id					bigint(20) 	  NOT NULL AUTO_INCREMENT,
   user_id 				varchar(30)   DEFAULT NULL COMMENT '操作用户ID',
   user_name            varchar(255)  DEFAULT NULL COMMENT '操作人名称',
   oper_time            datetime      DEFAULT NULL COMMENT '操作时间(yyyy-MM-dd HH:mm:ss)',
   client_ip	 		varchar(20)   DEFAULT NULL COMMENT '登录IP',
   req_url 				varchar(100)  DEFAULT NULL COMMENT '请求地址',
   method 				varchar(100)   DEFAULT NULL COMMENT '请求方法名称',
   oper_event			varchar(300)  DEFAULT NULL COMMENT '操作事件（删除，新增，修改，查询，登录，退出）',
   oper_status			tinyint(2)    DEFAULT NULL COMMENT '操作状态（1：成功，2：失败）',
   log_desc				varchar(300)  DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
