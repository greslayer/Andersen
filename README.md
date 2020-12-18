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
