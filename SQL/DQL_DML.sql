--Addreses
select * from addresses

--Authors
select * from authors

--Books
select * from books
    --get books by title
select * from books where title = '" + title + "'
    --get books by author
SELECT * FROM books INNER JOIN  authored ON books.isbn = authored.isbn INNER JOIN authors ON authored.aid = authors.aid WHERE authors.fname = '"+fname+"' AND authors.lname = '"+lname+"'
    --get books by genre
SELECT books.* FROM books INNER JOIN genres ON books.isbn = genres.isbn WHERE genres.gname = '" + genre + "'
    --get books by publisher
SELECT books.* FROM books INNER JOIN publishers ON books.pid = publishers.pid WHERE publishers.pid = '" + publisherId + "'
    --get books by isbn
select * from books where isbn = '" + isbn + "'

--Genres
select * from Genres
    --get genres by name
select * from Genres where gname = '" + genre + "'

--In_Orders
    --get the quantity of a single book in a single order
SELECT COUNT(*) AS count FROM in_orders WHERE isbn = '" + isbn + "' AND order_num = '" + orderNum + "'
    --insert new oder with quantity 1
INSERT INTO in_orders (isbn, order_num, quantity) VALUES ( '"+isbn+"', '"+orderNum+"', '"+1+"' )
    --update quantity value of newly added order
UPDATE in_orders SET quantity = quantity + 1 WHERE isbn = '"+isbn+"' AND order_num = '"+orderNum+"'

--Orders
select * from orders
    --get orders, books and order quantites by username
select orders.order_num, orders.tracking_num, orders.ord_date, orders.ord_cost, books.isbn, books.title, books.price, in_orders.quantity from orders INNER JOIN in_orders ON orders.order_num = in_orders.order_num INNER JOIN books ON in_orders.isbn = books.isbn WHERE orders.username  = '" + username + "'
    --get orders from last month
select * from orders where ord_date >= '" + startOfMonth + "' AND ord_date < '" + endOfMonth + "'
    --update stock value of a book
UPDATE Books SET stock= '" + newStock + "' WHERE isbn= '" + myArr.get(i)[0] + "'
    --get order count
SELECT COUNT(*) AS count FROM Orders
    --insert new order value
INSERT INTO Orders (order_num, tracking_num, ord_date, ord_cost, username) VALUES ( '"+orderId+"', '"+generateTrackingNum()+"', '"+LocalDate.now()+"', '"+computeCost(basket)+"', '"+username+"')

--Phone_numbers
select * from phone_numbers

--Publishers
select * from publishers

--Users
select * from users
    --get user by username and password
select * from users WHERE username = '" + username + "' AND pword = '" + password + "'
    --insert new user
INSERT INTO users(username,pword,fname,lname,isowner) VALUES('"+UInput+"','"+PInput+"','"+FNInput+"','"+LNInput+"','"+false+"')
select * from addresses
    --insert new address for user
INSERT INTO addresses(addr_id,street_name,street_num,unit,city,province,postal,country) VALUES('"+newid+"','"+input1+"','"+input2+"','"+input3+"','"+input4+"','"+input5+"','"+input6+"','"+input7+"')
    --insert user's new address in user_addrs
INSERT INTO user_addrs(username,addr_id,isshipping,isbilling) VALUES('"+userN+"','"+newid+"','"+ship+"','"+bill+"')
