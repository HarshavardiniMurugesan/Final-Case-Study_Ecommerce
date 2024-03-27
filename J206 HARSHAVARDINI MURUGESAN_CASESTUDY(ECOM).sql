

CREATE DATABASE CASESTUDY;
USE CASESTUDY;

-- CUSTOMERS TABLE-------------------------------------

CREATE TABLE CUSTOMER(CUSTOMER_ID INT PRIMARY KEY auto_increment,NAME VARCHAR(20) NOT NULL,
EMAIL VARCHAR(20),PASSWORD VARCHAR(20));

INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, EMAIL, PASSWORD) 
VALUES 
(1, 'Alice', 'alice@gmail.com', 'P@ssw0rd'),
(2, 'Bob', 'bob@yahoo.com', 'SecurePass123'),
(3, 'Charlie', 'charlie@gmail.com', 'StrongPass456'),
(4, 'David', 'david@gmail.com', 'Secret123!'),
(5, 'Eve', 'eve@gmail.com', 'P@$$w0rd!');

SELECT * FROM CUSTOMER;
DROP TABLE CUSTOMER;

INSERT INTO CUSTOMER (NAME, EMAIL, PASSWORD) 
VALUES 
( 'Grace', 'grace@gmail.com', 'P@ssw0rd');

-- PRODUCTS TABLE--------------------------------------

CREATE TABLE PRODUCT(PRODUCT_ID INT PRIMARY KEY auto_increment,NAME VARCHAR(20) NOT NULL,PRICE DECIMAL(8,2),
DESCRIPTION VARCHAR(40),STOCKQUANTITY INT);

INSERT INTO PRODUCT (PRODUCT_ID, NAME, PRICE, DESCRIPTION, STOCKQUANTITY) VALUES
(101, 'Laptop', 999.99, 'High-performance laptop', 50),
(102, 'Smartphone', 699.50, 'Flagship smartphone', 100),
(103, 'Headphones', 129.95, 'Noise-canceling headphones', 75),
(104, 'Tablet', 349.75, '10-inch tablet', 30),
(105, 'Wireless Mouse', 29.99, 'Ergonomic wireless mouse', 200);

insert into product(NAME, PRICE, DESCRIPTION, STOCKQUANTITY) values
( 'vision pro', 29.99, 'Ergonomic wireless mouse', 200);

SELECT * FROM PRODUCT;
DROP TABLE PRODUCT;

-- CART TABLE------------------------------------------

CREATE TABLE CART(CART_ID INT PRIMARY KEY auto_increment,CUSTOMER_ID INT,PRODUCT_ID INT,QUANTITY INT,
FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO CART (CART_ID, CUSTOMER_ID, PRODUCT_ID, QUANTITY) VALUES
(201, 1, 101, 2),
(202, 2, 102, 1),
(203, 3, 103, 3),
(204, 4, 104, 2),
(205, 5, 105, 1);

SELECT * FROM CART;
DROP TABLE CART;

INSERT INTO CART (CUSTOMER_ID, PRODUCT_ID, QUANTITY) VALUES
( 1, 101, 222);

-- ORDERS TABLE-----------------------------------------

CREATE TABLE ORDERSS(ORDER_ID INT PRIMARY KEY auto_increment,CUSTOMER_ID INT,ORDER_DATE varchar(20),TOTAL_PRICE DECIMAL(8,2),
SHIPPING_ADDRESS VARCHAR(20),FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) 
ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO ORDERSS(ORDER_ID,CUSTOMER_ID,ORDER_DATE,TOTAL_PRICE,SHIPPING_ADDRESS) VALUES
(301, 1, '2024-03-07', 50.00, 'Chennai'),
(302, 2, '2024-03-08', 75.50, 'Bangalore'),
(303, 3, '2024-03-09', 100.25, 'Pune'),
(304, 4, '2024-03-10', 45.75, 'Coimbatore'),
(305, 5, '2024-03-11', 200.00, 'Hydrebad');


SELECT * FROM ORDERSS;
DROP TABLE ORDERSS;

-- ORDER ITEMS TABLE-------------------------------------

CREATE TABLE ORDER_ITEMS(ORDER_ITEM_ID INT PRIMARY KEY auto_increment,ORDER_ID INT,PRODUCT_ID INT,QUANTITY INT,
FOREIGN KEY(ORDER_ID) REFERENCES ORDERSS(ORDER_ID) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO ORDER_ITEMS(ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY) VALUES
(401, 301, 101, 5),
(402, 302, 102, 7),
(403, 303, 103, 3),
(404, 304, 104, 4),
(405, 305, 105, 2);

SELECT * FROM ORDER_ITEMS;
DROP TABLE ORDER_ITEMS;

select cr.name,c.cart_id,c.product_id,p.name,c.quantity from cart c inner join product p on c.product_id=p.product_id inner join customer cr on cr.customer_id=c.CUSTOMER_ID;

update orderss set order_date='2024-04-14',total_price=5000 where customer_id=1;
update order_items set order_id=305,product_id=101,quantity=9;

UPDATE orderss  SET total_price =(
select Customer.customer_id,p.price*oi.quantity from order_items oi inner join product p
on p.product_id=oi.product_id inner join orderss orderss.order_id=oi.order_id inner join customer
on orderss.customer_id=customer.customer_id
);

UPDATE orderss  SET total_price =(select price from product where);

select c.name,o.*,p.name from orderss o inner join customer c on  c.customer_id=o.customer_id 
inner join product p on p.PRODUCT_ID=cr.PRODUCT_ID join order_items oi on oi.PRODUCT_ID=p.PRODUCT_ID
inner join cart cr on cr.PRODUCT_ID=p.PRODUCT_ID where o.customer_id=2;

update cart set quantity=11 where cart_Id=204;

select product_id from cart where cart_id=204;
update product set name='mobile' where product_id=104;

select o.*,oi.quantity from orderss o inner join order_items oi on oi.ORDER_ID=o.ORDER_ID;

select max(cart_id) from cart;
delete from cart where cart_id=206;

select o.*,oi.quantity,p.name from orderss o inner join order_items oi on oi.ORDER_ID=o.ORDER_ID
inner join product p on p.PRODUCT_ID=oi.PRODUCT_ID;

INSERT INTO ORDER_ITEMS(ORDER_ID, PRODUCT_ID, QUANTITY) VALUES
(301, 101, 5);
select customer_id from customer where Customer_id=2;
select * from product where name="laptop";
select c.name,o.* from orderss o inner join customer c on  c.customer_id=o.customer_id 
inner join order_items oi on oi.ORDER_ID=o.ORDER_ID o.customer_id=2;
insert into order_items(ORDER_ID, PRODUCT_ID, QUANTITY) values (311,101,8);

select name from product where PRODUCT_ID in(select PRODUCT_ID from order_items where ORDER_ID in(
select ORDER_ID from orderss where CUSTOMER_ID=3));
select o.order_id,o.customer_id,c.name,o.order_date,o.total_price from orderss o 
inner join customer c on  c.customer_id=o.customer_id where o.customer_id=3;