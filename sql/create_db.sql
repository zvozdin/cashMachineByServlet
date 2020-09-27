DROP DATABASE IF EXISTS final_project_db;

CREATE DATABASE final_project_db;

USE final_project_db;

CREATE TABLE roles
(
	id INT AUTO_INCREMENT NOT NULL,
	role VARCHAR(20) UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE senior_cashier
(
	id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
    role_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE cashiers
(
	id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
    role_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE commodity_experts
(
	id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
    role_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE products
(
	id INT AUTO_INCREMENT NOT NULL,
    code VARCHAR(20) UNIQUE NOT NULL,
	name VARCHAR(20) UNIQUE NOT NULL,
    size VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    price double NOT NULL,
    expert_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (expert_id) REFERENCES commodity_experts(id) ON update CASCADE
);

CREATE TABLE orders
(
	id INT AUTO_INCREMENT NOT NULL,
    order_code VARCHAR(20) UNIQUE NOT NULL,
	name VARCHAR(20) UNIQUE NOT NULL,
    quantity INT NOT NULL,
    is_paid boolean not null,
    status boolean NOT NULL,
    bill INT NOT NULL,
    user_id INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES cashiers(id)
);

CREATE TABLE report
(
	id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    quantity INT NOT NULL,
    sum INT NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES cashiers(id)
);

insert into roles(role) values ('SENIOR_CASHIER');
insert into roles(role) values ('CASHIER');
insert into roles(role) values ('COMMODITY_EXPERT');

insert into senior_cashier(role_id, login, password, name, surname) 
values (1, 'admin', 'admin', 'Admin', 'Adminovich');

insert into cashiers(role_id, login, password, name, surname) 
values (2, 'cashier1', '11111', 'Kasyr1', 'Kasyrov');
insert into cashiers(role_id, login, password, name, surname) 
values (2, 'cashier2', '22222', 'Kasyr2', 'Kasyrenko');
insert into cashiers(role_id, login, password, name, surname) 
values (2, 'cashier3', '33333', 'Kasyr3', 'Kasyrovych');

insert into commodity_experts(role_id, login, password, name, surname) 
values (3, 'expert1', '00000', 'Tovaroznavets', 'Tovaroznavets');
insert into commodity_experts(role_id, login, password, name, surname) 
values (3, 'expert2', '99999', 'Tovaroznatok', 'Tovaroznatok');

insert into products(code, name, size, quantity, price, expert_id) 
values ('1001', 'jacket', 'L', 20, 50, 1);
insert into products(code, name, size, quantity, price, expert_id) 
values ('1002', 'jacket', 'M', 30, 50, 1);
insert into products(code, name, size, quantity, price, expert_id) 
values ('2001', 'shirt', 'S', 15, 20, 1);
insert into products(code, name, size, quantity, price, expert_id) 
values ('2002', 'shirt', 'L', 20, 20, 2);