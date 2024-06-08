package com.quan_ly_ktx.Entity.SinhVien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUYTAC")
public class SinhVien_QuyTac {
	@Id
	@Column(name = "MASVVP")
	private int maSVVP;
	@Column(name = "MASV")
	private String maSV;
	@Column(name = "maQuyTac")
	private String maQuyTac;
	@Column(name = "THOIGIANVIPHAM")
	private String thoiGianViPham;
	@Column(name = "NGUOILAPBIENBAN")
	private String nguoiLapBienBan;
	public SinhVien_QuyTac() {
		super();
	}
	
	public SinhVien_QuyTac(int maSVVP, String maSV, String maQuyTac, String thoiGianViPham, String nguoiLapBienBan) {
		this.maSVVP = maSVVP;
		this.maSV = maSV;
		this.maQuyTac = maQuyTac;
		this.thoiGianViPham = thoiGianViPham;
		this.nguoiLapBienBan = nguoiLapBienBan;
	}

	
	public int getMaSVVP() {
		return maSVVP;
	}

	public void setMaSVVP(int maSVVP) {
		this.maSVVP = maSVVP;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getMaQuyTac() {
		return maQuyTac;
	}

	public void setMaQuyTac(String maQuyTac) {
		this.maQuyTac = maQuyTac;
	}

	public String getThoiGianViPham() {
		return thoiGianViPham;
	}

	public void setThoiGianViPham(String thoiGianViPham) {
		this.thoiGianViPham = thoiGianViPham;
	}
	
	
	public String getNguoiLapBienBan() {
		return nguoiLapBienBan;
	}

	public void setNguoiLapBienBan(String nguoiLapBienBan) {
		this.nguoiLapBienBan = nguoiLapBienBan;
	}

	@Override
	public String toString() {
		return "MaSVVP: " + maSVVP + "Mã sinh viên: " + maSV + ", Mã quy tắc" + maQuyTac + ", Thời gian vi phạm: " + thoiGianViPham + ", Người lập biên bản: " + nguoiLapBienBan;
	}
	
}
