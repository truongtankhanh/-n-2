-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2018 at 04:48 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuisines`
--

-- --------------------------------------------------------

--
-- Table structure for table `citys`
--

CREATE TABLE `citys` (
  `id` int(11) NOT NULL,
  `city_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city_name` text COLLATE utf8_unicode_ci NOT NULL,
  `image_address` text COLLATE utf8_unicode_ci NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `citys`
--

INSERT INTO `citys` (`id`, `city_code`, `city_name`, `image_address`, `create_at`, `update_at`) VALUES
(1, 'CT001', 'TP. Hồ Chí Minh', 'src/admin/modules/user/city/images/TP.HCM.jpg', '2018-10-17', '0000-00-00'),
(2, 'CT002', 'Hà Nội', 'src/admin/modules/user/city/images/ha_noi.jpg', '2018-10-17', '0000-00-00'),
(3, 'CT003', 'Đà Lạt', 'src/admin/modules/user/city/images/Da_Lat.jpg', '2018-10-17', '0000-00-00'),
(4, 'CT004', 'Đà Nẵng', 'src/admin/modules/user/city/images/da_nang.jpg', '2018-10-17', '0000-00-00'),
(5, 'CT005', 'Hạ Long Bay', 'src/admin/modules/user/city/images/ha_long_bay.jpg', '2018-10-17', '0000-00-00'),
(6, 'CT006', 'Hội An', 'src/admin/modules/user/city/images/hoi_an.jpg', '2018-10-17', '0000-00-00'),
(7, 'CT007', 'Huế', 'src/admin/modules/user/city/images/Hue.jpg', '2018-10-17', '0000-00-00'),
(8, 'CT008', 'Phan Thiết', 'src/admin/modules/user/city/images/mui_ne.jpg', '2018-10-17', '0000-00-00'),
(9, 'CT009', 'Nha Trang', 'src/admin/modules/user/city/images/nha_trang.jpg', '2018-10-17', '0000-00-00'),
(10, 'CT0010', 'Phú Quốc', 'src/admin/modules/user/city/images/phu_quoc.jpg', '2018-10-17', '0000-00-00'),
(11, 'CT0011', 'Sa Pa', 'src/admin/modules/user/city/images/Sa_Pa.jpg', '2018-10-17', '0000-00-00'),
(12, 'CT0012', 'Quảng Bình', 'src/admin/modules/user/city/images/quang_binh.jpg', '2018-10-17', '0000-00-00'),
(13, 'CT0013', 'TP. Hồ Chí Minh', 'src/admin/modules/user/city/images/', '2018-10-17', '2018-10-26');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `user_name` text COLLATE utf8_unicode_ci NOT NULL,
  `email` text COLLATE utf8_unicode_ci NOT NULL,
  `photo_url` text COLLATE utf8_unicode_ci NOT NULL,
  `commment` text COLLATE utf8_unicode_ci NOT NULL,
  `create_time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `id` int(11) NOT NULL,
  `food_code` text COLLATE utf8_unicode_ci NOT NULL,
  `food_name` text COLLATE utf8_unicode_ci NOT NULL,
  `food_address` text COLLATE utf8_unicode_ci NOT NULL,
  `food_price` text COLLATE utf8_unicode_ci NOT NULL,
  `image_address` text COLLATE utf8_unicode_ci NOT NULL,
  `res_code` text COLLATE utf8_unicode_ci NOT NULL,
  `kind_code` text COLLATE utf8_unicode_ci NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`id`, `food_code`, `food_name`, `food_address`, `food_price`, `image_address`, `res_code`, `kind_code`, `create_at`, `update_at`) VALUES
