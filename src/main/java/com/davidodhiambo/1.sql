
drop table if exists employee;
drop table if exists account;
drop table if exists customer;
drop table if exists chekingaccont;
drop table if exists checkingaccount;
drop table if exists savingsaccount;
drop table if exists user;

create table if not exists user(
    id int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null unique,
    password char(32) not null,
    usertype char(16) not null,
    accounttype char(16) not null,
    accountnumber char(16) not null,
    balance decimal(10,2) not null
);

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
    balance double,
    approved int default 0


);
CREATE TABLE IF NOT EXISTS savingsaccount(
    accnumber int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null UNIQUE,
    password char(32) not null,
    deposit double,
    balance double,
    approved int default 0

);
insert into checkingaccount(fname,lname,email,password,deposit,balance) values('John','Doe', 'jd@mail.com','secret',1000,1000);
insert into savingsaccount(fname,lname,email,password,deposit,balance) values('John','Doe', 'jd@mail.com','secret',1000,1000);
insert into checkingaccount(fname,lname,email,password,deposit,balance) values('John','Doe', 'd@mail.com','secret',1000,1000);
insert into savingsaccount(fname,lname,email,password,deposit,balance) values('John','Doe', 'd@mail.com','secret',1000,1000);





#display all the accounts
select * from checkingaccount;

select * from savingsaccount;
select * from employee;

select c.email checnkingaccount,
       s.email savingsaccount
from checkingaccount c, savingsaccount s
where c.email = s.email
group by c.email, s.email having count(*) >= 1
order by count(*) desc
;


