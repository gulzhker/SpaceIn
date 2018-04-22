/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-11 17:01:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  `FlightID` varchar(40) NOT NULL,
  `startTime` varchar(40) NOT NULL,
  `arrivalTime` varchar(40) NOT NULL,
  `startCity` varchar(40) NOT NULL,
  `arrivalCity` varchar(40) NOT NULL,
  `departureDate` varchar(40) NOT NULL,
  `price` int(8) NOT NULL,
  `currentPassengers` smallint(6) NOT NULL,
  `seatCapacity` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`FlightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES ('CZ1000', '11:10:00', '15:45:00', 'ShenZhen', 'BeiJing', '2017-5-15', '1000', '0', '300');
INSERT INTO `flight` VALUES ('CZ1001', '10:30:00', '12:20:00', 'ShenZhen', 'ShangHai', '2017-5-16', '300', '0', '300');
