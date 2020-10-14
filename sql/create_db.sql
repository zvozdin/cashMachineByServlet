DROP DATABASE IF EXISTS final_project_db;

CREATE DATABASE final_project_db;

USE final_project_db;

CREATE TABLE roles
(
	id INT AUTO_INCREMENT NOT NULL,
	role VARCHAR(20) UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE users
(
	id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
    role_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON update CASCADE
);

CREATE TABLE stock
(
	id INT AUTO_INCREMENT NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
	name VARCHAR(20) NOT NULL,
    name_UA VARCHAR(20) NOT NULL,
    size VARCHAR(20) NOT NULL NULL,
    quantity INT NOT NULL,
    price double NOT NULL,
	PRIMARY KEY (id)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE checks
(
	id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    check_code INT unique NOT NULL,
	dataTime DATETIME DEFAULT NOW(),
	PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE orders
(
	id INT AUTO_INCREMENT NOT NULL,
    check_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price double NOT NULL,
    bill double NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (check_id) REFERENCES checks(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES stock(id)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

insert into roles
(role) 
values 
('SENIOR_CASHIER'),
('CASHIER'),
('COMMODITY_EXPERT');

insert into users
(login, password, role_id) 
values 
('admin', '0', 1),
('cashier1', '0', 2),
('cashier2', '0', 2),
('expert1', '0', 3);

insert into stock
(code, name, name_UA, size, quantity, price) 
values 
('1001', 'Jacket', 'Куртка', 'L', 20, 50),
('1002', 'Jacket', 'Куртка', 'M', 30, 50),
('2001', 'Shirt', 'Сорочка', 'S', 15, 20),
('2002', 'Shirt', 'Сорочка', 'L', 15, 20),
('3001', 'T-shirt', 'Футболка', 'S', 30, 30),
('3002', 'T-shirt', 'Футболка', 'M', 45, 30),
('4001', 'Pullover', 'Светр', 'S', 80, 60),
('4002', 'Pullover', 'Светр', 'M', 60, 60),
('5001', 'Hat', 'Капелюх', 'S', 30, 40),
('5002', 'Hat', 'Капелюх', 'L', 25, 40);

insert into checks
(user_id, check_code) 
values 
(3, 1000),
(2, 1011),
(2, 1012);

insert into orders
(check_id, product_id, quantity, price, bill)
values 
(1, 1, 5, 20, quantity * price),
(1, 3, 7, 15, quantity * price),
(1, 5, 3, 10, quantity * price),
(2, 1, 5, 20, quantity * price),
(2, 1, 5, 20, quantity * price),
(3, 9, 2, 40, quantity * price);

DELIMITER | 
DROP TRIGGER IF EXISTS delete_product;|
DELIMITER |
CREATE TRIGGER delete_product
after DELETE ON orders 
FOR EACH ROW 
  BEGIN
    update stock set quantity = quantity + old.quantity where stock.id = old.product_id;
 END;
    |
    
DELIMITER | 
DROP TRIGGER IF EXISTS delete_check;|
DELIMITER |
CREATE TRIGGER delete_check
BEFORE DELETE ON checks 
FOR EACH ROW 
  BEGIN
    delete from orders  where check_id = old.id;
 END;
    |
    
DELIMITER | 
DROP TRIGGER IF EXISTS insert_orders;|
DELIMITER |
CREATE TRIGGER insert_orders
after insert ON orders 
FOR EACH ROW 
  BEGIN
    update stock set quantity = quantity - new.quantity where stock.id = new.product_id;
 END;
    |
