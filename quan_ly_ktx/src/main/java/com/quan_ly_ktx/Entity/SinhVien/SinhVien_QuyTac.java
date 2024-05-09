package com.quan_ly_ktx.Entity.SinhVien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUYTAC")
public class SinhVien_QuyTac {
	@Id
	@Column(name = "MASV")
	private String maSV;
	@Column(name = "maQuyTac")
	private int maQuyTac;
	@Column(name = "THOIGIANVIPHAM")
	private String thoiGianViPham;
	
	public SinhVien_QuyTac() {
		super();
	}
	
	public SinhVien_QuyTac(String maSV, int maQuyTac, String thoiGianViPham) {
		this.maSV = maSV;
		this.maQuyTac = maQuyTac;
		this.thoiGianViPham = thoiGianViPham;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public int getMaQuyTac() {
		return maQuyTac;
	}

	public void setMaQuyTac(int maQuyTac) {
		this.maQuyTac = maQuyTac;
	}

	public String getThoiGianViPham() {
		return thoiGianViPham;
	}

	public void setThoiGianViPham(String thoiGianViPham) {
		this.thoiGianViPham = thoiGianViPham;
	}
	
	@Override
	public String toString() {
		return "Mã sinh viên: " + maSV + ", Mã quy tắc" + maQuyTac + ", Thời gian vi phạm: " + thoiGianViPham;
	}
	
}
