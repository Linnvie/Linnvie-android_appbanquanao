package com.ntq.appbanhang;

public class ItemDonModel {
    private String tenSanPham,hinhanh;
    private int soLuong,giaTien;

    public ItemDonModel() {
    }

    public ItemDonModel(String tenSanPham, String hinhanh, int soLuong, int giaTien) {
        this.tenSanPham = tenSanPham;
        this.hinhanh = hinhanh;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
