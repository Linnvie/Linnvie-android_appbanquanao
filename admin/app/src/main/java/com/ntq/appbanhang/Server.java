package com.ntq.appbanhang;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Server {
   //public static String localhost = "192.168.1.13:8080";
    public static String localhost = "192.168.1.15:8080";

    public  static FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

    public static String duongDanSPMoiNhat = "http://" + localhost + "/server/getsanphammoinhat.php";
    public static String ID_NHAN="shopclothing.@gmail.com";
    public static final String ID_SEND="idsend";
    public static final String ID_RECEIVE="received";
    public static final String MESS="message";
    public static final String DATE="datetime";
    public static final String PATH_CHAT="chat";
    public  static String urlLoaiSP="http://"+localhost+"/server/getLoaiSanPham.php";
    public  static String urlSanPhamTheoLoai="http://"+localhost+"/server/getSPTheoLoai.php";
    public  static String urlInssertDonHang="http://"+localhost+"/server/insertDonHang.php";
    public  static String urlInsertDonHangChiTiet="http://"+localhost+"/server/insertDonHangChiTiet.php";
    public  static String urlgetDonHangChiTiet="http://"+localhost+"/server/getDonHang.php";
    public static List<GioHang> listGioHang;
    public static List<GioHang> listMuaHang=new ArrayList<>();


}
