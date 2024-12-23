create table patient(
	
	id bigint not null auto_increment,
	name varchar(100) not null,
	birth_date varchar(100) not null,
	blood_group varchar(100) not null,
	phone_number varchar(100) not null,
	email varchar(100) not null,
	address varchar(100) not null,
	
	primary key(id)
	);