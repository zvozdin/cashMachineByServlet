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
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
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
    code VARCHAR(20) UNIQUE NOT NULL,
	name_en VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    price double NOT NULL,
    is_paid boolean NOT NULL,
    bill INT NOT NULL,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    dataTime DATETIME DEFAULT NOW(),
	PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES stock(id)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE report
(
	id INT AUTO_INCREMENT NOT NULL,
    orders_count INT NOT NULL,
	common_bill INT NOT NULL,
	PRIMARY KEY (id)
);

insert into roles(role) values ('SENIOR_CASHIER');
insert into roles(role) values ('CASHIER');
insert into roles(role) values ('COMMODITY_EXPERT');

insert into users(login, password, role_id) 
values ('admin', 'admin', 1);
insert into users(login, password, role_id) 
values ('cashier1', '11111', 2);
insert into users(login, password, role_id) 
values ('cashier2', '77777', 2);
insert into users(login, password, role_id) 
values ('expert1', '00000', 3);

insert into stock(code, name, size, quantity, price) 
values ('1001', 'jacket', 'L', 20, 50);
insert into stock(code, name, size, quantity, price) 
values ('1002', 'jacket', 'M', 30, 50);
insert into stock(code, name, size, quantity, price) 
values ('2001', 'shirt', 'S', 15, 20);
insert into stock(code, name, size, quantity, price) 
values ('2002', 'shirt', 'L', 20, 20);

insert into checks(code, name_en,  quantity, price, is_paid,bill,user_id,product_id) 
values ('1001', 'jacket', 20, 50, false, 500,1,1);

SELECT * FROM final_project_db.checks;
