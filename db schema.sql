DROP DATABASE Sklad;
CREATE DATABASE Sklad;
use Skald;
CREATE TABLE uchet (product_code int NOT NULL auto_increment, product varchar(45) NOT NULL,  price int NOT NULL, date_of_delivery date NOT NULL,  date_of_sale date, amount int, PRIMARY KEY(product_code));
insert into uchet (product , price, date_of_delivery, date_of_sale, amount) values('Болт', 30, '2019-05-10', '2019-05-15', 100);
insert into uchet (product , price, date_of_delivery, date_of_sale, amount) values('Гвозди', 20, '2019-05-10', '2019-05-15', 250);
