package com.ntq.appbanhang;

import java.io.Serializable;

public class SanPham implements Serializable {
    public int ID;
    public String tenSP;
    public Integer giaSP;
    public Integer giaSale;
    public String hinhAnhSP;
    public String moTaSP;
    public String star1, star2, star3, star4, star5;
    public String heart;
    public String heartEd;
    public String sold;
    public int IdSP;
    public String kho;
    public String mau1;
    public String mau2;
    public String mau3;
    public String mau4;

    public SanPham( String tenSP, Integer giaSP, Integer giaSale, String hinhAnhSP, String moTaSP, String star1, String star2, String star3, String star4, String star5, String heart, int idSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.giaSale = giaSale;
        this.hinhAnhSP = hinhAnhSP;
        this.moTaSP = moTaSP;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
        this.heart = heart;
        this.IdSP = idSP;
    }

    public SanPham(int ID,String tenSP, Integer giaSP, Integer giaSale, String hinhAnhSP, String moTaSP, String star1, String star2, String star3, String star4, String star5, String heart, String mau1, String mau2, String mau3, String mau4,int idSP) {
        this.ID=ID;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.giaSale = giaSale;
        this.hinhAnhSP = hinhAnhSP;
        this.moTaSP = moTaSP;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
        this.heart = heart;
        this.IdSP = idSP;
        this.mau1 = mau1;
        this.mau2 = mau2;
        this.mau3 = mau3;
        this.mau4 = mau4;
    }

    public SanPham(int ID, String tenSP, Integer giaSP, Integer giaSale, String hinhAnhSP, String moTaSP, String star1, String star2, String star3, String star4, String star5, String heart, String heartEd, String sold, String kho, String mau1, String mau2, String mau3, String mau4, int idSP) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.giaSale = giaSale;
        this.hinhAnhSP = hinhAnhSP;
        this.moTaSP = moTaSP;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.star5 = star5;
        this.heart = heart;
        this.heartEd = heartEd;
        this.sold = sold;
        this.kho = kho;
        this.mau1 = mau1;
        this.mau2 = mau2;
        this.mau3 = mau3;
        this.mau4 = mau4;
        this.IdSP = idSP;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(Integer giaSP) {
        this.giaSP = giaSP;
    }

    public Integer getGiaSale() {
        return giaSale;
    }

    public void setGiaSale(Integer giaSale) {
        this.giaSale = giaSale;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getStar1() {
        return star1;
    }

    public void setStar1(String star1) {
        this.star1 = star1;
    }

    public String getStar2() {
        return star2;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public String getStar3() {
        return star3;
    }

    public void setStar3(String star3) {
        this.star3 = star3;
    }

    public String getStar4() {
        return star4;
    }

    public void setStar4(String star4) {
        this.star4 = star4;
    }

    public String getStar5() {
        return star5;
    }

    public void setStar5(String star5) {
        this.star5 = star5;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public int getIdSP() {
        return IdSP;
    }

    public void setIdSP(int idSP) {
        IdSP = idSP;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getKho() {
        return kho;
    }

    public void setKho(String kho) {
        this.kho = kho;
    }

    public String getMau1() {
        return mau1;
    }

    public void setMau1(String mau1) {
        this.mau1 = mau1;
    }

    public String getMau2() {
        return mau2;
    }

    public void setMau2(String mau2) {
        this.mau2 = mau2;
    }

    public String getMau3() {
        return mau3;
    }

    public void setMau3(String mau3) {
        this.mau3 = mau3;
    }

    public String getMau4() {
        return mau4;
    }

    public void setMau4(String mau4) {
        this.mau4 = mau4;
    }

    public String getHeartEd() {
        return heartEd;
    }

    public void setHeartEd(String heartEd) {
        this.heartEd = heartEd;
    }
}

