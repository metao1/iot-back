CREATE DATABASE IF NOT EXISTS iot;
use iot;
grant ALL PRIVILEGES ON iot.* TO 'iot'@'localhost';
flush privileges;
INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO `user` VALUES (1,'admin','2019-08-04 14:44:13.000000','admin','2019-08-04 14:44:18.000000',_binary '','key','admin@gmail.com',NULL,'en','admin','$2a$10$i.lSE/XbO0G.PGeF4Ox16ekThs8gXDpttXhouHF5KnpDOjF0VTR0.',NULL,NULL);
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS sequence_values (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               thread_id INT NOT NULL,
                                               created DATETIME DEFAULT CURRENT_TIMESTAMP
);

SET GLOBAL log_bin_trust_function_creators = 1;
DROP FUNCTION IF EXISTS sequence_nextval;
CREATE FUNCTION sequence_nextval()
    RETURNS INTEGER
    NOT DETERMINISTIC
    MODIFIES SQL DATA
BEGIN
    DECLARE nextval INTEGER;
    INSERT INTO sequence_values (thread_id) VALUE (CONNECTION_ID());
    SELECT id FROM sequence_values ORDER BY created DESC LIMIT 1 INTO nextval;
    RETURN nextval;
END;


