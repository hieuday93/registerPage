create database if not exists testDB;
use testDB;
drop table if exists testDB.members;
create table if not exists members (
	member_id int auto_increment,
    mobile_number varchar(20) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    date_of_birth date,
    gender tinyint(1),
    email varchar(255) not null unique,
    primary key(member_id)
);
