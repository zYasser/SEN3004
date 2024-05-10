create table author(id identity primary key, 
					fname varchar(50), 
					lname varchar(50));
					
create table book(id identity primary key, 
				  title varchar(50));
				  
create table auth_book(aid bigint, 
				       bid bigint,
				       foreign key(aid) references author(id),
				       foreign key(bid) references book(id));