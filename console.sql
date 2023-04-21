create database pizzeria;


create table role (
    role_id int primary key ,
    role_name varchar(5)
);

create table pizza
( pizza_id serial primary key,
    name varchar (128),
    cost numeric (8, 2));


create table users
(
    user_id     serial primary key,
    role        int REFERENCES role (role_id),
    password    varchar(128),
    person_name varchar(128),
    phone       varchar(128)
);

create table orders
( order_id serial primary key,
    customer_id int,
    pizza_name_id int,
    date_get timestamp,
    FOREIGN KEY (customer_id) REFERENCES users (user_id),
    FOREIGN KEY (pizza_name_id) REFERENCES pizza (pizza_id)
);

create table delivery_list
(
    delivery_id serial primary key,
    order_id int,
    date_arrived timestamp,
    delivered_in_time varchar(3),
    payment_method varchar(4),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);
insert into users(person_name, password, phone, role)
values ('Иван Иванов','135468', '+375333827732',1),
       ('Петр Петров','1332168', '+375329827732',2),
       ('Светлана Светикова','14358', '+375333824332',2),
       ('Иван Кожемякин','51325468', '+375333827154',2),
       ('Максим Комсомольцев','1321458', '+375333827226',2),
       ('Роман Дронов','1123548', '+375333820912',1),
       ('Лариса Тельникова','321338', '+375333820093',2),
       ('Кирилл Сарычев','154668', '+375333826521',2),
       ('Юлия Швец','1254768', '+375333821742',2);

insert into pizza(name, cost)
values ('Цыплёнок барбекю', 195),
       ('Ранч пицца', 123),
       ('Острая чили', 156),
       ('Пепперони', 200),
       ('Цыплёнок дорблю', 250),
       ('Гавайская', 360);


insert into orders(customer_id, pizza_name_id,date_get)
values (1, 2,'2023-02-16T14:30'),
       (2, 1,'2023-01-18T19:00'),
       (3, 3,'2023-01-25T15:00'),
       (4, 3,'2022-06-14T11:23'),
       (5, 5,'2022-05-16T12:46'),
       (6, 4,'2023-04-04T20:14'),
       (7, 5,'2023-02-12T22:56'),
       (8, 1,'2023-01-26T08:12'),
       (9, 6,'2022-10-25T11:35'),
       (5, 2,'2021-08-13T10:48'),
       (2, 4,'2022-09-06T13:11');

insert into delivery_list(order_id, date_arrived, delivered_in_time, payment_method)
values (1, '2023-02-16T15:30', 'Yes', 'Card'),
       (2, '2023-01-18T19:59', 'Yes', 'Card'),
       (3, '2023-01-25T15:45', 'Yes', 'Cash'),
       (4, '2022-06-14T12:35', 'No', 'Null'),
       (5, '2022-05-16T13:21', 'Yes', 'Cash'),
       (6, '2023-04-04T21:00', 'Yes', 'Card'),
       (7, '2023-02-12T23:45', 'Yes', 'Cash'),
       (8, '2023-01-26T08:59', 'Yes', 'Card'),
       (9, '2022-10-25T12:40', 'No', 'Null'),
       (10, '2021-08-13T11:25', 'Yes', 'Card'),
       (11, '2022-09-06T14:13', 'No', 'Null');

insert into role(role_id, role)
values (1, 'ADMIN'),
       (2, 'USER');