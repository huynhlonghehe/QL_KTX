package com.quan_ly_ktx.Entity.VATTU;

public class VT_PHONG {
    
    private String maVTPhong;
    private String maVT;
    private String maPhong;
    private double soTien;
    private String ngayCap;
    private String ngaySuaDoi;
    private int soLuong;
    private String tinhTrang;
    private boolean tienNo;

    // Constructors
    public VT_PHONG() {}

    public VT_PHONG(String maVTPhong, String maVT, String maPhong, double soTien, String ngayCap, int soLuong, String tinhTrang, boolean tienNo,String ngaySuaDoi) {
        this.maVTPhong = maVTPhong;
        this.maVT = maVT;
        this.maPhong = maPhong;
        this.soTien = soTien;
        this.ngayCap = ngayCap;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.tienNo = tienNo;
        this.ngaySuaDoi = ngaySuaDoi;
    }

    // Getters and setters
    public String getMaVTPhong() {
        return maVTPhong;
    }

    public void setMaVTPhong(String maVTPhong) {
        this.maVTPhong = maVTPhong;
    }

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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public boolean isTienNo() {
        return tienNo;
    }

    public void setTienNo(boolean tienNo) {
        this.tienNo = tienNo;
    }

	public String getNgaySuaDoi() {
		return ngaySuaDoi;
	}

	public void setNgaySuaDoi(String ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
}
