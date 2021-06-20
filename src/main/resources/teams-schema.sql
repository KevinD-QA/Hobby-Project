DROP TABLE IF EXISTS Teams cascade;

Create TABLE Teams(
teamID INT auto_increment,
teamName VARCHAR(255) NOT NULL,
PRIMARY KEY (teamID),
FK_HeroID INT,
FOREIGN KEY (FK_HeroID) REFERENCES Heroes(heroID));