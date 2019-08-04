INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN');
INSERT INTO `user` VALUES (1,'admin','2019-08-04 14:44:13.000000','admin','2019-08-04 14:44:18.000000',_binary '','key','admin@gmail.com',NULL,'en','admin','$2a$10$i.lSE/XbO0G.PGeF4Ox16ekThs8gXDpttXhouHF5KnpDOjF0VTR0.',NULL,NULL);
INSERT INTO `relay_preferences` VALUES (1);
INSERT INTO `rpi` VALUES (1,'Humidity Sensor',NULL,'127.0.0.1','admin','admin',5002,'RPI_3_MODEL_B');
INSERT INTO `rpi_pin` VALUES (1,'Input Pin','INPUT',0,_binary '',1,1);
INSERT INTO `humidity_sensor_preferences` VALUES (1,100,0,_binary '',_binary '');
INSERT INTO `humidity_sensor` VALUES (1,'1',1,1,1);
INSERT INTO `humidity_data` VALUES (1,'2019-08-04 12:44:44.000000',10,1);




