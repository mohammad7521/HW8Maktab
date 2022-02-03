CREATE TABLE IF NOT EXISTS mainUser(
   username varchar(20),
   password varchar(20)
);


CREATE TABLE IF NOT EXISTS customer(
    id serial primary key,
    address varchar (20),
    phoneNumber varchar (20),
    nationalCode varchar (20),
    balance decimal
) inherits (mainUser);


CREATE TABLE IF NOT EXISTS admin(

) INHERITS (mainUser);



CREATE TABLE IF NOT EXISTS shoppingKart(
    id serial primary key,
    orderDate timestamp,
    isPaid boolean,
    customerID int unique,
    constraint fk foreign key (customerID) references customer(id)
);


CREATE TABLE IF NOT EXISTS kartItems(
    id serial primary key,
    selectedQuantity int,
    shoppingKartID int,
    constraint fk foreign key (shoppingKartID) references shoppingKart(id)
);




CREATE TABLE IF NOT EXISTS product(
    id serial primary key,
    name varchar(20),
    price decimal,
    quantity int
);


CREATE TABLE IF NOT EXISTS parentCategory(
    id serial primary key,
    name varchar(20),
    productID int
);


CREATE TABLE IF NOT EXISTS childCategory(
     id serial primary key,
     name varchar(20),
     productID int,
     parentCategoryID int,
     constraint fk foreign key (productID) references product(id),
     constraint fk2 foreign key (parentCategoryID) references parentCategory(id)
);




