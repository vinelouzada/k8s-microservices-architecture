create table users
(
    id         bigint primary key auto_increment,
    name       varchar(100) not null,
    cpf        varchar(100) not null,
    address    varchar(100) not null,
    email      varchar(100) not null,
    phone      varchar(100) not null,
    created_at timestamp    not null
);