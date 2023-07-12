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

insert into role(role_id, role_name)
values (0, 'ADMIN'),
       (1, 'USER');

insert into pizza(name, cost)
values ('Цыплёнок барбекю', 195),
       ('Ранч пицца', 123),
       ('Острая чили', 156),
       ('Пепперони', 200),
       ('Цыплёнок дорблю', 250),
       ('Гавайская', 360);