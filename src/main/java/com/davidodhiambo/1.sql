
drop table if exists employee;
drop table if exists account;
drop table if exists customer;
drop table if exists chekingaccont;
drop table if exists checkingaccount;
drop table if exists savingsaccount;



create table if not exists employee(
    id int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null UNIQUE,
    password char(32) not null

);
CREATE TABLE IF NOT EXISTS checkingaccount(
    accnumber int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null UNIQUE,
    password char(32) not null,
    deposit double,
    balance double
);
CREATE TABLE IF NOT EXISTS savingsaccount(
    accnumber int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null UNIQUE,
    password char(32) not null,
    deposit double,
    balance double

);
insert into checkingaccount(fname,lname,email,password,deposit,balance) values('John','Doe', 'jd@mail.com','secret',1000,1000);

#display all the accounts
select * from checkingaccount;
select * from savingsaccount;

