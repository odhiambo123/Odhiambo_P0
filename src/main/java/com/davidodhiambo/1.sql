drop table if exists employee;
drop table if exists transaction;
drop table if exists account;
drop table if exists customer;



create table if not exists employee(
    id int primary key auto_increment,
    fname char(16) not null,
    lname char(16) not null,
    email char(32) not null
);

create table if not exists customer
(
    custid int primary key auto_increment,
    fname char(16) not null,
    ltname char(16) not null,
    city char(16) not null,
    dob DATE not null
);
create table if not exists account
(
    accnumber int primary key auto_increment,
    custid  int,
    opening_balance INT(8),
    accopendate DATE,
    acctype VARCHAR(10),
    accstatus VARCHAR(10),
    CONSTRAINT account_custid_fk FOREIGN KEY(custid) REFERENCES customer(custid)
);
create table if not exists transaction
(
    transnumber int primary key auto_increment,
    accnumber   int,
    transdate   DATE,
    transamount INT(7),
    CONSTRAINT transaction_accnumber_fk FOREIGN KEY(accnumber) REFERENCES account(accnumber)

);

insert into employee values(1,'John','Doe', 'jd@davidodhiambo.com');
insert into employee values(2, 'Kami', 'Sarawak','ks@davidodhiambo.com');
insert into employee values(3, 'Jokoniah', 'Baldwin','jb@davidodhiambo.com');

insert into customer values(1,'Gale', 'Lawrence', 'Kuala Lumpur', '1980-01-01');
insert into customer values(2,'Paul', 'Xuma', 'Paris', '2000-01-01');

insert into account values(1,1,0,'2018-01-01','Savings','Active');
insert into account values(2,1,0,'2018-01-01','Checking','Active');
insert into account values(3,2,0,'2018-01-01','Savings','Active');
insert into account values(4,2,0,'2018-01-01','Checking','Active');

insert into transaction values(1,1,'2018-01-01',100);
insert into transaction values(2,2,'2018-01-01',200);
