INSERT INTO gro.authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO gro.authority (name) VALUES ('ROLE_USER');
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
