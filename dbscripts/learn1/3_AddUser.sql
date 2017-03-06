/*Data for the table `role` */
CREATE TABLE `user_temp` (
	`FIRST_NAME` VARCHAR(50) NOT NULL,
	`LAST_NAME` VARCHAR(50) NOT NULL,
	`USER_EMAIL` varchar(50) NOT NULL,
	`USER_PASSWORD` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * password = 'sharpinu'
 */
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('user', '01', 'user01@sharp-in-u.com', '$2a$10$tKvnR70GqyBSZ.MLk/nm6u1ph7y5qiQQz5LW1ZD32fz8RkIwOfgnW');
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('user', '02','user02.tester@sharp-in-u.com', '$2a$10$eVdBRkAvscGJqR3f.P9sUOqnsf9cCwBMaWcv3R7PL.CsYymQPQ1N.');
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('consultancy', '01','consultancy01@sharp-in-u.com', '$2a$10$frDB1w73kQxJU0zLaJEy3esMVvnQ0YFT9yMEf41vEHtl5rdqS5bA2');
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('consultancy', '02','consultancy02@sharp-in-u.com', '$2a$10$nmxVuW2dWiQNZ9Wt/biqJe9kiGzYqUDAxQHX9FjeXKxq/8PBsb3Iu');
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('advisor', '01','advisor01@sharp-in-u.com', '$2a$10$T0JbB7YxAGnO6jI3axiGmOvLJMIYYBP/b89GtrwL0MAYl730sZ9hq');
INSERT INTO `user_temp`(`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD`) VALUES ('advisor', '02','advisor02@sharp-in-u.com', '$2a$10$HKxBpw7aFnQYbt3FLvaWBekLt/.zvUU1GhtlV9Q4ov2aanHo7SJw2');

INSERT INTO `user` (`FIRST_NAME`, `LAST_NAME`,`USER_EMAIL`, `USER_PASSWORD`)
	SELECT `FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_PASSWORD` FROM `user_temp` 
	WHERE `USER_EMAIL` NOT IN (SELECT `USER_EMAIL` FROM `user`);

CREATE TABLE `user_role_temp` (
	`USER_ID` INT(11) NOT NULL,
	`ROLE_ID` INT(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'user01@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'user02@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';

INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'consultancy01@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_CONSULTANCY';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'consultancy02@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_CONSULTANCY';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'consultancy01@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'consultancy02@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';

INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'advisor01@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_ADVISOR';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'advisor02@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_ADVISOR';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'advisor01@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';
INSERT INTO `user_role_temp` (`USER_ID`, `ROLE_ID`)
	SELECT u.`USER_ID`, r.`ROLE_ID` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'advisor02@sharp-in-u.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';

INSERT INTO `user_role` (`USER_ID`, `ROLE_ID`)
	SELECT `USER_ID`, `ROLE_ID` FROM `user_role_temp` 
	WHERE (`USER_ID`, `ROLE_ID`) NOT IN (SELECT `USER_ID`, `ROLE_ID` FROM `user_role`);
	
DROP TABLE IF EXISTS user_temp;
DROP TABLE IF EXISTS user_role_temp;