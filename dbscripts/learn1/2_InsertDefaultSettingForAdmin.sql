SET @ADMIN_USER_ID = (SELECT `USER_ID` from `user` WHERE `user_email` LIKE 'sharpinu.tester@gmail.com');

DROP TABLE IF EXISTS setting_group_temp;
CREATE TABLE  setting_group_temp (
	`SETTING_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
	`SETTING_GROUP_NAME` varchar(256),
	`CREATED_USER_ID` int(11) DEFAULT NULL,
	`UPDATED_USER_ID` int(11) DEFAULT NULL,
	 PRIMARY KEY (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO setting_group_temp(`SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID`) VALUE ('Footer Menu', @ADMIN_USER_ID, @ADMIN_USER_ID);
INSERT INTO setting_group_temp(`SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID`) VALUE ('Global', @ADMIN_USER_ID, @ADMIN_USER_ID);

INSERT INTO setting_group (`SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID`)
	SELECT `SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID` FROM setting_group_temp
	WHERE `SETTING_GROUP_NAME` NOT IN (SELECT `SETTING_GROUP_NAME` FROM setting_group);

DROP TABLE IF EXISTS setting_temp;
CREATE TABLE  setting_temp (
	`SETTING_ID` int(11) NOT NULL AUTO_INCREMENT,
	`SETTING_NAME` varchar(256),
	`SETTING_VALUE` VARCHAR(1000),
	`SETTING_TYPE` TINYINT(1) DEFAULT 0,
	`CREATED_USER_ID` int(11) DEFAULT NULL,
	`UPDATED_USER_ID` int(11) DEFAULT NULL,
	`SETTING_GROUP_ID` int(11) DEFAULT NULL,
	 PRIMARY KEY (`SETTING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET @FOOTER_MENU_GROUP_ID = (SELECT `SETTING_GROUP_ID` from `setting_group` WHERE `setting_group_name` LIKE 'Footer Menu');
SET @GLOBAL_GROUP_ID = (SELECT `SETTING_GROUP_ID` from `setting_group` WHERE `setting_group_name` LIKE 'Global');

INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.url','/SharpInU/news/1/list.in', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.title','Sharp-in-U provides list of valuable Big Data Testing', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.image.url','/SharpInU/assets/images/news.jpeg', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);

INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.url','/SharpInU/trend.in', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.title','Find out the latest software testing trends that will drive QA and testing in feature.', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.image.url','/SharpInU/assets/images/thinking.png', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);

INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('global.upload.image.folder','', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @GLOBAL_GROUP_ID);

INSERT INTO setting (`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`)
	SELECT `SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID` FROM setting_temp
	WHERE `SETTING_NAME` NOT IN (SELECT `SETTING_NAME` FROM setting);

	/*
 * Insert 2 new roles: consultancy and advisor
 */
DROP TABLE IF EXISTS role_temp;
CREATE TABLE `role_temp` (
	`role_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT  INTO `role_temp`(`role_name`) VALUES ('ROLE_CONSULTANCY'), ('ROLE_ADVISOR');

INSERT INTO `role` (`role_name`)
	SELECT `role_name` FROM `role_temp` 
	WHERE `role_name` NOT IN (SELECT `role_name` FROM `role`);
	
/*
 * insert statuses: New, Request for Info, Ready for Sign-up, In-Progress, Resolved, Cancelled, Closed
 */
DROP TABLE IF EXISTS ticket_status_temp;
CREATE TABLE `ticket_status_temp` (
	`STATUS_NAME` VARCHAR(30) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `ticket_status_temp`(`STATUS_NAME`) VALUES ('New'), ('Request for Info'), ('Ready for Sign-up'), ('In-Progress'), ('Resolved'), ('Cancelled'), ('Closed');

INSERT INTO `ticket_status` (`STATUS_NAME`)
	SELECT `STATUS_NAME` FROM `ticket_status_temp` 
	WHERE `STATUS_NAME` NOT IN (SELECT `STATUS_NAME` FROM `ticket_status`);

/*
 * Insert values into ticket_price
 */
DROP TABLE IF EXISTS ticket_price_temp;
CREATE TABLE `ticket_price_temp` (
  `TICKET_PRICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TICKET_TYPE` varchar(30) NOT NULL,
  `TICKET_CODE` varchar(30) NOT NULL,
  `STANDARD_COST` DECIMAL(6,2) NOT NULL,
  `ADDITIONAL_COST` DECIMAL(6,2) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TICKET_PRICE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('User\'s Career Ticket', 'UCAR', '10', '10'); 
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('User\'s Company Ticket', 'UCOM', '10', '10'); 
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('User\'s Resume Ticket', 'URES', '10', '10'); 
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('Advisor\'s Career Ticket', 'ACAR', '10', '10'); 
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('Advisor\'s Company Ticket', 'ACOM', '10', '10'); 
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('Advisor\'s Resume Ticket', 'ARES', '10', '10');
INSERT INTO `ticket_price_temp` (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) VALUES ('Other', 'N/A', '10', '10');

INSERT INTO ticket_price (`TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST`) 
	SELECT `TICKET_TYPE`, `TICKET_CODE`, `STANDARD_COST`, `ADDITIONAL_COST` FROM ticket_price_temp
	WHERE `TICKET_CODE` NOT IN (SELECT `TICKET_CODE` FROM ticket_price);
	
	
DROP TABLE IF EXISTS ticket_price_temp;
DROP TABLE IF EXISTS ticket_status_temp;
DROP TABLE IF EXISTS role_temp;
DROP TABLE IF EXISTS setting_temp;
DROP TABLE IF EXISTS setting_group_temp;
