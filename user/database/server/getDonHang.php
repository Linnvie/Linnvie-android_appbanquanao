<?php
include "connect.php";
$username=$_POST['username'];
$query= "SELECT * FROM donhang WHERE tenkhachhang= '$username'
			ORDER BY id DESC";
$data=mysqli_query($conn, $query);
$result=array();
while( $row=mysqli_fetch_assoc($data) ){
	$querychitiet='SELECT sanpham.tensanpham,chitietdonhang.soluong,	
			chitietdonhang.giatien,sanpham.hinhanhsanpham
			 FROM chitietdonhang INNER JOIN sanpham ON chitietdonhang.idsanpham=sanpham.id 
			WHERE chitietdonhang.iddonhang= '.$row["id"];
	$datachitiet=mysqli_query($conn, $querychitiet);
	$resultchitiet=array();
	while( $rowchitiet=mysqli_fetch_assoc($datachitiet) ){
		$resultchitiet[]=$rowchitiet;
	}
	
	$row['resultchitiet']=$resultchitiet;
	$result[]=$row;
	
}
print_r(json_encode($result));

?>

