-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wms_test_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `wms_test_db` ;

-- -----------------------------------------------------
-- Schema wms_test_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wms_test_db` DEFAULT CHARACTER SET utf8 ;
USE `wms_test_db` ;

DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS CartItems;
DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Products;

-- -----------------------------------------------------
-- Table `wms_test_db`.`Users`
-- -----------------------------------------------------
CREATE TABLE Users
(
    UserID INT NOT NULL,
    Email VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL, 
    UserPassword VARCHAR(50) NOT NULL,
    Phone CHAR(10) NOT NULL,
    PRIMARY KEY (UserID)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`Addresses`
-- -----------------------------------------------------
CREATE TABLE Addresses
(
    AddressID INT NOT NULL,
    UserID INT NOT NULL,
    RecipientName VARCHAR(50),
    Street VARCHAR(50) NOT NULL,
    Street2 VARCHAR(50),
    City VARCHAR(30) NOT NULL,
    State CHAR(2) NOT NULL,
    Zip CHAR(5) NOT NULL,
    IsShipping BOOL NOT NULL,
    IsBilling BOOL NOT NULL,
    PRIMARY KEY (AddressID),
    FOREIGN KEY (UserID) REFERENCES Users (UserID)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`Products`
-- -----------------------------------------------------
CREATE TABLE Products
(
    UPC CHAR(12) NOT NULL,
    ProdName VARCHAR(50) NOT NULL,
    Brand VARCHAR(50) NOT NULL,
    ProdDescription VARCHAR(100) NOT NULL,
    Category VARCHAR(50) NOT NULL,
    PricePerUnit FLOAT NOT NULL,
    ImageURL VARCHAR(2048),
    AvailableStock INT NOT NULL,
    ReservedStock INT NOT NULL,
    ShippedStock INT NOT NULL,
    PRIMARY KEY (UPC)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`CartItems`
-- -----------------------------------------------------
CREATE TABLE CartItems
(
    CartItemID INT NOT NULL,
    UserID INT NOT NULL,
    UPC CHAR(12) NOT NULL,
    Quantity INT,
    PRIMARY KEY (CartItemID),
    FOREIGN KEY (UserID) REFERENCES Users (UserID),
    FOREIGN KEY (UPC) REFERENCES Products (UPC)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`CreditCards`
-- -----------------------------------------------------
CREATE TABLE CreditCards
(
    CreditCardID INT NOT NULL,
    user_id INT NOT NULL,
    cardholder_name VARCHAR(40) NOT NULL,
    bank_name VARCHAR(40) NOT NULL,
    card_type VARCHAR(20) NOT NULL,
    card_number CHAR(16) NOT NULL,
    security_code VARCHAR(4) NOT NULL,
    expiration_year CHAR(4) NOT NULL,
    expiration_month CHAR(2) NOT NULL,
    PRIMARY KEY (CreditCardID),
    FOREIGN KEY (user_id) REFERENCES users (UserID)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`Orders`
-- -----------------------------------------------------
CREATE TABLE Orders
(
    OrderID INT NOT NULL,
    UserID INT NOT NULL,
    AddressID INT NOT NULL,
    Price FLOAT NOT NULL,
    CreditCardID INT NOT NULL,
    DateOrdered DATETIME NOT NULL,
    DateShipped DATETIME,
    DateDelivered DATETIME,
    OrderStatus VARCHAR(20) NOT NULL,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (UserID) REFERENCES Users (UserID),
    FOREIGN KEY (AddressID) REFERENCES Addresses (AddressID),
    FOREIGN KEY (CreditCardID) REFERENCES CreditCards (CreditCardID)
);

-- -----------------------------------------------------
-- Table `wms_test_db`.`Users`
-- -----------------------------------------------------
CREATE TABLE OrderItems
(
    OrderItemID INT NOT NULL,
    OrderID INT NOT NULL,
    Quantity INT NOT NULL,
    UPC CHAR(12) NOT NULL,
    PRIMARY KEY (OrderItemID),
    FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
    FOREIGN KEY (UPC) REFERENCES Products (UPC)
);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Users VALUES (1, "bobby.joe@gmail.com", "Bobby", "Joe", "password123", "9876543210");
INSERT INTO Users VALUES (2, "awilliams@yahoo.com", "Andrew", "Williams", "mypassword", "8376519501");
INSERT INTO Users VALUES (3, "n.johnson@gmail.com", "Nancy", "Johnson", "thisisabadpassword", "6504891123");
INSERT INTO Users VALUES (4, "jj1205@msn.com", "Jenny", "Jones", "iforgot", "6264589104");
INSERT INTO Users VALUES (5, "g.s.l.62@gmail.com", "George", "Lee", "whatismypassword", "3108573221");

INSERT INTO CreditCards VALUES(1, 1, 'Bobby Joe', 'Capital One', 'Visa', '4443123467891234', '808', '2022', '09' );
INSERT INTO CreditCards VALUES(2, 3, 'Nancy Johnson', 'Bank of America', 'Amex', '3734567891011121', '6290', '2024', '01' );
INSERT INTO CreditCards VALUES(3, 2, 'Andrew Williams', 'HSBC', 'MasterCard', '4264483469273672', '700', '2026', '09' );
INSERT INTO CreditCards VALUES(4, 5, 'George Lee', 'Chase', 'Visa', '4231902528753161', '232', '2025', '11' );
INSERT INTO CreditCards VALUES(5, 4, 'Jenny Jones', 'Wells Fargo', 'Amex', '3484127952481970', '1101', '2028', '10' );
INSERT INTO CreditCards VALUES(6, 3, 'Nancy Johnson', 'Discover', 'Discover', '6091862988814035', '444', '2027', '07' );
INSERT INTO CreditCards VALUES(7, 1, 'Bobby Joe', 'Citi', 'MasterCard', '4100360765536477', '121', '2023', '02' );

INSERT INTO Addresses VALUES (1, 1, "Bobby Joe", "100 This st", "Apt 20", "Los Angeles", "CA", "90025", TRUE, TRUE);
INSERT INTO Addresses VALUES (2, 2, "Andrew Williams", "3211 That Blvd", NULL, "Dallas", "TX", "75011", FALSE, TRUE);
INSERT INTO Addresses VALUES (3, 3, "Nancy Johnson", "20 Example Ave", NULL, "New York", "NY", "10205", TRUE, FALSE);
INSERT INTO Addresses VALUES (4, 4, "Jenny Jones", "777 Some Rd", "Apt 94", "Minneapolis", "MN", "55543", TRUE, TRUE);
INSERT INTO Addresses VALUES (5, 5, "George Lee", "57 Hello Ln", NULL, "Honolulu", "HI", "96720", TRUE, TRUE);

INSERT INTO Products VALUES ("100000000001", "Shark Bites", "Assorted Gummy Sharks", "Betty Crockers", "Snacks", 3.99, "https://m.media-amazon.com/images/I/51Xlrk8SOaL.jpg", 49, 5, 10);
INSERT INTO Products VALUES ("100000000011", "Cheerios", "General Mills", "Oat cold breakfast cereal", "Breakfast Cereals", 5.99, "https://i5.walmartimages.com/asr/ce09f4c7-0b16-4c56-9abe-76cf8ed53915.63028f7382a779cd875263c912632114.jpeg", 119, 3, 0);
INSERT INTO Products VALUES ("100000000111", "Frosted Flakes", "Kelloggs", "Sugary corn cold breakfast cereal", "Breakfast Cereals", 5.99, "https://bjs.scene7.com/is/image/bjs/3633?$bjs-Zoom$", 419, 25, 14);
INSERT INTO Products VALUES ("100000001111", "Kix", "General Mills", "Sugary corn puff cold breakfast cereal", "Breakfast Cereals", 4.99, "https://cereals.generalmills.com/wp-content/uploads/2019/04/kix.png", 44, 15, 5);
INSERT INTO Products VALUES ("100000011111", "Sriracha", "Huy Fong Foods", "Chili paste hot sauce", "Condiments", 8.99, "https://m.media-amazon.com/images/I/817HLNV5DZL._SL1500_.jpg", 0, 1, 12);
INSERT INTO Products VALUES ("100000111111", "Paper Towels", "Bounty", "Paper but towels", "Household", 2.99, "https://images.heb.com/is/image/HEBGrocery/003264047?fit=constrain,1&wid=800&hei=800&fmt=jpg&qlt=85,0&resMode=sharp2&op_usm=1.75,0.3,2,0", 59, 5, 10);
INSERT INTO Products VALUES ("100001111111", "Goldfish", "Pepperidge Farm","Goldfish Baked Snack Crackers Cheddar", "Snacks", 2.99, "https://www.kroger.com/product/images/large/front/1001410004897", 49, 6, 70);
INSERT INTO Products VALUES ("100011111111", "Malk", "Now With Vitamin R", "Not actually milk", "Drinks", 0.99, "https://target.scene7.com/is/image/Target/GUEST_fc940329-105c-42ce-8c26-d3d34b1a51b4?wid=800&hei=800&qlt=80&fmt=pjpeg", 39, 3, 3);
INSERT INTO Products VALUES ("600000000002", "Crystal Geyser", "Crystal Geyser Water Company", "Spring Water", "Drinks", 0.99, "https://images.heb.com/is/image/HEBGrocery/001966477", 121, 3, 5);

INSERT INTO Orders VALUES (1, 1, 1, 10.00, 1, "2011-12-18 13:17:17", "2011-12-18 13:17:17", NULL, "Canceled");
INSERT INTO Orders VALUES (2, 2, 2, 11.00, 2, "2012-12-18 13:17:17", "2012-12-18 13:17:17", "2012-12-20 13:17:17", "Delivered");
INSERT INTO Orders VALUES (3, 3, 3, 12.00, 3, "2013-12-18 13:17:17", "2013-12-18 13:17:17", "2013-12-20 13:17:17", "Delivered");
INSERT INTO Orders VALUES (4, 4, 4, 13.00, 4, "2014-12-18 13:17:17", "2014-12-18 13:17:17", NULL, "Canceled");
INSERT INTO Orders VALUES (5, 5, 5, 14.00, 5, "2015-12-18 13:17:17", "2015-12-18 13:17:17", NULL, "Canceled");
INSERT INTO Orders VALUES (6, 1, 1, 10.00, 1, "2015-12-18 13:17:17", "2015-12-18 13:17:17", "2015-12-20 13:17:17", "Delivered");
INSERT INTO Orders VALUES (7, 2, 2, 11.00, 2, "2016-12-18 13:17:17", "2016-12-18 13:17:17", "2016-12-20 13:17:17", "Delivered");
INSERT INTO Orders VALUES (8, 3, 3, 12.00, 3, "2017-12-18 13:17:17", "2017-12-18 13:17:17", NULL, "Canceled");
INSERT INTO Orders VALUES (9, 5, 5, 13.00, 5, "2018-12-17 13:17:17", "2018-12-18 13:17:17", NULL, "Shipped");
INSERT INTO Orders VALUES (10,5, 5, 14.00, 5, "2018-12-18 13:17:17", NULL, NULL, "Pending");

INSERT INTO OrderItems VALUES(1, 6, 72, '100000001111'); 
INSERT INTO OrderItems VALUES(2, 8, 59, '100001111111');
INSERT INTO OrderItems VALUES(3, 10, 8, '100000111111');
INSERT INTO OrderItems VALUES(4, 8, 56, '100000011111');
INSERT INTO OrderItems VALUES(5, 2, 37, '600000000002');
INSERT INTO OrderItems VALUES(6, 5, 41, '100000000011');
INSERT INTO OrderItems VALUES(7, 6, 64, '100000011111');
INSERT INTO OrderItems VALUES(8, 3, 1, '100001111111');
INSERT INTO OrderItems VALUES(9, 7, 95, '100000000111'); 
INSERT INTO OrderItems VALUES(10, 6, 57, '100000011111'); 
INSERT INTO OrderItems VALUES(11, 2, 36, '100001111111'); 
INSERT INTO OrderItems VALUES(12, 1, 64, '100000000001'); 
INSERT INTO OrderItems VALUES(13, 7, 10, '100000000111'); 
INSERT INTO OrderItems VALUES(14, 9, 86, '100000000011'); 
INSERT INTO OrderItems VALUES(15, 5, 50, '100000111111'); 
INSERT INTO OrderItems VALUES(16, 7, 59, '100000111111'); 
INSERT INTO OrderItems VALUES(17, 8, 62, '100011111111'); 
INSERT INTO OrderItems VALUES(18, 1, 14, '100011111111'); 
INSERT INTO OrderItems VALUES(19, 6, 43, '100000001111'); 
INSERT INTO OrderItems VALUES(20, 3, 13, '100011111111'); 
INSERT INTO OrderItems VALUES(21, 3, 32, '100000000111'); 
INSERT INTO OrderItems VALUES(22, 8, 5, '100000001111'); 
INSERT INTO OrderItems VALUES(23, 8, 70, '600000000002'); 
INSERT INTO OrderItems VALUES(24, 2, 43, '100000001111'); 
INSERT INTO OrderItems VALUES(25, 6, 42, '100000001111'); 
INSERT INTO OrderItems VALUES(26, 6, 88, '100000001111'); 
INSERT INTO OrderItems VALUES(27, 5, 41, '100011111111'); 
INSERT INTO OrderItems VALUES(28, 2, 5, '100011111111'); 
INSERT INTO OrderItems VALUES(29, 3, 5, '100000111111'); 
INSERT INTO OrderItems VALUES(30, 5, 53, '100000011111'); 
INSERT INTO OrderItems VALUES(31, 6, 61, '100001111111');
INSERT INTO OrderItems VALUES(32, 5, 16, '100000001111'); 
INSERT INTO OrderItems VALUES(33, 5, 40, '100001111111'); 
INSERT INTO OrderItems VALUES(34, 4, 34, '100011111111'); 
INSERT INTO OrderItems VALUES(35, 2, 13, '100000000111'); 
INSERT INTO OrderItems VALUES(36, 4, 28, '100000001111'); 
INSERT INTO OrderItems VALUES(37, 8, 16, '100000000001'); 
INSERT INTO OrderItems VALUES(38, 10, 13, '100000000111');
INSERT INTO OrderItems VALUES(39, 10, 86, '100000001111');

-- Dearo/Chuang added "Pending" rows into Orders
INSERT INTO Orders VALUES (20,5, 5, 14.00, 5, "2018-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (21, 1, 1, 10.00, 1, "2011-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (22, 2, 2, 11.00, 2, "2012-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (23, 3, 3, 12.00, 3, "2013-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (24, 4, 4, 13.00, 4, "2014-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (25, 5, 5, 14.00, 5, "2015-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (26, 1, 1, 10.00, 1, "2015-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (27, 2, 2, 11.00, 2, "2016-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (28, 3, 3, 12.00, 3, "2017-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (29, 5, 5, 13.00, 5, "2018-12-17 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (30,5, 5, 14.00, 5, "2018-12-18 13:17:17", NULL, NULL, "Pending"); 
INSERT INTO Orders VALUES (31, 1, 1, 10.00, 1, "2011-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (32, 2, 2, 11.00, 2, "2012-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (33, 3, 3, 12.00, 3, "2013-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (34, 4, 4, 13.00, 4, "2014-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (35, 5, 5, 14.00, 5, "2015-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (36, 1, 1, 10.00, 1, "2015-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (37, 2, 2, 11.00, 2, "2016-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (38, 3, 3, 12.00, 3, "2017-12-18 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (39, 5, 5, 13.00, 5, "2018-12-17 13:17:17", NULL, NULL, "Pending");
INSERT INTO Orders VALUES (40,5, 5, 14.00, 5, "2018-12-18 13:17:17", NULL, NULL, "Pending");
