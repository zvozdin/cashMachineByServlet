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
	role_id INT AUTO_INCREMENT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
	PRIMARY KEY (login),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE cashiers
(
	role_id INT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
	PRIMARY KEY (login),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE commodity_experts
(
	role_id INT NOT NULL,
    login VARCHAR(20) UNIQUE,
    password VARCHAR(20),
	name VARCHAR(20),
    surname VARCHAR(20),
	PRIMARY KEY (login),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

insert into roles(role) values ('SENIOR_CASHIER');
insert into roles(role) values ('CASHIER');
insert into roles(role) values ('COMMODITY_EXPERT');

insert into senior_cashier(login, password, name, surname) 
values ('admin', 'admin', 'Admin', 'Adminovich');

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