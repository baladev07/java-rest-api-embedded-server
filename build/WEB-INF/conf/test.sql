# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.5.21)
# Database: test
# Generation Time: 2022-02-23 21:39:13 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table customer_table
# ------------------------------------------------------------

DROP TABLE IF EXISTS `customer_table`;

CREATE TABLE `customer_table` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(45) DEFAULT NULL,
  `customer_product` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_name_UNIQUE` (`customer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table FileTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `FileTable`;

CREATE TABLE `FileTable` (
  `FileId` int(11) NOT NULL AUTO_INCREMENT,
  `FileName` varchar(45) DEFAULT NULL,
  `FileStream` longtext NOT NULL,
  `CreatedTime` bigint(100) NOT NULL,
  `LastUpdatedTime` bigint(100) NOT NULL,
  PRIMARY KEY (`FileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table IssueTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `IssueTable`;

CREATE TABLE `IssueTable` (
  `IssueId` int(11) NOT NULL AUTO_INCREMENT,
  `IssueDescription` varchar(45) DEFAULT NULL,
  `FileId` int(11) DEFAULT NULL,
  `AssignedTo` int(11) DEFAULT NULL,
  `IssueTitle` varchar(45) NOT NULL,
  `CreatedTime` bigint(50) NOT NULL,
  `LastUpdatedTime` bigint(50) NOT NULL,
  `IssueStatus` int(11) NOT NULL,
  `IsNotified` tinyint(4) NOT NULL,
  `createdBy` int(11) NOT NULL,
  PRIMARY KEY (`IssueId`),
  UNIQUE KEY `IssueTitle_UNIQUE` (`IssueTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TaskTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TaskTable`;

CREATE TABLE `TaskTable` (
  `TaskID` int(11) NOT NULL AUTO_INCREMENT,
  `TaskTitle` varchar(45) NOT NULL,
  `TaskDescription` varchar(45) DEFAULT NULL,
  `CreatedTime` bigint(50) NOT NULL,
  `LastUpdatedTime` bigint(50) NOT NULL,
  `CreatedBy` int(11) NOT NULL,
  `AssignedTo` int(11) NOT NULL,
  `Status` int(11) NOT NULL,
  `DueDate` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`TaskID`),
  UNIQUE KEY `TaskTitle_UNIQUE` (`TaskTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TestCasesTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TestCasesTable`;

CREATE TABLE `TestCasesTable` (
  `TestId` int(11) NOT NULL AUTO_INCREMENT,
  `TestGroupId` varchar(50) NOT NULL,
  `RequestApi` mediumtext NOT NULL,
  `RequestHeader` mediumtext NOT NULL,
  `RequestPayload` mediumtext NOT NULL,
  `CreatedTime` bigint(50) NOT NULL,
  `LastUpdatedTime` bigint(50) NOT NULL,
  `RunsCount` bigint(90) NOT NULL DEFAULT '-1',
  `ResultTime1` int(11) DEFAULT NULL,
  `ResultTime2` int(11) DEFAULT NULL,
  `ResultTime3` int(11) DEFAULT NULL,
  `ResultTime4` int(11) DEFAULT NULL,
  `ResultTime5` int(11) DEFAULT NULL,
  `LastRunTime` bigint(50) DEFAULT NULL,
  `RequestMethod` varchar(45) NOT NULL,
  `MinResponseTime` int(11) unsigned zerofill DEFAULT NULL,
  `MaxResponseTime` int(11) unsigned zerofill DEFAULT NULL,
  `BuildLabel` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table TestGroupTable
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TestGroupTable`;

CREATE TABLE `TestGroupTable` (
  `TestGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `TestOrgId` varchar(50) NOT NULL,
  `CreatedTime` bigint(50) NOT NULL,
  `LastUpdatedTime` bigint(50) NOT NULL,
  `TestGroupName` varchar(50) NOT NULL DEFAULT 'Untitled',
  `LastRunTime` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`TestGroupId`),
  UNIQUE KEY `TestGroupId_UNIQUE` (`TestGroupId`),
  UNIQUE KEY `TestOrgId_UNIQUE` (`TestOrgId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `useremail` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `createdtime` bigint(50) DEFAULT NULL,
  `lastmodifiedby` bigint(50) DEFAULT NULL,
  `lastupdatedtime` bigint(50) NOT NULL,
  `createdby` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `useremail_UNIQUE` (`useremail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