(1, 'F001', 'Ẩm Thực Buffet Chay An Đông', '2 - 4 Lê Đại Hành, P. 6,  Quận 11, TP. HCM', '50.000đ - 200.000đ', 'images/Ẩm Thực Buffet Chay An Đông.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(2, 'F002', 'La Brasserie Restaurant - Hotel Nikko Saigon', 'Hotel Nikko Saigon, 235 Nguyễn Văn Cừ, Quận 1,  Quận 1, TP. HCM', '250.000đ - 1.200.000đ', 'images/La Brasserie Restaurant - Hotel Nikko Saigon.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(3, 'F003', 'Parkview Buffet - New World Saigon Hotel', 'New World Saigon Hotel, 76 Lê Lai, P. Bến Nghé,  Quận 1, TP. HCM', '500.000đ - 1.650.000đ', 'images/Parkview Buffet - New World Saigon Hotel.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(4, 'F004', 'Pachi Pachi - Thịt Nướng Nhật Bản', '52 Mạc Đĩnh Chi, P. Đakao,  Quận 1, TP. HCM', '50.000đ - 220.000đ', 'images/Pachi Pachi - Thịt Nướng Nhật Bản.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(5, 'F005', 'Hương Rừng 1 - Buffet', '371A Nguyễn Trãi, P. Nguyễn Cư Trinh,  Quận 1, TP. HCM', '100.000đ - 1.100.000đ', 'images/Hương Rừng 1 - Buffet.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(6, 'F006', 'Buffet Liberty Central Saigon Riverside', '17 Tôn Đức Thắng,  Quận 1, TP. HCM', '380.000đ - 680.000đ', 'images/Buffet Liberty Central Saigon Riverside.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(7, 'F007', 'Riverside Cafe Buffet - Renaissance Riverside Hotel', 'Renaissance Riverside Hotel, 8 - 15 Tôn Đức Thắng,  Quận 1, TP. HCM', '500.000đ - 1.100.000đ', 'images/Riverside Cafe Buffet - Renaissance Riverside Hotel.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(8, 'F008', 'Mezz - Buffet Tương Tác - Sofitel Saigon Plaza', '17 Lê Duẩn, P. Bến Nghé,  Quận 1, TP. HCM', '386.000đ - 1.605.450đ', 'images/Mezz - Buffet Tương Tác - Sofitel Saigon Plaza.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(9, 'F009', 'The Square - Novotel Saigon Centre', 'Tầng 2,  Tầng 2 Novotel Saigon Centre, 167 Hai Bà Trưng,  Quận 3, TP. HCM', '400.000đ - 1.000.000đ', 'images/The Square - Novotel Saigon Centre.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(10, 'F0010', 'Buffet Chay Cỏ Nội', '61 - 63 Hai Bà Trưng, P. Bến Nghé,  Quận 1, TP. HCM', '', 'images/Buffet Chay Cỏ Nội.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(11, 'F0011', 'Buffet Đệ Nhất', '18 Hoàng Việt, P. 4,  Quận Tân Bình, TP. HCM', '140.000đ - 295.000đ', 'images/Buffet Đệ Nhất.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(12, 'F0012', 'The Grill Restaurant Buffet - Duxton Hotel', '63 Nguyễn Huệ,  Quận 1, TP. HCM', '350.000đ - 660.000đ', 'images/The Grill Restaurant Buffet - Duxton Hotel.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(13, 'F0013', 'Tajmasago Castle - Buffet & Café', '6 Phan Văn Chương,  Quận 7, TP. HCM', '300.000đ - 500.000đ', 'images/Tajmasago Castle - Buffet & Cafe.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(14, 'F0014', 'Au Lac Do Brazil - Thịt Nướng Brazil - Pasteur', '238 Pasteur, P. 6,  Quận 3, TP. HCM', '800.000đ - 1.500.000đ', 'images/Au Lac Do Brazil - Thịt Nướng Brazil - Pasteur.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(15, 'F0015', 'Blue Diamond - Buffet', '48 - 50 Thủ Khoa Huân, Phường Bến Thành,  Quận 1, TP. HCM', '50.000đ - 220.000đ', 'images/Blue Diamond - Buffet.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(16, 'F0016', 'Continental Palace Saigon Buffet', '132 - 134 Đồng Khởi, P. Bến Nghé,  Quận 1, TP. HCM', '600.000đ - 2.200.000đ', 'images/Continental Palace Saigon Buffet.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(17, 'F0017', 'Buffet Gánh - Palace Hotel Saigon', 'Tầng 05, Khách Sạn Palace Sài Gòn, 56 - 66 Nguyễn Huệ,  Quận 1, TP. HCM', '200.000đ - 440.000đ', 'images/Buffet Gánh - Palace Hotel Saigon.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(18, 'F0018', 'Oscar Saigon Buffet - Nguyễn Huệ', '68 Nguyễn Huệ,  Quận 1, TP. HCM', '200.000đ - 385.000đ', 'images/Oscar Saigon Buffet - Nguyễn Huệ.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(19, 'F0019', 'Buffet Victory - Món Ăn 3 Miền', '14 Võ Văn Tần,  Quận 3, TP. HCM', '100.000đ - 220.000đ', 'images/Buffet Victory - Món Ăn 3 Miền.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(20, 'F0020', 'Buffet Béo 99k', '6 Tăng Nhơn Phú, P. Phước Long B,  Quận 9, TP. HCM', '75.000đ - 99.000đ', 'images/Buffet Béo 99k.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(21, 'F0021', 'Buffet Nụ Cười', '55B Nguyễn Thị Minh Khai, P. Bến Thành,  Quận 1, TP. HCM', '150.000đ - 275.000đ', 'images/Buffet Nụ Cười.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(22, 'F0022', 'Buffet Thanh Niên', '11 Nguyễn Văn Chiêm,  Quận 1, TP. HCM', '100.000đ - 275.000đ', 'images/Buffet Thanh Niên.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(23, 'F0023', 'Mặt Trời Đỏ - Buffet BBQ', '295 Phạm Văn Đồng, P. 1,  Quận Gò Vấp, TP. HCM', '50.000đ - 300.000đ', 'images/Mặt Trời Đỏ - Buffet BBQ.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(24, 'F0024', 'BBQ House - Buffet Lẩu & Nướng', '433 Lê Trọng Tấn, P. Sơn Kỳ,  Quận Tân Phú, TP. HCM', '199.000đ - 399.000đ', 'images/BBQ House - Buffet Lẩu & Nướng.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(25, 'F0025', 'Saigon Night Buffet 99K', '1 Quang Trung, P. 10,  Quận Gò Vấp, TP. HCM', '75.000đ - 99.000đ', 'images/Saigon Night Buffet 99K.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(26, 'F0026', 'Hell Bull - BBQ & Beer', '41 Xuân Thuỷ, P. Thảo Điền,  Quận 2, TP. HCM', '169.000đ - 500.000đ', 'images/Hell Bull - BBQ & Beer.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(27, 'F0027', 'Mr Grill - BBQ Buffet - Pandora City', 'Lầu 4 Foodcourt Pandora City, 1/1 Trường Chinh, P. Tây Thạnh,  Quận Tân Phú, TP. HCM', '50.000đ - 150.000đ', 'images/Mr Grill - BBQ Buffet - Pandora City.jpg', '', 'K001', '2018-10-17', '2018-10-17'),
(28, 'F0028', 'Bếp Xanh An Duyên - Nhà Hàng Chay', '10 Nguyễn Tri Phương,  Quận 5, TP. HCM', '30.000đ - 250.000đ', 'images/Bếp Xanh An Duyên - Nhà Hàng Chay.jpg', 'R0015', 'K002', '2018-10-18', '2018-10-18'),
(29, 'F0029', 'Vajra - Nhà Hàng Chay', '711 Lê Hồng Phong, P. 12,  Quận 10, TP. HCM', '25.000đ - 150.000đ', 'images/Vajra - Nhà Hàng Chay.jpg', 'R0016', 'K002', '2018-10-18', '2018-10-18'),
(30, 'F0030', 'Nhà Hàng Chay Phương Mai', '86F Võ Thị Sáu, P. Tân Định,  Quận 1, TP. HCM', '50.000đ - 200.000đ', 'images/Nhà Hàng Chay Phương Mai.jpg', 'R0017', 'K002', '2018-10-18', '2018-10-18'),
(31, 'F0031', 'Nhà Hàng Chay Đóa Sen Vàng', '253/8 Nguyễn Văn Trỗi, P. 10,  Quận Phú Nhuận, TP. HCM', '30.000đ - 100.000đ', 'images/Nhà Hàng Chay Đóa Sen Vàng.jpg', 'R0018', 'K002', '2018-10-18', '2018-10-18'),
(32, 'F0032', 'Nhà Hàng Chay Here & Now', '89E Nguyễn Công Hoan, P. 7,  Quận Phú Nhuận, TP. HCM', '50.000đ - 100.000đ', 'images/Nhà Hàng Chay Here & Now.jpg', 'R0019', 'K002', '2018-10-18', '2018-10-18'),
(33, 'F0033', 'Ấn Tâm - Nhà Hàng Ẩm Thực Chay', '18 Đường A4, P. 12,  Quận Tân Bình, TP. HCM', '50.000đ - 110.000đ', 'images/Ấn Tâm - Nhà Hàng Ẩm Thực Chay.jpg', 'R0020', 'K002', '2018-10-18', '2018-10-18'),
(34, 'F0034', 'Lẩu Nấm Chay An Nhiên', '70 Nguyễn Phi Khanh, P. Tân Định,  Quận 1, TP. HCM', '20.000đ - 250.000đ', 'images/Lẩu Nấm Chay An Nhiên - Nguyễn Phi Khanh.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(35, 'F0035', 'Hủ Tiếu Chay Cây Đề', '386 Lê Văn Sỹ,  Quận Tân Bình, TP. HCM', '15.000đ - 80.000đ', 'images/Hủ Tiếu Chay Cây Đề - Lê Văn Sỹ.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(36, 'F0036', 'Việt Chay - Chùa Vĩnh Nghiêm', '290/21A Nam Kỳ Khởi Nghĩa, P. 7,  Quận 3, TP. HCM', '40.000đ - 165.000đ', 'images/Việt Chay - Chùa Vĩnh Nghiêm.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(37, 'F0037', 'Cơm Chay Mani', '291/2 Võ Văn Tần, P. 5,  Quận 3, TP. HCM', '30.000đ - 55.000đ', 'images/Cơm Chay Mani.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(38, 'F0038', 'Sân Mây - Nhà Hàng Chay', '771 Lê Hồng Phong (Nối Dài), P. 12,  Quận 10, TP. HCM', '20.000đ - 150.000đ', 'images/Sân Mây - Nhà Hàng Chay.jpg', 'R0021', 'K002', '2018-10-18', '2018-10-18'),
(39, 'F0039', 'Quán Chay Bình An', '49 Ngô Quyền, P. 6,  Quận 10, TP. HCM', '20.000đ - 28.000đ', 'images/Quán Chay Bình An.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(40, 'F0040', 'Giác Tha - Quán Chay', '46B Trần Đình Xu, P. Cô Giang,  Quận 1, TP. HCM', '15.000đ - 35.000đ', 'images/Giác Tha - Quán Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(41, 'F0041', 'Quán Chay Thiện Tâm', '389 Sư Vạn Hạnh, P. 12,  Quận 10, TP. HCM', '15.000đ - 85.000đ', 'images/Quán Chay Thiện Tâm - Sư Vạn Hạnh.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(42, 'F0042', 'Quán Chay Thôi Kệ', '733 Lê Hồng Phong, P. 12,  Quận 10, TP. HCM', '10.000đ - 22.000đ', 'images/Quán Chay Thôi Kệ - Lê Hồng Phong.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(43, 'F0043', 'Vegeta - Ẩm Thực Chay', '684 Điện Biên Phủ, P. 10,  Quận 10, TP. HCM', '15.000đ - 150.000đ', 'images/Vegeta - Ẩm Thực Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(44, 'F0044', 'Định Ý - Thực Đơn Món Chay Phong Phú', '171B Cống Quỳnh,  Quận 1, TP. HCM', '20.000đ - 33.000đ', 'images/Định Ý - Thực Đơn Món Chay Phong Phú.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(45, 'F0045', 'Bếp Chay Phạm Hồng Phước', '146/1 Võ Thị Sáu, P. 8,  Quận 3, TP. HCM', '25.000đ - 120.000đ', 'images/Bếp Chay Phạm Hồng Phước - Võ Thị Sáu.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(46, 'F0046', 'Nhà Hàng Thuần Chay - iVegan', '6D Trần Quang Diệu, P. 13,  Quận 3, TP. HCM', '10.000đ - 195.000đ', 'images/Nhà Hàng Thuần Chay - iVegan.jpg', 'R0022', 'K002', '2018-10-18', '2018-10-18'),
(47, 'F0047', 'K Vegetarian - Ẩm Thực Chay & Café', '20/15 Phan Đăng Lưu, P. 6,  Quận Bình Thạnh, TP. HCM', '30.000đ - 200.000đ', 'images/K Vegetarian - Ẩm Thực Chay & Cafe.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(48, 'F0048', 'Quán Chay Phúc Ấn An Gia', '163/21/3 Tô Hiến Thành,  Quận 10, TP. HCM', '20.000đ - 50.000đ', 'images/Quán Chay Phúc Ấn An Gia.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(49, 'F0049', 'Quán Cơm Chay Thanh Lương', '545A Đường 3 Tháng 2, P. 8,  Quận 10, TP. HCM', '20.000đ - 50.000đ', 'images/Quán Cơm Chay Thanh Lương.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(50, 'F0050', 'Pháp Hoa - Quán Chay', '198 Nguyễn Cư Trinh, P. Nguyễn Cư Trinh,  Quận 1, TP. HCM', '20.000đ - 44.000đ', 'images/Pháp Hoa - Quán Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(51, 'F0051', 'Nhà Hàng Thiên Quốc Chay', '421/8 Sư Vạn Hạnh Nối Dài, P. 12,  Quận 10, TP. HCM', '30.000đ - 66.000đ', 'images/Nhà Hàng Thiên Quốc Chay.jpg', 'R0023', 'K002', '2018-10-18', '2018-10-18'),
(52, 'F0052', 'Vegan Kitchen - Bếp Thuần Chay', '23B Nguyễn Hữu Cảnh, P. 22,  Quận Bình Thạnh, TP. HCM', '20.000đ - 55.000đ', 'images/Vegan Kitchen - Bếp Thuần Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(53, 'F0053', 'Huệ Tâm - Ẩm Thực Chay', '9 Nguyễn Tuân, P. 3,  Quận Gò Vấp, TP. HCM', '20.000đ - 180.000đ', 'images/Huệ Tâm - Ẩm Thực Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(54, 'F0054', 'Quán Chay An Lạc', 'Tầng 1, 175/1 Phạm Ngũ Lão,  Quận 1, TP. HCM', '25.000đ - 55.000đ', 'images/Quán Chay An Lạc - Phạm Ngũ Lão.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(55, 'F0055', 'Nhà Hàng Chay Chuông Vàng', '769 Lê Hồng Phong , P. 12,  Quận 10, TP. HCM', '30.000đ - 150.000đ', 'images/Nhà Hàng Chay Chuông Vàng.jpg', 'R0024', 'K002', '2018-10-18', '2018-10-18'),
(56, 'F0056', 'Cơm Chay Diệu Giác', '177 Trần Não, P. Bình An,  Quận 2, TP. HCM', '15.000đ - 55.000đ', 'images/Cơm Chay Diệu Giác.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(57, 'F0057', 'Ngộ Quán - Ẩm Thực Chay', '6A Lê Quý Đôn, P. 6,  Quận 3, TP. HCM', '100.000đ - 150.000đ', 'images/Ngộ Quán - Ẩm Thực Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(58, 'F0058', 'Nhà Hàng Chay Thiện Duyên - Ẩm Thực Chay', '505 Xa Lộ Hà Nội, P. An Phú,  Quận 2, TP. HCM', '15.000đ - 50.000đ', 'images/Nhà Hàng Chay Thiện Duyên - Ẩm Thực Chay.jpg', 'R0025', 'K002', '2018-10-18', '2018-10-18'),
(59, 'F0059', 'Tiệm Chay Tường Viên', '58 - 62 Huỳnh Mẫn Đạt, P. 2,  Quận 5, TP. HCM', '20.000đ - 55.000đ', 'images/Tiệm Chay Tường Viên - Huỳnh Mẫn Đạt.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(60, 'F0060', 'Pô O Tu - Nhà Hàng Chay', '210 Nguyễn Thái Bình, P. 12,  Quận Tân Bình, TP. HCM', '2.000đ - 180.000đ', 'images/Pô O Tu - Nhà Hàng Chay.jpg', 'R0026', 'K002', '2018-10-18', '2018-10-18'),
(61, 'F0061', 'Nhà Hàng Chay Bồ Đề Ngọc Xanh', '181 Nguyễn Thái Bình, P. Nguyễn Thái Bình,  Quận 1, TP. HCM', '10.000đ - 90.000đ', 'images/Nhà Hàng Chay Bồ Đề Ngọc Xanh.jpg', 'R0027', 'K002', '2018-10-18', '2018-10-18'),
(62, 'F0062', 'The Organik House - Nhà Hàng Chay', '7F Nguyễn Thị Minh Khai,  Quận 1, TP. HCM', '40.000đ - 100.000đ', 'images/The Organik House - Nhà Hàng Chay.jpg', 'R0028', 'K002', '2018-10-18', '2018-10-18'),
(63, 'F0063', 'Quán Chay Thiên Kim', '359 Cây Trâm, P. 8,  Quận Gò Vấp, TP. HCM', '10.000đ - 20.000đ', 'images/Quán Chay Thiên Kim.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(64, 'F0064', 'Pi Vegetarian Bistro - Ẩm Thực Chay', 'Lầu 1, 19 Võ Văn Tần, P. 6,  Quận 3, TP. HCM', '30.000đ - 200.000đ', 'images/Pi Vegetarian Bistro - Ẩm Thực Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(65, 'F0065', 'Quán Chay Phật Hữu Duyên', '28 Văn Thân, P. 8,  Quận 6, TP. HCM', '40.000đ - 100.000đ', 'images/Quán Chay Phật Hữu Duyên - Văn Thân.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(66, 'F0066', 'Cơm Chay Tịnh Độ', '177 Võ Duy Ninh, P. 22,  Quận Bình Thạnh, TP. HCM', '10.000đ - 30.000đ', 'images/Cơm Chay Tịnh Độ.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(67, 'F0067', 'Quán Chay Bồ Đề Tịnh Tâm', '160 Trần Não, P. Bình An,  Quận 2, TP. HCM', '20.000đ - 35.000đ', 'images/Quán Chay Bồ Đề Tịnh Tâm.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(68, 'F0068', 'Mani - Nhà Hàng Chay', '17 Hoa Mai, P. 2,  Quận Phú Nhuận, TP. HCM', '10.000đ - 149.000đ', 'images/Mani - Nhà Hàng Chay.jpg', 'R0029', 'K002', '2018-10-18', '2018-10-18'),
(69, 'F0069', 'Quán Chay Nghĩa Trúc', '119 Nguyễn Gia Trí (Đường D2), P. 25,  Quận Bình Thạnh, TP. HCM', '10.000đ - 190.000đ', 'images/Quán Chay Nghĩa Trúc.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(70, 'F0070', 'Nhà Hàng Cơm Chay Thái Nhân', '491/10 Nguyễn Đình Chiểu ,P. 2,  Quận 3, TP. HCM', '20.000đ - 120.000đ', 'images/Nhà Hàng Cơm Chay Thái Nhân.jpg', 'R0030', 'K002', '2018-10-18', '2018-10-18'),
(71, 'F0071', 'Dzị - Nhà Hàng Chay & Café', '74 Út Tịch,  Quận Tân Bình, TP. HCM', '30.000đ - 220.000đ', 'images/Dzị - Nhà Hàng Chay & Cafe.jpg', 'R0031', 'K002', '2018-10-18', '2018-10-18'),
(72, 'F0072', 'Quảng Đức - Ẩm Thực Chay', '599 Cách Mạng Tháng 8, P. 15,  Quận 10, TP. HCM', '35.000đ - 55.000đ', 'images/Quảng Đức - Ẩm Thực Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(73, 'F0073', 'Nhà Hàng Chay Liên Hương', '10D Trần Nhật Duật, P. Tân Định,  Quận 1, TP. HCM', '25.000đ - 100.000đ', 'images/Nhà Hàng Chay Liên Hương.jpg', 'R0032', 'K002', '2018-10-18', '2018-10-18'),
(74, 'F0074', 'Ẩm Thực Chay Đỉnh Bồ Đề', '304 Nguyễn Thái Sơn,  Quận Gò Vấp, TP. HCM', '50.000đ - 150.000đ', 'images/Ẩm Thực Chay Đỉnh Bồ Đề.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(75, 'F0075', 'Nhà Hàng Chay Thanh Hương', '161 Ba Vân, P. 4,  Quận Tân Bình, TP. HCM', '20.000đ - 150.000đ', 'images/Nhà Hàng Chay Thanh Hương.jpg', 'R0033', 'K002', '2018-10-18', '2018-10-18'),
(76, 'F0076', 'Quán Chay Trúc Lâm', '36 Nguyễn Chí Thanh, P. 16,  Quận 11, TP. HCM', '6.000đ - 120.000đ', 'images/Quán Chay Trúc Lâm.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(77, 'F0077', 'Nhà Hàng Vườn Chay Garden', '482/39 Lê Quang Định, P. 11,  Quận Bình Thạnh, TP. HCM', '100.000đ - 170.000đ', 'images/Nhà Hàng Vườn Chay Garden.jpg', 'R0034', 'K002', '2018-10-18', '2018-10-18'),
(78, 'F0078', 'Thì Là Veggie - Nhà Hàng Chay', '226 Lý Long Tường, Khu Mỹ Kim 3, P. Tân Phong,  Quận 7, TP. HCM', '30.000đ - 100.000đ', 'images/Thì Là Veggie - Nhà Hàng Chay.jpg', 'R0035', 'K002', '2018-10-18', '2018-10-18'),
(79, 'F0079', 'Bún Riêu Chay', '53 Quốc Lộ 13,  Quận Thủ Đức, TP. HCM', '10.000đ - 25.000đ', 'images/Bún Riêu Chay.jpg', '', 'K002', '2018-10-18', '2018-10-18'),
(80, 'F0080', 'Nhà Hàng Amitaba - Ẩm Thực Chay', '40 Nguyễn Thái Học, P. Cầu Ông Lãnh,  Quận 1, TP. HCM', '35.000đ - 150.000đ', 'images/Nhà Hàng Amitaba - Ẩm Thực Chay.jpg', 'R0036', 'K002', '2018-10-18', '2018-10-18'),
(81, 'F0081', 'Hanuri - Quán Ăn Hàn Quốc', '284A Nguyễn Đình Chiểu, P. 6,  Quận 3, TP. HCM', '50.000đ - 110.000đ', 'images/Hanuri - Quán Ăn Hàn Quốc - Nguyễn Đình Chiểu.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(82, 'F0082', 'Beefsteak Ciao Vợ Đẹp - Italian Cuisine', '10 Cao Bá Nhạ, P. Nguyễn Cư Trinh,  Quận 1, TP. HCM', '80.000đ - 150.000đ', 'images/Beefsteak Ciao Vợ Đẹp - Italian Cuisine.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(83, 'F0083', 'Chảo Restaurant - Bánh Mì Chảo', '72C Trần Quốc Toản, P. 8,  Quận 3, TP. HCM', '25.000đ - 70.000đ', 'images/Chảo Restaurant - Bánh Mì Chảo.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(84, 'F0084', 'Sushi Hokkaido Sachi 北海道サチ', '40 - 42 Đông Du ,  Quận 1, TP. HCM', '150.000đ - 500.000đ', 'images/Sushi Hokkaido Sachi 北海道サチ - Đông Du.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(85, 'F0085', 'Pizza Hut', '264-266 Nguyễn Trãi,  Quận 5, TP. HCM', '150.000đ - 330.000đ', 'images/Pizza Hut - Nguyễn Trãi.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(86, 'F0086', 'El Sol - Meat & Wine', '140/2 Võ Thị Sáu,  Quận 3, TP. HCM', '35.000đ - 349.000đ', 'images/El Sol - Meat & Wine - Võ Thị Sáu.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(87, 'F0087', 'Sushi Dining AOI - Món Nhật', '53 - 55 Bà Huyện Thanh Quan, P. 6,  Quận 3, TP. HCM', '200.000đ - 500.000đ', 'images/Sushi Dining AOI - Món Nhật.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(88, 'F0088', 'Food Court', 'Tầng Trệt,  Tầng Trệt AEON Mall Tân Phú, 30 Bờ Bao Tân Thắng, P. Sơn Kỳ,  Quận Tân Phú, TP. HCM', '30.000đ - 22.000đ', 'images/Food Court - AEON Mall Tân Phú.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(89, 'F0089', 'Barbos - Nhà Hàng Gà Hàn Quốc', '11 - 13 Lê Thị Riêng, P. Bến Thành,  Quận 1, TP. HCM', '59.000đ - 280.000đ', 'images/Barbos - Nhà Hàng Gà Hàn Quốc.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(90, 'F0090', 'Ocean Palace - Ẩm Thực Trung Hoa', '2 Lê Duẩn,  Quận 1, TP. HCM', '150.000đ - 550.000đ', 'images/Ocean Palace - Ẩm Thực Trung Hoa.jpg', 'R0037', 'K003', '2018-10-18', '2018-10-18'),
(91, 'F0091', 'The Deck Saigon', '38 Nguyễn Ư Dĩ, P. Thảo Điền,  Quận 2, TP. HCM', '100.000đ - 385.000đ', 'images/The Deck Saigon - Ven Sông Saigon.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(92, 'F0092', 'Hẻm Spaghetti', '225/9 Nguyễn Đình Chiểu, P. 5,  Quận 3, TP. HCM', '30.000đ - 200.000đ', 'images/Hẻm Spaghetti - Nguyễn Đình Chiểu.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(93, 'F0093', 'The LOG Restaurant', 'Rooftop, GEM Center, 8 Nguyễn Bỉnh Khiêm,  Quận 1, TP. HCM', '80.000đ - 1.120.000đ', 'images/The LOG Restaurant.jpg', 'R0038', 'K003', '2018-10-18', '2018-10-18'),
(94, 'F0094', 'Én Tea House & Restaurant', '308-308c Điện Biên Phủ, P. 4,  Quận 3, TP. HCM', '50.000đ - 200.000đ', 'images/Én Tea House & Restaurant.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(95, 'F0095', 'Phong Cua 1 - Cây Sứ Quán', '1019A Bình Quới, P. 28,  Quận Bình Thạnh, TP. HCM', '200.000đ - 330.000đ', 'images/Phong Cua 1 - Cây Sứ Quán.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(96, 'F0096', 'Shri - Rooftop Restaurant & Lounge', 'Lầu 23 Centec Tower, 72 - 74 Nguyễn Thi Minh Khai,  Quận 3, TP. HCM', '300.000đ - 660.000đ', 'images/Shri - Rooftop Restaurant & Lounge.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(97, 'F0097', 'White Palace - Tiệc Cưới & Hội Nghị', '194 Hoàng Văn Thụ, P. 9,  Quận Phú Nhuận, TP. HCM', '4.000.000đ - 11.000.000đ', 'images/White Palace - Tiệc Cưới & Hội Nghị.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(98, 'F0098', 'Dae Jang Gum', 'Tầng 1,  Tầng 1 - Mplaza Saigon, 39 Lê Duẩn, P. Bến Nghé,  Quận 1, TP. HCM', '150.000đ - 330.000đ', 'images/Dae Jang Gum - Mplaza Saigon.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(99, 'F0099', 'Kimchi Kimchi', '83 Nguyễn Thái Học. P. Cầu Ông Lãnh,  Quận 1, TP. HCM', '49.000đ - 329.000đ', 'images/Kimchi Kimchi - Nguyễn Thái Học.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(100, 'F00100', 'Mr.BBQ - Ẩm thực Hàn Quốc', '137 Tôn Dật Tiên,  Quận 7, TP. HCM', '150.000đ - 330.000đ', 'images/Mr.BBQ - Ẩm thực Hàn Quốc.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(101, 'F00101', 'Mì Cay Naga', '224 Phạm Văn Đồng, P.1 ,  Quận Gò Vấp, TP. HCM', '35.000đ - 45.000đ', 'images/Mì Cay Naga - 224 Phạm Văn Đồng.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(102, 'F00102', 'Nhà Hàng Ngon', '160 Pasteur, P. Bến Nghé,  Quận 1, TP. HCM', '200.000đ - 550.000đ', 'images/Nhà Hàng Ngon - Pasteur.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(103, 'F00103', 'Hải Sản Maria Saigon', '172C Nguyễn Đình Chiểu, P. 6,  Quận 3, TP. HCM', '500.000đ - 1.100.000đ', 'images/Hải Sản Maria Saigon.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(104, 'F00104', 'Yuri Yuri - Ẩm Thực Hàn Quốc', '358 Nguyễn Văn Nghi, P. 7,  Quận Gò Vấp, TP. HCM', '30.000đ - 250.000đ', 'images/Yuri Yuri - Ẩm Thực Hàn Quốc.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(105, 'F00105', 'Bò Sinh Đôi - Steak & Lẩu Bò Mỹ', '192 Phan Xích Long, P. 2,  Quận Phú Nhuận, TP. HCM', '49.000đ - 92.000đ', 'images/Bò Sinh Đôi - Steak & Lẩu Bò Mỹ.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(106, 'F00106', 'Shabu Ya', 'Tầng 4,  Tầng 4 - SC VivoCity, 1058 Nguyễn Văn Linh, KP. 1, P. Tân Phong,  Quận 7, TP. HCM', '210.000đ - 280.000đ', 'images/Shabu Ya - SC VivoCity.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(107, 'F00107', 'Curry Shika - Ăn Vặt Nhật Bản', '1/4 Nguyễn Văn Tráng, P. Bến Thành,  Quận 1, TP. HCM', '50.000đ - 280.000đ', 'images/Curry Shika - Ăn Vặt Nhật Bản.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(108, 'F00108', 'Riverside Palace - Tiệc Cưới & Hội Nghị', '360D Bến Vân Đồn,  Quận 4, TP. HCM', '3.200.000đ - 6.600.000đ', 'images/Riverside Palace - Tiệc Cưới & Hội Nghị.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(109, 'F00109', 'So Baek San - Ẩm Thực Hàn Quốc', '85 Nguyễn Thái Học. P. Cầu Ông Lãnh ,  Quận 1, TP. HCM', '45.000đ - 198.000đ', 'images/So Baek San - Ẩm Thực Hàn Quốc.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(110, 'F00110', 'Secret Garden - Vietnamese Restaurant & Tea House', '158 Bis/40-41 Pasteur, P. Bến Nghé,  Quận 1, TP. HCM', '150.000đ - 330.000đ', 'images/Secret Garden - Vietnamese Restaurant & Tea House.jpg', '', 'K003', '2018-10-18', '2018-10-18'),
(111, 'F00111', 'Bánh Tráng Trộn Chú Viên', '38 Nguyễn Thượng Hiền, P. 5,  Quận 3, TP. HCM', '10.000đ - 22.000đ', 'images/Bánh Tráng Trộn Chú Viên.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(112, 'F00112', 'Chè Khúc Bạch Thanh', '68/210 Trần Quang Khải,  Quận 1, TP. HCM', '20.000đ - 33.000đ', 'images/Chè Khúc Bạch Thanh - Chân Cầu Hoàng Hoa Thám.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(113, 'F00113', 'Xôi Gà Bà Chiểu', 'Chợ Bà Chiểu, 318/1 Bùi Hữu Nghĩa, P. 1 (Quán Bên Trong, Không Phải Xe Đẩy),  Quận Bình Thạnh, TP. HCM', '15.000đ - 28.000đ', 'images/Xôi Gà Bà Chiểu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(114, 'F00114', 'Quán 223 - Bánh Flan Thập Cẩm', '223 Trần Bình Trọng,  Quận 5, TP. HCM', '15.000đ - 22.000đ', 'images/Quán 223 - Bánh Flan Thập Cẩm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(115, 'F00115', 'Bánh Tráng Nướng Đà Lạt', '53 - 57 Cao Thắng,  Quận 3, TP. HCM', '10.000đ - 22.000đ', 'images/Bánh Tráng Nướng Đà Lạt - Cao Thắng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(116, 'F00116', 'Bánh Đúc Phan Đăng Lưu', '116/11 Phan Đăng Lưu, P. 3,  Quận Phú Nhuận, TP. HCM', '10.000đ - 22.000đ', 'images/Bánh Đúc Phan Đăng Lưu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(117, 'F00117', 'Bánh Tráng Nướng C002', '002 Lô C, Chung Cư 23/49 Đinh Tiên Hoàng, P. 3,  Quận Bình Thạnh, TP. HCM', '10.000đ - 23.000đ', 'images/Bánh Tráng Nướng C002 - Đinh Tiên Hoàng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(118, 'F00118', 'Cơm Trộn 2 Cô', '294/63 Xô Viết Nghệ Tĩnh, P. 21,  Quận Bình Thạnh, TP. HCM', '30.000đ - 50.000đ', 'images/Cơm Trộn 2 Cô - Xô Viết Nghệ Tĩnh.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(119, 'F00119', 'Trái Cây Tô A Bo', '268 Tô Hiến Thành,  Quận 10, TP. HCM', '26.000đ - 26.000đ', 'images/Trái Cây Tô A Bo.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(120, 'F00120', 'Chè Hà Ký', '138 Châu Văn Liêm, P. 11,  Quận 5, TP. HCM', '15.000đ - 33.000đ', 'images/Chè Hà Ký - Châu Văn Liêm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(121, 'F00121', 'Há Cảo - Phá Lấu Bò Marie Curie', '433/44 Lê Đại Hành, P.11,  Quận 11, TP. HCM', '5.000đ - 15.000đ', 'images/Há Cảo - Phá Lấu Bò Marie Curie.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(122, 'F00122', 'Bột Chiên Đạt Thành', '277 Võ Văn Tần, P. 5,  Quận 3, TP. HCM', '20.000đ - 33.000đ', 'images/Bột Chiên Đạt Thành.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(123, 'F00123', 'Trái Cây Tô Alley Fruit Cocktail', 'Hẻm 177 Lý Tự Trọng,  Quận 1, TP. HCM', '20.000đ - 44.000đ', 'images/Trái Cây Tô Alley Fruit Cocktail - Lý Tự Trọng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(124, 'F00124', 'Ăn Vặt - Cá Viên Chiên', '176 Đặng Văn Ngữ,  Quận Phú Nhuận, TP. HCM', '10.000đ - 15.000đ', 'images/Ăn Vặt - Cá Viên Chiên.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(125, 'F00125', 'Ốc Tuyết', 'Hẻm 456 Dương Bá Trạc,  Quận 8, TP. HCM', '20.000đ - 20.000đ', 'images/Ốc Tuyết.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(126, 'F00126', 'Sushi Nhí', '21 Nguyễn Công Trứ, P. 19,  Quận Bình Thạnh, TP. HCM', '30.000đ - 50.000đ', 'images/Sushi Nhí - Nguyễn Công Trứ.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(127, 'F00127', 'Chị Tuyền - Bún Thịt Nướng', '175 Cô Giang,  Quận 1, TP. HCM', '30.000đ - 66.000đ', 'images/Chị Tuyền - Bún Thịt Nướng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(128, 'F00128', 'Há Cảo Xíu Mại', 'Xe Đẩy Góc Phan Xích Long & Vạn Kiếp,  Quận Bình Thạnh, TP. HCM', '10.000đ - 20.000đ', 'images/Há Cảo Xíu Mại - Vạn Kiếp.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(129, 'F00129', 'Quán Cô Ba - Bánh Căn, Bánh Xèo Phan Rang', '10 Khu A, CC Phú Thọ, Nguyễn Thị Nhỏ, P. 15,  Quận 11, TP. HCM', '8.000đ - 37.000đ', 'images/Quán Cô Ba - Bánh Căn, Bánh Xèo Phan Rang.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(130, 'F00130', 'Cháo Sườn Ông Tạ', '294 Phạm Văn Hai, P. 5,  Quận Tân Bình, TP. HCM', '10.000đ - 17.000đ', 'images/Cháo Sườn Ông Tạ - Phạm Văn Hai.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(131, 'F00131', 'Há Cảo Xíu Mại', '57A Đề Thám, P. Cầu Ông Lãnh,  Quận 1, TP. HCM', '5.000đ - 20.000đ', 'images/Há Cảo Xíu Mại - Đề Thám.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(132, 'F00132', 'Hải Thanh - Há Cảo & Xíu Mại', '167 Bùi Hữu Nghĩa, P. 7,  Quận 5, TP. HCM', '10.000đ - 20.000đ', 'images/Hải Thanh - Há Cảo & Xíu Mại.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(133, 'F00133', 'Sushi Viên Phương Mập', '188 Hoàng Diệu,  Quận 4, TP. HCM', '1.000đ - 8.000đ', 'images/Sushi Viên Phương Mập - Hoàng Diệu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(134, 'F00134', 'Ốc 10.000', '1569 Phạm Thế Hiển,  Quận 8, TP. HCM', '10.000đ - 20.000đ', 'images/Ốc 10.000 - Phạm Thế Hiển.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(135, 'F00135', 'Súp Cua Nhà Thờ Đức Bà', '86 Nguyễn Du, P. Bến Nghé,  Quận 1, TP. HCM', '15.000đ - 28.000đ', 'images/Súp Cua Nhà Thờ Đức Bà.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(136, 'F00136', 'Bò Lá Lốt', '47/20 Đường Số 2, P. Tân Quy,  Quận 7, TP. HCM', '22.000đ - 55.000đ', 'images/Bò Lá Lốt - Tân Quy.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(137, 'F00137', 'Thanh Vy - Bò Lá Lốt & Gà Nướng', '267A Minh Phụng, P. 2,  Quận 11, TP. HCM', '10.000đ - 28.000đ', 'images/Thanh Vy - Bò Lá Lốt & Gà Nướng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(138, 'F00138', 'Ăn Vặt Ba Tròn', '016 Chung Cư A3 Phan Xích Long,  Quận Phú Nhuận, TP. HCM', '10.000đ - 40.000đ', 'images/Ăn Vặt Ba Tròn - Phan Xích Long.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(139, 'F00139', 'Bánh Bèo Huế Ngọc Trâm', '419 Sư Vạn Hạnh, P. 12,  Quận 10, TP. HCM', '20.000đ - 55.000đ', 'images/Bánh Bèo Huế Ngọc Trâm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(140, 'F00140', 'Phá Lấu Chiên', '90 Vũ Tùng, P. 2,  Quận Bình Thạnh, TP. HCM', '15.000đ - 20.000đ', 'images/Phá Lấu Chiên - Vũ Tùng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(141, 'F00141', 'Út Tuyền - Súp Cua', '160 Nguyễn Thị Nhỏ,  Quận 11, TP. HCM', '10.000đ - 20.000đ', 'images/Út Tuyền - Súp Cua.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(142, 'F00142', 'Thanh - Mực Khổng Lồ Hồng Kông', '1101A Đường 3 Tháng 2, P. 6,  Quận 11, TP. HCM', '25.000đ - 100.000đ', 'images/Thanh - Mực Khổng Lồ Hồng Kông - 1101A Đường 3 Tháng 2.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(143, 'F00143', 'Kem Miếng Dừa', '450 Nguyễn Chí Thanh, P. 6,  Quận 10, TP. HCM', '25.000đ - 44.000đ', 'images/Kem Miếng Dừa - Nguyễn Chí Thanh.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(144, 'F00144', 'Bánh Tráng Nướng Đà Lạt', '61 Cao Thắng,  Quận 3, TP. HCM', '10.000đ - 16.000đ', 'images/Bánh Tráng Nướng Đà Lạt - 61 Cao Thắng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(145, 'F00145', 'Kim Thảo - Hột Vịt Lộn', '104 Xuân Thủy,  Quận 2, TP. HCM', '8.000đ - 30.000đ', 'images/Kim Thảo - Hột Vịt Lộn.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(146, 'F00146', 'Bánh Flan Quen Mà Lạ', '195 - 210 Lê Quang Sung, P. 6,  Quận 6, TP. HCM', '6.000đ - 15.000đ', 'images/Bánh Flan Quen Mà Lạ.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(147, 'F00147', 'Ốc Nho Quán', '190 Lê Quốc Hưng, P. 12,  Quận 4, TP. HCM', '30.000đ - 50.000đ', 'images/Ốc Nho Quán.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(148, 'F00148', 'Trái Cây Tô Bu Bu', '247 Tô Hiến Thành,  Quận 10, TP. HCM', '20.000đ - 25.000đ', 'images/Trái Cây Tô Bu Bu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(149, 'F00149', 'Quán Nướng Ngói Sài Gòn', '232 Phạm Văn Đồng, P. 1,  Quận Gò Vấp, TP. HCM', '10.000đ - 30.000đ', 'images/Quán Nướng Ngói Sài Gòn.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(150, 'F00150', 'Chả Lụi 369', '343/20 Tô Hiến Thành, P. 12,  Quận 10, TP. HCM', '15.000đ - 30.000đ', 'images/Chả Lụi 369.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(151, 'F00151', 'Thiên Hương - Bánh Bèo & Bánh Nậm', '12/1 Cư Xá Lữ Gia, P. 15,  Quận 11, TP. HCM', '22.000đ - 28.000đ', 'images/Thiên Hương - Bánh Bèo & Bánh Nậm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(152, 'F00152', 'Bánh Đa Trộn Badar', '117 Lê Văn Sỹ,  Quận Phú Nhuận, TP. HCM', '17.000đ - 17.000đ', 'images/Bánh Đa Trộn Badar - Lê Văn Sỹ.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(153, 'F00153', 'Khanh - Bánh Khọt Vũng Tàu', '7 Đồng Nai, P. 15,  Quận 10, TP. HCM', '15.000đ - 50.000đ', 'images/Khanh - Bánh Khọt Vũng Tàu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(154, 'F00154', 'Cút Vịt Lộn 10 Món', '775 Phan Văn Trị, P. 7,  Quận Gò Vấp, TP. HCM', '8.000đ - 16.000đ', 'images/Cút Vịt Lộn 10 Món.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(155, 'F00155', 'Bò Lá Lốt - Bánh Xèo Bắc Hải', 'Bắc Hải & CMT8,  Quận 10, TP. HCM', '20.000đ - 55.000đ', 'images/Bò Lá Lốt - Bánh Xèo Bắc Hải.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(156, 'F00156', 'Bún Riêu Đà Lạt', '87 Cô Giang, P. Cô Giang,  Quận 1, TP. HCM', '25.000đ - 40.000đ', 'images/Bún Riêu Đà Lạt - Cô Giang.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(157, 'F00157', 'Súp Cua - Lê Quang Định', '278 Lê Quang Ðịnh,  Quận Bình Thạnh, TP. HCM', '15.000đ - 33.000đ', 'images/Súp Cua - Lê Quang Định.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(158, 'F00158', 'Trà Sữa Tam Cốc', '543A/3 Lê Đức Thọ, P.17,  Quận Gò Vấp, TP. HCM', '9.000đ - 15.000đ', 'images/Trà Sữa Tam Cốc.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(159, 'F00159', 'Bánh Flan Quỳnh Hoa', 'Hẻm 195 Hoàng Diệu,  Quận 4, TP. HCM', '10.000đ - 20.000đ', 'images/Bánh Flan Quỳnh Hoa.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(160, 'F00160', 'Hàu Nướng 10k', '16 Đinh Tiên Hoàng, P. Đa Kao,  Quận 1, TP. HCM', '10.000đ - 30.000đ', 'images/Hàu Nướng 10k.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(161, 'F00161', 'Bánh Tráng Me TNF - Bắc Hải', '29 Bắc Hải, P. 15,  Quận 10, TP. HCM', '6.000đ - 60.000đ', 'images/Bánh Tráng Me TNF - Bắc Hải.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(162, 'F00162', 'Chị Mười - Bánh Flan', 'Hẻm 14 Trần Bình Trọng,  Quận 5, TP. HCM', '5.000đ - 10.000đ', 'images/Chị Mười - Bánh Flan.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(163, 'F00163', 'Bánh Takoyaki Nhật Bản - Thoại Ngọc Hầu', '26 Tân Thành, P. Hòa Thạnh,  Quận Tân Phú, TP. HCM', '3.000đ - 12.000đ', 'images/Bánh Takoyaki Nhật Bản - Thoại Ngọc Hầu.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(164, 'F00164', 'Phá Lấu Rubi', '230 Nguyễn Hồng Đào, P. 14 ,  Quận Tân Bình, TP. HCM', '18.000đ - 25.000đ', 'images/Phá Lấu Rubi - Nguyễn Hồng Đào.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(165, 'F00165', 'Kem Bơ & Trái Cây Tô 251', '251 Tô Hiến Thành, P. 13,  Quận 10, TP. HCM', '10.000đ - 25.000đ', 'images/Kem Bơ & Trái Cây Tô 251.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(166, 'F00166', 'Chè Cô Giang', '85 Cô Giang,  Quận 1, TP. HCM', '10.000đ - 15.000đ', 'images/Chè Cô Giang.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(167, 'F00167', 'Takoyaki SP', '135 Thành Thái, P. 14,  Quận 10, TP. HCM', '18.000đ - 30.000đ', 'images/Takoyaki SP - Thành Thái.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(168, 'F00168', 'Há Cảo - Nguyễn Văn Lạc', '45 Nguyễn Văn Lạc P. 19,  Quận Bình Thạnh, TP. HCM', '22.000đ - 30.000đ', 'images/Há Cảo - Nguyễn Văn Lạc.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(169, 'F00169', 'Pizza Đà Lạt - Lê Lợi', '13 Lê Lợi,  Quận Gò Vấp, TP. HCM', '10.000đ - 20.000đ', 'images/Pizza Đà Lạt - Lê Lợi.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(170, 'F00170', 'Bánh Xèo Ngọc Sơn', '103 Ngô Quyền, P. 11,  Quận 5, TP. HCM', '40.000đ - 110.000đ', 'images/Bánh Xèo Ngọc Sơn.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(171, 'F00171', 'Trà Sữa Yolo', '15 Đường Số 44, P. 10,  Quận 6, TP. HCM', '10.000đ - 22.000đ', 'images/Trà Sữa Yolo.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(172, 'F00172', 'Đặc Sản Phan Rang 2', 'CC20 Trường Sơn, P. 15,  Quận 10, TP. HCM', '40.000đ - 88.000đ', 'images/Đặc Sản Phan Rang 2 - Trường Sơn.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(173, 'F00173', 'Takoyaki Nhật Tảo', '0010 Lô C Chung Cư Ấn Quang, Bà Hạt, P. 9,  Quận 10, TP. HCM', '15.000đ - 30.000đ', 'images/Takoyaki Nhật Tảo - Bà Hạt.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(174, 'F00174', 'Gỏi Cuốn - Lê Văn Sỹ', '359/1/9A Lê Văn Sỹ,  Quận 3, TP. HCM', '5.000đ - 10.000đ', 'images/Gỏi Cuốn - Lê Văn Sỹ.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(175, 'F00175', 'Bánh Flan Rau Câu Dừa', 'Sạp Sấm Phụng - Chợ Tôn Thất Đạm, 64B Tôn Thất Đạm,  Quận 1, TP. HCM', '45.000đ - 110.000đ', 'images/Bánh Flan Rau Câu Dừa - Sấm Phụng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(176, 'F00176', 'Bánh Flan Tây Ban Nha', 'Đường Số 1, P. 11,  Quận Gò Vấp, TP. HCM', '15.000đ - 30.000đ', 'images/Bánh Flan Tây Ban Nha.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(177, 'F00177', 'Thành Tín - Hủ Tiếu Nam Vang', 'Vỉa Hè 80 Cao Thắng,  Quận 3, TP. HCM', '20.000đ - 40.000đ', 'images/Thành Tín - Hủ Tiếu Nam Vang.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(178, 'F00178', 'Cơm Tấm Huyền', 'Hẻm 95 Đinh Tiên Hoàng, P. 3,  Quận Bình Thạnh, TP. HCM', '30.000đ - 45.000đ', 'images/Cơm Tấm Huyền - Đinh Tiên Hoàng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(179, 'F00179', 'Phá Lấu - Phùng Văn Cung', '34 Phùng Văn Cung,  Quận Phú Nhuận, TP. HCM', '15.000đ - 25.000đ', 'images/Phá Lấu - Phùng Văn Cung.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(180, 'F00180', 'Bánh Flan & Bánh Bèo', 'Chợ Hạnh Thông Tây,  Quận Gò Vấp, TP. HCM', '15.000đ - 25.000đ', 'images/Bánh Flan & Bánh Bèo - Chợ Hạnh Thông Tây.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(181, 'F00181', 'Xôi Cadé - Trần Phú', '451 Trần Phú, P. 7,  Quận 5, TP. HCM', '5.000đ - 15.000đ', 'images/Xôi Cadé - Trần Phú.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(182, 'F00182', 'Quán Vườn Cau - Nước Mía Sầu Riêng & Củ Mì Hấp Cốt Dừa', '259A Quốc Lộ 22, Tân Thông Hội,  Củ Chi, TP. HCM', '5.000đ - 15.000đ', 'images/Quán Vườn Cau - Nước Mía Sầu Riêng & Củ Mì Hấp Cốt Dừa.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(183, 'F00183', 'Quán Cây Me - Bò Tùng Xẻo & Cua Cà Mau', '18/3 Nguyễn Lâm, P. 6,  Quận 10, TP. HCM', '50.000đ - 165.000đ', 'images/Quán Cây Me - Bò Tùng Xẻo & Cua Cà Mau.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(184, 'F00184', 'Cơm Chiên Da Gà & Xôi Gà', 'Hẻm Đối Diện THPT Vân Đồn, Hoàng Diệu, P. 9,  Quận 4, TP. HCM', '10.000đ - 15.000đ', 'images/Cơm Chiên Da Gà & Xôi Gà.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(185, 'F00185', 'Gà Nướng Bùi Thị Xuân', '154 Bùi Thị Xuân,  Quận 1, TP. HCM', '6.000đ - 28.000đ', 'images/Gà Nướng Bùi Thị Xuân.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(186, 'F00186', 'Quán Ốc 63 - Cari Cua & Hải Sản Tươi Sống', '63 Nguyễn Trường Tộ,  Quận 4, TP. HCM', '100.000đ - 200.000đ', 'images/Quán Ốc 63 - Cari Cua & Hải Sản Tươi Sống.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(187, 'F00187', 'Xiên nướng Ngói - Quang Trung', 'Quang Trung,  Quận Gò Vấp, TP. HCM', '60.000đ - 150.000đ', 'images/Xiên nướng Ngói - Quang Trung.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(188, 'F00188', 'Bánh Khọt Đặng Dung', 'Hẻm 32 Đặng Dung,  Quận 1, TP. HCM', '15.000đ - 30.000đs', 'images/Bánh Khọt Đặng Dung.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(189, 'F00189', 'Bánh Mì Nướng Muối Ớt Cô Năm', '281 Đường 3 Tháng 2, P. 10,  Quận 10, TP. HCM', '12.000đ - 12.000đ', 'images/Bánh Mì Nướng Muối Ớt Cô Năm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(190, 'F00190', 'Bột Chiên Vạn Thành', 'Hẻm 185 Võ Văn Tần, P. 5,  Quận 3, TP. HCM', '15.000đ - 33.000đ', 'images/Bột Chiên Vạn Thành - Võ Văn Tần.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(191, 'F00191', 'Bún Cay Thái 2 Thuận', '320 Cây Trâm, P. 9,  Quận Gò Vấp, TP. HCM', '20.000đ - 30.000đ', 'images/Bún Cay Thái 2 Thuận.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(192, 'F00192', 'Cô Mai - Bánh Bèo Nóng & Bánh Canh Hẹ', '54 Hoa Sứ, P. 7,  Quận Phú Nhuận, TP. HCM', '20.000đ - 30.000đ', 'images/Cô Mai - Bánh Bèo Nóng & Bánh Canh Hẹ.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(193, 'F00193', 'Xiên Nướng KingKong', '19 Phạm Ngũ Lão, P. 3,  Quận Gò Vấp, TP. HCM', '5.000đ - 5.000đ', 'images/Xiên Nướng KingKong.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(194, 'F00194', 'Bánh Sữa Pate Chiên Trứng', '399A Hậu Giang, P. 11,  Quận 6, TP. HCM', '18.000đ - 20.000đ', 'images/Bánh Sữa Pate Chiên Trứng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(195, 'F00195', 'Takoyaki Hatachi', '23 Trần Khắc Chân, P.Tân Định,  Quận 1, TP. HCM', '5.000đ - 20.000đ', 'images/Takoyaki Hatachi - Trần Khắc Chân.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(196, 'F00196', 'Cô Liêng - Bò Lá Lốt & Mỡ Chài', '321 Võ Văn Tần,  Quận 3, TP. HCM', '50.000đ - 165.000đ', 'images/Cô Liêng - Bò Lá Lốt & Mỡ Chài.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(197, 'F00197', 'Bắp Xào - Cống Quỳnh', '189 Cống Quỳnh, P. Nguyễn Cư Trình,  Quận 1, TP. HCM', '10.000đ - 20.000đ', 'images/Bắp Xào - Cống Quỳnh.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(198, 'F00198', 'Bột Chiên - Hoàng Sa', '757 Hoàng Sa,  Quận 3, TP. HCM', '15.000đ - 30.000đ', 'images/Bột Chiên - Hoàng Sa.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(199, 'F00199', 'Bạch Tuộc Nướng Thương', '90 Đường 17, P. Tân Kiểng,  Quận 7, TP. HCM', '100.000đ - 220.000đ', 'images/Bạch Tuộc Nướng Thương.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(200, 'F00200', 'Cây Bàng 2 - Các Món Nướng & Trái Cây Tô', '32D Vũ Huy Tấn,  Quận Bình Thạnh, TP. HCM', '10.000đ - 50.000đ', 'images/Cây Bàng 2 - Các Món Nướng & Trái Cây Tô.jpg', '', 'K005', '2018-10-18', '2018-10-25'),
(201, 'F00201', 'Gặm - Chân Gà Muối Sả Ớt', '356/3 Bạch Đằng, P. 14,  Quận Bình Thạnh, TP. HCM', '45.000đ - 140.000đ', 'images/Gặm - Chân Gà Muối Sả Ớt.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(202, 'F00202', 'Bánh Xèo Cô Thêm', '154 Song Hành,  Hóc Môn, TP. HCM', '30.000đ - 72.000đ', 'images/Bánh Xèo Cô Thêm - Song Hành.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(203, 'F00203', 'Bò Bía - Nguyễn Thượng Hiền', '71 Nguyễn Thượng Hiền,  Quận Bình Thạnh, TP. HCM', '15.000đ - 20.000đ', 'images/Bò Bía - Nguyễn Thượng Hiền.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(204, 'F00204', 'Takoyaki Tân Phước', '48 Thiên Phước, P. 9,  Quận Tân Bình, TP. HCM', '15.000đ - 25.000đ', 'images/Takoyaki Tân Phước.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(205, 'F00205', 'Bánh Xèo Sammy', '14 Trường Sơn, P. 2,  Quận Tân Bình, TP. HCM', '20.000đ - 55.000đ', 'images/Bánh Xèo Sammy.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(206, 'F00206', 'Xôi Gà Bánh Bao 315', 'Hẻm 315 Hai Bà Trưng,  Quận 1, TP. HCM', '15.000đ - 20.000đ', 'images/Xôi Gà Bánh Bao 315 - Hai Bà Trưng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(207, 'F00207', 'Bò Nướng 347 - Bò Lá Lốt & Bò Nướng', '347/13 Huỳnh Văn Bánh,  Quận Phú Nhuận, TP. HCM', '200.000đ - 550.000đ', 'images/Bò Nướng 347 - Bò Lá Lốt & Bò Nướng.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(208, 'F00208', 'Ốc Cô Năm', 'Hẻm 432 Dương Bá Trạc,  Quận 8, TP. HCM', '20.000đ - 30.000đ', 'images/Ốc Cô Năm.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(209, 'F00209', 'Han Flan - Chè Khúc Bạch', '336 Lê Văn Thọ, P. 11,  Quận Gò Vấp, TP. HCM', '5.000đ - 10.000đ', 'images/Han Flan - Chè Khúc Bạch.jpg', '', 'K004', '2018-10-18', '2018-10-18'),
(210, 'F00210', 'Cô Tám - Bánh Mì Nướng Muối Ớt', '56T Lê Quang Sung, P. 6,  Quận 6, TP. HCM', '15.000đ - 20.000đ', 'images/Cô Tám - Bánh Mì Nướng Muối Ớt.jpg', '', 'K004', '2018-10-18', '2018-10-18');

-- --------------------------------------------------------

--
-- Table structure for table `kind`
--

CREATE TABLE `kind` (
  `id` int(11) NOT NULL,
  `kind_code` text COLLATE utf8_unicode_ci NOT NULL,
  `kind_name` text COLLATE utf8_unicode_ci NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `kind`
--

INSERT INTO `kind` (`id`, `kind_code`, `kind_name`, `create_at`, `update_at`) VALUES
(1, 'K001', 'Buffet - Món Việt', '2018-10-17', '2018-10-25'),
(2, 'K002', 'Món Chay - Món Việt', '2018-10-17', '2018-10-25'),
(4, 'K003', 'Nhà hàng - Món Việt', '2018-10-17', '2018-10-25'),
(5, 'K004', 'Ăn vặt/vỉa hè', '2018-10-17', '2018-10-17'),
(6, 'K005', 'Quán ăn - Món Việt - Món ngon', '2018-10-25', '2018-10-25');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `fullname` text COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `sex` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `email` text COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `level` int(11) NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `fullname`, `birthday`, `sex`, `email`, `password`, `level`, `create_at`, `update_at`) VALUES
(1, 'Trương Tấn Khánh', '1996-10-08', 'nam', 'khanhit100896@gmail.com', 'ec0989cbc7d9bc8efd824dc71130ec9e', 1, '2018-10-15', '2018-10-17'),
(3, 'Trương Tấn Khánh', '1996-08-10', 'nữ', 'khanhxahoa9677@gmail.com', '5762ef05fd48db05bae83e991098ca7d', 2, '2018-10-18', '2018-10-18'),
(4, 'Nguyễn Mạnh Hùng', '1996-04-30', 'nam', 'Kenvil.nero@gmail.com', '87cfe89dd6e63c2ae0a206cecc4c3b64', 1, '2018-10-24', '2018-10-24');

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
  `id` int(11) NOT NULL,
  `res_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `res_name` text COLLATE utf8_unicode_ci NOT NULL,
  `number_of_branches` int(11) NOT NULL,
  `image_address` text COLLATE utf8_unicode_ci NOT NULL,
  `city_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `restaurants`
--

INSERT INTO `restaurants` (`id`, `res_code`, `res_name`, `number_of_branches`, `image_address`, `city_code`, `create_at`, `update_at`) VALUES
(1, 'R001', 'Pizza 4Ps', 5, 'images/Pizza 4Ps - Pizza Kiểu Nhật - Nguyễn Văn Linh.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(2, 'R002', 'Buzza Pizza', 2, 'images/Buzza Pizza - Emart Gò Vấp.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(3, 'R003', 'The Sushi Bar', 5, 'images/The Sushi Bar - Hoàng Văn Thái.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(4, 'R004', 'Sumo BBQ', 10, 'images/Sumo BBQ - Quang Trung - Buffet Nướng & Lẩu.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(5, 'R005', 'Beefsteak Titi', 4, 'images/Beefsteak Titi - Vũ Huy Tấn.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(6, 'R006', 'Hancook Korean Fast Food', 3, 'images/Hancook Korean Fast Food - Nguyễn Trọng Tuyển.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(7, 'R007', 'Papas Chicken', 2, 'images/Papas Chicken - Phú Mỹ Hưng.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(8, 'R008', 'Gogi House', 26, 'images/Gogi House - Quán Nướng Hàn Quốc - Nguyễn Đình Chiểu.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(9, 'R009', 'Làng Nướng Nam Bộ', 3, 'images/Làng Nướng Nam Bộ - Tô Hiến Thành.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(10, 'R0010', 'Baozi', 1, 'images/Baozi - Ẩm Thực Đài Loan - Nguyễn Thái Học.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(11, 'R0011', 'Hana BBQ & Hot Pot Buffet', 6, 'images/Hana BBQ & Hot Pot Buffet - Phan Văn Trị.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(12, 'R0012', 'Mr. Park', 3, 'images/Mr. Park - Sườn Nướng Hàn Quốc - Nguyễn Công Trứ.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(13, 'R0013', 'Beefsteak Hai Con Bò', 4, 'images/Beefsteak Hai Con Bò - Nguyễn Thị Minh Khai.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(14, 'R0014', 'Bonjour Resto', 3, 'images/Bonjour Resto - Beefsteak Nguyễn Huy Tưởng.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(15, 'R0015', 'Bếp Xanh An Duyên', 1, 'images/Bếp Xanh An Duyên - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(16, 'R0016', 'Vajra', 1, 'images/Vajra - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-31'),
(17, 'R0017', 'Nhà Hàng Chay Phương Mai', 1, 'images/Nhà Hàng Chay Phương Mai.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(18, 'R0018', 'Nhà Hàng Chay Đóa Sen Vàng', 2, 'images/Nhà Hàng Chay Đóa Sen Vàng - Nguyễn Văn Trỗi.jpg', 'CT001', '2018-10-18', '2018-10-31'),
(19, 'R0019', 'Nhà Hàng Chay Here & Now', 1, 'images/Nhà Hàng Chay Here & Now.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(20, 'R0020', 'Ấn Tâm', 1, 'images/Ấn Tâm - Nhà Hàng Ẩm Thực Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(21, 'R0021', 'Sân Mây', 2, 'images/Sân Mây - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-31'),
(22, 'R0022', 'iVegan', 1, 'images/Nhà Hàng Thuần Chay - iVegan.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(23, 'R0023', 'Nhà Hàng Thiên Quốc', 1, 'images/Nhà Hàng Thiên Quốc Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(24, 'R0024', 'Nhà Hàng Chay Chuông Vàng', 1, 'images/Nhà Hàng Chay Chuông Vàng.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(25, 'R0025', 'Nhà Hàng Chay Thiện Duyên', 1, 'images/Nhà Hàng Chay Thiện Duyên - Ẩm Thực Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(26, 'R0026', 'Pô O Tu', 1, 'images/Pô O Tu - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(27, 'R0027', 'Nhà Hàng Chay Bồ Đề Ngọc Xanh', 1, 'images/Nhà Hàng Chay Bồ Đề Ngọc Xanh.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(28, 'R0028', 'The Organik House', 1, 'images/The Organik House - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(29, 'R0029', 'Mani', 1, 'images/Mani - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(30, 'R0030', 'Nhà Hàng Thái Nhân', 1, 'images/Nhà Hàng Cơm Chay Thái Nhân.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(31, 'R0031', 'Dzị', 1, 'images/Dzị - Nhà Hàng Chay & Cafe.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(32, 'R0032', 'Nhà Hàng Chay Liên Hương', 1, 'images/Nhà Hàng Chay Liên Hương.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(33, 'R0033', 'Nhà Hàng Chay Thanh Hương', 1, 'images/Nhà Hàng Chay Thanh Hương.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(34, 'R0034', 'Nhà Hàng Vườn Chay Garden', 1, 'images/Nhà Hàng Vườn Chay Garden.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(35, 'R0035', 'Thì Là Veggie', 1, 'images/Thì Là Veggie - Nhà Hàng Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(36, 'R0036', 'Nhà Hàng Amitaba', 1, 'images/Nhà Hàng Amitaba - Ẩm Thực Chay.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(37, 'R0037', 'Ocean Palace', 1, 'images/Ocean Palace - Ẩm Thực Trung Hoa.jpg', 'CT001', '2018-10-18', '2018-10-18'),
(38, 'R0038', 'The LOG', 1, 'images/The LOG Restaurant.jpg', 'CT001', '2018-10-18', '2018-10-18');

-- --------------------------------------------------------

--
-- Table structure for table `restaurants_branch`
--

CREATE TABLE `restaurants_branch` (
  `id` int(11) NOT NULL,
  `res_branch_code` text COLLATE utf8_unicode_ci NOT NULL,
  `res_code` text COLLATE utf8_unicode_ci NOT NULL,
  `res_branch_name` text COLLATE utf8_unicode_ci NOT NULL,
  `res_branch_address` text COLLATE utf8_unicode_ci NOT NULL,
  `res_branch_opentime` text COLLATE utf8_unicode_ci NOT NULL,
  `res_branch_price` text COLLATE utf8_unicode_ci NOT NULL,
  `res_branch_image` text COLLATE utf8_unicode_ci NOT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `restaurants_branch`
--

INSERT INTO `restaurants_branch` (`id`, `res_branch_code`, `res_code`, `res_branch_name`, `res_branch_address`, `res_branch_opentime`, `res_branch_price`, `res_branch_image`, `create_at`, `update_at`) VALUES
(1, 'BR001', 'R001', 'Pizza 4Ps - Pizza Kiểu Nhật - Lê Thánh Tôn', '8/15 Lê Thánh Tôn, P. Bến Nghé,  Quận 1, TP. HCM', '10:00 - 23:00', '250.000đ - 500.000đ', 'images/Pizza 4Ps - Pizza Kiểu Nhật - Lê Thánh Tôn.jpg', '2018-10-18', '2018-10-18'),
(2, 'BR002', 'R001', 'Pizza 4Ps - Pizza Kiểu Nhật - Nguyễn Văn Linh', 'Lô MD2, Nguyễn Văn Linh, P Tân Phú,  Quận 7, TP. HCM', '10:00 - 22:00', '200.000đ - 300.000đ', 'images/Pizza 4Ps - Pizza Kiểu Nhật - Nguyễn Văn Linh.jpg', '2018-10-18', '2018-10-18'),
(3, 'BR003', 'R001', 'Pizza 4Ps - Pizza Kiểu Nhật - Thủ Khoa Huân', '8 Thủ Khoa Huân, P. Bến Thành,  Quận 1, TP. HCM', '10:00 - 23:00 | 10:00 - 02:00', '150.000đ - 300.000đ', 'images/Pizza 4Ps - Pizza Kiểu Nhật - Thủ Khoa Huân.jpg', '2018-10-18', '2018-10-18'),
(4, 'BR004', 'R001', 'Pizza 4Ps - Pizza Kiểu Nhật - Saigon Centre', 'Tầng 6,  01 - 2 Lầu 6 Saigon Centre, 65 Lê Lợi, P. Bến Nghé,  Quận 1, TP. HCM', '10:00 - 23:00', '250.000đ - 400.000đ', 'images/Pizza 4Ps - Pizza Kiểu Nhật - Saigon Centre.jpg', '2018-10-18', '2018-10-18'),
(5, 'BR005', 'R001', 'Pizza 4Ps - Pizza Kiểu Nhật - Hai Bà Trưng', '151 Hai Bà Trưng,  Quận 3, TP. HCM', '10:00 - 23:00', '250.000đ - 400.000đ', 'images/Pizza 4Ps - Pizza Kiểu Nhật - Hai Bà Trưng.jpg', '2018-10-18', '2018-10-18'),
(6, 'BR006', 'R002', 'Buzza Pizza - Nguyễn Trung Trực', 'Tầng 3,  Tầng 3, 5-7-9 Nguyễn Trung Trực, P. Bến Thành,  Quận 1, TP. HCM', '10:00 - 22:30', '49.000đ - 199.000đ', 'images/Buzza Pizza - Nguyễn Trung Trực.jpg', '2018-10-18', '2018-10-18'),
(7, 'BR007', 'R002', 'Buzza Pizza - Emart Gò Vấp', 'Tầng Trệt Emart Gò Vấp - 366 Phan Văn Trị, P. 5,  Quận Gò Vấp, TP. HCM', '10:00 - 22:30', '50.000đ - 200.000đ', 'images/Buzza Pizza - Emart Gò Vấp.jpg', '2018-10-18', '2018-10-18'),
(8, 'BR008', 'R003', 'The Sushi Bar - Thiên Quế - Hai Bà Trưng', '179B Hai Bà Trưng, P. 6,  Quận 3, TP. HCM', '11:00 - 14:30 | 17:00 - 22:30', '100.000đ - 330.000đ', 'images/The Sushi Bar - Thiên Quế - Hai Bà Trưng.jpg', '2018-10-18', '2018-10-18'),
(9, 'BR009', 'R003', 'The Sushi Bar - Nguyễn Đình Chiểu', '54 Nguyễn Đình Chiểu,  Quận 1, TP. HCM', '11:00 - 14:30 | 17:00 - 22:30', '100.000đ - 330.000đ', 'images/The Sushi Bar - Nguyễn Đình Chiểu.jpg', '2018-10-18', '2018-10-18'),
(10, 'BR0010', 'R003', 'The Sushi Bar - Hoàng Văn Thái', '73 Hoàng Văn Thái, P. Tân Phú,  Quận 7, TP. HCM', '11:00 - 14:30 | 17:00 - 22:30', '100.000đ - 330.000đ', 'images/The Sushi Bar - Hoàng Văn Thái.jpg', '2018-10-18', '2018-10-18'),
(11, 'BR0011', 'R003', 'The Sushi Bar - Saigon Court', '149 Nguyễn Đình Chiểu ,  Quận 3, TP. HCM', '11:00 - 14:30 | 17:00 - 22:30', '100.000đ - 330.000đ', 'images/The Sushi Bar - Saigon Court.jpg', '2018-10-18', '2018-10-18'),
(12, 'BR0012', 'R003', 'The Sushi Bar - Capri Hotel Residences', 'C6B02-2, New South Urban City, P. Tân Phú,  Quận 7, TP. HCM', '11:00 - 14:30 | 17:00 - 22:30', '100.000đ - 330.000đ', 'images/The Sushi Bar - Capri Hotel Residences.jpg', '2018-10-18', '2018-10-18'),
(13, 'BR0013', 'R004', 'Sumo BBQ - Nguyễn Đình Chiểu - Buffet Nướng & Lẩu', '120 Bis Nguyễn Đình Chiểu,  Quận 1, TP. HCM', ' 11:00 - 16:00 | 17:00 - 22:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - Nguyễn Đình Chiểu - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(14, 'BR0014', 'R004', 'Sumo BBQ - Vincom Center - Buffet Nướng & Lẩu', 'Tầng Hầm 3,  Tầng B3 Vincom Center, 72 Lê Thánh Tôn, P. Bến Nghé,  Quận 1, TP. HCM', ' 11:00 - 16:00 | 17:00 - 22:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - Vincom Center - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(15, 'BR0015', 'R004', 'Sumo BBQ - Phan Xích Long - Buffet Nướng & Lẩu', '210 Phan Xích Long, P. 2,  Quận Phú Nhuận, TP. HCM', '11:00 - 16:00 | 17:00 - 22:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - Phan Xích Long - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(16, 'BR0016', 'R004', 'Sumo BBQ - Lê Văn Sỹ - Buffet Nướng & Lẩu', '300 Lê Văn Sỹ, P. 1,  Quận Tân Bình, TP. HCM', '11:00 - 16:00 | 17:00 - 22:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - Lê Văn Sỹ - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(17, 'BR0017', 'R004', 'Sumo BBQ - Quang Trung - Buffet Nướng & Lẩu', '50 Quang Trung, P. 10,  Quận Gò Vấp, TP. HCM', '05:00 - 22:00 | 11:00 - 16:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - Quang Trung - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(18, 'BR0018', 'R004', 'Sumo BBQ - Cao Thắng - Buffet Nướng & Lẩu', '59B Cao Thắng,  Quận 3, TP. HCM', '11:00 - 22:00', '239.000đ - 378.000đ', 'images/Sumo BBQ - Cao Thắng - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(19, 'BR0019', 'R004', 'Sumo BBQ - SC VivoCity - Buffet Nướng & Lẩu', 'Tầng 2,  Tầng 2 Lô 02 - 39 SC Vivo City, 1058 Nguyễn Văn Linh, KP. 1, P. Tân Phong,  Quận 7, TP. HCM', '09:00 - 21:00', '300.000đ - 407.000đ', 'images/Sumo BBQ - SC VivoCity - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(20, 'BR0020', 'R004', 'Sumo BBQ - Lê Lai - Buffet Nướng & Lẩu', '134 - 136 Lê Lai, P. Bến Thành,  Quận 1, TP. HCM', '09:00 - 22:00', '100.000đ - 600.000đ', 'images/Sumo BBQ - Lê Lai - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(21, 'BR0021', 'R004', 'Sumo BBQ - Aeon Mall Bình Tân - Buffet Nướng & Lẩu', 'Tầng Trệt,  Tầng Trệt Aeon Mall Bình Tân, 1 Đường Số 17A, P. Bình Trị Đông,  Quận Bình Tân, TP. HCM', '10:00 - 22:00', '300.000đ - 450.000đ', 'images/Sumo BBQ - Aeon Mall Bình Tân - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(22, 'BR0022', 'R004', 'Sumo BBQ - Vạn Hạnh Mall - Buffet Nướng & Lẩu', 'Tầng 5,  Tầng 5 Vạn Hạnh Mall, 11 Sư Vạn Hạnh, P. 12,  Quận 10, TP. HCM', '10:00 - 22:00', '269.000đ - 389.000đ', 'images/Sumo BBQ - Vạn Hạnh Mall - Buffet Nướng & Lẩu.jpg', '2018-10-18', '2018-10-18'),
(23, 'BR0023', 'R005', 'Beefsteak Titi - Vũ Huy Tấn', '67/4/118 Vũ Huy Tấn, P. 3,  Quận Bình Thạnh, TP. HCM', '10:00 - 21:30', '55.000đ - 110.000đ', 'images/Beefsteak Titi - Vũ Huy Tấn.jpg', '2018-10-18', '2018-10-18'),
(24, 'BR0024', 'R005', 'Beefsteak TiTi - Hồ Hảo Hớn', '30 Hồ Hảo Hớn, P. Cô Giang,  Quận 1, TP. HCM', '10:00 - 22:00', '55.000đ - 110.000đ', 'images/Beefsteak TiTi - Hồ Hảo Hớn.jpg', '2018-10-18', '2018-10-18'),
(25, 'BR0025', 'R005', 'Beefsteak Titi - Tôn Thất Hiệp', '14 Tôn Thất Hiệp, P. 13,  Quận 11, TP. HCM', '08:00 - 22:30', '60.000đ - 150.000đ', 'images/Beefsteak Titi - Tôn Thất Hiệp.jpg', '2018-10-18', '2018-10-18'),
(26, 'BR0026', 'R005', 'Beefsteak Titi - Trần Cao Vân', '25 Trần Cao Vân, P. Đa Kao,  Quận 1, TP. HCM', '07:00 - 22:00', '50.000đ - 100.000đ', 'images/Beefsteak Titi - Trần Cao Vân.jpg', '2018-10-18', '2018-10-18'),
(27, 'BR0027', 'R006', 'Hancook Korean Fast Food - Đường 3 Tháng 2', '181/2 Đường 3 Tháng 2,  Quận 10, TP. HCM', '09:00 - 22:00', '60.000đ - 165.000đ', 'images/Hancook Korean Fast Food - Đường 3 Tháng 2.jpg', '2018-10-18', '2018-10-18'),
(28, 'BR0028', 'R006', 'Hancook Korean Fast Food - Nguyễn Tri Phương', '263 Nguyễn Tri Phương, P. 5,  Quận 10, TP. HCM', '09:00 - 22:00', '15.000đ - 265.000đ', 'images/Hancook Korean Fast Food - Nguyễn Tri Phương.jpg', '2018-10-18', '2018-10-18'),
(29, 'BR0029', 'R006', 'Hancook Korean Fast Food - Nguyễn Trọng Tuyển', '320 Nguyễn Trọng Tuyển, P. 1,  Quận Tân Bình, TP. HCM', '09:00 - 22:00', '10.000đ - 265.000đ', 'images/Hancook Korean Fast Food - Nguyễn Trọng Tuyển.jpg', '2018-10-18', '2018-10-18'),
(30, 'BR0030', 'R007', 'Papas Chicken - Phú Mỹ Hưng', 'S37-1 Hưng Vượng 2, Bùi Bằng Đoàn,  Quận 7, TP. HCM', '10:30 - 23:00', '100.000đ - 165.000đ', 'images/Papas Chicken - Phú Mỹ Hưng.jpg', '2018-10-18', '2018-10-18'),
(31, 'BR0031', 'R007', 'Papas Chicken - AEON Mall Bình Tân', 'Tầng 2,  Tầng 2, Aeon Mall Bình Tân, 1 Đường Số 17A, P. Bình Trị Đông B,  Quận Bình Tân, TP. HCM', '10:00 - 22:00', '50.000đ - 100.000đ', 'images/Papas Chicken - AEON Mall Bình Tân.jpg', '2018-10-18', '2018-10-18'),
(32, 'BR0032', 'R008', 'Gogi House - Nướng Hàn Quốc - Phan Xích Long', '198 Phan Xích Long,  Quận Phú Nhuận, TP. HCM', '10:00 - 22:00', '65.000đ - 315.000đ', 'images/Gogi House - Nướng Hàn Quốc - Phan Xích Long.jpg', '2018-10-18', '2018-10-18'),
(33, 'BR0033', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Saigon Superbowl', 'Saigon Superbowl, A43 Trường Sơn,  Quận Tân Bình, TP. HCM', '10:30 - 22:00', '300.000đ - 550.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Saigon Superbowl.jpg', '2018-10-18', '2018-10-18'),
(34, 'BR0034', 'R008', 'Gogi House - Thịt Nướng Hàn Quốc - Lê Văn Sỹ', '254 - 256 Lê Văn Sỹ,  Quận 3, TP. HCM', '10:30 - 22:00', '300.000đ - 550.000đ', 'images/Gogi House - Thịt Nướng Hàn Quốc - Lê Văn Sỹ.jpg', '2018-10-18', '2018-10-18'),
(35, 'BR0035', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Vincom Thủ Đức', 'Tầng 3,  Tầng 3 Vincom Thủ Đức, 216 Võ Văn Ngân,  Quận Thủ Đức, TP. HCM', '10:00 - 22:00', '79.000đ - 250.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Vincom Thủ Đức.jpg', '2018-10-18', '2018-10-18'),
(36, 'BR0036', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - SC VivoCity', 'Tầng 4,  Tầng 4 - 12A SC VivoCity, 1058 Nguyễn Văn Linh, KP. 1, P. Tân Phong,  Quận 7, TP. HCM', '10:00 - 22:00', '79.000đ - 250.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - SC VivoCity.jpg', '2018-10-18', '2018-10-18'),
(37, 'BR0037', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Vincom Center', 'Vincom Center, 72 Lê Thánh Tôn, P. Bến Nghé,  Quận 1, TP. HCM', '10:00 - 22:00', '100.000đ - 300.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Vincom Center.jpg', '2018-10-18', '2018-10-18'),
(38, 'BR0038', 'R008', 'Gogi House - Nướng Hàn Quốc - Vincom Quang Trung', 'Tầng Hầm 1,  B1-04 Vincom Quang Trung, 190 Quang Trung,  Quận Gò Vấp, TP. HCM', '09:00 - 21:00', '100.000đ - 300.000đ', 'images/Gogi House - Nướng Hàn Quốc - Vincom Quang Trung.jpg', '2018-10-18', '2018-10-18'),
(39, 'BR0039', 'R008', 'Gogi House - Quán Thịt Nướng Hàn Quốc - Pearl Plaza', 'Tầng 4,  Lầu 4 - 02 Pearl Plaza, 561A Điện Biên Phủ, P. 25,  Quận Bình Thạnh, TP. HCM', '10:00 - 22:00', '300.000đ - 550.000đ', 'images/Gogi House - Quán Thịt Nướng Hàn Quốc - Pearl Plaza.jpg', '2018-10-18', '2018-10-18'),
(40, 'BR0040', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - AEON Mall Tân Phú', 'Tầng 1,  Tầng 1 Lô F33, Aeon Mall Tân Phú, 30 Bờ Bao Tân Thắng, P. Sơn Kỳ,  Quận Tân Phú, TP. HCM', '08:00 - 22:00', '300.000đ - 550.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - AEON Mall Tân Phú.jpg', '2018-10-18', '2018-10-18'),
(41, 'BR0041', 'R008', 'Gogi House - Thịt Nướng Hàn Quốc - E-Mart Gò Vấp', 'Tầng 1 E Mart Gò Vấp, 168 Phan Văn Trị, P. 5,  Quận Gò Vấp, TP. HCM', '08:30 - 22:00', '100.000đ - 500.000đ', 'images/Gogi House - Thịt Nướng Hàn Quốc - E-Mart Gò Vấp.jpg', '2018-10-18', '2018-10-18'),
(42, 'BR0042', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Vincom Lê Văn Việt', 'Tầng 4,  Tầng L4-02 Vincom Lê Văn Việt, 50 Lê Văn Việt, P. Hiệp Phú,  Quận 9, TP. HCM', '10:00 - 22:00', '300.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Vincom Lê Văn Việt.jpg', '2018-10-18', '2018-10-18'),
(43, 'BR0043', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Lotte Mart Gò Vấp', 'Lotte Mart Gò Vấp, 242 Nguyễn Văn Lượng, P. 10,  Quận Gò Vấp, TP. HCM', '11:00 - 22:00', '300.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Lotte Mart Gò Vấp.jpg', '2018-10-18', '2018-10-18'),
(44, 'BR0044', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Saigon Centre', 'Saigon Centre, 65 Lê Lợi, P. Bến Nghé,  Quận 1, TP. HCM', '09:00 - 22:00', '300.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Saigon Centre.jpg', '2018-10-18', '2018-10-18'),
(45, 'BR0045', 'R008', 'GoGi House - Quán Thịt Nướng Hàn Quốc - Aeon Mall Bình Tân', 'Tầng 3,  Tầng 3, Aeon Mall Bình Tân,1 Đường Số 17A, KP. 11, P. Bình Trị Đông B,  Quận Bình Tân, TP. HCM', '10:00 - 22:00', '150.000đ - 250.000đ', 'images/GoGi House - Quán Thịt Nướng Hàn Quốc - Aeon Mall Bình Tân.jpg', '2018-10-18', '2018-10-18'),
(46, 'BR0046', 'R008', 'GoGi House - Quán Nướng Hàn Quốc - Sân Bay Tân Sơn Nhất', 'Tầng B1, Nhà Xe Ga Nội Địa, Trường Sơn, P. 2,  Quận Tân Bình, TP. HCM', '10:00 - 21:30', '100.000đ - 300.000đ', 'images/GoGi House - Quán Nướng Hàn Quốc - Sân Bay Tân Sơn Nhất.jpg', '2018-10-18', '2018-10-18'),
(47, 'BR0047', 'R008', 'GoGi House - Nướng Hàn Quốc - Nguyễn Hồng Đào', '127 Nguyễn Hồng Đào, P. 14,  Quận Tân Bình, TP. HCM', '10:00 - 22:00', '100.000đ - 300.000đ', 'images/GoGi House - Nướng Hàn Quốc - Nguyễn Hồng Đào.jpg', '2018-10-18', '2018-10-18'),
(48, 'BR0048', 'R008', 'GoGi House - Quán Nướng Hàn Quốc - Satra Phạm Hùng', 'Tầng 1,  Tầng 1 & 2 TTTM Satra, C6/27 Phạm Hùng, Xã Bình Hưng,  Bình Chánh, TP. HCM', '08:00 - 22:00', '100.000đ - 350.000đ', 'images/GoGi House - Quán Nướng Hàn Quốc - Satra Phạm Hùng.jpg', '2018-10-18', '2018-10-18'),
(49, 'BR0049', 'R008', 'GoGi House - Nguyễn Xí - Quán Thịt Nướng Hàn Quốc', 'L1 - 02B Tầng 1 Vincom Plaza SaigonRes, 188 Nguyễn Xí, P. 26,  Quận Bình Thạnh, TP. HCM', '10:00 - 22:00', '150.000đ - 250.000đ', 'images/GoGi House Nguyễn Xí - Quán Thịt Nướng Hàn Quốc.jpg', '2018-10-18', '2018-10-18'),
(50, 'BR0050', 'R008', 'GoGi House - Nướng Hàn Quốc - Âu Cơ', '303 Âu Cơ, P. Phú Trung,  Quận Tân Phú, TP. HCM', '10:00 - 22:00', '150.000đ - 250.000đ', 'images/GoGi House - Nướng Hàn Quốc - Âu Cơ.jpg', '2018-10-18', '2018-10-18'),
(51, 'BR0051', 'R008', 'Gogi House - Thịt Nướng Hàn Quốc - Cao Thắng', '19 Cao Thắng, P. 2,  Quận 3, TP. HCM', '10:30 - 22:00', '200.000đ - 500.000đ', 'images/Gogi House - Thịt Nướng Hàn Quốc - Cao Thắng.jpg', '2018-10-18', '2018-10-18'),
(52, 'BR0052', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Nguyễn Tri Phương', '521 Nguyễn Tri Phương, P. 8,  Quận 10, TP. HCM', '10:00 - 22:00', '150.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Nguyễn Tri Phương.jpg', '2018-10-18', '2018-10-18'),
(53, 'BR0053', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Nguyễn Ảnh Thủ', '1C/A Nguyễn Ảnh Thủ, KP. 1, P. Trung Mỹ Tây,  Quận 12, TP. HCM', '10:00 - 22:00', '150.000đ - 200.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Nguyễn Ảnh Thủ.jpg', '2018-10-18', '2018-10-18'),
(54, 'BR0054', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Vạn Hạnh Mall', 'Tầng 5,  02 Vạn Hạnh Mall, 11 Sư Vạn Hạnh, P. 12,  Quận 10, TP. HCM', '09:00 - 22:00', '150.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Vạn Hạnh Mall.jpg', '2018-10-18', '2018-10-18'),
(55, 'BR0055', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Nguyễn Đình Chiểu', '35A Nguyễn Đình Chiểu,  Quận 1, TP. HCM', '09:00 - 22:00', '150.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Nguyễn Đình Chiểu.jpg', '2018-10-18', '2018-10-18'),
(56, 'BR0056', 'R008', 'Gogi House - Thịt Nướng Hàn Quốc - Quang Trung', '1 Quang Trung,  Quận Gò Vấp, TP. HCM', '09:00 - 22:00', '30.000đ - 400.000đ', 'images/Gogi House - Thịt Nướng Hàn Quốc - Quang Trung.jpg', '2018-10-18', '2018-10-18'),
(57, 'BR0057', 'R008', 'Gogi House - Quán Nướng Hàn Quốc - Đinh Tiên Hoàng', '229 - 231 Đinh Tiên Hoàng, P. Tân Định,  Quận 1, TP. HCM', '10:00 - 22:00', '89.000đ - 500.000đ', 'images/Gogi House - Quán Nướng Hàn Quốc - Đinh Tiên Hoàng.jpg', '2018-10-18', '2018-10-18'),
(58, 'BR0058', 'R0010', 'Baozi - Ẩm Thực Đài Loan - Nguyễn Thái Học', '165 Nguyễn Thái Học, P. Phạm Ngũ Lão,  Quận 1, TP. HCM', '00:01 - 01:00 | 10:00 - 23:59', '40.000đ - 100.000đ', 'images/Baozi - Ẩm Thực Đài Loan - Nguyễn Thái Học.jpg', '2018-10-18', '2018-10-18'),
(59, 'BR0059', 'R009', 'Làng Nướng Nam Bộ - Tô Hiến Thành', '302A Tô Hiến Thành, P. 15,  Quận 10, TP. HCM', '10:00 - 23:30', '80.000đ - 165.000đ', 'images/Làng Nướng Nam Bộ - Tô Hiến Thành.jpg', '2018-10-18', '2018-10-18'),
(60, 'BR0060', 'R009', 'Làng Nướng Nam Bộ - Hòa Bình', '49 Hòa Bình,  Quận 11, TP. HCM', '10:00 - 23:30', '100.000đ - 220.000đ', 'images/Làng Nướng Nam Bộ - Hòa Bình.jpg', '2018-10-18', '2018-10-18'),
(61, 'BR0061', 'R009', 'Làng Nướng Nam Bộ - Trần Quý Cáp', '36 Trần Quý Cáp, P. 11,  Quận Bình Thạnh, TP. HCM', '08:00 - 22:00', '100.000đ - 220.000đ', 'images/Làng Nướng Nam Bộ - Trần Quý Cáp.jpg', '2018-10-18', '2018-10-18'),
(62, 'BR0062', 'R0011', 'Hana BBQ & Hot Pot Buffet - Nguyễn Quý Đức', '65 Nguyễn Quý Đức, P. An Phú,  Quận 2, TP. HCM', '08:00 - 22:00', '120.000đ - 220.000đ', 'images/Hana BBQ & Hot Pot Buffet - Nguyễn Quý Đức.jpg', '2018-10-18', '2018-10-18'),
(63, 'BR0063', 'R0011', 'Hana BBQ & Hot Pot Buffet - Điện Biên Phủ', '243 Điện Biên Phủ, P. 6,  Quận 3, TP. HCM', '08:00 - 22:00', '200.000đ - 330.000đ', 'images/Hana BBQ & Hot Pot Buffet - Điện Biên Phủ.jpg', '2018-10-18', '2018-10-18'),
(64, 'BR0064', 'R0011', 'Hana BBQ & Hot Pot Buffet - Mạc Đĩnh Chi', '45A Mạc Đĩnh Chi,  Quận 1, TP. HCM', '11:00 - 22:30', '200.000đ - 330.000đ', 'images/Hana BBQ & Hot Pot Buffet - Mạc Đĩnh Chi.jpg', '2018-10-18', '2018-10-18'),
(65, 'BR0065', 'R0011', 'Hana BBQ & Hot Pot Buffet - Phạm Viết Chánh', '25 - 25A Phạm Viết Chánh,  Quận 1, TP. HCM', '11:00 - 15:00 | 17:00 - 23:00', '200.000đ - 350.000đ', 'images/Hana BBQ & Hot Pot Buffet - Phạm Viết Chánh.jpg', '2018-10-18', '2018-10-18'),
(66, 'BR0066', 'R0011', 'Hana BBQ & Hot Pot Buffet - Phan Văn Trị', '705 - 707 Phan Văn Trị, P. 7,  Quận Gò Vấp, TP. HCM', '11:00 - 22:00', '200.000đ - 330.000đ', 'images/Hana BBQ & Hot Pot Buffet - Phan Văn Trị.jpg', '2018-10-18', '2018-10-18'),
(67, 'BR0067', 'R0011', 'Hana BBQ & Hot Pot Buffet - Nguyễn Văn Linh', '1034 Nguyễn Văn Linh, P. Tân Phong,  Quận 7, TP. HCM', '08:00 - 22:00', '200.000đ - 350.000đ', 'images/Hana BBQ & Hot Pot Buffet - Nguyễn Văn Linh.jpg', '2018-10-18', '2018-10-18'),
(68, 'BR0068', 'R0012', 'Mr. Park - Sườn Nướng Hàn Quốc', '14 Lê Quý Đôn, P. 6,  Quận 3, TP. HCM', '10:30 - 23:00', '45.000đ - 385.000đ', 'images/Mr. Park - Sườn Nướng Hàn Quốc.jpg', '2018-10-18', '2018-10-18'),
(69, 'BR0069', 'R0012', 'Mr. Park - Sườn Nướng Hàn Quốc - Nguyễn Công Trứ', '210 Nguyễn Công Trứ, P. Nguyễn Thái Bình,  Quận 1, TP. HCM', '10:00 - 22:30', '32.000đ - 275.000đ', 'images/Mr. Park - Sườn Nướng Hàn Quốc - Nguyễn Công Trứ.jpg', '2018-10-18', '2018-10-18'),
(70, 'BR0070', 'R0012', 'Mr. Park - Sườn Nướng Hàn Quốc - The Garden Mall', 'Tầng Trệt,  Tầng Trệt The Garden Mall, 190 Hồng Bàng, P. 12,  Quận 5, TP. HCM', '09:00 - 22:30', '50.000đ - 400.000đ', 'images/Mr. Park - Sườn Nướng Hàn Quốc - The Garden Mall.jpg', '2018-10-18', '2018-10-18'),
(71, 'BR0071', 'R0013', 'Beefsteak Hai Con Bò - Nguyễn Cư Trinh', '85 Nguyễn Cư Trinh,  Quận 1, TP. HCM', '06:30 - 22:30', '65.000đ - 110.000đ', 'images/Beefsteak Hai Con Bò - Nguyễn Cư Trinh.jpg', '2018-10-18', '2018-10-18'),
(72, 'BR0072', 'R0013', 'Beefsteak Hai Con Bò - Thống Nhất', '30 Thống Nhất,  Quận Thủ Đức, TP. HCM', '06:30 - 22:30', '65.000đ - 110.000đ', 'images/Beefsteak Hai Con Bò - Thống Nhất.jpg', '2018-10-18', '2018-10-18'),
(73, 'BR0073', 'R0013', 'Beefsteak Hai Con Bò - Nguyễn Thị Minh Khai', '496C Nguyễn Thị Minh Khai,  Quận 3, TP. HCM', '06:30 - 22:30', '50.000đ - 130.000đ', 'images/Beefsteak Hai Con Bò - Nguyễn Thị Minh Khai.jpg', '2018-10-18', '2018-10-18'),
(74, 'BR0074', 'R0013', 'Beefsteak Hai Con Bò - Quang Trung', '417 Quang Trung,  Quận Gò Vấp, TP. HCM', '06:00 - 22:00', '50.000đ - 130.000đ', 'images/Beefsteak Hai Con Bò - Quang Trung.jpg', '2018-10-18', '2018-10-18'),
(75, 'BR0075', 'R0014', 'Bonjour Resto - Beefsteak Nguyễn Trãi', '150/18 Nguyễn Trãi,  Quận 1, TP. HCM', '09:00 - 22:00', '100.000đ - 200.000đ', 'images/Bonjour Resto - Beefsteak Nguyễn Trãi.jpg', '2018-10-18', '2018-10-18'),
(76, 'BR0076', 'R0014', 'Bonjour Resto - Beefsteak Nguyễn Huy Tưởng', '6/2 Nguyễn Huy Tưởng, P. 6,  Quận Bình Thạnh, TP. HCM', '08:00 - 21:00', '100.000đ - 200.000đ', 'images/Bonjour Resto - Beefsteak Nguyễn Huy Tưởng.jpg', '2018-10-18', '2018-10-18'),
(77, 'BR0077', 'R0014', 'Bonjour Resto - Beefsteak Pasteur', '178/8 Pasteur, P. Bến Nghé,  Quận 1, TP. HCM', '09:00 - 22:00', '100.000đ - 200.000đ', 'images/Bonjour Resto - Beefsteak Pasteur.jpg', '2018-10-18', '2018-10-18'),
(78, 'BR0078', 'R0015', 'Bếp Xanh An Duyên - Nhà Hàng Chay', '10 Nguyễn Tri Phương,  Quận 5, TP. HCM', '10:00 - 14:30 | 16:30 - 21:00', '30.000đ - 250.000đ', 'images/Bếp Xanh An Duyên - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(79, 'BR0079', 'R0016', 'Vajra - Nhà Hàng Chay', '711 Lê Hồng Phong, P. 12,  Quận 10, TP. HCM', '07:00 - 23:00', '25.000đ - 150.000đ', 'images/Vajra - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(80, 'BR0080', 'R0017', 'Nhà Hàng Chay Phương Mai', '86F Võ Thị Sáu, P. Tân Định,  Quận 1, TP. HCM', '09:00 - 21:00', '50.000đ - 200.000đ', 'images/Nhà Hàng Chay Phương Mai.jpg', '2018-10-31', '2018-10-31'),
(81, 'BR0081', 'R0018', 'Nhà Hàng Chay Đóa Sen Vàng - Tân Phú', '5 Trần Hưng Đạo, P. Tân Thành,  Quận Tân Phú, TP. HCM', '06:00 - 21:30', '30.000đ - 110.000đ', 'images/Nhà Hàng Chay Đóa Sen Vàng - Tân Phú.jpg', '2018-10-31', '2018-10-31'),
(82, 'BR0082', 'R0018', 'Nhà Hàng Chay Đóa Sen Vàng - Nguyễn Văn Trỗi', '253/8 Nguyễn Văn Trỗi, P. 10,  Quận Phú Nhuận, TP. HCM', '08:00 - 22:00', '30.000đ - 100.000đ', 'images/Nhà Hàng Chay Đóa Sen Vàng - Nguyễn Văn Trỗi.jpg', '2018-10-31', '2018-10-31'),
(83, 'BR0083', 'R0019', 'Nhà Hàng Chay Here & Now', '89E Nguyễn Công Hoan, P. 7,  Quận Phú Nhuận, TP. HCM', '07:30 - 14:00 | 17:00 - 21:30', '50.000đ - 100.000đ', 'images/Nhà Hàng Chay Here & Now.jpg', '2018-10-31', '2018-10-31'),
(84, 'BR0084', 'R0020', 'Ấn Tâm - Nhà Hàng Ẩm Thực Chay', '18 Đường A4, P. 12,  Quận Tân Bình, TP. HCM', '09:30 - 21:00', '50.000đ - 110.000đ', 'images/Ấn Tâm - Nhà Hàng Ẩm Thực Chay.jpg', '2018-10-31', '2018-10-31'),
(85, 'BR0085', 'R0021', 'Sân Mây Cafe & Nhà Hàng Chay', '155 Nguyễn Văn Thủ, P. Đa Kao,  Quận 1, TP. HCM', '07:00 - 22:00', '11.000đ - 265.000đ', 'images/Sân Mây Cafe & Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(86, 'BR0086', 'R0021', 'Sân Mây - Nhà Hàng Chay', '771 Lê Hồng Phong (Nối Dài), P. 12,  Quận 10, TP. HCM', '07:00 - 21:30', '20.000đ - 150.000đ', 'images/Sân Mây - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(87, 'BR0087', 'R0022', 'Nhà Hàng Thuần Chay - iVegan', '6D Trần Quang Diệu, P. 13,  Quận 3, TP. HCM', '10:30 - 22:00', '10.000đ - 195.000đ', 'images/Nhà Hàng Thuần Chay - iVegan.jpg', '2018-10-31', '2018-10-31'),
(88, 'BR0088', 'R0023', 'Nhà Hàng Thiên Quốc Chay', '421/8 Sư Vạn Hạnh Nối Dài, P. 12,  Quận 10, TP. HCM', '09:00 - 14:00 | 16:00 - 21:00', '30.000đ - 66.000đ', 'images/Nhà Hàng Thiên Quốc Chay.jpg', '2018-10-31', '2018-10-31'),
(89, 'BR0089', 'R0024', 'Nhà Hàng Chay Chuông Vàng', '769 Lê Hồng Phong , P. 12,  Quận 10, TP. HCM', '09:00 - 22:00', '30.000đ - 150.000đ', 'images/Nhà Hàng Chay Chuông Vàng.jpg', '2018-10-31', '2018-10-31'),
(90, 'BR0090', 'R0025', 'Nhà Hàng Chay Thiện Duyên - Ẩm Thực Chay', '505 Xa Lộ Hà Nội, P. An Phú,  Quận 2, TP. HCM', '06:30 - 22:00', '15.000đ - 50.000đ', 'images/Nhà Hàng Chay Thiện Duyên - Ẩm Thực Chay.jpg', '2018-10-31', '2018-10-31'),
(91, 'BR0091', 'R0027', 'Nhà Hàng Chay Bồ Đề Ngọc Xanh', '181 Nguyễn Thái Bình, P. Nguyễn Thái Bình,  Quận 1, TP. HCM', '08:30 - 20:30', '10.000đ - 90.000đ', 'images/Nhà Hàng Chay Bồ Đề Ngọc Xanh.jpg', '2018-10-31', '2018-10-31'),
(92, 'BR0092', 'R0028', 'The Organik House - Nhà Hàng Chay', '7F Nguyễn Thị Minh Khai,  Quận 1, TP. HCM', '08:00 - 21:30', '40.000đ - 100.000đ', 'images/The Organik House - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(93, 'BR0093', 'R0029', 'Mani - Nhà Hàng Chay', '17 Hoa Mai, P. 2,  Quận Phú Nhuận, TP. HCM', '07:00 - 22:00', '10.000đ - 149.000đ', 'images/Mani - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(94, 'BR0094', 'R0030', 'Nhà Hàng Cơm Chay Thái Nhân', '491/10 Nguyễn Đình Chiểu ,P. 2,  Quận 3, TP. HCM', '07:00 - 22:00', '20.000đ - 120.000đ', 'images/Nhà Hàng Cơm Chay Thái Nhân.jpg', '2018-10-31', '2018-10-31'),
(95, 'BR0095', 'R0031', 'Dzị - Nhà Hàng Chay & Cafe', '74 Út Tịch,  Quận Tân Bình, TP. HCM', '07:00 - 22:30', '30.000đ - 220.000đ', 'images/Dzị - Nhà Hàng Chay & Cafe.jpg', '2018-10-31', '2018-10-31'),
(96, 'BR0096', 'R0032', 'Nhà Hàng Chay Liên Hương', '10D Trần Nhật Duật, P. Tân Định,  Quận 1, TP. HCM', '09:00 - 21:00', '25.000đ - 100.000đ', 'images/Nhà Hàng Chay Liên Hương.jpg', '2018-10-31', '2018-10-31'),
(97, 'BR0097', 'R0033', 'Nhà Hàng Chay Thanh Hương', '161 Ba Vân, P. 4,  Quận Tân Bình, TP. HCM', '08:00 - 21:00', '20.000đ - 150.000đ', 'images/Nhà Hàng Chay Thanh Hương.jpg', '2018-10-31', '2018-10-31'),
(98, 'BR0098', 'R0034', 'Nhà Hàng Vườn Chay Garden', '482/39 Lê Quang Định, P. 11,  Quận Bình Thạnh, TP. HCM', '09:00 - 20:00 ', '100.000đ - 170.000đ', 'images/Nhà Hàng Vườn Chay Garden.jpg', '2018-10-31', '2018-10-31'),
(99, 'BR0099', 'R0035', 'Thì Là Veggie - Nhà Hàng Chay', '226 Lý Long Tường, Khu Mỹ Kim 3, P. Tân Phong,  Quận 7, TP. HCM', '10:00 - 21:00', '30.000đ - 100.000đ', 'images/Thì Là Veggie - Nhà Hàng Chay.jpg', '2018-10-31', '2018-10-31'),
(100, 'BR00100', 'R0036', 'Nhà Hàng Amitaba - Ẩm Thực Chay', '40 Nguyễn Thái Học, P. Cầu Ông Lãnh,  Quận 1, TP. HCM', '08:00 - 22:00', '35.000đ - 150.000đ', 'images/Nhà Hàng Amitaba - Ẩm Thực Chay.jpg', '2018-10-31', '2018-10-31'),
(101, 'BR00101', 'R0037', 'Ocean Palace - Ẩm Thực Trung Hoa', '2 Lê Duẩn,  Quận 1, TP. HCM', '09:00 - 14:30 | 16:45 - 22:30', '150.000đ - 550.000đ', 'images/Ocean Palace - Ẩm Thực Trung Hoa.jpg', '2018-10-31', '2018-10-31'),
(102, 'BR00102', 'R0038', 'The LOG Restaurant', 'Rooftop, GEM Center, 8 Nguyễn Bỉnh Khiêm,  Quận 1, TP. HCM', '18:00 - 23:30', '80.000đ - 1.120.000đ', 'images/The LOG Restaurant.jpg', '2018-10-31', '2018-10-31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `citys`
--
ALTER TABLE `citys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kind`
--
ALTER TABLE `kind`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `restaurants_branch`
--
ALTER TABLE `restaurants_branch`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `citys`
--
ALTER TABLE `citys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211;

--
-- AUTO_INCREMENT for table `kind`
--
ALTER TABLE `kind`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `restaurants`
--
ALTER TABLE `restaurants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `restaurants_branch`
--
ALTER TABLE `restaurants_branch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
