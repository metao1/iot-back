INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO `user` VALUES (1,'admin','2019-08-04 14:44:13.000000','admin','2019-08-04 14:44:18.000000',_binary '','key','admin@gmail.com',NULL,'en','admin','$2a$10$i.lSE/XbO0G.PGeF4Ox16ekThs8gXDpttXhouHF5KnpDOjF0VTR0.',NULL,NULL);
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN');
INSERT INTO `relay_preferences` VALUES (1);
INSERT INTO `rpi` VALUES (1,'Humidity Sensor',NULL,'127.0.0.1','admin','admin',5002,'RPI_3_MODEL_B');
INSERT INTO `rpi_pin` VALUES (1,'Input Pin','INPUT',0,_binary '',1,1);
INSERT INTO `humidity_sensor_preferences` VALUES (1,100,0,_binary '',_binary '');
INSERT INTO `humidity_sensor` VALUES (1,'1',1,1,1);
INSERT INTO `humidity_data` VALUES (1,'2019-08-04 12:44:44.000000',10,1);

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


