package com.quan_ly_ktx.Entity.SinhVien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUANLY_SINHVIEN")
public class QuanLy_SinhVien {
	@Id
	@Column(name = "MAQL_SV")
	private int maQL_SV;
	@Column(name = "MAQL")
	private String maQL;
	@Column(name = "MASV")
	private String  maSV;
	
	public QuanLy_SinhVien() {
		super();
	}
	
	public QuanLy_SinhVien(int maQL_SV, String maQL, String maSV) {
		this.maQL_SV = maQL_SV;
		this.maQL = maQL;
		this.maSV = maSV;
	}

	public int getMaQL_SV() {
		return maQL_SV;
	}

	public void setMaQL_SV(int maQL_SV) {
		this.maQL_SV = maQL_SV;
	}

	public String getMaQL() {
		return maQL;
	}

	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	
	@Override
	public String toString() {
		return "MaQL_SV: " + maQL_SV + ", MaQL: " + maQL + ", MaSV: " + maSV;
	}
}
