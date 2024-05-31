package com.quan_ly_ktx.Entity.HopDong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUANLY_HOPDONG")
public class QuanLy_HopDong {
	@Id
	@Column(name = "MAQL_HD")
	private int maQL_HD;
	@Column(name = "MAQL")
	private String maQL;
	@Column(name = "MAHD")
	private String  maHD;
	
	public QuanLy_HopDong() {
		super();
	}
	
	public QuanLy_HopDong(int maQL_HD, String maQL, String maHD) {
		this.maQL_HD = maQL_HD;
		this.maQL = maQL;
		this.maHD = maHD;
	}

	public int getMaQL_HD() {
		return maQL_HD;
	}

	public void setMaQL_HD(int maQL_HD) {
		this.maQL_HD = maQL_HD;
	}

	public String getMaQL() {
		return maQL;
	}

	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	
	
}
