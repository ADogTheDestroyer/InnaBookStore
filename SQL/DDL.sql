--DDL to define tables
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
    province VARCHAR(15) NOT NULL,
    postal VARCHAR(6) NOT NULL,
    country VARCHAR(25) NOT NULL

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
    phone_num BIGINT NOT NULL CHECK (phone_num between 0 and 9999999999),
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
    username VARCHAR(30), FOREIGN KEY (username) REFERENCES Users (username),
    addr_id INT, FOREIGN KEY (addr_id) REFERENCES Addresses (addr_id),
    isShipping BOOLEAN NOT NULL,
    isBilling BOOLEAN NOT NULL,
    PRIMARY KEY(username, addr_id)
);

CREATE TABLE Orders(
    order_num INT PRIMARY KEY,
    tracking_num VARCHAR(10) NOT NULL,
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
    gname VARCHAR (20),
    PRIMARY KEY(isbn, gname)
);

CREATE TABLE Authored(
    aid INT NOT NULL, FOREIGN KEY (aid) REFERENCES Authors (aid),
    isbn VARCHAR(13) NOT NULL, FOREIGN KEY (isbn) REFERENCES Books (isbn),
    PRIMARY KEY(aid, isbn)
);

CREATE TABLE In_Orders(
    isbn VARCHAR(13), FOREIGN KEY (isbn) REFERENCES Books (isbn),
    order_num INT, FOREIGN KEY (order_num) REFERENCES Orders (order_num),
    quantity INT NOT NULL,
    PRIMARY KEY(isbn, order_num)
);