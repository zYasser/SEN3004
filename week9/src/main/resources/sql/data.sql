insert into person(fname, lname, dob) values('Jack', 'Miller', parsedatetime('12-02-2000', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('Eric', 'Becker', parsedatetime('19-02-1994', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('Stephanie', 'Wiley', parsedatetime('10-04-1986', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('Laura', 'Thompson', parsedatetime('06-11-2002', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('David', 'Diamond', parsedatetime('03-05-1974', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('Alison', 'Fowler', parsedatetime('26-06-1989', 'dd-MM-yyyy'));
insert into person(fname, lname, dob) values('Karen', 'Evans', parsedatetime('01-07-1998', 'dd-MM-yyyy'));

insert into phone_number(number, personid) values('2121112233', 2);
insert into phone_number(number, personid) values('2122223344', 2);
insert into phone_number(number, personid) values('2123334455', 3);
insert into phone_number(number, personid) values('2124445566', 1);
insert into phone_number(number, personid) values('2125556677', 1);
insert into phone_number(number, personid) values('2126667788', 1);
insert into phone_number(number, personid) values('2127778899', 4);