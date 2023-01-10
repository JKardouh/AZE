# Employee time tracking tool
## Team work project for programming module
TODO
## projectname
AZE - Arbeitszeiterfassung

## brief description of our project
AZE (Arbeitszeiterfassung) is a timemanagment and tracking tool for the Employsees of an office, assisting the Employees in tracking and managing their workhours, tasks, exceptions like vacation time or sick leave, etc.

## group memebers
* Judy Kardouh
* Alexander Kronheiser
* Fatima Jawad

## The functionalities include:
* (x) registering Employees as Users & assigning UIDs
* (x) login of exiting Users
* (x) defining start and end of worktime
* (x) defining start and end of breaks
* (x) calculating work time including breaktime
* ( ) calculationg total work time exluding the breaktime
* (x) the option to choose the workmode from a list ("office", "homeoffice", "vacation", "Sick Leave", "religious days", "time compensation")
* ( ) viewing the collected data and calculated worktime in a seperate stage
* ( ) entering and saving notes and defining work tasks

# Overview of UI stages and functionalities:
## login:
![image](https://user-images.githubusercontent.com/121894511/211634127-f4fe5d2b-052e-48ab-8a47-3a8693dff5a3.png)

After running the program, the login scene starts. An existing User has the chance to login

## register:
![image](https://user-images.githubusercontent.com/121894511/211634355-c5c90e19-1088-47c1-bd70-ecb3ebbcfc72.png)


## timesheet:
![image](https://user-images.githubusercontent.com/121894511/211634500-1fe99882-a074-4d51-b2ab-c913ff28e222.png)





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

