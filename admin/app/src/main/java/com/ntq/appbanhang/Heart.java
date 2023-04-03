package com.ntq.appbanhang;

public class Heart {
    private int ID = 0;
    private String tenSP = "";
    private Integer giaSP = 0;
    private Integer giaSPSale= 0;
    private String hinhAnhSP = "";
    private String Star1 = "";
    private String Star2 = "";
    private String Star3 = "";
    private String Star4 = "";
    private String Star5 = "";
    private String Heart = "";

    public Heart(int ID, String tenSP, Integer giaSP, Integer giaSPSale, String hinhAnhSP, String star1, String star2, String star3, String star4, String star5, String heart) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.giaSPSale = giaSPSale;
        this.hinhAnhSP = hinhAnhSP;
        Star1 = star1;
        Star2 = star2;
        Star3 = star3;
        Star4 = star4;
        Star5 = star5;
        Heart = heart;
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

    public Integer getGiaSPSale() {
        return giaSPSale;
    }

    public void setGiaSPSale(Integer giaSPSale) {
        this.giaSPSale = giaSPSale;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
    }

    public String getStar1() {
        return Star1;
    }

    public void setStar1(String star1) {
        Star1 = star1;
    }

    public String getStar2() {
        return Star2;
    }

    public void setStar2(String star2) {
        Star2 = star2;
    }

    public String getStar3() {
        return Star3;
    }

    public void setStar3(String star3) {
        Star3 = star3;
    }

    public String getStar4() {
        return Star4;
    }

    public void setStar4(String star4) {
        Star4 = star4;
    }

    public String getStar5() {
        return Star5;
    }

    public void setStar5(String star5) {
        Star5 = star5;
    }

    public String getHeart() {
        return Heart;
    }

    public void setHeart(String heart) {
        Heart = heart;
    }
}
