<?php
include "connect.php";
$json=$_POST['jsonchitiet'];
$data=json_decode($json,true);
foreach($data as $value){
	$madonhang=$value['madonhang'];
	$masanpham=$value['masanpham'];
	$tensanpham=$value['tensanpham'];
	$soluong=$value['soluong'];
	$giasanpham=$value['giasanpham'];

	$query=" INSERT INTO `chitietdonhang`(`id`, `iddonhang`, `idsanpham`, `tensanpham`, `soluong`, `giatien`)
		 VALUES (null,'$madonhang','$masanpham','$tensanpham','$soluong','$giasanpham') ";

	$result= mysqli_query($conn, $query);
}
if($result){
	echo "1";
}else{
	echo "0";
}
?>
