package com.quan_ly_ktx.Entity.VATTU;

public class VT_PHONG {
    
    private String maVT;
    private String maPhong;
    private double soTien;
    private String ngayCap;

    // Constructors
    public VT_PHONG() {}

    public VT_PHONG(String maVT, String maPhong, double soTien, String ngayCap) {
        this.maVT = maVT;
        this.maPhong = maPhong;
        this.soTien = soTien;
        this.ngayCap = ngayCap;
    }

    // Getters and setters
    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }
}
