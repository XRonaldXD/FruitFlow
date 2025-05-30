-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3306
-- 產生時間： 2025-04-25 11:36:28
-- 伺服器版本： 8.2.0
-- PHP 版本： 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `esd_assignment`
--

-- --------------------------------------------------------

--
-- 資料表結構 `borrowing`
--

DROP TABLE IF EXISTS `borrowing`;
CREATE TABLE IF NOT EXISTS `borrowing` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `from_shop_id` int NOT NULL,
  `to_shop_id` int NOT NULL,
  `fruit_id` int NOT NULL,
  `quantity` int NOT NULL,
  `borrow_date` date NOT NULL,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  PRIMARY KEY (`borrow_id`),
  KEY `fruit_id` (`fruit_id`),
  KEY `to_shop_id` (`to_shop_id`),
  KEY `from_shop_id` (`from_shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `borrowing`
--

INSERT INTO `borrowing` (`borrow_id`, `from_shop_id`, `to_shop_id`, `fruit_id`, `quantity`, `borrow_date`, `status`) VALUES
(1, 3, 1, 2, 100, '2025-04-26', 'Pending'),
(2, 4, 1, 1, 500, '2025-04-29', 'Pending'),
(4, 4, 1, 2, 250, '2025-04-29', 'Pending'),
(5, 1, 4, 2, 250, '2025-04-28', 'Approved'),
(6, 1, 4, 5, 5000, '2025-04-28', 'Rejected'),
(7, 4, 1, 1, 999, '2025-04-28', 'Pending');

-- --------------------------------------------------------

--
-- 資料表結構 `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `city_name` (`city_name`),
  KEY `country_id` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `cities`
--

INSERT INTO `cities` (`city_id`, `city_name`, `country_id`) VALUES
(1, 'Tokyo', 1),
(2, 'New York', 2),
(3, 'Hong Kong', 3);

-- --------------------------------------------------------

--
-- 資料表結構 `consumption`
--

DROP TABLE IF EXISTS `consumption`;
CREATE TABLE IF NOT EXISTS `consumption` (
  `consumption_id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int NOT NULL,
  `fruit_id` int NOT NULL,
  `quantity` int NOT NULL,
  `consumption_date` date NOT NULL,
  `season` enum('Spring','Summer','Autumn','Winter') NOT NULL,
  PRIMARY KEY (`consumption_id`),
  KEY `shop_id` (`shop_id`),
  KEY `fruit_id` (`fruit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `consumption`
--

INSERT INTO `consumption` (`consumption_id`, `shop_id`, `fruit_id`, `quantity`, `consumption_date`, `season`) VALUES
(1, 1, 1, 500, '2025-07-08', 'Summer'),
(2, 1, 2, 1000, '2025-04-24', 'Spring'),
(3, 1, 3, 5000, '2025-04-17', 'Spring');

-- --------------------------------------------------------

--
-- 資料表結構 `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `country_id` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(50) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `countries`
--

INSERT INTO `countries` (`country_id`, `country_name`) VALUES
(3, 'Hong Kong'),
(1, 'Japan'),
(2, 'USA');

-- --------------------------------------------------------

--
-- 資料表結構 `deliveries`
--

DROP TABLE IF EXISTS `deliveries`;
CREATE TABLE IF NOT EXISTS `deliveries` (
  `delivery_id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `fruit_id` int NOT NULL,
  `from_warehouse_id` int NOT NULL,
  `to_warehouse_id` int DEFAULT NULL,
  `to_shop_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `delivery_date` date NOT NULL,
  `status` enum('In Transit','Delivered') DEFAULT 'In Transit',
  PRIMARY KEY (`delivery_id`),
  KEY `fruit_id` (`fruit_id`),
  KEY `reservation_id` (`reservation_id`),
  KEY `from_warehouse_id` (`from_warehouse_id`),
  KEY `to_warehouse_id` (`to_warehouse_id`),
  KEY `to_shop_id` (`to_shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `deliveries`
--

INSERT INTO `deliveries` (`delivery_id`, `reservation_id`, `fruit_id`, `from_warehouse_id`, `to_warehouse_id`, `to_shop_id`, `quantity`, `delivery_date`, `status`) VALUES
(1, 12, 2, 1, 22, NULL, 500, '2025-04-30', 'In Transit'),
(6, 8, 2, 1, NULL, 3, 150, '2025-05-07', 'In Transit'),
(7, 8, 2, 1, NULL, 3, 150, '2025-05-06', 'In Transit'),
(8, 8, 2, 1, NULL, 3, 150, '2025-05-03', 'In Transit'),
(9, 8, 2, 1, NULL, 3, 150, '2025-05-09', 'In Transit'),
(10, 8, 2, 1, NULL, 3, 150, '2025-05-06', 'In Transit'),
(11, 9, 1, 1, NULL, 3, 100, '2025-04-29', 'In Transit');

-- --------------------------------------------------------

--
-- 資料表結構 `fruits`
--

DROP TABLE IF EXISTS `fruits`;
CREATE TABLE IF NOT EXISTS `fruits` (
  `fruit_id` int NOT NULL AUTO_INCREMENT,
  `fruit_name` varchar(50) NOT NULL,
  `source_location` varchar(50) NOT NULL,
  PRIMARY KEY (`fruit_id`),
  UNIQUE KEY `fruit_name` (`fruit_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `fruits`
--

INSERT INTO `fruits` (`fruit_id`, `fruit_name`, `source_location`) VALUES
(1, 'Apple', 'USA'),
(2, 'Banana', 'Philippines'),
(3, 'Strawberry', 'Japan'),
(4, 'HongKongPotato', 'HK'),
(5, 'Tomato', 'India');

-- --------------------------------------------------------

--
-- 資料表結構 `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int DEFAULT NULL,
  `warehouse_id` int DEFAULT NULL,
  `fruit_id` int NOT NULL,
  `quantity` int NOT NULL,
  `reservation_date` date NOT NULL,
  `status` enum('Pending','Approved','Rejected','In Transit','Delivered') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Pending',
  PRIMARY KEY (`reservation_id`),
  KEY `fruit_id` (`fruit_id`),
  KEY `shop_id` (`shop_id`),
  KEY `warehouses_id` (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `shop_id`, `warehouse_id`, `fruit_id`, `quantity`, `reservation_date`, `status`) VALUES
(1, 1, NULL, 2, 500, '2025-04-22', 'Delivered'),
(2, 1, NULL, 1, 500, '2025-04-26', 'Rejected'),
(3, 1, NULL, 3, 100, '2025-04-20', 'Approved'),
(4, 1, NULL, 1, 50, '2025-04-29', 'Rejected'),
(5, 1, NULL, 2, 100, '2025-04-25', 'Approved'),
(7, 3, NULL, 3, 1000, '2025-04-29', 'Approved'),
(8, 3, NULL, 2, 150, '2025-04-30', 'In Transit'),
(9, 3, NULL, 1, 100, '2025-04-21', 'Approved'),
(11, 3, NULL, 1, 500, '2025-04-28', 'Approved'),
(12, NULL, 22, 2, 500, '2025-04-28', 'Pending'),
(13, 1, NULL, 2, 100, '2025-04-30', 'Pending'),
(14, NULL, 1, 4, 5000, '2025-04-28', 'Pending');

-- --------------------------------------------------------

--
-- 資料表結構 `shops`
--

DROP TABLE IF EXISTS `shops`;
CREATE TABLE IF NOT EXISTS `shops` (
  `shop_id` int NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(255) NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `city_id` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `shops`
--

INSERT INTO `shops` (`shop_id`, `shop_name`, `city_id`) VALUES
(1, 'Tokyo Bakery', 1),
(2, 'NYC Bakery', 2),
(3, 'HK Bakery', 3),
(4, 'Osaka Bakery', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `stock_id` int NOT NULL AUTO_INCREMENT,
  `fruit_id` int NOT NULL,
  `shop_id` int DEFAULT NULL,
  `warehouse_id` int DEFAULT NULL,
  `stock_level` int NOT NULL,
  PRIMARY KEY (`stock_id`),
  KEY `fruit_id` (`fruit_id`),
  KEY `shop_id` (`shop_id`),
  KEY `warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `stock`
--

INSERT INTO `stock` (`stock_id`, `fruit_id`, `shop_id`, `warehouse_id`, `stock_level`) VALUES
(1, 1, NULL, 1, 500),
(4, 1, 1, NULL, 500),
(5, 3, 4, NULL, 100),
(6, 2, 1, NULL, 250),
(7, 3, 1, NULL, 100),
(8, 2, NULL, 1, 1500),
(9, 3, NULL, 1, 200),
(10, 1, 3, NULL, 300),
(11, 2, 3, NULL, 500),
(12, 3, 3, NULL, 100);

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('BakeryShopStaff','WarehouseStaff','SeniorManagement') NOT NULL,
  `shop_id` int DEFAULT NULL,
  `warehouse_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `shop_id` (`shop_id`),
  KEY `warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `role`, `shop_id`, `warehouse_id`) VALUES
(1, 'Ronald Sham', 'RonaldSham@gmail.com', '12345678', 'BakeryShopStaff', 1, NULL),
(4, 'Chim Sir', 'ChimSir@gmail.com', '246810', 'WarehouseStaff', NULL, 1),
(6, 'admin', 'admin@admin.com', 'admin', 'SeniorManagement', NULL, NULL),
(7, 'Annie Sham', 'AnnieSham@gmail.com', '1357911', 'BakeryShopStaff', 3, NULL),
(8, 'Kenny S', 'KennyS@gmail.com', '123321', 'BakeryShopStaff', 4, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `warehouses`
--

DROP TABLE IF EXISTS `warehouses`;
CREATE TABLE IF NOT EXISTS `warehouses` (
  `warehouse_id` int NOT NULL AUTO_INCREMENT,
  `warehouse_name` varchar(255) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`warehouse_id`),
  KEY `country_id` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `warehouses`
--

INSERT INTO `warehouses` (`warehouse_id`, `warehouse_name`, `country_id`) VALUES
(1, 'Japan Central Warehouse', 1),
(22, 'Hong Kong Central Warehouse', 3);

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `borrowing`
--
ALTER TABLE `borrowing`
  ADD CONSTRAINT `borrowing_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`),
  ADD CONSTRAINT `borrowing_ibfk_2` FOREIGN KEY (`to_shop_id`) REFERENCES `shops` (`shop_id`),
  ADD CONSTRAINT `borrowing_ibfk_3` FOREIGN KEY (`from_shop_id`) REFERENCES `shops` (`shop_id`);

--
-- 資料表的限制式 `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`);

--
-- 資料表的限制式 `consumption`
--
ALTER TABLE `consumption`
  ADD CONSTRAINT `consumption_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`),
  ADD CONSTRAINT `consumption_ibfk_2` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`);

--
-- 資料表的限制式 `deliveries`
--
ALTER TABLE `deliveries`
  ADD CONSTRAINT `deliveries_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`),
  ADD CONSTRAINT `deliveries_ibfk_2` FOREIGN KEY (`from_warehouse_id`) REFERENCES `warehouses` (`warehouse_id`),
  ADD CONSTRAINT `deliveries_ibfk_3` FOREIGN KEY (`to_warehouse_id`) REFERENCES `warehouses` (`warehouse_id`),
  ADD CONSTRAINT `deliveries_ibfk_4` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`),
  ADD CONSTRAINT `deliveries_ibfk_5` FOREIGN KEY (`to_shop_id`) REFERENCES `shops` (`shop_id`);

--
-- 資料表的限制式 `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`warehouse_id`);

--
-- 資料表的限制式 `shops`
--
ALTER TABLE `shops`
  ADD CONSTRAINT `shops_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`);

--
-- 資料表的限制式 `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`),
  ADD CONSTRAINT `stock_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`),
  ADD CONSTRAINT `stock_ibfk_3` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`warehouse_id`);

--
-- 資料表的限制式 `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`warehouse_id`);

--
-- 資料表的限制式 `warehouses`
--
ALTER TABLE `warehouses`
  ADD CONSTRAINT `warehouses_ibfk_3` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
