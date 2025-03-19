use TechShop;

/*1.	Write an SQL query to retrieve the names and emails of all customers.  */
Select FirstName,LastName,Email from Customers; 

/*2.	Write an SQL query to list all orders with their order dates and corresponding customer names. */
select O.OrderID, O.OrderDate, C.FirstName, C.LastName from Orders O join Customers C on O.CustomerID = C.CustomerID;

/*3.	Write an SQL query to insert a new customer record into the "Customers" table. Include customer information such as name, email, and address. */
insert into Customers(FirstName,LastName,Email,Phone,Address) values ('Suriya', 'Prakash', 'suriya099@gmail.com', '9999999981', '145,vasuki nagar,madurai');
select * from Customers;

/*4.	Write an SQL query to update the prices of all electronic gadgets in the "Products" table by increasing them by 10%. */
update Products set Price = Price * 1.10 ;

/*5.	Write an SQL query to delete a specific order and its associated order details from the "Orders" and "OrderDetails" tables. Allow users to input the order ID as a parameter. */
delete from OrderDetails where OrderId=2;
select * from OrderDetails;

delete from Orders where OrderId=2;
select * from Orders;

/*6.	Write an SQL query to insert a new order into the "Orders" table. Include the customer ID, order date, and any other necessary information. */
insert into Orders(CustomerID, OrderDate, TotalAmount) values (4, '2025-07-27', 17700.00);
select * from Orders;

/*7.	Write an SQL query to update the contact information (e.g., email and address) of a specific customer in the "Customers" table. Allow users to input the customer ID and new contact information. */
update Customers set Email = 'sanjay078@gmail.com', Address = '456 vengai street,chennai' where CustomerID = 1;
select * from Customers;

/*8.	Write an SQL query to recalculate and update the total cost of each order in the "Orders" table based on the prices and quantities in the "OrderDetails" table. */
update Orders O set TotalAmount = (
    select SUM(OD.Quantity * P.Price) from OrderDetails OD join Products P on OD.ProductID = P.ProductID where OD.OrderID = O.OrderID
);

/*9.	Write an SQL query to delete all orders and their associated order details for a specific customer from the "Orders" and "OrderDetails" tables. Allow users to input the customer ID as a parameter. */
delete from OrderDetails where OrderID in (select OrderID from Orders where CustomerID = @CustomerID);
delete from Orders where CustomerID = @CustomerID;

/*10.	Write an SQL query to insert a new electronic gadget product into the "Products" table, including product name, category, price, and any other relevant details. */
insert into Products(ProductName, Descriptions, Price) values ('Projector', 'A high quality projector in LG with 32 hours of continous screen presence', 27800.00);
select * from Products;

/*11.	Write an SQL query to update the status of a specific order in the "Orders" table (e.g., from "Pending" to "Shipped"). Allow users to input the order ID and the new status. */
alter table Orders add column status VARCHAR(20);
select * from Orders;

update Orders set status = 'Shipped' where Orderid = 1;
update Orders set status = 'Pending' where Orderid = 2;
update Orders set status = 'Shipped' where Orderid = 3;
update Orders set status = 'Pending' where Orderid = 4;
update Orders set status = 'Shipped' where Orderid = 5;
update Orders set status = 'Pending' where Orderid = 6;
update Orders set status = 'Shipped' where Orderid = 7;
update Orders set status = 'Pending' where Orderid = 8;
update Orders set status = 'Shipped' where Orderid = 9;
update Orders set status = 'Pending' where Orderid = 10;

Select * from Orders;

update Orders set status='Shipped' where Orderid=8;
select * from Orders;

/*12.	Write an SQL query to calculate and update the number of orders placed by each customer in the "Customers" table based on the data in the "Orders" table. */
update Customers C set TotalOrders = ( select COUNT(O.OrderID) from Orders O where O.CustomerID = C.CustomerID
);
