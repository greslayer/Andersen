CREATE DATABASE jdbcemployee;
USE jdbcemployee;

CREATE TABLE employee(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(20),
    lastName varchar(20),
    salary varchar(20)
);