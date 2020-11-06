create database company;


use company;

create table employee (
	id_number int primary key auto_increment,
    name varchar (30) not null,
    salary int,
    phone_number char(10),
    dept_name varchar(30)
);

select * from employee;


describe employee;

insert into employee values(null, 'Daniel Mclain', 50000, '0000000000', 'Sales');
insert into employee values(null, 'Jeffrey Chiang', 50000, '0000000001', 'Sales');
insert into employee values(null, 'Mel Lamagna', 50000, '0000000002', 'Sales');
insert into employee values(null, 'Ken Cheng', 50000, '0000000003', 'Marketing');
insert into employee values(null, 'Vinent Cardenas', 50000, '0000000004', 'Marketing');
insert into employee values(null, 'Arthur Morgan', 50000, '000000005', 'Marketing');
insert into employee values(null, 'John Marston', 50000, '0000000006', 'Engineering');
insert into employee values(null, 'John Smith', 500000, '0000000007', 'Engineering');
insert into employee values(null, 'Jane Doe', 500000, '0000000008', 'Engineering');

create table department(
	name varchar(30) primary key,
    phone varchar(10),
    budget int
);

describe department;


insert into department values('Sales', '1111111111', 1000000);
insert into department values('Marketing', '2222222222', 1000000);
insert into department values('Engineering', '3333333333', 1000000);

select * from department;







select * from employee;
