package com.quan_ly_ktx.Entity;

public class VatTu {
    private String maSo;
    private String maKhu; // Thêm trường mới cho mã khu
    private String maVT;
    private String tenVT;
    private double giaTien;
    private String maQL;
    
    // Getters and setters
	public String getMaSo() {
		return maSo;
	}
	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}
	public String getTenVT() {
		return tenVT;
	}
	public void setTenVT(String tenVT) {
		this.tenVT = tenVT;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	public String getMaQL() {
		return maQL;
	}
	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
	public String getMaKhu() {
		return maKhu;
	}
	public void setMaKhu(String maKhu) {
		this.maKhu = maKhu;
	}
	public String getMaVT() {
		return maVT;
	}
	public void setMaVT(String maVT) {
		this.maVT = maVT;
	}
}

