CREATE TABLE test (a INT, b CHAR (20), INDEX (a));

START TRANSACTION;
INSERT INTO test VALUES (10, 'Heikki');
COMMIT;

/*
 * Test rollback
 */
SET autocommit=0;

INSERT INTO test VALUES (15, 'John');
INSERT INTO test VALUES (20, 'Paul');
DELETE FROM test WHERE a = '10';
ROLLBACK;