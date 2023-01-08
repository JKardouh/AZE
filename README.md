# Employee time tracking tool
## Team work project for programming module
TODO


Make the DB with commands

create database aze_db;

CREATE TABLE `aze_db`.`worker_account` (

  `account_id` INT NOT NULL AUTO_INCREMENT,
  
  `firstname` VARCHAR(45) NOT NULL,
  
  `lastname` VARCHAR(45) NOT NULL,
  
  `username` VARCHAR(45) NOT NULL,
  
  `password` VARCHAR(45) NOT NULL,
  
  PRIMARY KEY (`account_id`),
  
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE,
  
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
  



SELECT * FROM worker_account;


INSERT INTO worker_account (firstname, lastname, username, password) VALUES ('some','ones','name','pass');

