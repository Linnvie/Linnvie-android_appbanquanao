-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2022 at 11:37 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appbanhang`
--

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloai` varchar(200) NOT NULL,
  `hinhloai` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloai`, `hinhloai`) VALUES
(1, 'Áo thun nam', 'https://www.google.com/url?sa=i&url=http%3A%2F%2Fclipart-library.com%2Ffree%2Fcartoon-shirt-png.html&psig=AOvVaw29j9QaOca8d5co6eZr1JNe&ust=1665322086507000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMjnoK3e0PoCFQAAAAAdAAAAABAK'),
(2, 'Áo sơ mi nam', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fvi.cleanpng.com%2Fpng-8idz8t%2F&psig=AOvVaw29j9QaOca8d5co6eZr1JNe&ust=1665322086507000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMjnoK3e0PoCFQAAAAAdAAAAABAE'),
(3, 'Áo thun nữ', 'https://images.squarespace-cdn.com/content/v1/5da8b9112a4fea6a5c56c8c8/1594846491197-IJEA4LTJGY3HD3YDMER3/HG-Apparel-icons-sized-TSP-05.png'),
(4, 'Áo sơ mi nữ', 'https://png.pngtree.com/png-clipart/20200701/original/pngtree-ladies-white-lace-shirt-png-image_5402560.jpg'),
(5, 'Áo polo nam', 'https://banner2.cleanpng.com/20180804/fwy/kisspng-polo-shirt-t-shirt-collar-sleeve-shoe-other-brand-polo-shirts-chainada-5b65c841290819.2339846415333970571681.jpg'),
(6, 'Áo khoác nam', 'https://library.kissclipart.com/20180908/zbw/kissclipart-file-format-clipart-jacket-clothing-clip-art-cd72a5ea06ae74df.jpg'),
(7, 'Áo khoác nữ', 'https://img.freepik.com/premium-vector/autumn-coat-hand-drawn-vector-illustration-raincoat-sketch-design-element-isolated-white-background-fashion-fall-season-clothing-outerwear-parka-coat-ink-pen-freehand-drawing_231873-2175.jpg'),
(8, 'Áo kiểu nữ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhBGApxne_MwRPW0VjT_bIZ0Ab6s3XJZti7A&usqp=CAU'),
(9, 'Croptop nữ', 'https://img.pikbest.com/png-images/qiantu/cultural-shirt-cartoon-shirt-hand-drawn-t-shirt-elements_2660857.png!c1024wm0'),
(10, 'Đầm maxi', 'https://cdn4.vectorstock.com/i/1000x1000/65/58/princess-dress-drawing-vector-38666558.jpg'),
(11, 'Váy nữ', 'https://www.pngitem.com/pimgs/m/43-431372_bridal-dress-mannequin-wedding-png-image-black-and.png'),
(12, 'Chân váy nữ', 'https://img.cdn.vncdn.io/nvn/ncdn/store1/41822/pc/icon_338571_1568273450_ch%C3%A2n%20v%C3%A1y.png'),
(13, 'Yếm nữ', 'https://cf.shopee.vn/file/e70095f02424e1e5d8e0eab2f6a122ef'),
(14, 'Hoodie', 'https://www.supercoloring.com/sites/default/files/styles/coloring_full/public/cif/2022/01/hoodie-coloring-page_2.png'),
(15, 'Quần nam', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlMcjMt4_tZ0-8U7sW4PRmY9sfbG38OQui-A&usqp=CAU'),
(16, 'Quần nữ', 'https://thumbs.dreamstime.com/b/denim-shorts-thin-line-icon-summer-clothes-concept-sign-white-background-jeans-female-pants-outline-style-mobile-web-187070544.jpg'),
(17, 'Đồ bộ nam', 'https://thumbs.dreamstime.com/b/boy-outfit-icon-cartoon-isolated-black-white-t-shirt-short-pants-vector-illustration-graphic-design-149733944.jpg'),
(18, 'Đồ bộ nữ', 'https://us.123rf.com/450wm/amin268/amin2681905/amin268190500560/123087898-pajamas-thin-line-icon-clothes-and-nightwear-pyjama-sign-vector-graphics-a-linear-pattern-on-a-white.jpg?ver=6'),
(19, 'Phụ kiện nữ', 'https://media.istockphoto.com/vectors/hair-accessories-doodle-style-vector-illustration-of-hair-bands-and-vector-id1351276896?s=612x612');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
