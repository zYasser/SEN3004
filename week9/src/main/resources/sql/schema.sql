create table person(id identity primary key, fname varchar(50), lname varchar(50), dob date);

create table phone_number(id identity primary key, number varchar(10), personid bigint, foreign key(personid) references person(id));