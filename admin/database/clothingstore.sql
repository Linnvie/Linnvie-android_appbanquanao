-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221014.c92621d023
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 18, 2022 lúc 10:13 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `clothingstore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tensanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Áo Thun', 'https://cf.shopee.vn/file/60428805e034dd0db80f812709c123d5'),
(2, 'Quần Baggy dáng suông rộng', 'https://cf.shopee.vn/file/69164aa65bcd264463e2df0c55eb0934');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `pricesale` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` mediumtext NOT NULL,
  `star` varchar(200) NOT NULL,
  `heart` varchar(200) NOT NULL,
  `sold` varchar(200) NOT NULL,
  `warehouse` varchar(150) NOT NULL,
  `mau1` varchar(200) NOT NULL,
  `mau2` varchar(200) NOT NULL,
  `mau3` varchar(200) NOT NULL,
  `mau4` varchar(200) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `pricesale`, `hinhanhsanpham`, `motasanpham`, `star`, `heart`, `sold`, `warehouse`, `mau1`, `mau2`, `mau3`, `mau4`, `idsanpham`) VALUES
(1, ' Quần Jeans, Quần baggy jean trắng', 200000, 190000, 'https://cf.shopee.vn/file/b84adc07f3045f18b5e3cd51a8ced9a0', 'QUẦN JEANS BAGGY , DÁNG ỐNG SUÔNG, RỘNG NAM CAO CẤP\r\n- Có phải bạn đang muốn tìm cho mình một chiếc quần jean baggy  cao cấp mang style hàn quốc? \r\nMay mắn là bạn đã tìm thấy chúng tôi.\r\n-  Chiếc quần jean baggy dành cho  nam này tại cửa hàng chúng tôi bán khoảng 600 chiếc mỗi tháng.\r\nĐã bán hơn 6.000 chiếc chỉ tính riêng trên hệ thống của Shopee Việt Nam, chưa kể đến những kênh bán khác!\r\n-  Bạn cũng sẽ là một trong số những người sẽ sở hữu chiếc quần jean baggy đen mang phong cách hàn quốc này.\r\nBởi vì đây là một chiếc quần jean mà cực kỳ dễ phối đồ từ áo thun, hoodie, áo khoác..cho đến các loại sneakers, boots..', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '200', '2', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Circle_Beige_Solid.svg/1200px-Circle_Beige_Solid.svg.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Circle_Khaki_Solid.svg/640px-Circle_Khaki_Solid.svg.png', 'https://cdn141.picsart.com/321151132045211.png', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 2),
(2, 'Quần bò đen ống rộng cạp đinh', 100000, 99000, 'https://cf.shopee.vn/file/000071dbee6cb21fabf6ba705b853465', 'Chất liệu sản phẩm JEAND\r\n-Hàng của shop là hàng freesize\r\nSIZE M : nữ mặc vừa người\r\nSIZE L: nam nữ mặc form unisex ạ\r\n👗 Freesize : cao m6 nặng 52kg đổ lại là đẹp nhé\r\nchiều cao tỉ lệ thuận với cân nặng nha khách', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '1.1k', '1', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 'https://fitcon.com/wp-content/uploads/2017/08/BLUE-CIRCLE.png', 'https://upload.wikimedia.org/wikipedia/commons/8/8e/Pan_Blue_Circle.png', 'https://i.pinimg.com/originals/32/ae/a1/32aea150742ae5fc9b6af2a727271900.png', 2),
(3, 'Áo phông unisex tay lỡ CD', 150000, 120000, 'https://cf.shopee.vn/file/e278ce10286ec935bd428437126b4095', 'THÔNG TIN SẢN PHẨM: \r\n- Xuất sứ: Việt Nam \r\n- Chất liệu: 35% Cotton, 60% Polyester, 5% Spandex\r\n- Dày dặn, mềm mịn, co giãn 4 chiều, không xù, không nhăn, không hút bụi bẩn.\r\n- Size áo: FREESIZE form rộng chuẩn TAY LỠ UNISEX cực đẹp.\r\n- Chiều dài áo: 72cm\r\n- Chiều rộng áo: 55cm\r\n- Chiều dài tay áo: 20cm\r\n- Từ 50-60KG (mặc rộng thoải mái) \r\n- Từ 60-70KG (mặc rộng vừa).', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '500', '150', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Circle_Brown_Solid.svg/1024px-Circle_Brown_Solid.svg.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Circle_Sandy-Brown_Solid.svg/1200px-Circle_Sandy-Brown_Solid.svg.png', 'https://images.emojiterra.com/twitter/v13.1/512px/1f7e4.png', 1),
(4, 'Áo thun nam nữ unisex tay lỡ LF84', 200000, 150000, 'https://cf.shopee.vn/file/eb08bc5c6e565dcf65e57c72a2b8dd4a', 'Áo thun nam nữ unisex tay lỡ LF84, áo phông tay lỡ unisex form rộng oversize streetwear\r\nTHÔNG TIN SẢN PHẨM: \r\n- Tên sản phẩm: Áo thun tay lỡ form rộng UNISEX\r\n- Xuất sứ: Việt Nam \r\n- Chất liệu: cotton DÀY MỀM MỊN MÁT không xù lông. Form áo rộng chuẩn TAY LỠ UNISEX cực đẹp.\r\n- Size áo: FREESIZE form rộng\r\n- Chiều dài áo: 72cm\r\n- Chiều rộng áo: 55cm\r\n- Chiều dài tay áo: 20cm\r\n- Từ 45-55KG (mặc rộng thoải mái) \r\n- Từ 55-65KG (mặc rộng vừa).', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '55', '9', 'https://static.wikia.nocookie.net/speed-city/images/1/1e/BlueCircleIMG.png/revision/latest?cb=20190304215308', 'https://fitcon.com/wp-content/uploads/2017/08/BLUE-CIRCLE.png', 'https://img.icons8.com/emoji/256/blue-circle-emoji.png', 'https://i.pinimg.com/originals/32/ae/a1/32aea150742ae5fc9b6af2a727271900.png', 1),
(5, 'Áo thun form rộng nam nữ tay lỡ Planed', 200000, 1900, 'https://cf.shopee.vn/file/60428805e034dd0db80f812709c123d5', 'THÔNG TIN SẢN PHẨM: \r\n- Xuất sứ: Việt Nam \r\n- Chất liệu: 35% Cotton, 60% Polyester, 5% Spandex\r\n- Dày dặn, mềm mịn, co giãn 4 chiều, không xù, không nhăn, không hút bụi bẩn.\r\n- Size áo: FREESIZE form rộng chuẩn TAY LỠ UNISEX cực đẹp.\r\n- Chiều dài áo: 72cm\r\n- Chiều rộng áo: 55cm\r\n- Chiều dài tay áo: 20cm\r\n- Từ 50-60KG (mặc rộng thoải mái) \r\n- Từ 60-70KG (mặc rộng vừa).', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '900', '9', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 'https://fitcon.com/wp-content/uploads/2017/08/BLUE-CIRCLE.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Red_Circle%28small%29.svg/2048px-Red_Circle%28small%29.svg.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Transparent_red_circle.svg/768px-Transparent_red_circle.svg.png', 1),
(6, 'Áo thun polo unisex Corecury tay lỡ form rộng', 250000, 100000, 'https://cf.shopee.vn/file/731a81523faa98c6e15c4e81d2cd368c', ' Chất liệu vải cá sấu Cotton xuất xịn, chuẩn form cao cấp không xù lông, bề mặt vải mượt và bóng, thoáng mát, mang vận động vẫn thích hợp. \r\nMàu Sắc:   Đen + Trắng. Hình thêu đẹp xuất xắc.\r\n Áo polo co giãn 4 chiều, bền, có thể giặt máy nhờ 5% sợi Spandex. Chất liệu thun cá sấu cao cấp không xù lông, bề mặt vải mượt và bóng, thoáng mát, mang vận động vẫn thích hợp.', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '200', '15', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Transparent_red_circle.svg/768px-Transparent_red_circle.svg.png', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Ski_trail_rating_symbol-green_circle.svg/1200px-Ski_trail_rating_symbol-green_circle.svg.png', 'https://fitcon.com/wp-content/uploads/2017/08/BLUE-CIRCLE.png', 1),
(7, 'Quần jean ống rộng thẳng unisex', 250000, 240000, 'https://cf.shopee.vn/file/0fc72b2511e02f25f03bb736c15f9965', 'Quần jean nam ống thẳng rộng mùa hè 2022 J202\r\n\r\nQuần khi nhận được dài các bạn có thể lên gấu khoảng 5-7cm cho phù hợp nhé vì nếu để ngắn luôn nhiều bạn cao quá sẽ cộc.', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '400', '4', 'https://fitcon.com/wp-content/uploads/2017/08/BLUE-CIRCLE.png', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 'https://upload.wikimedia.org/wikipedia/en/thumb/f/fb/Yellow_icon.svg/1200px-Yellow_icon.svg.png', 'http://www.clker.com/cliparts/1/u/V/l/X/u/light-yellow-circle-md.png', 2),
(8, 'Quần jean cho nữ', 250000, 220000, 'https://cf.shopee.vn/file/bfdc1c3014b878d55ac4036a7ae243e3', '\r\nPhong cách: quần ống rộng\r\nKiểu eo: eo cao\r\nChiều dài: quần\r\nPhong cách: Đi làm đơn giản / phong cách Hàn Quốc\r\nCác chi tiết phổ biến: nút, sờn, màu trơn\r\nVải / Chất liệu: denim / cotton\r\nHàm lượng chất liệu: 71% - 80%', 'https://cdn2.iconfinder.com/data/icons/actions-states-vol-1-colored/48/JD-13-1024.png', 'https://cdn2.iconfinder.com/data/icons/media-player-ui/512/Media-Icon-25-1024.png', '4k', '20', 'http://www.clker.com/cliparts/1/u/V/l/X/u/light-yellow-circle-md.png', 'https://upload.wikimedia.org/wikipedia/commons/8/8e/Pan_Blue_Circle.png', 'https://i.pinimg.com/originals/32/ae/a1/32aea150742ae5fc9b6af2a727271900.png', 'https://cdn-icons-png.flaticon.com/512/0/14.png', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
