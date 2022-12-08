
USE LookInnaBookDb;
CREATE TABLE Users(
	uname VARCHAR(30) NOT NULL PRIMARY KEY,
	pword VARCHAR(30) NOT NULL,
	fname VARCHAR(30) NOT NULL,
	lname VARCHAR(30),
	isOwner BOOLEAN NOT NULL
);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Robo.adam10', 'password', 'Adam', 'Lin', true);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Jimbo234', 'sdfnsljk', 'Jim', 'Harper', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Manny290', 'zxcmno', 'Mannuel', 'Rodriguez', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Damion4093', 'asdfaf', 'Damion', 'Smith', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Daniel9009', 'sldkfjowiej', 'Daniel', 'Burtnick', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('KingSully', 'sodijfoweinf', 'Seleman', 'Shinwarie', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('PerditaTheDog', 'sdifjoiejofisj', 'Perdita', 'Dalmatian', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('ForrestPlump', 'foeifnksjfnieu', 'Forrest', 'Gump', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('SavingMe', 'sldkfjoeinf', 'Private', 'Ryan', false);

INSERT INTO Users (uname, pword, fname, lname, isOwner)
VALUES ('Leon', 'soeifmoeimfos', 'Leonard', 'Hoffstater', false);