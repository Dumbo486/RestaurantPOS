create table customer (
	customer_id	number(4),
	name	varchar2(20),
	birth	number(4),
	phone	number(4),
	position	varchar2(20),
	account	number(20),
	primary key (customer_id)
);

create table staff (
	staff_id	number(4),
	name	varchar2(20),
	position	varchar2(20),
	primary key (staff_id)
);

create table menu (
	menu_id	number(4),
	name	varchar2(30),
	price	number(20),
	primary key (menu_id)
);

create table payinfo (
	pay_id	number(25),
	price	number(10),
	term	varchar2(10),
	staff_id	number(4),
	menu_id	number(4),
	primary key (pay_id),
	foreign key (staff_id) references staff,
	foreign key (menu_id) references menu
);

