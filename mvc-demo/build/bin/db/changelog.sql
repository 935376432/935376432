--liquibase formatted sql

--changeset dbtest:1
create table tbl_user (
    id int auto_increment,
    login_name varchar(64),
    password varchar(64),
    name varchar(128)
);

--changeset dbtest:2
insert into tbl_user (login_name, password, name)
    values ('admin', 'admin', 'Administrator');
insert into tbl_user (login_name, password, name)
    values ('viewer', 'viewer', 'Read-only User');