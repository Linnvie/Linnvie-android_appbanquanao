package com.ntq.appbanhang;

public class LoaiSanPham {
    private int id;
    private String tenLoai;
    private String hinhLoai;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int id, String tenLoai, String hinhLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
        this.hinhLoai = hinhLoai;
    }

    public int getId() {
        return id;
    }
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhLoai() {
        return hinhLoai;
    }

    public void setHinhAnh(String hinhLoai) {
        this.hinhLoai = hinhLoai;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "id=" + id +
                ", tenLoai='" + tenLoai + '\'' +
                ", hinhAnh='" + hinhLoai + '\'' +
                '}';
    }
}