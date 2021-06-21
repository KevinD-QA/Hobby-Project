DROP TABLE IF EXISTS Heroes cascade;

CREATE TABLE Heroes(
heroID INT auto_increment,
heroName VARCHAR(255) NOT NULL,
Element VARCHAR(255)  NOT NULL,
Weapon VARCHAR(255) NOT NULL,
Levels int,
PRIMARY KEY (heroID));