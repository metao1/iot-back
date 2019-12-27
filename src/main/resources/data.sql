CREATE DATABASE IF NOT EXISTS iot;
use iot;
grant ALL PRIVILEGES ON iot.* TO 'iot'@'localhost';
flush privileges;

CREATE TABLE IF NOT EXISTS sequence_values (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               thread_id INT NOT NULL,
                                               created DATETIME DEFAULT CURRENT_TIMESTAMP
);

SET GLOBAL log_bin_trust_function_creators = 1;
DROP FUNCTION IF EXISTS sequence_nextval;
CREATE FUNCTION sequence_nextval()
    RETURNS INTEGER
    DETERMINISTIC
BEGIN
    DECLARE nextval INTEGER;
    INSERT INTO sequence_values (thread_id) VALUE (CONNECTION_ID());
    SELECT id FROM sequence_values ORDER BY created DESC LIMIT 1 INTO nextval;
    RETURN nextval;
END;


