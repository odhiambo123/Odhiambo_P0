
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

insert into employee(fname, lname, email, password) VALUES ('Xuma', 'Azike','xuma@mail.com','secret');
INSERT INTO employee(fname, lname, email, password) VALUES ('Jayodhi', 'Kai','jbg@mail.com','secret');

###  Store procedure to get employees.

DELIMITER //
CREATE PROCEDURE list_all_my_employees()
BEGIN
    select * from employee;
END //

DELIMITER ;




#display all the accounts
select * from checkingaccount;

select * from savingsaccount;
select * from employee;
select approved from checkingaccount where email = 'df';

select c.email checnkingaccount,
       s.email savingsaccount
from checkingaccount c, savingsaccount s
where c.email = s.email
group by c.email, s.email having count(*) >= 1
order by count(*) desc
;
### SHOW database contents

DELETE FROM employee WHERE email = 'JANBO';

SHOW CREATE TABLE mysql.general_log;


SHOW CREATE TABLE mysql.slow_log;
select * from mysql.general_log;

SELECT @@GLOBAL.log_error_services;
SET GLOBAL log_error_services = 'log_filter_internal; log_sink_internal';
SET PERSIST log_error_services = 'log_filter_internal; log_sink_internal; log_sink_json';


#https://dev.mysql.com/doc/refman/8.0/en/log-destinations.html
SET @old_log_state = @@GLOBAL.general_log; #save the old state of the log
SET GLOBAL general_log = 'ON';#turn off the log temporarily to speed up the process
ALTER TABLE mysql.general_log ENGINE = MyISAM; #change the engine to MyISAM to speed up the process
SET GLOBAL general_log = @old_log_state; #restore the old state of the log
