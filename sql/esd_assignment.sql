-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3306
-- 產生時間： 2025-04-22 14:16:08
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `borrowing`
--

INSERT INTO `borrowing` (`borrow_id`, `from_shop_id`, `to_shop_id`, `fruit_id`, `quantity`, `borrow_date`, `status`) VALUES
(1, 3, 1, 2, 100, '2025-04-26', 'Pending'),
(2, 4, 1, 1, 500, '2025-04-29', 'Pending'),
(3, 1, 1, 3, 250, '2025-04-30', 'Pending');

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
-- 資料表結構 `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `country_id` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(50) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `fruit_id` int NOT NULL,
  `from_location` varchar(255) NOT NULL,
  `to_location` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `delivery_date` date NOT NULL,
  `status` enum('In Transit','Delivered') DEFAULT 'In Transit',
  PRIMARY KEY (`delivery_id`),
  KEY `fruit_id` (`fruit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `fruits`
--

INSERT INTO `fruits` (`fruit_id`, `fruit_name`, `source_location`) VALUES
(1, 'Apple', 'USA'),
(2, 'Banana', 'Philippines'),
(3, 'Strawberry', 'Japan');

-- --------------------------------------------------------

--
-- 資料表結構 `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `shop_id` int NOT NULL,
  `fruit_id` int NOT NULL,
  `quantity` int NOT NULL,
  `reservation_date` date NOT NULL,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  PRIMARY KEY (`reservation_id`),
  KEY `fruit_id` (`fruit_id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `shop_id`, `fruit_id`, `quantity`, `reservation_date`, `status`) VALUES
(1, 1, 2, 500, '2025-04-22', 'Pending'),
(2, 1, 1, 500, '2025-04-26', 'Pending'),
(3, 1, 3, 100, '2025-04-20', 'Pending'),
(4, 1, 1, 50, '2025-04-29', 'Pending'),
(5, 1, 2, 100, '2025-04-25', 'Pending');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `stock`
--

INSERT INTO `stock` (`stock_id`, `fruit_id`, `shop_id`, `warehouse_id`, `stock_level`) VALUES
(1, 1, NULL, 1, 100),
(4, 1, 1, NULL, 500),
(5, 3, 4, NULL, 100);

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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `role`, `shop_id`) VALUES
(1, 'Ronald Sham', 'RonaldSham@gmail.com', '12345678', 'BakeryShopStaff', 1),
(2, 'Chim Sir', 'ChimSir@gmail.com', '246810', 'WarehouseStaff', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `warehouses`
--

INSERT INTO `warehouses` (`warehouse_id`, `warehouse_name`, `country_id`) VALUES
(1, 'Japan Central Warehouse', 1);

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
-- 資料表的限制式 `deliveries`
--
ALTER TABLE `deliveries`
  ADD CONSTRAINT `deliveries_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`);

--
-- 資料表的限制式 `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`fruit_id`) REFERENCES `fruits` (`fruit_id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`);

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
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`shop_id`);

--
-- 資料表的限制式 `warehouses`
--
ALTER TABLE `warehouses`
  ADD CONSTRAINT `warehouses_ibfk_3` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
