package com.ntq.appbanhang;

import java.sql.Timestamp;
import java.util.List;

public class LichSuModel {
    private int maDonHang;
    private String sdt,diaChi,ghiChu;
    private String thoigian;
    private List<ItemDonModel> resultChiTiet;

    public LichSuModel() {
    }

    public LichSuModel(int maDonHang, String sdt, String diaChi, String ghiChu, String thoigian, List<ItemDonModel> resultChiTiet) {
        this.maDonHang = maDonHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ghiChu = ghiChu;
        this.thoigian = thoigian;
        this.resultChiTiet = resultChiTiet;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<ItemDonModel> getResultChiTiet() {
        return resultChiTiet;
    }

    public void setResultChiTiet(List<ItemDonModel> resultChiTiet) {
        this.resultChiTiet = resultChiTiet;
    }
}
