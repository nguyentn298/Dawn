/*
 * Change engine table to INNODB
 */
DROP PROCEDURE IF EXISTS `db_update_engine_innodb`;
DELIMITER ;;
CREATE PROCEDURE `db_update_engine_innodb`() 
	BEGIN

		DECLARE TableSchema VARCHAR(64);
	
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'advisor' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `advisor` ENGINE=INNODB;
		END IF;
	
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'advisor_post' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `advisor_post` ENGINE=INNODB;
		END IF;
	
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'statistic' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `statistic` ENGINE=INNODB;
		END IF;
	
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'ticket' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `ticket` ENGINE=INNODB;
		END IF;
	
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'ticket_conv' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `ticket_conv` ENGINE=INNODB;
		END IF;

	END;;
DELIMITER ;
CALL db_update_engine_innodb();
DROP PROCEDURE IF EXISTS `db_update_engine_innodb`;
-- --------------------------------------------------------

--
-- Table structure for table `setting_group`
--

CREATE TABLE IF NOT EXISTS `setting_group` (
  `SETTING_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETTING_GROUP_NAME` varchar(255) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `CREATED_USER_ID` int(11) DEFAULT NULL,
  `UPDATED_USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE IF NOT EXISTS `setting` (
  `SETTING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETTING_NAME` varchar(255) NOT NULL,
  `SETTING_VALUE` varchar(10000) NOT NULL,
  `SETTING_TYPE` TINYINT(1) DEFAULT 0 COMMENT 'GLOBAL = 0, USER = 1',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `CREATED_USER_ID` int(11) DEFAULT NULL,
  `UPDATED_USER_ID` int(11) DEFAULT NULL,
  `SETTING_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SETTING_ID`),
  KEY `FK_S_CREATED_USER_ID` (`CREATED_USER_ID`),
  KEY `FK_S_UPDATED_USER_ID` (`UPDATED_USER_ID`),
  KEY `FK_S_SETTING_GROUP_ID` (`SETTING_GROUP_ID`),
  CONSTRAINT `FK_S_CREATED_USER_ID` FOREIGN KEY (`CREATED_USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `FK_S_UPDATED_USER_ID` FOREIGN KEY (`UPDATED_USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `FK_S_SETTING_GROUP_ID` FOREIGN KEY (`SETTING_GROUP_ID`) REFERENCES `setting_group` (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
 * Create new table `ticket_price`
 * (ticket_price_id, ticket_type, standard_price, additional_cost, created_date, updated_date).
 */

CREATE TABLE IF NOT EXISTS `ticket_price` (
  `TICKET_PRICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TICKET_TYPE` varchar(30) NOT NULL,
  `TICKET_CODE` varchar(30) NOT NULL,
  `STANDARD_COST` DECIMAL(6,2) NOT NULL,
  `ADDITIONAL_COST` DECIMAL(6,2) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TICKET_PRICE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*
 * Create new table `ticket_status`
 * (ticket_status_id, status_name, description)
 */

CREATE TABLE IF NOT EXISTS `ticket_status` (
  `TICKET_STATUS_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `STATUS_NAME` VARCHAR(30) NOT NULL,
  `DESCRIPTION` VARCHAR(512),
  PRIMARY KEY (`TICKET_STATUS_ID`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*
 * create new table `ticket_status_history`
 */

CREATE TABLE IF NOT EXISTS `ticket_status_history`(
  `TICKET_STATUS_HISTORY_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TICKET_ID` INT(11) NULL,
  `TICKET_STATUS_ID` INT(11) NULL,
  `USER_ROLE_ID` INT(11) NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TICKET_STATUS_HISTORY_ID`),
  CONSTRAINT `FK_TSH_T_TICKET_ID` FOREIGN KEY (`TICKET_ID`) REFERENCES `ticket`(`TICKET_ID`),
  CONSTRAINT `FK_TSH_TS_TICKET_STATUS_ID` FOREIGN KEY (`TICKET_STATUS_ID`) REFERENCES `ticket_status`(`TICKET_STATUS_ID`),
  CONSTRAINT `FK_TSH_UR_USER_ROLE_ID` FOREIGN KEY (`USER_ROLE_ID`) REFERENCES `user_role`(`user_role_id`)
) ENGINE=INNODB CHARSET=utf8 AUTO_INCREMENT=1;

/*
 * create new table `repository_lookup`
 */
 
CREATE TABLE IF NOT EXISTS `repository_lookup` (
  `REPOSITORY_LOOKUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `HOST` varchar(100) NOT NULL DEFAULT 'localhost',
  `PORT` int(11) NOT NULL DEFAULT '8080',
  `LOOKUP_PROPERTIES` varchar(512) DEFAULT '[org.apache.jackrabbit.repository.uri=http://{HOST}:{PORT}/jackrabbit-webapp-2.3.6/server]' COMMENT 'Properties used to look up the repo, separated by comma like [prop1=value1],[prop2=value2]',
  `PROTOCOL` enum('FileSystem') DEFAULT 'FileSystem',
  `LOGIN_USER_NAME` varchar(32) DEFAULT NULL,
  `LOGIN_PASSWORD` varchar(100) DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `IS_DEFAULT` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`REPOSITORY_LOOKUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
 * create new table `advisor_ticket`
 */
CREATE TABLE IF NOT EXISTS `advisor_ticket`(
  `ADVISOR_TICKET_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TICKET_ID` INT(11) NOT NULL,
  `USER_ROLE_ID` INT(11) NOT NULL,
  `ADVISOR_PRICE` DECIMAL(6,2),
  `IS_READ_ONLY` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`ADVISOR_TICKET_ID`),
  CONSTRAINT `FK_AT_UR_USER_ROLE_ID` FOREIGN KEY (`USER_ROLE_ID`) REFERENCES `user_role`(`USER_ROLE_ID`),
  CONSTRAINT `FK_AT_T_TICKET_ID` FOREIGN KEY (`TICKET_ID`) REFERENCES `ticket`(`TICKET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
 * Update table `user`
 * Add new columns `PHONE_NUMBER` and `PHOTO_FILE_ID`
 */
DROP PROCEDURE IF EXISTS `db_update_table_user`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_user`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'PHONE_NUMBER' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` ADD COLUMN `PHONE_NUMBER` varchar(20);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'PHOTO_FILE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` DROP COLUMN `PHOTO_FILE`;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'PHOTO_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` ADD COLUMN `PHOTO_FILE_ID` INT(11);
		ALTER TABLE `user` ADD CONSTRAINT `FK_U_F_PHOTO_FILE_ID` FOREIGN KEY (`PHOTO_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'ADDRESS' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` ADD COLUMN `ADDRESS` varchar(255) AFTER `PHONE_NUMBER`;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'BIRTHDAY' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` ADD COLUMN `BIRTHDAY` timestamp NULL DEFAULT NULL AFTER `ADDRESS`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.table_constraints WHERE table_name = 'user'  AND constraint_name = 'FK_user_profile_profile_id' AND constraint_type = 'FOREIGN KEY' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` DROP FOREIGN KEY `FK_user_profile_profile_id`;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'user' AND column_name = 'GENDER' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `user` ADD COLUMN `GENDER` varchar(15);
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_user();
DROP PROCEDURE IF EXISTS `db_update_table_user`;

/*
 * Update table `ticket`
 * Add new columns `TICKET_NAME`, `CLOSED_DATE`, `UPDATED_DATE`, `TICKET_TYPE`
 * Add new columns `USER_PAID`, `USER_STANDARD_COST`, `USER_ADD_COST`
 * Add new columns `ADVISOR_PAID`, `ADVISOR_STANDARD_COST`, `ADVISOR_ADD_COST`
 */
DROP PROCEDURE IF EXISTS `db_update_table_ticket`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_ticket`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'TICKET_NAME' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` DROP COLUMN `TICKET_NAME`;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'CLOSED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `CLOSED_DATE` TIMESTAMP NULL DEFAULT NULL AFTER `CREATED_DATE`;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'UPDATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `UPDATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `CLOSED_DATE`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'TO_USER' AND table_schema = @TableSchema AND is_nullable = 'NO') THEN 
		ALTER TABLE `ticket` MODIFY `TO_USER` INT(11);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'FROM_USER' AND table_schema = @TableSchema AND is_nullable = 'NO') THEN 
		ALTER TABLE `ticket` MODIFY `FROM_USER` INT(11);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'IS_PAID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` CHANGE `IS_PAID` `USER_PAID` TINYINT(1) DEFAULT 0  NULL;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'USER_PAID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `USER_PAID` TINYINT(1) DEFAULT 0  NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'USER_STANDARD_COST' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `USER_STANDARD_COST` DECIMAL(6,2) DEFAULT '0.00';
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'USER_ADD_COST' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `USER_ADD_COST` DECIMAL(6,2) DEFAULT '0.00';
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'ADVISOR_PAID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `ADVISOR_PAID` TINYINT(1) DEFAULT 0  NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'ADVISOR_STANDARD_COST' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `ADVISOR_STANDARD_COST` DECIMAL(6,2) DEFAULT '0.00';
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'ADVISOR_ADD_COST' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `ADVISOR_ADD_COST` DECIMAL(6,2) DEFAULT '0.00';
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL AFTER `TICKET_ID`;
		ALTER TABLE `ticket` ADD CONSTRAINT `FK_T_F_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'TITLE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` CHANGE `TITLE` `SHORT_DESCRIPTION` varchar(1024);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'TICKET_STATUS_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `TICKET_STATUS_ID` INT(11) NULL AFTER `ATTACHED_FILE_ID`;
		ALTER TABLE `ticket` ADD CONSTRAINT `FK_T_TS_TICKET_STATUS_ID` FOREIGN KEY (`TICKET_STATUS_ID`) REFERENCES `ticket_status`(`TICKET_STATUS_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'FROM_USER_ROLE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `FROM_USER_ROLE_ID` INT(11) NULL AFTER `TICKET_STATUS_ID`;
		ALTER TABLE `ticket` ADD CONSTRAINT `FK_T_UR_FROM_USER_ROLE_ID` FOREIGN KEY (`FROM_USER_ROLE_ID`) REFERENCES `user_role`(`user_role_id`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'PHONE_NUMBER' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `PHONE_NUMBER` varchar(20) AFTER `FROM_USER_ROLE_ID`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'USER_EMAIL' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` MODIFY `USER_EMAIL` varchar(50);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'USER_EMAIL' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `USER_EMAIL` varchar(50) AFTER `PHONE_NUMBER`;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'SITUATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `SITUATION` TEXT NULL AFTER `PHONE_NUMBER`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'SITUATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` MODIFY `SITUATION` TEXT NULL;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'EXPECTATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `EXPECTATION` TEXT NULL AFTER `SITUATION`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'EXPECTATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` MODIFY `EXPECTATION` TEXT NULL;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'HAS_DELETED' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `HAS_DELETED` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'NORMAL = 0, DELETED = 1';
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'HAS_SIGNUP' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` ADD COLUMN `HAS_SIGNUP` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'NOT SIGN UP = 0, SIGNED UP = 1';
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_ticket();
DROP PROCEDURE IF EXISTS `db_update_table_ticket`;

/*
 * Update table `file`
 * Add new columns `REPOSITORY_LOOKUP_ID` REFERENCES table `repository_lookup` at `REPOSITORY_LOOKUP_ID`, `USER_ID` REFERENCES table `user` at `USER_ID`.
 * Add new columns `TYPE`, `UPDATED_DATE`.
 */
 
DROP PROCEDURE IF EXISTS `db_update_table_user`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_user`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);
	/*SET FOREIGN_KEY_CHECKS = 0;*/

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'file' AND column_name = 'REPOSITORY_LOOKUP_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `file` ADD COLUMN `REPOSITORY_LOOKUP_ID` INT(11) NULL;
		ALTER TABLE `file` ADD CONSTRAINT `FK_F_R_REPOSITORY_LOOKUP_ID` FOREIGN KEY (`REPOSITORY_LOOKUP_ID`) REFERENCES `repository_lookup`(`REPOSITORY_LOOKUP_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'file' AND column_name = 'USER_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `file` ADD COLUMN `USER_ID` INT(11) NOT NULL;
		ALTER TABLE `file` ADD CONSTRAINT `FK_F_U_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user`(`USER_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'file' AND column_name = 'TYPE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `file` ADD COLUMN `TYPE` VARCHAR(30) NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'file' AND column_name = 'UPDATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `file` ADD COLUMN `UPDATED_DATE` TIMESTAMP NULL DEFAULT NULL AFTER `CREATED_DATE`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'file' AND column_name = 'PATH' AND table_schema = @TableSchema AND is_nullable = 'NO') THEN
		ALTER TABLE `file` MODIFY `PATH` VARCHAR(512);
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_user();
DROP PROCEDURE IF EXISTS `db_update_table_user`;

/*
 * Update table `advisor`
 * Change columns `ATTACHED_FILE` to `ATTACHED_FILE_ID`, and  REFERENCES table `file` at `FILE_ID`;
 * Change columns `TICKET_ID` to `ADVISOR_TICKET_ID`, and  REFERENCES table `advisor_ticket` at `ADVISOR_TICKET_ID`;
 */
DROP PROCEDURE IF EXISTS `db_update_table_advisor`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_advisor`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'advisor' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `advisor` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL AFTER `ADVISOR_ID`;
		ALTER TABLE `advisor` ADD CONSTRAINT `FK_A_F_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'advisor' AND column_name = 'ADVISOR_TICKET_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `advisor` ADD COLUMN `ADVISOR_TICKET_ID` INT(11) NULL AFTER `ATTACHED_FILE_ID`;
		ALTER TABLE `advisor` ADD CONSTRAINT `FK_A_T_ADVISOR_TICKET_ID` FOREIGN KEY (`ADVISOR_TICKET_ID`) REFERENCES `advisor_ticket`(`ADVISOR_TICKET_ID`);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'advisor_ticket' AND column_name = 'ADVISOR_PRICE' AND DATA_TYPE='DOUBLE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `advisor_ticket` MODIFY `ADVISOR_PRICE` DECIMAL(6,2);
	END IF;
	
	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'advisor_ticket' AND column_name = 'ADVISOR_ID' AND DATA_TYPE='DOUBLE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `advisor_ticket` DROP COLUMN `ADVISOR_ID`;
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ourpractice' AND column_name = 'IMAGE_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ourpractice` ADD COLUMN `IMAGE_FILE_ID` INT(11) NULL;
		ALTER TABLE `ourpractice` ADD CONSTRAINT `FK_OP_F_IMAGE_FILE_ID` FOREIGN KEY (`IMAGE_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;
	
	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'news' AND column_name = 'IMAGE_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `news` ADD COLUMN `IMAGE_FILE_ID` INT(11) NULL;
		ALTER TABLE `news` ADD CONSTRAINT `FK_N_F_IMAGE_FILE_ID` FOREIGN KEY (`IMAGE_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_advisor();
DROP PROCEDURE IF EXISTS `db_update_table_advisor`;

/*
 * Update table `ticket_conv`
 * Change columns `DATE` to `DATETIME`
 * Add new columns `USER_ROLE_ID` REFERENCES table `user_role` at `USER_ROLE_ID`.
 * Add new columns `ATTACHED_FILE_ID` REFERENCES table `file` at `FILE_ID`.
 */
DROP PROCEDURE IF EXISTS `db_update_table_ticket_conv`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_ticket_conv`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_conv' AND column_name = 'DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_conv` CHANGE `DATE` `CREATED_DATE` DATETIME NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_conv' AND column_name = 'USER_ROLE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_conv` ADD COLUMN `USER_ROLE_ID` INT(11) NULL AFTER `TICKET_ID`;
		ALTER TABLE `ticket_conv` ADD CONSTRAINT `FK_TC_UR_USER_ROLE_ID` FOREIGN KEY (`USER_ROLE_ID`) REFERENCES `user_role`(`USER_ROLE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_conv' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_conv` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL AFTER `USER_ROLE_ID`;
		ALTER TABLE `ticket_conv` ADD CONSTRAINT `FK_TC_F_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.table_constraints WHERE table_name = 'ticket_conv'  AND constraint_name = 'FK_TC_T_TICKET_ID' AND constraint_type = 'FOREIGN KEY' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_conv` ADD CONSTRAINT `FK_TC_T_TICKET_ID` FOREIGN KEY (`TICKET_ID`) REFERENCES `ticket`(`TICKET_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_conv' AND column_name = 'FROM_USER_ROLE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_conv` ADD COLUMN `FROM_USER_ROLE_ID` INT(11) NULL AFTER `USER_ROLE_ID`;
		ALTER TABLE `ticket_conv` ADD CONSTRAINT `FK_TC_UR_FROM_USER_ROLE_ID` FOREIGN KEY (`FROM_USER_ROLE_ID`) REFERENCES `user_role`(`user_role_id`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_price' AND column_name = 'TICKET_CODE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_price` ADD COLUMN `TICKET_CODE` varchar(30) NOT NULL AFTER `TICKET_TYPE`;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_price' AND column_name = 'STANDARD_PRICE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_price` CHANGE `STANDARD_PRICE` `STANDARD_COST` DECIMAL(6,2) NOT NULL;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_price' AND column_name = 'ADDITIONAL_COST' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_price` MODIFY `ADDITIONAL_COST` DECIMAL(6,2) NOT NULL;
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_ticket_conv();
DROP PROCEDURE IF EXISTS `db_update_table_ticket_conv`;

/*
 * Edit 'DATETIME' type to 'TIMESTAMP'
 */
DROP PROCEDURE IF EXISTS `db_update_format_timestamp`;
DELIMITER ;;
CREATE PROCEDURE `db_update_format_timestamp`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_price' AND column_name = 'UPDATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_price` MODIFY `UPDATED_DATE` TIMESTAMP NULL DEFAULT NULL;
	END IF;
	
	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_price' AND column_name = 'CREATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_price` MODIFY `CREATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_status_history' AND column_name = 'UPDATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_status_history` MODIFY `UPDATED_DATE` TIMESTAMP NULL DEFAULT NULL;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket_status_history' AND column_name = 'CREATED_DATE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket_status_history` MODIFY `CREATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'repository_lookup' AND column_name = 'DATE_CREATED' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `repository_lookup` CHANGE `DATE_CREATED` `CREATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'repository_lookup' AND column_name = 'DATE_UPDATED' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `repository_lookup` CHANGE `DATE_UPDATED` `UPDATED_DATE` TIMESTAMP NULL DEFAULT NULL;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'CLOSED_DATE' AND DATA_TYPE='DATETIME' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` MODIFY `CLOSED_DATE` TIMESTAMP NULL DEFAULT NULL;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'ticket' AND column_name = 'UPDATED_DATE' AND DATA_TYPE='DATETIME' AND  table_schema = @TableSchema) THEN 
		ALTER TABLE `ticket` MODIFY `UPDATED_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
	END IF;

	END;;
DELIMITER ;
CALL db_update_format_timestamp();
DROP PROCEDURE IF EXISTS `db_update_format_timestamp`;

/*
 * Update table `company`, `career`, `resume`.
 */
DROP PROCEDURE IF EXISTS `db_update_table_company_career_resume`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_company_career_resume`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'company' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `company` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL;
		ALTER TABLE `company` ADD CONSTRAINT `FK_COMPANY_FILE_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'career' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `career` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL AFTER `ADDITIONAL_INFO`;
		ALTER TABLE `career` ADD CONSTRAINT `FK_CAREER_FILE_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'resume' AND column_name = 'ATTACHED_FILE_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `resume` ADD COLUMN `ATTACHED_FILE_ID` INT(11) NULL AFTER `CV`;
		ALTER TABLE `resume` ADD CONSTRAINT `FK_RESUME_FILE_ATTACHED_FILE_ID` FOREIGN KEY (`ATTACHED_FILE_ID`) REFERENCES `file`(`FILE_ID`);
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'resume' AND column_name = 'SITUATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `resume` DROP COLUMN `SITUATION`;
	END IF;
	
	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'resume' AND column_name = 'EXPECTATION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `resume` DROP COLUMN `EXPECTATION`;
	END IF;
	
	END;;
DELIMITER ;
CALL db_update_table_company_career_resume();
DROP PROCEDURE IF EXISTS `db_update_table_company_career_resume`;

DROP PROCEDURE IF EXISTS `db_update_table_post`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_post`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'post' AND column_name = 'NUM_OF_VIEWS' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `post` ADD COLUMN `NUM_OF_VIEWS` INT(11) NOT NULL;
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_post();
DROP PROCEDURE IF EXISTS `db_update_table_post`;

/*
 * Update table `contact_us`
 */
DROP PROCEDURE IF EXISTS `db_update_table_post`;
DELIMITER ;;
CREATE PROCEDURE `db_update_table_post`() BEGIN
	DECLARE TableSchema VARCHAR(64);
	SET @TableSchema  = (SELECT DATABASE() FROM DUAL);

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'contact_us' AND column_name = 'COMPANY_ID' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `contact_us` MODIFY `COMPANY_ID` varchar(255) NULL;
	END IF;

	IF EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'contact_us' AND column_name = 'PHONE' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `contact_us` MODIFY `PHONE` varchar(15) NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'contact_us' AND column_name = 'SUBJECT' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `contact_us` ADD `SUBJECT` varchar(255) NULL;
	END IF;

	IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'contact_us' AND column_name = 'POSITION' AND table_schema = @TableSchema) THEN 
		ALTER TABLE `contact_us` ADD `POSITION` varchar(255) NULL;
	END IF;

	END;;
DELIMITER ;
CALL db_update_table_post();
DROP PROCEDURE IF EXISTS `db_update_table_post`;

/*
 * Drop 17 tables if exist.
 */

DROP PROCEDURE IF EXISTS dropForeignKeysFromTable;
DELIMITER ;;
CREATE PROCEDURE dropForeignKeysFromTable(IN param_table_name VARCHAR(255)) BEGIN
	DECLARE done INT DEFAULT FALSE;
	DECLARE dropCommand VARCHAR(255);
	DECLARE dropCur CURSOR FOR 
	SELECT CONCAT('alter table ',table_schema,'.',table_name,' DROP FOREIGN KEY ',constraint_name, ';') FROM information_schema.table_constraints
	WHERE constraint_type='FOREIGN KEY' 
	AND table_name = param_table_name AND table_schema = (SELECT DATABASE() FROM DUAL);

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN dropCur;

	read_loop: LOOP
	FETCH dropCur INTO dropCommand;
	IF done THEN
	LEAVE read_loop;
	END IF;

	SET @sdropCommand = dropCommand;

		PREPARE dropClientUpdateKeyStmt FROM @sdropCommand;

		EXECUTE dropClientUpdateKeyStmt;

		DEALLOCATE PREPARE dropClientUpdateKeyStmt;
	END LOOP;

	CLOSE dropCur;
END ;;

DELIMITER ;

CALL dropForeignKeysFromTable('city');
CALL dropForeignKeysFromTable('country');
CALL dropForeignKeysFromTable('job');
CALL dropForeignKeysFromTable('job_apply');
CALL dropForeignKeysFromTable('job_skill');
CALL dropForeignKeysFromTable('profile');
CALL dropForeignKeysFromTable('skill');
CALL dropForeignKeysFromTable('t_option');
CALL dropForeignKeysFromTable('t_question');
CALL dropForeignKeysFromTable('t_test');
CALL dropForeignKeysFromTable('t_test_question');
CALL dropForeignKeysFromTable('t_user_test');
CALL dropForeignKeysFromTable('u_certification');
CALL dropForeignKeysFromTable('u_education');
CALL dropForeignKeysFromTable('u_employment');
CALL dropForeignKeysFromTable('u_license');
CALL dropForeignKeysFromTable('work_category');

DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS job_apply;
DROP TABLE IF EXISTS job_skill;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS t_option;
DROP TABLE IF EXISTS t_question;
DROP TABLE IF EXISTS t_test;
DROP TABLE IF EXISTS t_test_question;
DROP TABLE IF EXISTS t_user_test;
DROP TABLE IF EXISTS u_certification;
DROP TABLE IF EXISTS u_education;
DROP TABLE IF EXISTS u_employment;
DROP TABLE IF EXISTS u_license;
DROP TABLE IF EXISTS work_category;
DROP PROCEDURE IF EXISTS dropForeignKeysFromTable;
