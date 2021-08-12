create table JURY(
id varchar(50) not null,
password varchar(50) not null
);

insert into JURY values('judge1','judge1');
insert into JURY values('judge2','judge2');
insert into JURY values('judge2','judge3');

create table team(
	id varchar(10) not null,
	type varchar(50) not null,
	size int not null,
	year int(4),
	marks1 int,
	marks2 int,
	marks3 int,
	primary key(id)
);

insert into team values('1','Group Dance',10,2017,null,null,null);
insert into team values('2','Group Song',5,2019,null,null,null);
insert into team values('3','Solo Dance',1,2018,null,null,null);
insert into team values('4','Drama',15,2016,null,null,null);
