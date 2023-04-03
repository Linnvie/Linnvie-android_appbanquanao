<?php
	include "connect.php";
	$mangSPMoiNhat = array();
	$query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 6";
	$data = mysqli_query($conn, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangSPMoiNhat, new SanPhamMoiNhat(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['pricesale'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['star'],
			$row['heart'],
			$row['sold'],
			$row['warehouse'],
			$row['mau1'],
			$row['mau2'],
			$row['mau3'],
			$row['mau4'],
			$row['idsanpham']
		));
	}
	echo json_encode($mangSPMoiNhat);

	class SanPhamMoiNhat
	{
		
		function __construct($id, $tenSanPham, $giaSP, $pricesale, $hinhAnhSP, $moTaSP, $star, $heart, $sold, $warehouse, $mau1, $mau2, $mau3, $mau4, $idSP)
		{
			$this->id = $id;
			$this->tensanpham = $tenSanPham;
			$this->giasanpham = $giaSP;
			$this->pricesale = $pricesale;
			$this->hinhanhsanpham = $hinhAnhSP;
			$this->moTasanpham = $moTaSP;
			$this->star = $star;
			$this->heart = $heart;
			$this->sold = $sold;
			$this->warehouse = $warehouse;
			$this->mau1 = $mau1;
			$this->mau2 = $mau2;
			$this->mau3 = $mau3;
			$this->mau4 = $mau4;
			$this->idsanpham = $idSP;
		}
	}
?>