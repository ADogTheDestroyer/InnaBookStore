--Creating Tables
CREATE TABLE Authors(
                       aid INT PRIMARY KEY,
                       fname VARCHAR(30) NOT NULL,
                       lname VARCHAR(30) NOT NULL
);

CREATE TABLE Addresses(
                        addr_id INT PRIMARY KEY,
                        street_name VARCHAR(50) NOT NULL,
                        street_num INT NOT NULL,
                        unit INT,
                        city VARCHAR(50) NOT NULL,
                        province VARCHAR(2) NOT NULL,
                        postal VARCHAR(6) NOT NULL

);

CREATE TABLE Publishers(
                          pid INT PRIMARY KEY,
                          fname VARCHAR(30) NOT NULL,
                          lname VARCHAR(30) NOT NULL,
                          email VARCHAR(50) NOT NULL,
                          bank_acc INT NOT NULL,
                          addr_id INT NOT NULL, FOREIGN KEY (addr_id) REFERENCES Addresses (addr_id)
);

CREATE TABLE Phone_numbers(
                             pid INT, FOREIGN KEY (pid) REFERENCES Publishers (pid),
                             phone_num INT NOT NULL CHECK (phone_num between 0 and 9999999999),
                             PRIMARY KEY (pid, phone_num)
);

CREATE TABLE Books(
                     isbn VARCHAR(13) PRIMARY KEY,
                     title VARCHAR(100) NOT NULL,
                     pages INT NOT NULL,
                     price NUMERIC(5, 2) NOT NULL,
                     royalty_percent NUMERIC(4, 2) NOT NULL,
                     stock INT NOT NULL,
                     pid INT NOT NULL, FOREIGN KEY (pid) REFERENCES Publishers (pid)

);

CREATE TABLE Users(
                      username VARCHAR(30) PRIMARY KEY,
                      pword VARCHAR(30) NOT NULL,
                      fname VARCHAR(30) NOT NULL,
                      lname VARCHAR(30) NOT NULL,
                      isOwner BOOLEAN NOT NULL
);

CREATE TABLE User_addrs(
                          username VARCHAR(30) NOT NULL, FOREIGN KEY (username) REFERENCES Users (username),
                          addr_id INT NOT NULL, FOREIGN KEY (addr_id) REFERENCES Addresses (addr_id),
                          PRIMARY KEY(username, addr_id)
);

CREATE TABLE Orders(
                       order_num INT PRIMARY KEY,
                       tracking_num INT NOT NULL,
                       ord_date DATE NOT NULL,
                       ord_cost NUMERIC(8,2) NOT NULL,
                       username VARCHAR(30) NOT NULL, FOREIGN KEY (username) REFERENCES Users (username)
);

CREATE TABLE Order_addrs(
                           order_num INT, FOREIGN KEY (order_num) REFERENCES Orders (order_num),
                           addr_id INT,FOREIGN KEY (addr_id) REFERENCES Addresses (addr_id),
                           PRIMARY KEY (order_num, addr_id)
);

CREATE TABLE Genres (
                       isbn VARCHAR(13), FOREIGN KEY (isbn) REFERENCES Books (isbn),
                       name VARCHAR (20),
                       PRIMARY KEY(isbn, name)
);

CREATE TABLE Authored(
                         aid INT NOT NULL, FOREIGN KEY (aid) REFERENCES Authors (aid),
                         isbn VARCHAR(13) NOT NULL, FOREIGN KEY (isbn) REFERENCES Books (isbn),
                         PRIMARY KEY(aid, isbn)
);

-- Inserting Values
INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Robo.adam10', 'password', 'Adam', 'Lin', true);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('ColeKaufs', 'password', 'Cole', 'Kaufman', true);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Jimbo234', 'sdfnsljk', 'Jim', 'Harper', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Manny290', 'zxcmno', 'Mannuel', 'Rodriguez', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Damion4093', 'asdfaf', 'Damion', 'Smith', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Daniel9009', 'sldkfjowiej', 'Daniel', 'Burtnick', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('KingSully', 'sodijfoweinf', 'Seleman', 'Shinwarie', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('PerditaTheDog', 'sdifjoiejofisj', 'Perdita', 'Dalmatian', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('ForrestPlump', 'foeifnksjfnieu', 'Forrest', 'Gump', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('SavingMe', 'sldkfjoeinf', 'Private', 'Ryan', false);

INSERT INTO Users (username, pword, fname, lname, isOwner)
VALUES ('Leon', 'soeifmoeimfos', 'Leonard', 'Hoffstater', false);