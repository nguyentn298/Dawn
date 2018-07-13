/*Create table with foreign key*/

CREATE TABLE IF NOT EXISTS `reprint_request` (
  `REPRINT_REQUEST_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `BATCH_GUID` VARCHAR(36) NOT NULL,
  `USER_ID` INT(11) NOT NULL,
  `CLIENT_ID` INT(11) NOT NULL,
  `PRODUCT_ID` INT(11) NOT NULL,
  `PUT_REPRINTED_FILE_IN_MANGLER_OUTPUT_FOLDER` tinyint(1) DEFAULT '1',
  `REMOVE_OLD_FILES_SUCESSFULL_COMPLETION` tinyint(1) DEFAULT '1',
  `STATUS` ENUM('In Progress','Finished', 'Finished with Error') NOT NULL DEFAULT 'In Progress',
  `DATE_CREATED` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DATE_UPDATED` TIMESTAMP NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`REPRINT_REQUEST_ID`),
  KEY `FK_reprint_request_user` (`USER_ID`),
  CONSTRAINT `FK_reprint_request_user` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
	
/*Stored Procedure*/
/*=======================================================*/
/*Note: Delimiter has space and 2 semicolon (;)*/
	
DROP PROCEDURE IF EXISTS `test1`;
DELIMITER ;;
	CREATE PROCEDURE `test1`()
	BEGIN
		
		DECLARE TableSchema VARCHAR(64);
		SET @TableSchema  = (SELECT DATABASE() FROM DUAL);
		
		/* Add column If not exists */
		/* Use after to add, cannot use "before" */
		/* ALTER TABLE `card` ADD COLUMN `TEST_ADD` VARCHAR(255) NULL AFTER `TYPE`; */ 
		IF NOT EXISTS(SELECT * FROM information_schema.columns WHERE TABLE_NAME = 'card' AND COLUMN_NAME = 'TEST_ADD' AND TABLE_SCHEMA = @TableSchema) THEN
			ALTER TABLE `card` ADD COLUMN `TEST_ADD` VARCHAR(255) NULL;	
		END IF;
		
		/* Modify column If exists */
		IF EXISTS(SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'card' AND COLUMN_NAME = 'TYPE' AND TABLE_SCHEMA = @TableSchema AND COLUMN_TYPE = 'varchar(50)') THEN
			ALTER TABLE `card` MODIFY `TYPE` VARCHAR(100);
		END IF;

		/*Drop column If exists */
		IF EXISTS(SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'card' AND COLUMN_NAME = 'EXPIRATION_RANGE' AND TABLE_SCHEMA = @TableSchema) THEN
			ALTER TABLE `card` DROP COLUMN `EXPIRATION_RANGE`;
		END IF;
		
		/* Change column If exists */
		IF EXISTS(SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'card' AND COLUMN_NAME = 'TEST_ADD' AND TABLE_SCHEMA = @TableSchema) THEN
			ALTER TABLE `card` CHANGE `TEST_ADD` `CHANGE_ADD` VARCHAR(69);
		END IF;
		
		/* Update engine of table If not exists */
		IF NOT EXISTS (SELECT * FROM information_schema.tables WHERE table_name = 'card' AND ENGINE != 'InnoDB' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `testengine` ENGINE=INNODB;
		END IF;
		
		/* Add constraint if exist column */
		IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'card_detail' AND column_name = 'CARD_ID' AND table_schema = @TableSchema) THEN 
			ALTER TABLE `card_detail` ADD CONSTRAINT `FK_CD_CLIENT` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client`(`CLIENT_ID`);
		END IF;
		
		/* Change enum */
		IF NOT EXISTS (SELECT * FROM information_schema.columns WHERE table_name = 'reprint_request' AND column_name = 'STATUS' AND COLUMN_TYPE = 'enum(\'In Progress\',\'Finished\',\'Finished with Error\')' AND table_schema = @TableSchema) THEN
			ALTER TABLE `reprint_request` CHANGE `STATUS` `STATUS` ENUM('In Progress', 'Finished', 'Finished with Error') NOT NULL DEFAULT 'In Progress';
		END IF;
		
	END;;
DELIMITER ;
CALL `test1`();
DROP PROCEDURE IF EXISTS `test1`;

/*
 * Drop tables if exist constraint.
 */

	/* Remove foreign key */
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

	/* Drop tables has removed foreign key */
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS job_apply;
DROP TABLE IF EXISTS job_skill;
DROP TABLE IF EXISTS profile;
DROP PROCEDURE IF EXISTS dropForeignKeysFromTable;