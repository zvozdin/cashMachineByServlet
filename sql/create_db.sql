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
    FOREIGN KEY (user_id) REFERENCES users(id) ON update CASCADE
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
	FOREIGN KEY (check_id) REFERENCES checks(id),
    FOREIGN KEY (product_id) REFERENCES stock(id) ON update CASCADE
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
insert into orders(check_id, product_id, quantity, price, bill) values (1, 1, 1, 1, 1);
