CREATE TABLE Accounts (
	User_Name VARCHAR (20) NOT NULL,
	Active BOOLEAN NOT NULL,
	PASSWORD VARCHAR (20) NOT NULL,
	User_Role VARCHAR (20) NOT NULL,
	NAME VARCHAR (50) NOT NULL,
	Email VARCHAR (50) NOT NULL,
	Phone_Number VARCHAR (12),
	Address VARCHAR (200),
	Date_Create DATE NOT NULL,
	Date_Updated DATE NOT NULL,
	PRIMARY KEY (User_Name)
);

CREATE TABLE Order_Details (
	ID VARCHAR (50) NOT NULL,
	Amount DOUBLE PRECISION NOT NULL,
	Price DOUBLE PRECISION NOT NULL,
	Quanity INTEGER NOT NULL,
	ORDER_ID VARCHAR (50) NOT NULL,
	PRODUCT_ID VARCHAR (20) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Orders (
	ID VARCHAR (50) NOT NULL,
	Amount DOUBLE PRECISION NOT NULL,
	Customer_Address VARCHAR (255) NOT NULL,
	Customer_Email VARCHAR (128) NOT NULL,
	Customer_Name VARCHAR (255) NOT NULL,
	Customer_Phone VARCHAR (128) NOT NULL,
	Order_Date DATE NOT NULL,
	Order_Num INTEGER NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Categories (
	ID VARCHAR (20) NOT NULL,
	NAME VARCHAR (255) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE Products (
	Code VARCHAR (20) NOT NULL,
	Create_Date TIME NOT NULL,
	Image BYTEA,
	NAME VARCHAR (255) NOT NULL,
	Price DOUBLE PRECISION NOT NULL,
	Category VARCHAR (20) NOT NULL,
	Date_Updated DATE NOT NULL,
	PRIMARY KEY (Code)
);

ALTER TABLE Orders ADD CONSTRAINT UK_sxhpvsj665kmi4f7jdu9d2791 UNIQUE (Order_Num);

ALTER TABLE Order_Details ADD CONSTRAINT ORDER_DETAIL_ORD_FK FOREIGN KEY (ORDER_ID) REFERENCES Orders (ID);

ALTER TABLE Order_Details ADD CONSTRAINT ORDER_DETAIL_PROD_FK FOREIGN KEY (PRODUCT_ID) REFERENCES Products (Code);

ALTER TABLE Products ADD CONSTRAINT PRODUCT_CAT_FK FOREIGN KEY (Category) REFERENCES Categories (ID)

--insert data Accounts
insert into Accounts (user_name, active, password, user_role, name, email, phone_number, address, date_create, date_updated)
values ('employee1', '1', '123', 'EMPLOYEE','thaihoc1','thaihoc2105@gmail.com','01223410987','address1','2017-07-07','2017-07-07');

insert into Accounts (user_name, active, password, user_role, name, email, phone_number, address, date_create, date_updated)
values ('manager1', '1', '123', 'MANAGER','thaihoc2','thaihoc2105@gmail.com','01223410987','address2','2017-07-07','2017-07-07');

--insert data Categories
INSERT INTO public.Categories(ID, NAME) VALUES ('C001', 'Adidas');
INSERT INTO public.Categories(ID, NAME) VALUES ('C002', 'Puma');
INSERT INTO public.Categories(ID, NAME) VALUES ('C003', 'Nike');

--insert data Products
INSERT INTO public.products(code, name, price,create_date, date_updated, category)
VALUES ('S001', 'Core Java', 100, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'C001');
 
INSERT INTO public.products(code, name, price,create_date, date_updated,category)
VALUES ('S002', 'Spring for Beginners', 50, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'C001');

INSERT INTO public.products(code, name, price,create_date, date_updated,category)
VALUES ('S003', 'Swift for Beginners', 120, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'C002');

INSERT INTO public.products(code, name, price,create_date, date_updated,category)
VALUES ('S004', 'Oracle XML Parser', 120, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'C002');

INSERT INTO public.products(code, name, price,create_date, date_updated,category,'C003')
VALUES ('S005', 'CSharp Tutorial for Beginers', 110, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);