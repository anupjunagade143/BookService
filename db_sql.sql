create database bookservice

CREATE TABLE bookservice.book (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(30) NOT NULL,
description VARCHAR(30) NOT NULL,
author VARCHAR(30) NOT NULL,
rating int,
)

select * from bookservice.book

insert into bookservice.book(name,description,author,rating) values ('physics' , 'molecular science' , 'apj abdul' , 5)
insert into bookservice.book(name,description,author,rating) values ('maths' , 'algebra and geometry' , 'junagade' , 7)

