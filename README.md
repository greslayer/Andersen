MySQL Database for Logging Coffee Machine app

```MySQL
CREATE DATABASE Coffee;
USE Coffee;
CREATE TABLE Volume
(
    Id INT PRIMARY KEY,
    Size VARCHAR(10) NOT NULL UNIQUE,
    Volume INT NOT NULL
);
CREATE TABLE Coffee
(
    Id INT PRIMARY KEY,
    Title Varchar(20) NOT NULL UNIQUE
);

CREATE TABLE Coffee_contents
(
    Id INT PRIMARY KEY,
    Coffee_id INT NOT NULL,
    Volume_id INT NOT NULL,
    Water_amount INT NOT NULL,
    Milk_amount INT NOT NULL,
    Beans_amount INT NOT NULL,
    FOREIGN KEY(Coffee_id) REFERENCES coffee(Id) ON DELETE RESTRICT,
    FOREIGN KEY(Volume_id) REFERENCES Volume(id) ON DELETE RESTRICT
);

CREATE TABLE Coffee_logcoffee
(
    Id INT PRIMARY KEY,
    Coffee_contents_id INT NOT NULL,
    State VARCHAR(20) NOT NUll,
    Made_at DATETIME NOT NULL,
    FOREIGN KEY(Coffee_contents_id) references coffee_contents(id) ON DELETE RESTRICT
);
	
```
Course work from university
``` MySQL
	CREATE DATABASE TSZH;
USE TSZH;

CREATE TABLE Flat(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    HouseNumber INT NOT NULL,
	Area INT NOT NULL,
    Entrance INT NOT NULL,
    Phone INT
);

CREATE TABLE Payments(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    Amount INT NOT NULL,
    Flat INT NOT NULL,
    FOREIGN KEY(Flat) References Flat(ID) ON DELETE RESTRICT
);

CREATE TABLE Person(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    SurName VARCHAR(50),
    DateOfBitrth DATE NOT NULL,
    RegisterDate DATE NOT NULL,
    Flat INT NOT NULL,
    FOREIGN KEY(Flat) REFERENCES Flat(ID) ON DELETE RESTRICT
);

CREATE TABLE Service(
	Title VARCHAR(50) PRIMARY KEY,
	MeasureUnit VARCHAR(20)
);

CREATE TABLE Rate(
	Title VARCHAR(50) PRIMARY KEY,
    FromPeople INT,
    ToPeople INT,
    FromArea INT,
    ToArea INT,
    Floor INT,
	DeviceNeeded BOOL        
);
CREATE TABLE Prices(
	ID INT PRIMARY KEY AUTO_INCREMENT,
	Service VARCHAR(50),
    Rate VARCHAR(50),
    PriceForUnit INT,
    FOREIGN KEY (Service) REFERENCES Service(Title) ON DELETE RESTRICT,
    FOREIGN KEY  (Rate) REFERENCES Rate(Title) ON DELETE RESTRICT
);
CREATE TABLE Subscriptions(
	ID INT PRIMARY KEY AUTO_INCREMENT,
    Price INT NOT NULL,
    Flat INT NOT NULL,
    Enabled BOOLEAN NOT NULL,
    DateOfSubscription DATE NOT NULL,
    FOREIGN KEY (Price) REFERENCES Prices(ID) ON DELETE RESTRICT,
    FOREIGN KEY (Flat) REFERENCES Flat(ID) ON DELETE RESTRICT
);
```
