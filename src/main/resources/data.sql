CREATE TABLE BOOK(
    id varchar(250) PRIMARY KEY ,
    bookName varchar(250),
    isbn varchar(250),
    aisle INT,
    author varchar (250)
);

insert into BOOK(id,aisle,author,bookName,isbn) VALUES ('test123',123,'coderman','spring','test');
insert into BOOK(id,aisle,author,bookName,isbn) VALUES ('test124',124,'coderman','react','test');
insert into BOOK(id,aisle,author,bookName,isbn) VALUES ('test125',125,'coderman','selenium','test');