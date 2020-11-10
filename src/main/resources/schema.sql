DROP TABLE IF EXISTS color;

CREATE TABLE color (
 id INT PRIMARY KEY,
 name VARCHAR (255) NOT NULL
);

DROP TABLE IF EXISTS person;

CREATE TABLE person (
 id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 first_name varchar(225),
 last_name varchar(225),
 city varchar(225),
 zip_code int,
 color int,
 FOREIGN KEY (color) REFERENCES color(id)
);