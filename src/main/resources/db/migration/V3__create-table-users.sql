create table user (
    id bigint not null auto_increment,
    login varchar(100) not null unique,
    password varchar(255) not null,
    role varchar(50) default 'USER',
    primary key (id)
);