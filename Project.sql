create table user_(
	username varchar(10),
	name varchar(100),
	date_of_birth date NOT NULL,
	gender char,
	address varchar(500),
	usertype varchar(10) NOT NULL,
	designation varchar(15) NOT NULL,
	email_id varchar(25),
	PRIMARY KEY(username)
);

create table phone_nos
(
	username varchar(10),
	phone_no char(10),
	PRIMARY KEY(phone_no),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table Authentication
(
	username varchar(10),
	password varchar(20) NOT NULL,
	PRIMARY KEY(username),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table Manager
(
	username varchar(10) NOT NULL UNIQUE,
	employee_id varchar(7),
	hierarchy_id int NOT NULL,
	supervisor_id varchar(7),
	PRIMARY KEY(employee_id),
	FOREIGN KEY(username) REFERENCES user_(username),
	FOREIGN KEY(supervisor_id) REFERENCES Manager(employee_id)
);

create table state_
(
	state_id varchar(2),
	state_name varchar(50) NOT NULL,
	no_of_forms_submitted int,
	status boolean,
	PRIMARY KEY(state_id)
);

create table District
(
	district_id varchar(4),
	state_id varchar(2) NOT NULL,
	district_name varchar(25) NOT NULL,
	no_of_forms_submitted int,
	status boolean,
	PRIMARY KEY(district_id),
	FOREIGN KEY(state_id) REFERENCES state_(state_id)
);

create table Areas
(
	username varchar(10) NOT NULL,
	area varchar(6),
	PRIMARY KEY(area),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table Teacher
(
	username varchar(10) NOT NULL UNIQUE,
	teacher_id varchar(7),
	school_id varchar(5) NOT NULL,
	assigned_area_id varchar(6) NOT NULL,
	school_name varchar (30),
	district_id varchar(4) NOT NULL,
	manager_id varchar(7) NOT NULL,
	PRIMARY KEY(teacher_id),
	FOREIGN KEY(username) REFERENCES user_(username),
	FOREIGN KEY(manager_id) REFERENCES manager(employee_id),
	FOREIGN KEY(district_id) REFERENCES District(district_id),
	FOREIGN KEY(assigned_area_id) REFERENCES Areas(area)
);

create table Director
(
	username varchar(10) NOT NULL UNIQUE,
	state_name varchar(25) NOT NULL,
	state_id varchar(6),
	PRIMARY KEY(state_id),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table District_id
(
	username varchar(10) NOT NULL,
	district_id varchar(4),
	PRIMARY KEY(district_id),
	FOREIGN KEY(district_id) REFERENCES district(district_id),
	FOREIGN KEY(username) REFERENCES Director(username)
);

create table HeadMaster
(
	username varchar(10) NOT NULL UNIQUE,
	school_name varchar (20) NOT NULL,
	school_id char(5) NOT NULL,
	district_id varchar(4),
	PRIMARY KEY(username),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table Commissioner
(
	username varchar(10),
	is_published boolean,
	PRIMARY KEY(username),
	FOREIGN KEY(username) REFERENCES user_(username)
);

create table state_id
(
	username varchar(10) NOT NULL,
	state_id varchar(6),
	PRIMARY KEY(state_id),
	FOREIGN KEY(state_id) REFERENCES state_(state_id),
	FOREIGN KEY(username) REFERENCES Commissioner(username)
);

create table Census
(
	form_id char(6),
	teacher_id char(7) NOT NULL,
	number_of_member int,
	apt_no int,
	street_no int,
	street_name varchar(15),
	district_id varchar(4) NOT NULL,
	state_id varchar(2) NOT NULL,
	firstname varchar(15),
	middlename varchar(15),
	lastname varchar(15),
	mother_name varchar(25),
	father_name varchar(25),
	phone_no char(10) UNIQUE,
	aadhaar_no char(12),
	house_ownership boolean NOT NULL,
	relation_to_head varchar(15) NOT NULL,
	date_of_birth date NOT NULL,
	gender char NOT NULL,
	education varchar(50),
	occupation varchar(50),
	nationality varchar(25) NOT NULL,
	PRIMARY KEY(form_id),
	FOREIGN KEY(teacher_id) REFERENCES Teacher(teacher_id),
	FOREIGN KEY(district_id) REFERENCES district(district_id),
	FOREIGN KEY(state_id) REFERENCES state_(state_id)
);

create table Statistics
(
	stat_id varchar(5),
	stat_year int NOT NULL,
	district_id varchar(2) NOT NULL,
	state_id varchar(4) NOT NULL,
	stat_name varchar(50),
	district_wise_stat int,
	state_wise_stat int,
	nation_wise_stat int,
	PRIMARY KEY(stat_id),
	FOREIGN KEY(district_id) REFERENCES district(district_id),
	FOREIGN KEY(state_id) REFERENCES state_(state_id)
);

insert into user_ values
('CDMSTE0001','Niket Kumar ',TO_DATE('21/03/1995','DD/MM/YYYY'),'M','12,Ashok Nagar,Chennai,Tamil Nadu,600083','Teacher','Teacher','niket@gmail.com'),
('CDMSMA0001','Pradeep Kumar',TO_DATE('22/06/1985','DD/MM/YYYY'),'M','15,Mall Road,Shimla,Himachal Pradesh,171001','Manager','Head Master','pradeep@gmail.com'),
('CDMSTE0002','Diya Mehta',TO_DATE('13/07/1993','DD/MM/YYYY'),'F','11,Park Street,Kolkata,West Bengal,700016','Teacher','Teacher','diya@gmail.com'),
('CDMSMA0002','Agastya Verma',TO_DATE('05/11/1999','DD/MM/YYYY'),'M','6,Colaba,Mumbai,Maharashtra,400005','Manager','Director','agastya@gmail.com'),
('CDMSTE0003','Aditi Singh',TO_DATE('28/07/1992','DD/MM/YYYY'),'F','26,Sampige Road,Bengaluru,Karnataka,560003','Teacher','Teacher','aditi@gmail.com'),
('CDMSMA0003','Divya Bhatt',TO_DATE('05/05/1989','DD/MM/YYYY'),'F','23,KK Nagar, Chennai, Tamil Nadu, 600078','Manager','Commissioner','divya@gmail.com'),
('CDMSTE0004','Sam Smith',TO_DATE('20/04/1997','DD/MM/YYYY'),'M','1,Ashok Nagar,Chennai,Tamil Nadu,600083','Teacher','Teacher','samsmith@gmail.com'),
('CDMSTE0005','Ashwin Patel',TO_DATE('27/08/1991','DD/MM/YYYY'),'M','8,Mall Road,Shimla,Himachal Pradesh,171001','Teacher','Teacher','ashwin@gmail.com');

insert into phone_nos values
('CDMSTE0001','9957131561'),
('CDMSTE0002','8885104875'),
('CDMSTE0003','9253476669'),
('CDMSTE0004','6375176297'),
('CDMSTE0005','7314928299'),
('CDMSMA0001','9156728048'),
('CDMSMA0002','7836303548'),
('CDMSMA0002','6946286851'),
('CDMSMA0003','8793241254');

insert into Authentication values
('CDMSTE0001','13r8c02ru'),
('CDMSTE0002','1p9c492r0b8'),
('CDMSTE0003','b92c3hr8r'),
('CDMSTE0004','3r8gc2r9p24'),
('CDMSTE0005','teach'),
('CDMSMA0001','-81392vhrp'),
('CDMSMA0002','08gp9hqer'),
('CDMSMA0003','manage');

insert into Manager values
('CDMSTE0001','TE23011',1,'MA23011'),
('CDMSTE0002','TE23012',1,'MA23011'),
('CDMSTE0003','TE23013',1,'MA23011'),
('CDMSTE0004','TE23014',1,'MA23011'),
('CDMSTE0005','TE23015',1,'MA23011'),
('CDMSMA0001','MA23011',2,'MA23012'),
('CDMSMA0002','MA23012',3,'MA23013'),
('CDMSMA0003','MA23013',4,null);

insert into state_  values
('01','JAMMU AND KASHMIR',0,false),
('02','HIMACHAL PRADESH',0,false),
('03','PUNJAB',0,false),
('04','CHANDIGARH',0,false),
('05','UTTARAKHAND',0,false),
('06','HARYANA',0,false),
('07','DELHI',0,false),
('08','RAJASTHAN',0,false),
('09','UTTAR PRADESH',0,false),
('10','BIHAR',0,false),
('11','SIKKIM',0,false),
('12','ARUNACHAL PRADESH',0,false),
('13','NAGALAND',0,false),
('14','MANIPUR',0,false),
('15','MIZORAM',0,false),
('16','TRIPURA',0,false),
('17','MEGHALAYA',0,false),
('18','ASSAM',0,false),
('19','WEST BENGAL',0,false),
('20','JHARKHAND',0,false),
('21','ODISHA',0,false),
('22','CHATTISGARH',0,false),
('23','MADHYA PRADESH',0,false),
('24','GUJARAT',0,false),
('25','DADRA AND NAGAR HAVELI AND DAMAN AND DIU',0,false),
('26','MAHARASHTRA',0,false),
('27','ANDHRA PRADESH(BEFORE DIVISION)',0,false),
('28','KARNATAKA',0,false),
('29','GOA',0,false),
('30','LAKSHADWEEP',0,false),
('31','KERALA',0,false),
('32','TAMIL NADU',0,false),
('33','PUDUCHERRY',0,false),
('34','ANDAMAN AND NICOBAR ISLANDS',0,false),
('35','TELANGANA',0,false),
('36','ANDHRA PRADESH',0,false),
('37','LADAKH',0,false);

insert into District values
('0211','02','Shimla',0,false);

insert into Areas values
('CDMSMA0001','021101');

insert into Teacher values
('CDMSTE0001','TE23011','SC751','021101','Eastwood High','0211','MA23011'),
('CDMSTE0002','TE23012','SC751','021101','Eastwood High','0211','MA23011'),
('CDMSTE0003','TE23013','SC751','021101','Eastwood High','0211','MA23011'),
('CDMSTE0004','TE23014','SC751','021101','Eastwood High','0211','MA23011'),
('CDMSTE0005','TE23015','SC751','021101','Eastwood High','0211','MA23011'),

insert into Director values
('CDMSMA0002','HIMACHAL PRADESH','02');

insert into District_id values
('CDMSMA0002','0211');

insert into HeadMaster values
('CDMSMA0001','Eastwood High','SC751','0211');

insert into Commissioner values
('CDMSMA0003',false);

insert into state_id values
('CDMSMA0003','01'),
('CDMSMA0003','02'),
('CDMSMA0003','03'),
('CDMSMA0003','04'),
('CDMSMA0003','05'),
('CDMSMA0003','06'),
('CDMSMA0003','07'),
('CDMSMA0003','08'),
('CDMSMA0003','09'),
('CDMSMA0003','10'),
('CDMSMA0003','11'),
('CDMSMA0003','12'),
('CDMSMA0003','13'),
('CDMSMA0003','14'),
('CDMSMA0003','15'),
('CDMSMA0003','16'),
('CDMSMA0003','17'),
('CDMSMA0003','18'),
('CDMSMA0003','19'),
('CDMSMA0003','20'),
('CDMSMA0003','21'),
('CDMSMA0003','22'),
('CDMSMA0003','23'),
('CDMSMA0003','24'),
('CDMSMA0003','25'),
('CDMSMA0003','26'),
('CDMSMA0003','27'),
('CDMSMA0003','28'),
('CDMSMA0003','29'),
('CDMSMA0003','30'),
('CDMSMA0003','31'),
('CDMSMA0003','32'),
('CDMSMA0003','33'),
('CDMSMA0003','34'),
('CDMSMA0003','35'),
('CDMSMA0003','36'),
('CDMSMA0003','37');

insert into Census  values
('F00001','TE23011',2,12,13,'Mall Road','0211','02','Aman','Kumar','Singh','Nandhini Kaur','Sandeep Singh','8402428307','123456789012',false,'Son',TO_DATE('17/03/1998', 'DD/MM/YYYY'),'M','M.Tech','Software Engineer','Indian'),
('F00002','TE23012',4,9,15,'Mall Road','0211','02','Siddharth',null,'Kumar','Uma Kumari','Ramesh Kumar','7756867187','123456789012',true,'Son',TO_DATE('11/12/1993', 'DD/MM/YYYY'),'M','B.Tech','Software Engineer','Indian');


select * from user_;
select * from phone_nos;
select * from Authentication;
select * from Manager ;
select * from state_  ;
select * from District ;
select * from Areas;
select * from Teacher;
select * from Director;
select * from District_id;
select * from HeadMaster;
select * from Commissioner ;
select * from state_id;
select * from Census ;
select * from statistics ;

select form_id, teacher_id, district_id, firstname, phone_no, aadhaar_no, gender from Census;


drop table statistics;
drop table Census ;
drop table state_id;
drop table Commissioner ;
drop table HeadMaster;
drop table District_id;
drop table Director;
drop table Teacher;
drop table Areas;
drop table District ;
drop table state_  ;
drop table Manager ;
drop table Authentication;
drop table phone_nos;
drop table user_;

create table submission_status
(
	teacher_id varchar(7) NOT NULL,
	is_submitted int NOT NULL DEFAULT 0,
	PRIMARY KEY(teacher_id),
	FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id)
);

drop table submission_status;

insert into submission_status(teacher_id) values ('TE23011'),('TE23012'),('TE23013'),('TE23014'),('TE23015'),('100'),('101');

select * from submission_status;

update submission_status set is_submitted = 0 where teacher_id='TE23014';

user_, authentication, teacher, submission_status

select username,password,teacher_id,school_id,name,date_of_birth,assigned_area_id,is_submitted from teacher natural join user_ natural join authentication natural join submission_status;

select * from teacher_management;
