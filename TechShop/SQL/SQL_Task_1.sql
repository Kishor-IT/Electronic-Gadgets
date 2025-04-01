CREATE DATABASE TechShop;
use TechShop;

CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Phone VARCHAR(15) UNIQUE NOT NULL,
    Address TEXT NOT NULL
);

INSERT INTO Customers (FirstName, LastName, Email,Phone,Address) VALUES
('Sanjay', 'Kumar', 'sanjaykumar@gmail.com', '9999999991', '123,indhira nagar,chennai'),
('Varun', 'Sharma', 'varun03@gmail.com', '9999999992', '456,gandhi nagar,coimbatore'),
('Sudharsan', 'Kannan', 'sudhar_07@gmail.com', '9999999993', '789,rajiv nagar,madurai'),
('Mani', 'Kandan', 'mani09@gmail.com', '9999999994', '012,indhira nagar,palani'),
('David', 'Miller', 'davidsa@gmail.com', '9999999995', '345,swami nagar,erode'),
('Sophia', 'Davis', 'sophicutie@gmail.com', '9999999996', '678,ganga nagar,chennai'),
('Andria', 'Merry', 'andreajohn@gmail.com', '9999999997', '901,yamuna nagar,coimbatore'),
('Shruthi', 'Hassan', 'shruthi001@gamil.com', '9999999998', '234,kaveri nagar,kanyakumari'),
('Deepa', 'Kumari', 'deepakumar02@gmail.com', '9999999999', '567,eshwar nagar,thirichy'),
('Rani', 'Rose', 'rani0789@gamil.com.', '9999999990', '123,indhira nagar,chennai');

select * from Customers;

CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    Descriptions TEXT,
    Price DECIMAL(10,2) 
);

INSERT INTO Products (ProductName, Descriptions, Price) VALUES
('LG Monitor', 'High-performance Monitor with 16GB RAM', 45000.00),
('Smartphone', 'OnePlus 13R, Smarter with OnePlus AI ', 42798.00),
('Iphone', 'IPhone 15. A16 Bionic chip with 5-core GPU. Ultra-fast 5G mobile connectivity.', 61900.00),
('Smartwatch', 'Fitness tracker with heart rate monitor', 1399.00),
('TrackPad', 'Magic Trackpad (USB‑C) - Black Multi-Touch Surface', 14500.00),
('Bluetooth Speaker', 'Commando X7 Improve Your Gaming Experience With New Earbuds', 1999.00),
('Lap Cleaner', 'OXO Sweep & Swipe Laptop Cleaner', 430.00),
('Monitor', '27-inch 144Hz gaming monitor', 17700.00),
('Laptop', 'Microsoft Surface Laptop 7th Edition', 52599.00),
('Gaming Console', 'Sony PlayStation 5 Pro Console', 32799.00);

select * from Products;

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    OrderDate DATE,
    TotalAmount DECIMAL(10,2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES
(1, '2025-09-11', 45000.00),
(2, '2024-12-22', 42798.00),
(3, '2023-11-30', 61900.00),
(4, '2025-12-04', 1399.00),
(5, '2024-01-17', 14500.00),
(6, '2023-06-27', 1999.00),
(7, '2025-06-15', 430.99),
(8, '2024-04-04', 17700.00),
(9, '2023-03-09', 52599.00),
(10, '2025-08-10', 32799.00);

select * from Orders;

CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES
(1, 1, 2),  
(1, 3, 1), 
(2, 2, 3),  
(3, 4, 2),  
(4, 5, 1),  
(5, 6, 3), 
(6, 7, 2),  
(7, 8, 4),  
(8, 9, 5),  
(9, 10, 4); 

select * from OrderDetails;

CREATE TABLE Inventory (
    InventoryID INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT NOT NULL,
    QuantityInStock INT NOT NULL,
    LastStockUpdate DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO Inventory (ProductID, QuantityInStock) VALUES
(1, 10),
(3, 15),
(2, 20),
(4, 12),
(5, 8),
(7, 30),
(8, 18),
(9, 22),
(10, 14);

select * from Inventory;




