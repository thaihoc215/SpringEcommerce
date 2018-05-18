 create table Accounts (
        User_Name varchar(20) not null,
        Active boolean NOT NULL,
        Password varchar(20) not null,
        User_Role varchar(20) not null,
		Name varchar(50) not null,
		Email varchar(50) not null,
		Phone_Number varchar(12),
		Address varchar(200),
		Date_Create date not null,
		Date_Updated date not null,
        primary key (User_Name)
    );

create table Order_Details (
        ID varchar(50) not null,
        Amount double precision not null,
        Price double precision not null,
        Quanity integer not null,
        ORDER_ID varchar(50) not null,
        PRODUCT_ID varchar(20) not null,
        primary key (ID)
    );
 
    create table Orders (
        ID varchar(50) not null,
        Amount double precision not null,
        Customer_Address varchar(255) not null,
        Customer_Email varchar(128) not null,
        Customer_Name varchar(255) not null,
        Customer_Phone varchar(128) not null,
        Order_Date date not null,
        Order_Num integer not null,
        primary key (ID)
    );
 
    create table Products (
        Code varchar(20) not null,
        Create_Date TIME not null,
        Image BYTEA,
        Name varchar(255) not null,
        Price double precision not null,
		Date_Updated date not null,
        primary key (Code)
    );
 
    alter table Orders
        add constraint UK_sxhpvsj665kmi4f7jdu9d2791  unique (Order_Num);
 
    alter table Order_Details
        add constraint ORDER_DETAIL_ORD_FK
        foreign key (ORDER_ID)
        references Orders (ID);
 
    alter table Order_Details
        add constraint ORDER_DETAIL_PROD_FK
        foreign key (PRODUCT_ID)
        references Products (Code);

--insert data Accounts
insert into Accounts (user_name, active, password, user_role, name, email, phone_number, address, date_create, date_updated)
values ('employee1', '1', '123', 'EMPLOYEE','thaihoc1','thaihoc2105@gmail.com','01223410987','address1','2017-07-07','2017-07-07');

insert into Accounts (user_name, active, password, user_role, name, email, phone_number, address, date_create, date_updated)
values ('manager1', '1', '123', 'MANAGER','thaihoc2','thaihoc2105@gmail.com','01223410987','address2','2017-07-07','2017-07-07');
 
--insert data Products
INSERT INTO public.products(code, name, price,create_date, date_updated)
VALUES ('S001', 'Core Java', 100, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
 
INSERT INTO public.products(code, name, price,create_date, date_updated)
VALUES ('S002', 'Spring for Beginners', 50, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO public.products(code, name, price,create_date, date_updated)
VALUES ('S003', 'Swift for Beginners', 120, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO public.products(code, name, price,create_date, date_updated)
VALUES ('S004', 'Oracle XML Parser', 120, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO public.products(code, name, price,create_date, date_updated)
VALUES ('S005', 'CSharp Tutorial for Beginers', 110, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);