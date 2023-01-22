# Employee time tracking tool
## Team work project for programming module
TODO
## projectname
AZE - Arbeitszeiterfassung

## brief description of our project
AZE (Arbeitszeiterfassung) is a timemanagment and tracking tool for the Employees of an office, assisting the Employees in tracking and managing their workhours, tasks, exceptions like vacation time or sick leave, etc.

## group members
* Judy Kardouh
* Alexander Kroneiser
* Fatima Jawad

## The functionalities include:
* (x) registering Employees as Users & assigning UIDs
* (x) login of existing Users
* (x) query unique employee ID
* (x) defining start and end of worktime
* (x) defining start and end of breaks
* ( ) calculating work time including breaktime
* ( ) calculating total work time exluding the breaktime
* (x) the option to choose the workmode from a list ("office", "homeoffice", "vacation", "sick Leave", "religious days", "time compensation")
* ( ) viewing the collected data and calculated worktime in a seperate stage
* ( ) entering and saving notes and defining work tasks

# Overview of UI stages and functionalities:
## login:
![image](https://user-images.githubusercontent.com/121894511/211634127-f4fe5d2b-052e-48ab-8a47-3a8693dff5a3.png)

After running the program, the login scene starts. An existing User has the chance to login

## register:
![image](https://user-images.githubusercontent.com/121894511/211634355-c5c90e19-1088-47c1-bd70-ecb3ebbcfc72.png)

stage linked to login, registering a new user to the database with UID, firstname, lastname, password + password confirmation

## timesheet:
![image](https://user-images.githubusercontent.com/121894511/211634500-1fe99882-a074-4d51-b2ab-c913ff28e222.png)

linked stage for user to collect working relevant data like start and end of worktime, start and end of breaks, workmode, to be retrieved mySQL and displayed in an upcoming additional stage


## mySQL workbench

Make the DB with commands
~~~~sql
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

CREATE TABLE `aze_db`.`timesheet_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `event_type` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `comment` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
~~~~
  


examples of some commands  

SELECT * FROM worker_account;

INSERT INTO worker_account (firstname, lastname, username, password) VALUES ('some','ones','name','pass');

