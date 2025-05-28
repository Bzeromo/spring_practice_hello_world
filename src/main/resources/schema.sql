-- drop table if exists user;

create table if not exists users (
    userId varchar(18) not null,
    password varchar(18) not null,
    name varchar(10) not null,
    email varchar(50) not null,
    createdAt datetime default current_timestamp,
    primary key (userId)
);