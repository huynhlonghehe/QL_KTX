package com.quan_ly_ktx.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SINHVIEN")
public class SinhVien {
	@Id
	@Column(name = "MASV")
	private String maSV;
	@Column(name = "HO")
	private String ho;
	@Column(name = "TEN")
	private String ten;
	@Column(name = "NGAYSINH")
	private String ngaySinh;
	@Column(name = "DIACHI")
	private String diaChi;
	@Column(name = "SDT")
	private String sdt;
	@Column(name = "GIOITINH")
	private String gioiTinh;
	@Column(name = "MAQL")
	private String maQL;
	@Column(name = "LOP")
	private String lop;
	@Column(name = "USERNAME")
	private String username;
	public SinhVien() {
		super();
	}
	public SinhVien(String maSV, String ho, String ten, String ngaySinh, String diaChi, String sdt, String gioiTinh, String maQL, String lop, String username) {
		this.maSV = maSV;
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.maQL = maQL;
		this.lop = lop;
		this.username = username;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getMaQL() {
		return maQL;
	}
	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "maSV: " + maSV + ", ho: " + ho + ", ten: " + ten + ", ngaysinh: " + ngaySinh + ", username: " + username + ", maQL: " + maQL;
	}
}
