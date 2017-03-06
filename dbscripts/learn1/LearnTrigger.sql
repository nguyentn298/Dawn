DELIMITER $$

USE `aklero_idea_db_2_14`$$

DROP TRIGGER /*!50032 IF EXISTS */ `batch_lookup_update_date_updated`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `batch_lookup_update_date_updated` BEFORE UPDATE ON `batch_lookup` 
    FOR EACH ROW SET NEW.DATE_UPDATED = CURRENT_TIMESTAMP;
$$

DELIMITER ;