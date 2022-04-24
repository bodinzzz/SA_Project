-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3307
-- 產生時間： 2022-01-16 08:02:05
-- 伺服器版本： 10.4.14-MariaDB
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `final_project`
--

-- --------------------------------------------------------

--
-- 資料表結構 `dealorder`
--

CREATE TABLE `dealorder` (
  `order_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `order_status` varchar(30) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `dealorder`
--

INSERT INTO `dealorder` (`order_id`, `seller_id`, `buyer_id`, `product_id`, `product_name`, `order_status`, `created`, `modified`) VALUES
(70, 33, 23, 62, 'textbook', '已完成', '2022-01-07 07:47:42', '2022-01-07 07:47:42'),
(71, 33, 24, 62, 'textbook', '已取消', '2022-01-07 07:47:54', '2022-01-07 07:47:54');

-- --------------------------------------------------------

--
-- 資料表結構 `manager`
--

CREATE TABLE `manager` (
  `user_id` int(11) NOT NULL,
  `account` int(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `manager`
--

INSERT INTO `manager` (`user_id`, `account`, `user_name`, `password`) VALUES
(5, 1, '管理員一號', '1');

-- --------------------------------------------------------

--
-- 資料表結構 `member`
--

CREATE TABLE `member` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `stu_id` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `user_phone` varchar(11) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `user_status` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `member`
--

INSERT INTO `member` (`user_id`, `user_name`, `stu_id`, `email`, `password`, `user_phone`, `created`, `modified`, `user_status`) VALUES
(23, '買家1', '108403001', '2@gmail.com', 'test', '0911111111', '2022-01-07 00:25:35', '2022-01-07 00:25:35', '1'),
(24, '買家2', '108403002', '3@gmail.com', 'test', '0922222222', '2022-01-07 00:26:19', '2022-01-07 00:26:19', '1'),
(25, '蕭名容', '108403040', 'sandy900825@gmail.com', 'test', '0966111111', '2022-01-07 00:26:19', '2022-01-07 00:26:19', 'available');

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_name` varchar(250) NOT NULL,
  `price` int(11) NOT NULL,
  `product_type` varchar(30) NOT NULL,
  `product_state` varchar(30) NOT NULL,
  `content` varchar(250) NOT NULL,
  `place` varchar(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `status` varchar(30) NOT NULL,
  `num_of_wait` int(11) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `product`
--

INSERT INTO `product` (`product_id`, `user_id`, `product_name`, `price`, `product_type`, `product_state`, `content`, `place`, `image`, `status`, `num_of_wait`, `created`, `modified`) VALUES
(22, 25, 'UML 系統分析與實作', 500, '教科書/參考書', '近全新', '資管系大三上SA課程用書', '後門', 'statics/img/t1.png', '可購買', 0, '2022-01-06 16:55:06', '2022-01-06 16:55:06'),
(23, 25, '原子習慣', 200, '課外書', '7.5成新', '暢銷書!!!非常推薦~', '女一到四門口', 'statics/img/r1.png', '可購買', 0, '2022-01-06 16:56:10', '2022-01-06 16:56:10'),
(24, 25, '加密貨幣之王', 150, '課外書', '5成新', '無', '後門', 'statics/img/r2.png', '可購買', 0, '2022-01-06 16:56:52', '2022-01-06 16:56:52'),
(28, 25, '口紅', 200, '美妝保養', '全新', '用不到~故售出', '女一到四門口', 'statics/img/m1.jpg', '可購買', 0, '2022-01-07 04:07:12', '2022-01-07 04:07:12'),
(29, 25, '白色襯衫', 450, '服飾配件', '全新', '僅試穿，穿起來很舒服~', '管院或後門', 'statics/img/c1.png', '可購買', 0, '2022-01-07 04:08:07', '2022-01-07 04:08:07'),
(30, 25, '黑色外套', 100, '服飾配件', '近全新', '僅穿過一次', '大門', 'statics/img/c2.png', '可購買', 0, '2022-01-07 04:08:59', '2022-01-07 04:08:59'),
(31, 25, '後背包', 200, '生活用品', '5成新', '小拉鍊壞掉了，原價2000', '宵夜街口', 'statics/img/l2.png', '可購買', 0, '2022-01-07 04:09:57', '2022-01-07 04:09:57'),
(32, 25, '蕎拉燕麥', 200, '飲食', '全新', '交換禮物抽到，對燕麥過敏故售出', '女一到四門口', 'statics/img/f1.jpg', '可購買', 0, '2022-01-07 04:11:04', '2022-01-07 04:11:04'),
(33, 25, '洋芋片', 50, '飲食', '全新', '很好吃', '後門', 'statics/img/f2.jpg', '可購買', 0, '2022-01-07 04:26:27', '2022-01-07 04:26:27'),
(34, 25, '韋禮安專輯', 400, '其他', '近全新', '很棒', '女一到四門口', 'statics/img/o1.png', '可購買', 0, '2022-01-07 04:28:08', '2022-01-07 04:28:08'),
(35, 25, '地毯', 10, '生活用品', '3成新', '地毯', '後門', 'statics/img/l1.png', '可購買', 0, '2022-01-07 04:30:39', '2022-01-07 04:30:39'),
(40, 25, 'iphone', 100, '電器用品', '近全新', '快壞了', '女一到四門口', 'statics/img/e2.png', '可購買', 0, '2022-01-07 05:21:46', '2022-01-07 05:21:46'),
(41, 25, '電腦', 100, '電器用品', '近全新', '電腦', '後門', 'statics/img/e1.png', '可購買', 0, '2022-01-07 05:22:13', '2022-01-07 05:22:13'),
(62, 33, 'textbook', 100, '教科書/參考書', '5成新', '大一', '女一到四門口', 'statics/asset/img/textbook1.png', '已售出', 2, '2022-01-07 07:47:12', '2022-01-07 07:47:12');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `dealorder`
--
ALTER TABLE `dealorder`
  ADD PRIMARY KEY (`order_id`);

--
-- 資料表索引 `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`user_id`);

--
-- 資料表索引 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`user_id`);

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `dealorder`
--
ALTER TABLE `dealorder`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `manager`
--
ALTER TABLE `manager`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member`
--
ALTER TABLE `member`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
