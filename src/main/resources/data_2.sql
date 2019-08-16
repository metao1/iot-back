INSERT INTO gro.authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO gro.authority (name) VALUES ('ROLE_USER');
INSERT INTO gro.humidity_data (id, timestamp, humidity, component_id) VALUES (1, '2019-08-04 12:44:44', 10, 1);
INSERT INTO gro.humidity_sensor (id, alias, type, rpi_id, preferences_id) VALUES (1, '1', 1, 1, 1);
INSERT INTO gro.humidity_sensor_preferences (id, max_value, min_value, notification_enabled, notified) VALUES (1, 100, 0, true, true);
INSERT INTO gro.moisture_data (id, timestamp, moisture, component_id) VALUES (1, '2019-08-04 19:27:25', 90, 2);
INSERT INTO gro.moisture_sensor (id, alias, type, rpi_id, preferences_id) VALUES (2, '1', 1, 2, 2);
INSERT INTO gro.moisture_sensor_preferences (id, max_value, min_value, notification_enabled, notified) VALUES (1, 100, 0, true, true);
INSERT INTO gro.moisture_sensor_preferences (id, max_value, min_value, notification_enabled, notified) VALUES (2, 100, 0, true, true);
INSERT INTO gro.relay_preferences (id) VALUES (1);
INSERT INTO gro.rpi (id, description, image_url, ip, mqtt_password, mqtt_username, port, type) VALUES (1, 'Humidity Sensor', null, '127.0.0.1', 'admin', 'admin', 5002, 'RPI_3_MODEL_B');
INSERT INTO gro.rpi (id, description, image_url, ip, mqtt_password, mqtt_username, port, type) VALUES (2, 'Moisture Sensor', null, '127.0.0.2', 'admin', 'admin', 5002, 'RPI_3_MODEL_B');
INSERT INTO gro.rpi (id, description, image_url, ip, mqtt_password, mqtt_username, port, type) VALUES (3, 'Temperature Sensor', null, '127.0.0.3', 'admin', 'admin', 5002, 'RPI_3_MODEL_B');
INSERT INTO gro.rpi_pin (id, description, direction, physical_pin, usable, component_id, rpi_id) VALUES (1, 'Input Pin Humidity', 'INPUT', 0, true, 1, 1);
INSERT INTO gro.rpi_pin (id, description, direction, physical_pin, usable, component_id, rpi_id) VALUES (2, 'Input Pin Moisture', 'INPUT', 1, true, 2, 2);
INSERT INTO gro.rpi_pin (id, description, direction, physical_pin, usable, component_id, rpi_id) VALUES (3, 'Input Pin Temperature', 'INPUT', 2, true, 3, 3);
INSERT INTO gro.temperature_data (id, timestamp, temperature, component_id) VALUES (1, '2019-08-04 19:59:13', 20, 3);
INSERT INTO gro.temperature_sensor (id, alias, type, rpi_id, preferences_id) VALUES (3, '3', 3, 3, 1);
INSERT INTO gro.temperature_sensor_preferences (id, max_value, min_value, notification_enabled, notified, temperature_unit) VALUES (1, 300, -100, true, true, 'CELSIUS');
INSERT INTO gro.user (id, created_by, created_date, last_modified_by, last_modified_date, activated, activation_key, email, image_url, lang_key, name, password_hash, reset_date, reset_key) VALUES (1, 'admin', '2019-08-04 14:44:13', 'admin', '2019-08-04 14:44:18', true, 'key', 'admin@gmail.com', null, 'en', 'admin', '$2a$10$i.lSE/XbO0G.PGeF4Ox16ekThs8gXDpttXhouHF5KnpDOjF0VTR0.', null, null);
INSERT INTO gro.user_authority (user_id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO gro.user_authority (user_id, authority_name) VALUES (1, 'ROLE_USER');

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
