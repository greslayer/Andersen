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
    Coffee_id INT NOT NULL REFERENCES Coffee(Id),
    Volume_id INT NOT NULL REFERENCES Volume(Id),
    Water_amount INT NOT NULL,
    Milk_amount INT NOT NULL,
    Beans_amount INT NOT NULL
);

CREATE TABLE Coffee_log
(
	Id INT PRIMARY KEY,
    Coffee_contents_id INT NOT NULL REFERENCES Coffee_contents(Id),
    State VARCHAR(20) NOT NUll,
    Made_at DATETIME NOT NULL
);
```
