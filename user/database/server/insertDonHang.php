<?php
include "connect.php";
$tenkhachhang=$_POST['tenkhachhang'];
$sdt=$_POST['sdt'];
$diachi=$_POST['diachi'];
$email=$_POST['email'];
$ghichu=$_POST['ghichu'];
$tienvanchuyen=$_POST['tienvanchuyen'];
if(strlen($tenkhachhang)>0 && strlen($sdt)>0 && strlen($diachi)>0 && strlen($email)>0){
	if (!$conn) {
 		 die("Connection failed: " . mysqli_connect_error());
	}
	$query= "INSERT INTO `donhang`(`id`, `tenkhachhang`, `sodienthoai`, `diachi`, `email`,`ghichu`,`tienvanchuyen`)
		 VALUES (null,'$tenkhachhang','$sdt','$diachi','$email','$ghichu','$tienvanchuyen')";
	$data=mysqli_query($conn, $query);
	if($data==true){
		$iddonhang= $conn->insert_id;
		echo $iddonhang;;
	}else{
		echo "Thất bại";
	}


	

}else{
	echo "Vui lòng bổ sung đầy đủ dữ liệu";
}
?>

