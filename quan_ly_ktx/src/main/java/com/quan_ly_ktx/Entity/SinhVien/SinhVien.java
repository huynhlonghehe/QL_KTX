package com.quan_ly_ktx.Entity.SinhVien;

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
	@Column(name = "LOP")
	private String lop;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "NGAYTAO")
	private String ngayTao;
	@Column(name = "NGAYSUADOI")
	private String ngaySuaDoi;
	@Column(name = "NGUOISUADOICUOI")
	private String nguoiSuaDoiCuoi;
	
	
	public SinhVien() {
		super();
	}
	public SinhVien(String maSV, String ho, String ten, String ngaySinh, String diaChi, String sdt, String gioiTinh, String lop, String username) {
		this.maSV = maSV;
		this.ho = ho;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
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
	
	public String getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	public String getNgaySuaDoi() {
		return ngaySuaDoi;
	}
	public void setNgaySuaDoi(String ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
	public String getNguoiSuaDoiCuoi() {
		return nguoiSuaDoiCuoi;
	}
	public void setNguoiSuaDoiCuoi(String nguoiSuaDoiCuoi) {
		this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
	}
	
	@Override
	public String toString() {
		return "maSV: " + maSV + ", ho: " + ho + ", ten: " + ten + ", ngaysinh: " + ngaySinh + ", username: " + username + ", ngay sua doi: " + ngaySuaDoi;
	}
}
