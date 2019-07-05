DROP DATABASE Sklad;

CREATE DATABASE Sklad;

use Skald;

CREATE TABLE uchet (product_code int NOT NULL auto_increment, product varchar(45) NOT NULL,  price int NOT NULL, date_of_delivery date NOT NULL,  date_of_sale date, amount int, PRIMARY KEY(product_code));

insert into uchet (product , price, date_of_delivery, date_of_sale, amount) values('Gvozdi', 30, '2019-05-10', '2019-05-15', 100);

insert into uchet (product , price, date_of_delivery, date_of_sale, amount) values('Bolti', 20, '2019-05-10', '2019-05-15', 250);

CREATE TABLE uchet_type(product_code int NOT NULL auto_increment,product_type VARCHAR(45) NOT NULL,PRIMARY KEY(product_code));

ALTER TABLE  uchet_type ADD FOREIGN KEY (product_code)
REFERENCES uchet (product_code);

insert into uchet_type(product_code,product_type) values(1,'Инструмент');

alter table uchet_type drop foreign key uchet_type_ibfk_1;
                                                                                                               
ALTER TABLE  uchet_type ADD FOREIGN KEY (product_code) references uchet(product_code) on delete cascade;
