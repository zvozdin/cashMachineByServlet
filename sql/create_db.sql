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
('admin', 'admin', 1),
('cashier1', '11111', 2),
('cashier2', '77777', 2),
('expert1', '00000', 3);

insert into stock
(code, name, size, quantity, price) 
values 
('1001', 'jacket', 'L', 20, 50),
('1002', 'jacket', 'M', 30, 50),
('2001', 'shirt', 'S', 15, 20),
('2002', 'shirt', 'L', 20, 20);

insert into checks(user_id, check_code) values (3, 1000);
insert into checks(user_id, check_code) values (2, 1011);
insert into checks(user_id, check_code) values (2, 1010);
insert into checks(user_id, check_code) values (2, 1012);
insert into checks(user_id, check_code) values (3, 1013);
insert into checks(user_id, check_code) values (3, 1014);
insert into checks(user_id, check_code) values (3, 1015);
insert into orders(check_id, product_id, quantity, price, bill) values (1, 1, 1, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (1, 2, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (2, 3, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (2, 4, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (3, 1, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (3, 4, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (4, 4, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (4, 1, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (6, 4, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (6, 4, 100, 1, 1);
insert into orders(check_id, product_id, quantity, price, bill) values (7, 4, 100, 1, 1);

insert into orders(check_id, product_id, quantity, price, bill) values (1, 1, 300, 1, 1);

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
