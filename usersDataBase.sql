DROP DATABASE IF EXISTS `login_db2`;
CREATE SCHEMA `login_db2` ;
USE `login_db2`;

CREATE TABLE `users` (
  `User_id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `UserType` varchar(45) DEFAULT NULL,
  `Balance` double NOT NULL,
  `address` VARCHAR(200) NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `postal_code` VARCHAR(20) NULL,
  `phone_number` VARCHAR(20) NULL,
  `vendor_company` VARCHAR(50) NULL,
  `authorized` INT NOT NULL,
  PRIMARY KEY (`User_id`)
);

INSERT INTO `users` (
user_id,
Username,
Email,
Password,
UserType,
balance,
authorized)
 VALUES (1,'we','we','we','Costumer', 123.12, 1),
(2,'hanie','jdshsggsgs','12345','Vendor', 25.99, 1),
(3,'adds','adsd','adsd','Consumer', 80.15, 1),
(4,'sds','dsad','add','Vendor', 150.78, 1),
(5,'fgg','fgg','fgg','Vendor', 123.92, 1),
(6,'zahra','hahaha','q223','Vendor', 32.16, 1),
(7,'hgsgs','shahhs','shjahs','Consumer', 72.54, 1),
(8,'Matin','szfdxcgvjhbkl','password','Vendor', 123.89, 1),
(9,'erfuun','erfuun.mi82@gmail.com','Erfan159753','Admin', 78.56, 1),
(10, 'a', 'a.email.com', 'a', 'Costumer', 100, 1),
(11, 'sajjad', 'sajjadjh1403@gmail.com', 'sajjad', 'Costumer', 125, 1),
(12, 'admin', 'empty', 'admin', 'admin', 10000, 1);
