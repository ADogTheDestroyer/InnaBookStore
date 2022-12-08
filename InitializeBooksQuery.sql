use lookinnabookdb;

CREATE TABLE Books(
	isbn VARCHAR(30) NOT NULL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	pages INT NOT NULL,
	royalty DOUBLE,
	stock INT,
    genre1 VARCHAR(30),
    genre2 VARCHAR(30),
    genre3 VARCHAR(30)
);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (1, 'Lord of The Rings', '500', 10.0, 100, 'fantasy', 'adventure', 'medival');

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (2, 'Harry Potter', '700', 20.0, 88, 'fantasy', 'magic', 'adventure');

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (3, 'Life of Pi', '200', 5.0, 21, 'adventure', null, null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (4, 'Kite Runner', '233', 3.0, 19, 'war', null, null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (5, 'The Hobbit', '344', 21.7, 78, 'fantasy', 'adventure', null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (6, 'Geronimo Stilton', '50', 10.0, 100, 'kids', 'adventure', null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (7, 'Magic Tree House', '109', 5.5, 150, 'magic', 'adventure', 'kids');

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (8, 'Fifty Shades of Grey', '500', 10.0, 11, 'romance', 'erotic', null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (9, 'Pride and Prejudice', '276', 7.0, 22, 'romance', null, null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (10, 'Dictionary', '800', 0.5, 99, 'non-fiction', 'education', null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (11, 'Atlas', '88', 2.2, 40, 'non-fiction', 'education', 'geography');

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (12, 'Spider-man', '21', 12.2, 28, 'comic', 'superhero', 'kids');

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (13, 'Daredevil', '23', 12.2, 54, 'comic', 'superhero', null);

INSERT INTO Books (isbn, title, pages, royalty, stock, genre1, genre2, genre3)
VALUES (14, 'X-men', '25', 12.2, 100, '49', 'superhero', null);
